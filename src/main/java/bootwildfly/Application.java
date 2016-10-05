package bootwildfly;

import bootwildfly.controllers.ProblemController;
import com.google.common.base.Predicates;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cglib.core.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import bootwildfly.models.Problem;
import bootwildfly.models.ProblemTest;
import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.ProblemTestRepository;
import bootwildfly.models.repositories.UserRepository;
import bootwildfly.security.JwtFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
@EnableSwagger2
@ComponentScan
@SpringBootApplication
@ActiveProfiles(value = "development")
public class Application extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().paths(PathSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error")))
				.paths(Predicates.not(PathSelectors.regex("/login")))
				.paths(Predicates.not(PathSelectors.regex("angular")))
				.build();
	}

//	@Bean
//	public FilterRegistrationBean jwtFilter() {
//		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		registrationBean.setFilter(new JwtFilter());
//		//registrationBean.addUrlPatterns("/api/*");
//
//		return registrationBean;
//	}

//	@RequestMapping("/token")
//	@ResponseBody
//	public Map<String, String> token(HttpSession session) {
//		return Collections.singletonMap("token", session.getId());
//	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
	public CommandLineRunner demo(UserRepository repository, ProblemRepository repProb, ProblemTestRepository testRep) {
		return (args) -> {
			// save a couple of customers
			User u = new User();
			u.setEmail("daca");
			u.setRole(Role.ADMIN);
			u.setPassword("daca");
			repository.save(u);

			Problem p = new Problem();
			p.setName("Problem name");
			p.setDescription("Description");

			ProblemTest t = new ProblemTest();
			t.setName("Teste 1");
			p.getTests().add(t);
			repProb.save(p);

			Problem problem = repProb.findOne(1L);
			log.info(problem.getTests().size() + " Número de testes para o problema 1");

			u.getResolvidos().add(problem);
			repository.save(u);
			User userBD = repository.findOne(1L);
			log.info(userBD.getResolvidos().size() + " Problemas resolvidos pelo user 1");
		};
	}
}

