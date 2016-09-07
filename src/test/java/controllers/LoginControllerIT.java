package controllers;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class LoginControllerIT {
	private static final String LOGIN = "/login";
	private static final String LOGOUT = "/logout";
	@Value("${local.server.port}")
	private int serverPort;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;
	}
	
	@Test
	public void doLoginTest() throws JSONException{
		JSONObject js = new JSONObject("{\"email\": \"daca\", \"password\": \"daca\"}");
		given().body(js.toString())
		.contentType("application/json; charset=UTF-8")		
		.when().post(LOGIN)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("data", equalTo("login successful"));
	}
	
	@Test
	public void dontLoginTest(){
		given().formParam("email", "wesley@gmail.com")
		.formParam("password", "12345")
		.when().post(LOGIN)
		.then()
		.statusCode(HttpStatus.SC_NOT_FOUND)
		.body("data.message", equalTo("login unsuccessful"));
	}
	
	@Test
	public void doLogoutTest(){
		when().post(LOGOUT)
		.then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.message", equalTo("logout successful"));
	}
	
	public void dontLogoutTest(){
		when().post(LOGOUT)
		.then()
		.statusCode(HttpStatus.SC_NOT_FOUND)
		.body("data.message", equalTo("logout unsuccessful"));
	}

}
