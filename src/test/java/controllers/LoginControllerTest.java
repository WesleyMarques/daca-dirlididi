package controllers;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
import org.apache.http.HttpStatus;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import bootwildfly.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@ActiveProfiles(value = "test")
public class LoginControllerTest {
	private static final String LOGIN = "/login";
	private static final String LOGOUT = "/logout";
	public static Response response;
	private String token = "";
	@Value("${local.server.port}")
	private int serverPort;

	@Autowired
	UserRepository userRepository;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;
		userRepository.deleteAll();
		User u = new User();
		u.setEmail("daca");
		u.setRole(Role.ADMIN);
		u.setPassword("daca");
		userRepository.save(u);
	}
	
	@Test
	public void testLoginSuccessful() throws JSONException{
		JSONObject js = new JSONObject();
		js.put("email", "daca");
		js.put("password", "daca");
		response = given().body(js.toString())
		.contentType("application/json; charset=UTF-8")		
		.when().post(LOGIN)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.contentType(ContentType.JSON).  // check that the content type return from the API is JSON
        extract().response();

		Assert.assertTrue(response != null);
		token = response.asString();
	}
	
	@Test
	public void testLoginFailed() throws JSONException{
		JSONObject js = new JSONObject("{\"email\":\"daca\", \"password\":\"dac\"}");
		given().body(js.toString())
		.contentType("application/json; charset=UTF-8")
		.when().post(LOGIN)
		.then()
		.statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
		.body("message", equalTo("Invalid login or password"));
	}
	
//	@Test
//	public void doLogoutTest(){
//		when().post(LOGOUT)
//		.then()
//		.statusCode(HttpStatus.SC_OK)
//		.body("data.message", equalTo("logout successful"));
//	}
//	
//	public void dontLogoutTest(){
//		when().post(LOGOUT)
//		.then()
//		.statusCode(HttpStatus.SC_NOT_FOUND)
//		.body("data.message", equalTo("logout unsuccessful"));
//	}

}
