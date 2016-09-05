package bootwildfly.controllers;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bootwildfly.Application;
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
	@RequestMapping(method = RequestMethod.POST, path = "/login", produces = "application/json")
	@ApiOperation(value = "Login in the system", notes = "Realizes the login in the sytem")
	public String login(@RequestBody final String email, @RequestBody final String password) throws ServletException {
		System.out.println(email+" "+password);
		User user = userRepository.findOneByEmail(email);
		if (user == null || user.getPassword() != password) {
            throw new ServletException("Invalid login");
        }
        return ("{token: '"+Jwts.builder().setSubject(email)
            .claim("roles", Arrays.asList(user.getRole())).setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey").compact()+"'}");
	}

	@SuppressWarnings("unused")
	private static class UserLogin {
		public String email;
		public String password;
	}
	
	@SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }

	@RequestMapping(method = RequestMethod.GET, path = "/login", produces = "application/json")
	public String loginPage() {
		return ("{message : 'Login Page'}");
	}

	@RequestMapping(method = RequestMethod.POST, path = "/logout", produces = "application/json")
	@ApiOperation(value = "Logout of the system", notes = "Realizes the logout of the sytem")
	public String logout() {
		return ("{message : 'Logout successful'}");
	}
}