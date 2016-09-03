package integration.controllers;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

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
public class InfoControllerIT {
	private static final String INFO = "/info";
	
	@Value("${local.server.port}")
	private int serverPort;

	@Before
	public void setUp() {
		RestAssured.port = serverPort;
	}
	
	
	
	@Test
	public void getAccountTest(){
		
		when()
		.get(INFO).then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.total_users", equalTo(15))
		.body("data.total_problems", equalTo(10))
		.body("data.problems_you_solved", equalTo(5));
	}
	
	

}
