package controllers;

import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.UserRepository;
import org.junit.Assert;
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
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import bootwildfly.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ProblemControllerTest {

	private final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYWNhIiwicm9sZXMiOltudWxsXSwiaWF0IjoxNDczMjA5NzE3LCJleHAiOjI5NDY0MTk0MzV9.4bA5G4pqTuk96S5-o2cvKKsVVN7-v2G0PLCqELlAxoY";

	private static final String PROBLEM = "/api/problem";
	private static final String PROBLEM_ID = "/api/problem/{id}";

	@Value("${local.server.port}")
	private int serverPort;

	@Autowired
	ProblemRepository problemRepository;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;
	}
	
	@Test
	public void getAllProblemsTest(){
		given().header("Authorization", TOKEN)
		.when().get(PROBLEM)
		.then().statusCode(HttpStatus.SC_OK)
		.body("get(0).name", equalTo("Problem name"));
	}

	@Test
	public void getProblemByIdTest(){
		given().header("Authorization", TOKEN)
        .pathParam("id", "1")
		.when().get(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("name", equalTo("problem name"));
	}
	
	@Test
	public void putProblemTest() throws JSONException{		
		JSONObject updateProblem = new JSONObject();
		updateProblem.put("name", "problem 190");
		updateProblem.put("description", "nova descricao");
		updateProblem.put("tip", "dica para o problema");

		given().header("Authorization", TOKEN)
		.contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		.pathParam("id", "1")
		.body(updateProblem.toString())
		.when()
		.put(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("message", equalTo("Problem updated successfully"));

		Assert.assertTrue(problemRepository.findOne(1L).getName().equals("problem 190"));
	}
	
	@Test
	public void deleteProblemTest(){
		given().header("Authorization", TOKEN)
		.pathParam("id", "1")
		.delete(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("message", equalTo("Problem deleted successfully"));

		given().header("Authorization", TOKEN)
        .pathParam("id", "1")
		.when().get(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

		Assert.assertTrue(problemRepository.count() == 0);
	}
	
	@Test
	public void postNewProblemTest() throws JSONException{
		JSONObject newProblem = new JSONObject();
		newProblem.put("name", "problem 1");
		newProblem.put("description", "descricao ...");
		newProblem.put("tip", "dica para o problema...");

		given().header("Authorization", TOKEN)
		.contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		.body(newProblem.toString())
		.when()
		.post(PROBLEM).then()
		.statusCode(HttpStatus.SC_OK)
		.body("message", equalTo("Problem created successfully"));

	}

}

