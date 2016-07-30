package daca.jbosswildfly.git;

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

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;

import bootwildfly.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ProblemControllerIT {
	private static final String PROBLEM = "/problem";
	private static final String PROBLEM_ID = "/problem/{id}";
	private static final String PROBLEM_STATUS = "/problem/{id}/status";
	@Value("${local.server.port}")
	private int serverPort;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;
	}
	
	@Test
	public void getAllProblemsTest(){
		when().get(PROBLEM)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.findAll{}.name", hasItems("problema 1"));
	}
	
	@Test
	public void postStatusTest(){
		given()
        .pathParam("id", "1")
        .formParam("status", "solved")
		.when().post(PROBLEM_STATUS)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.message", equalTo("The problem was marked as solved"));
	}
	
	@Test
	public void getProblemByIdTest(){
		given().
        pathParam("id", "10").
		when().get(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.findAll{it.id == 10}.name", hasItems("problema 10"));
	}
	
	@Test
	public void putProblemTest(){
		Map<String, String> updateProblem= new HashMap<String, String>();
		updateProblem.put("name", "problem 1");
		updateProblem.put("desc", "nova descricao ...");
		updateProblem.put("dica", "dica para o problema...");
		updateProblem.put("testes", "testes");
		
		given().
		pathParam("id", "1").
		formParameters(updateProblem)
		.when()
		.put(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.message", equalTo("Problem altered successfully"));	
	}
	
	@Test
	public void deleteProblemTest(){		
		given().
		pathParam("id", "1")
		.delete(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.message", equalTo("Problem removed successfully"));
		
		given().
        pathParam("id", "1").
		when().get(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_NOT_FOUND);
	}
	
	@Test
	public void postNewProblemTest(){
		Map<String, String> newProblem= new HashMap<String, String>();
		newProblem.put("name", "problem 1");
		newProblem.put("desc", "descricao ...");
		newProblem.put("dica", "dica para o problema...");
		newProblem.put("testes", "testes");
		
		given().
		formParameters(newProblem)
		.post(PROBLEM).then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.message", equalTo("Problem created successfully"));
		
		given().
        pathParam("id", 1).
		when().get(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.findAll{it.id == 1}.name", hasItems("problema 1"));		
	}

}

