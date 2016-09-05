package bootwildfly;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import bootwildfly.models.Problem;
import bootwildfly.models.ProblemTest;
import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.ProblemTestRepository;
import bootwildfly.models.repositories.UserRepository;
import bootwildfly.security.JwtFilter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }

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

