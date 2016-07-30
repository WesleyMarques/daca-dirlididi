package bootwildfly.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@Api(value="account", description="Operations about account")
@RestController
public class AccountController {

	@RequestMapping(method = RequestMethod.GET, path="/account", produces = "application/json")
	@ApiOperation(value = "Returns the account info of session user", notes = "Returns the account info of session user")
    public String get(){
        return ("{name : 'matheus',email : 'matheusgr@gmail.com',tipo : 'admin'}");
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
    public String save(){
        return ("{message : 'Account created successfully'}");
    }
}