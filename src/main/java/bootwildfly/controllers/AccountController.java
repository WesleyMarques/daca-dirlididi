package bootwildfly.controllers;

import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
import bootwildfly.services.UserService;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value="account", description="Operations about account")
@RestController
public class AccountController {

	@Autowired
	UserRepository repository;

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET, path="/api/account", produces = "application/json")
	@ApiOperation(value = "Returns the account info of session user", notes = "Returns the account info of session user")
    public User get(){
		return repository.findAll().get(0);
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