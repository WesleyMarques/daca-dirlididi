package bootwildfly.controllers;

import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
import bootwildfly.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

@Api(value="account", description="Operations about account")
@RestController
public class AccountController {

	@Autowired
	UserRepository repository;

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET, path="/account", produces = "application/json")
	@ApiOperation(value = "Returns the account info of session user", notes = "Returns the account info of session user")
    public List<User> get(){
		return repository.findAll();
    }

	@ApiImplicitParams({
        @ApiImplicitParam(
        	name = "name", value = "User's name", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "email", value = "User's email", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "type", value = "User's type (normal|admin)", required = true,
            	dataType = "string", paramType = "body")
      })
	@RequestMapping(method = RequestMethod.POST, path="/account", produces = "application/json")
	@ApiOperation(value = "Saves an user in the system", notes = "Saves an user in the system")
    public String save(@RequestBody User user){
		String error = userService.getErrorsUser(user);
		if (error == null) {
			repository.save(user);
			return ("{message : 'Account created successfully'}");
		} else {
			return "{error :'" + error + "'}";
		}
    }
}