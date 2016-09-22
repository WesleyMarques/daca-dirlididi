package controllers;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

import bootwildfly.Application;
import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class AccountControllerTest {
	private static final String ACCOUNT = "/api/account";
	private final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYWNhIiwicm9sZXMiOltudWxsXSwiaWF0IjoxNDc0NTAyMjA5LCJleHAiOjI5NDkwMDQ0MTh9.muWwD8DeRbZ1W1qNaXK-yEkeELmrEbp-n4td_KyfPso";

	@Value("${local.server.port}")
	private int serverPort;

	@Autowired
	UserRepository userRepository;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;
		userRepository.deleteAll();
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

		Assert.assertTrue(userRepository.count() == 1);
	}
	
	@Test
	public void getAccountTest() throws JSONException {
		User u = new User();
		u.setEmail("daca");
		u.setRole(Role.ADMIN);
		u.setPassword("daca");
		userRepository.save(u);
		
		JSONObject js = new JSONObject();
		try {
			js.put("email", "daca");
			js.put("password", "daca");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		Map<String,String> cookiesReq = given().body(js.toString())
		.contentType("application/json; charset=UTF-8")		
		.when().post("/login").getCookies();
		
        

		given().header("Authorization", TOKEN).cookies(cookiesReq)
		.when()
		.get(ACCOUNT).then()
		.statusCode(HttpStatus.SC_OK)
				.body("email", equalTo("daca"))
				.extract().response();
		//System.out.print(response.getBody().print());
	}
	
	

}
