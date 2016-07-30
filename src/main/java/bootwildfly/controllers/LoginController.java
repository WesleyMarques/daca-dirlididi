package bootwildfly.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@RestController
public class LoginController {

	@ApiImplicitParams({
        @ApiImplicitParam(
        		name = "email", value = "User's email", required = true, 
        		dataType = "string", paramType = "body")
      })
	@RequestMapping(method = RequestMethod.POST, path="/login", produces = "application/json")
    public String login(){
        return ("{message : 'Login successful'}");
    }
	
	@RequestMapping(method = RequestMethod.POST, path="/logout", produces = "application/json")
    public String logout(){
        return ("{message : 'Logout successful'}");
    }
}