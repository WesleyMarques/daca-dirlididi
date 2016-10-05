package controllers;

import bootwildfly.Application;
import bootwildfly.models.Problem;
import bootwildfly.models.ProblemTest;
import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.ProblemTestRepository;
import bootwildfly.models.repositories.UserRepository;
import com.jayway.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@ActiveProfiles(value = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProblemControllerTest {

	private final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYWNhIiwicm9sZXMiOltudWxsXSwiaWF0IjoxNDczMjA5NzE3LCJleHAiOjI5NDY0MTk0MzV9.4bA5G4pqTuk96S5-o2cvKKsVVN7-v2G0PLCqELlAxoY";

	private static final String PROBLEM = "/api/problem";
	private static final String PROBLEM_ID = "/api/problem/{id}";
	private static final String PROBLEM_SOLUTION = "/api/problem/{id}/solution";

	@Value("${local.server.port}")
	private int serverPort;

	@Autowired
	ProblemRepository problemRepository;

	@Autowired
	ProblemTestRepository problemTestRepository;

	@Autowired
	UserRepository userRepository;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;

		User u = new User();
		u.setEmail("teste@gmail.com");
		u.setPassword("1234");
		u.setRole(Role.USER);
		userRepository.save(u);

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

		Assert.assertTrue(problemRepository.count() == 2);

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

		Assert.assertTrue(problemRepository.count() == 1);
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

	@Test
	public void postSolutionTest() throws JSONException {

		ProblemTest test = new ProblemTest();
		test.setName("Test01");
		test.setInput("14");
		test.setOutput("15");
		Problem p = problemRepository.findAll().get(0);
		p.getTests().add(test);
		problemRepository.save(p);

		JSONArray array = new JSONArray();
		JSONObject output = new JSONObject();

		JSONObject teste = new JSONObject();
		teste.put("name", "Test01");
		teste.put("input", "14");
		teste.put("id", problemTestRepository.findAll().get(0).getId());
		teste.put("output", "15");

		output.put("value", "15");
		output.put("test", teste);

		array.put(output);

		JSONObject data = new JSONObject();
		data.put("body", "solution description");
		data.put("outputs", array);
		
		JSONObject js = new JSONObject();
		try {
			js.put("email", "teste@gmail.com");
			js.put("password", "1234");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Map<String,String> cookiesReq = given().body(js.toString())
		.contentType("application/json; charset=UTF-8")		
		.when().post("/login").getCookies();
		System.err.println(problemRepository.findAll().get(0).getId());

		given().header("Authorization", TOKEN).cookies(cookiesReq)
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
				.body(data.toString())
				.when()
				.pathParam("id", problemRepository.findAll().get(0).getId())
				.post(PROBLEM_SOLUTION).then()
				.statusCode(HttpStatus.SC_OK)
				.body("size()", equalTo(0));

	}
}

