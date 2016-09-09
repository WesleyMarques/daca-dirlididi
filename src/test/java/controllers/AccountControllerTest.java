package controllers;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.net.MediaType;
import com.jayway.restassured.RestAssured;

import bootwildfly.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class AccountControllerTest {
	private static final String ACCOUNT = "/api/account";
	private final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYWNhIiwicm9sZXMiOltudWxsXSwiaWF0IjoxNDczMjA5NzE3LCJleHAiOjI5NDY0MTk0MzV9.4bA5G4pqTuk96S5-o2cvKKsVVN7-v2G0PLCqELlAxoY";

	@Value("${local.server.port}")
	private int serverPort;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}
	
	@Test
	public void postNewAccountTest() throws JSONException{
		JSONObject json = new JSONObject();
		json.put("email", "wesley@gmail.com");
		json.put("password", "123456");
		json.put("role", "ADMIN");		
		given().header("Authorization", TOKEN).
		contentType(org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE).
		body(json.toString()).
        when().post(ACCOUNT).
        then().statusCode(HttpStatus.SC_OK)
        .body("message", equalTo("Account created successfully"));
	}
	
	@Test
	public void getAccountTest() throws JSONException {

		Response response = given().header("Authorization", TOKEN).
		when()
		.get(ACCOUNT).then()
		.statusCode(HttpStatus.SC_OK)
				.body("email", equalTo("daca"))
				.extract().response();
		System.out.print(response.getBody().print());
	}
	
	

}
