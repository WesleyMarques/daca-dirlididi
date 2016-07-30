package bootwildfly.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@Api(value="solution", description="Operations about solution")
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
	@ApiOperation(value = "Submit a solution to a problem")
    public String save(){
        return ("{failed_tests : [{“desc” : “teste 1”,“dica” : “dica 1”}]}");
    }
}