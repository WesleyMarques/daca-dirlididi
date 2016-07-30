package bootwildfly.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@RestController
public class SolutionController {
	
	@ApiImplicitParams({
        @ApiImplicitParam(
        	name = "problem_code", value = "Problem's code", required = true, 
        	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "desc", value = "Solution's description", required = true, 
            	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "output", value = "Solution's outputs", required = true, 
            	dataType = "string", paramType = "body")
      })	
	@RequestMapping(method = RequestMethod.POST, path="/solution", produces = "application/json")
    public String save(){
        return ("{failed_tests : [{“desc” : “teste 1”,“dica” : “dica 1”}]}");
    }
}