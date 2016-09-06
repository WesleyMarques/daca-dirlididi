package bootwildfly.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bootwildfly.models.Problem;
import bootwildfly.models.ProblemTest;
import bootwildfly.models.Solution;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.services.SolutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="problem", description="Operations about problem")
@RestController
public class ProblemController {

	@Autowired
	ProblemRepository repProblem;

	@Autowired
	SolutionService solutionService;

	@ApiImplicitParams({
        @ApiImplicitParam(
        	name = "start", value = "The start of pagination", 
        	required = true, dataType = "int", paramType = "query"),
        @ApiImplicitParam(
            name = "max", value = "The size of pagination", required = true, 
            dataType = "int", paramType = "query"),
        @ApiImplicitParam(
            	name = "order_col", value = "The column to be ordered", required = true, 
            	dataType = "string", paramType = "query"),
        @ApiImplicitParam(
            	name = "order_type", value = "The type of ordernation (asc|desc)", required = true, 
            	dataType = "string", paramType = "query")
      })
	@RequestMapping(method = RequestMethod.GET, path="/api/problem", produces = "application/json")
	@ApiOperation(value = "Returns all the problems of system", notes = "Return the array of objects relative to all the problems of the system")
    public List<Problem> get(){
        return repProblem.findAll();
    }
	
	@RequestMapping(method = RequestMethod.GET, path="/problem/{id}", produces = "application/json")
	@ApiOperation(value = "Returns a problem by Id")
    public Problem show(@PathVariable("id") Long id){
        return repProblem.findOne(id);
    }
	
	@ApiImplicitParams({
        @ApiImplicitParam(
        	name = "name", value = "Problem's name", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "description", value = "Problem's description", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tip", value = "Problem's typ", required = true, 
            	dataType = "string", paramType = "body"),
		@ApiImplicitParam(
				name = "published", value = "Problem's published flag", required = true,
				dataType = "boolean", paramType = "body"),
        @ApiImplicitParam(
            	name = "tests", value = "Problem's tests", required = false, 
            	dataType = "array of objects {}", paramType = "body", 
            	defaultValue = "[{desc: “teste 1”,tip: “dica 1”,input: “entrada 1”,output: “saida 1”}]")
      })	
	@RequestMapping(method = RequestMethod.POST, path="/problem", produces = "application/json")
	@ApiOperation(value = "Saves a problem in the system")
	@Transactional
    public String save(@RequestBody Problem problem){
        repProblem.save(problem);
		return ("{message : “Problem created successfully”}");
    }
	
	@ApiImplicitParams({
        @ApiImplicitParam(
        	name = "name", value = "New problem's name", required = false, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "desc", value = "New problem's description", required = false, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tip", value = "New problem's typ", required = false, 
            	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tests", value = "New problem's test(s)", required = false, 
            	dataType = "array of objects {}", paramType = "body", 
            	defaultValue = "[{desc: “teste 1”,tip: “dica 1”,input: “entrada 1”,output: “saida 1”}]")
      })	
	@RequestMapping(method = RequestMethod.PUT, path="/problem/{id}", produces = "application/json")
	@ApiOperation(value = "Updates a problem by Id")
	@Transactional
    public String update(@PathVariable("id") Long id, @RequestBody Problem problem){
		problem.setId(id);
		repProblem.save(problem);
        return ("{message : “Problem edited successfully”}");
    }

	@ApiImplicitParams({
			@ApiImplicitParam(
					name = "desc", value = "Solution's description", required = true,
					dataType = "string", paramType = "body"),
			@ApiImplicitParam(
					name = "output", value = "Solution's outputs", required = true,
					dataType = "string", paramType = "body")
	})
	@RequestMapping(method = RequestMethod.POST, path="/problem/{id}/solution", produces = "application/json")
	@ApiOperation(value = "Submit a solution to a problem by Id")
	public List<ProblemTest> submitSolution(@PathVariable("id") Long id, @RequestBody Solution solution){
		Problem problem = repProblem.findOne(id);
		List<ProblemTest> failedTests = solutionService.testSolution(solution);
		if (failedTests.size() == 0) {
			solutionService.pushSolution(problem, solution);
		}
		return failedTests;
	}
}