package bootwildfly.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "login", description = "Operations of login and logout of system")
@RestController
public class LoginController {
	
	@Autowired
	UserRepository userRepository;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "email", value = "User's email", required = true, dataType = "string", paramType = "body") })
	@RequestMapping(method = RequestMethod.POST, path = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = "application/json")
	@ApiOperation(value = "Login in the system", notes = "Realizes the login in the sytem")
	public String login(@RequestBody final User user) throws ServletException {
		User result = userRepository.findOneByEmail(user.getEmail());
		if(result == null || !result.getPassword().equals(user.getPassword()) ){
			throw new ServletException("Invalid login or password");
		} else {
			return ("{\"token\": \""+Jwts.builder().setSubject(user.getEmail())
					.claim("roles", Arrays.asList(user.getRole())).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis()*2))
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact()+"\"}");
		}
	}

	private static class UserLogin {
		public String email;
		public String password;
	}
	
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }

	@RequestMapping(method = RequestMethod.POST, path = "/logout", produces = "application/json")
	@ApiOperation(value = "Logout of the system", notes = "Realizes the logout of the sytem")
	public String logout() {
		return ("{message : 'Logout successful'}");
	}
}