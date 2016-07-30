package daca.jbosswildfly.git;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import bootwildfly.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class LoginControllerIT {
	private static final String HELLO = "/hello";
	private static final String LOGIN = "/login";
	private static final String LOGOUT = "/logout";
	private static final String ACCOUNT = "/account";
	private static final String INFO = "/info";
	private static final String PROBLEM = "/problem";
	private static final String PROBLEM_ID = "/problem/{id}";
	private static final String PROBLEM_STATUS = "/problem/{id}/status";
	private static final String SOLUTION = "/solution";
	@Value("${local.server.port}")
	private int serverPort;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;
	}

	@Test
	public void getItemsShouldReturnBothItems() {
		when().get(HELLO).then().statusCode(200);
	}

}
