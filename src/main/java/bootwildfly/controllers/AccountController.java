package bootwildfly.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@RestController
public class AccountController {

	@RequestMapping(method = RequestMethod.GET, path="/account", produces = "application/json")
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
    public String save(){
        return ("{message : 'Account created successfully'}");
    }
}