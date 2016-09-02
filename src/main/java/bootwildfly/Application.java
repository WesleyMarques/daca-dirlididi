package bootwildfly;

import bootwildfly.models.*;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Application extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
    @Bean
	public CommandLineRunner demo(UserRepository repository, ProblemRepository repProb, TestRepository testRep) {
		return (args) -> {
			// save a couple of customers
			User u = new User();
			u.email = "daca";
			u.role = Role.ADMIN;
			u.password = "daca";
			repository.save(u);

			Problem p = new Problem();
			p.name = "Problem name";
			p.description = "Description";
			Test t = new Test();
			t.name = "Teste 1";
			p.tests.add(t);
			repProb.save(p);

			Problem problem = repProb.findOne(1L);
			log.info(problem.tests.size() + " Número de testes para o problema 1");

			u.resolvidos.add(problem);
			repository.save(u);
			User userBD = repository.findOne(1L);
			log.info(userBD.resolvidos.size() + " Problemas resolvidos pelo user 1");
		};
	}
}

