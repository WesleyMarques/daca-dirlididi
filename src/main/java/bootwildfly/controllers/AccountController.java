package bootwildfly.controllers;

import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
import bootwildfly.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(method = RequestMethod.GET, path="/account", produces = "application/json")
	@ApiOperation(value = "Returns the account info of session user", notes = "Returns the account info of session user")
    public List<User> get(){
		return repository.findAll();
    }

	@RequestMapping(method = RequestMethod.POST, path="/account", produces = "application/json")
	@ApiOperation(value = "Saves an user in the system", notes = "Saves an user in the system")
	@Transactional
    public String save(@RequestBody User user){
		System.out.print("CHEGOOU");
		String error = userService.getErrorsUser(user);
		System.out.print("CHEGOOU2");
		if (error == null) {
			repository.save(user);
			return ("{message : 'Account created successfully'}");
		} else {
			return "{error :'" + error + "'}";
		}
    }
}