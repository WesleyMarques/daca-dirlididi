package controllers;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import bootwildfly.models.Problem;
import bootwildfly.models.ProblemTest;
import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.UserRepository;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

import bootwildfly.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class StatisticsControllerIT {
	private static final String STATISTICS = "/api/statistics";
	private final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYWNhIiwicm9sZXMiOltudWxsXSwiaWF0IjoxNDczMjA5NzE3LCJleHAiOjI5NDY0MTk0MzV9.4bA5G4pqTuk96S5-o2cvKKsVVN7-v2G0PLCqELlAxoY";
	
	@Value("${local.server.port}")
	private int serverPort;

	@Autowired
	ProblemRepository problemRepository;

	@Autowired
	UserRepository userRepository;



	@Before
	public void setUp() {
		RestAssured.port = serverPort;

		userRepository.deleteAll();
		problemRepository.deleteAll();

		User u = new User();
		u.setEmail("daca");
		u.setRole(Role.ADMIN);
		u.setPassword("daca");
		userRepository.save(u);

		Problem p = new Problem();
		p.setName("Problem name");
		p.setDescription("Description");

		ProblemTest t = new ProblemTest();
		t.setName("Teste 1");
		p.getTests().add(t);
		problemRepository.save(p);

		Problem problem = problemRepository.findAll().get(0);
		u.getResolvidos().add(problem);
		userRepository.save(u);
	}

	@Test
	public void testGetStatistics() {
		given().header("Authorization", TOKEN)
				.when()
				.get(STATISTICS).then()
				.statusCode(HttpStatus.SC_OK)
				.body("total_users", equalTo(1))
				.body("total_problems", equalTo(1))
				.body("problems_you_solved", equalTo(1));
	}
}
