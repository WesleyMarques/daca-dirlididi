package bootwildfly.controllers;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
import bootwildfly.services.AuthService;
import bootwildfly.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value="account", description="Operations about account")
@RestController
public class AccountController {

	@Autowired
	UserRepository repository;

	@Autowired
	UserService userService;

	@Autowired
	AuthService authService;

	@RequestMapping(method = RequestMethod.GET, path="/api/account", produces = "application/json")
	@ApiOperation(value = "Returns the account info of session user", notes = "Returns the account info of session user")
    public Object get(Principal principal, HttpSession session) throws ServletException {
		if (!authService.isAuth(session)) {
			return null;
		}
		return authService.getUserAuthenticated(session);
    }

	@RequestMapping(method = RequestMethod.POST, path="/api/account", produces = "application/json")
	@ApiOperation(value = "Saves an user in the system", notes = "Saves an user in the system")
	@Transactional
    public String save(@RequestBody User user) throws JSONException{
		JSONObject jsonRes = new JSONObject();
		String error = userService.getErrorsUser(user);
		if (error == null) {
			repository.save(user);
			jsonRes.put("message", "Account created successfully");
			return jsonRes.toString();
		} else {
			jsonRes.put("error", error);
			return jsonRes.toString();
		}
    }
}