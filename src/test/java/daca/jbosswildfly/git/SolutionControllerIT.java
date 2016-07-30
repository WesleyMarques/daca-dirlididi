package daca.jbosswildfly.git;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
public class SolutionControllerIT {
	private static final String SOLUTION = "/solution";
	
	@Value("${local.server.port}")
	private int serverPort;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;
	}
	
	
	
	@Test
	public void getAccountTest(){
		
		Map<String, String> newSolution= new HashMap<String, String>();
		newSolution.put("problem_code", "1002");
		newSolution.put("code_solution", "while(true){syso('done');}");
		newSolution.put("leng", "java");
		given()
		.formParameters(newSolution)
		.when()
		.post(SOLUTION).then()
		.statusCode(HttpStatus.SC_OK);
	}
	
	

}
