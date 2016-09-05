package integration.controllers;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import bootwildfly.models.repositories.UserRepository;
import org.apache.http.HttpStatus;
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


import bootwildfly.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class AccountControllerIT {
	private static final String ACCOUNT = "/account";
	
	@Value("${local.server.port}")
	private int serverPort;

	@Autowired
	UserRepository userRepository;

	@Before
	public void setUp() {
		userRepository.deleteAll();
		RestAssured.port = serverPort;
	}
	
	@Test
	public void postNewAccountTest(){
		Map<String, String> newAccount= new HashMap<String, String>();
		newAccount.put("email", "wesley@gmail.com");
		newAccount.put("password", "123456");
		newAccount.put("role", "ADMIN");
		
		given().
		formParameters(newAccount)
		.when()
		.post(ACCOUNT).then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.message", equalTo("user created successfully"));	
	}
	
	@Test
	public void getAccountTest(){
		
		when()
		.get(ACCOUNT).then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.email", equalTo("wesley@gmail.com"));	
	}
	
	

}
