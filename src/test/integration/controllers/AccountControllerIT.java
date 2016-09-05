package integration.controllers;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import bootwildfly.models.repositories.UserRepository;
import org.apache.http.HttpStatus;
import org.apache.http.impl.DefaultHttpRequestFactory;
import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import bootwildfly.Application;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class AccountControllerIT {
	private static final String ACCOUNT = "/account";

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
	public void postNewAccountTest(){
		MultiValueMap<String, String> newAccount= new LinkedMultiValueMap<String, String>();
		newAccount.add("email", "wesley@gmail.com");
		newAccount.add("password", "123456");
		newAccount.add("role", "ADMIN");
		MockHttpServletRequestBuilder requestBuilder =
				MockMvcRequestBuilders.post(ACCOUNT)
						.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
						.params(newAccount);
		try {
			Assert.assertTrue(
					mvc.perform(requestBuilder).andReturn().getResponse().getStatus() == HttpStatus.SC_OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAccountTest(){
		
		when()
		.get(ACCOUNT).then()
		.statusCode(HttpStatus.SC_OK)
		.body("data.email", equalTo("wesley@gmail.com"));	
	}
	
	

}
