package bootwildfly.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@Api(value="login", description="Operations of login and logout of system")  
@RestController
public class LoginController {

	@ApiImplicitParams({
        @ApiImplicitParam(
        		name = "email", value = "User's email", required = true, 
        		dataType = "string", paramType = "body")
      })
	@RequestMapping(method = RequestMethod.POST, path="/login", produces = "application/json")
	@ApiOperation(value = "Login in the system", notes = "Realizes the login in the sytem")
    public String login(){
        return ("{message : 'Login successful'}");
    }
	
	@RequestMapping(method = RequestMethod.POST, path="/logout", produces = "application/json")
	@ApiOperation(value = "Logout of the system", notes = "Realizes the logout of the sytem")
    public String logout(){
        return ("{message : 'Logout successful'}");
    }
}