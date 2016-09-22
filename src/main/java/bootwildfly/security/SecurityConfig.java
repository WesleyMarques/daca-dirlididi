package bootwildfly.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Client
@RestController
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    OAuth2ClientContext oAuth2ClientContext;

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/*", "/resources/static/**").permitAll()
        .and()
        .csrf().disable();
//        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.antMatcher("/logout")
                //.logout().logoutSuccessUrl("/").permitAll().and()
                .antMatcher("/**")
        .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
                //.logout().logoutSuccessUrl("/").permitAll();
    }

    private Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");
        OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), oAuth2ClientContext);
        facebookFilter.setRestTemplate(facebookTemplate);
        facebookFilter.setTokenServices(new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId()));
        return facebookFilter;
    }

    @Bean
    @ConfigurationProperties("facebook.client")
    public AuthorizationCodeResourceDetails facebook() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("facebook.resource")
    public ResourceServerProperties facebookResource() {
        return new ResourceServerProperties();
    }

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws
    // Exception {
    // auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    // }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	// @formatter:off
//        http
//        .authorizeRequests()
//        .antMatchers("/*", "/resources/static/**").permitAll()
//        .and()
//        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .invalidateHttpSession(true)
//                .logoutSuccessUrl("/login")
//                .deleteCookies("JSESSIONID")
//                .permitAll();
     // @formatter:on
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/static/**");
//    }

}
