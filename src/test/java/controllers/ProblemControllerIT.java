package controllers;

import bootwildfly.models.Problem;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.UserRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
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

import bootwildfly.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ProblemControllerIT {

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
		problemRepository.deleteAll();
		Problem p = new Problem();
		p.setName("Problem name");
		p.setDescription("Description");

		problemRepository.save(p);
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
		Long id_problem = problemRepository.findAll().get(0).getId();
		given().header("Authorization", TOKEN)
        .pathParam("id", id_problem)
		.when().get(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("name", equalTo("Problem name"));
	}
	
	@Test
	public void putProblemTest() throws JSONException {

		JSONObject data = new JSONObject();
		data.put("name", "problem 190");
		data.put("description", "new description");
		Long id_problem = problemRepository.findAll().get(0).getId();

		given().header("Authorization", TOKEN)
		.contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		.pathParam("id", id_problem)
		.body(data.toString())
		.when()
		.put(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("message", equalTo("Problem updated successfully"));

		Assert.assertTrue(problemRepository.findAll().get(0).getName().equals("problem 190"));
	}
	
	@Test
	public void deleteProblemTest(){
		Long id_problem = problemRepository.findAll().get(0).getId();

		given().header("Authorization", TOKEN)
		.pathParam("id", id_problem)
		.delete(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("message", equalTo("Problem deleted successfully"));

		given().header("Authorization", TOKEN)
        .pathParam("id", id_problem)
		.when().get(PROBLEM_ID)
		.then()
		.statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);

		Assert.assertTrue(problemRepository.count() == 0);
	}
	
	@Test
	public void postNewProblemTest() throws JSONException {
		JSONObject data = new JSONObject();
		data.put("name", "problem 1");
		data.put("description", "description...");

		given().header("Authorization", TOKEN)
		.contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		.body(data.toString())
		.when()
		.post(PROBLEM).then()
		.statusCode(HttpStatus.SC_OK)
		.body("message", equalTo("Problem created successfully"));

	}

}

