package bootwildfly.controllers;

import bootwildfly.models.Problem;
import bootwildfly.models.ProblemTest;
import bootwildfly.models.Solution;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.services.SolutionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="problem", description="Operations about problem")
@RestController
public class ProblemController {

	@Autowired
	ProblemRepository repProblem;

	@Autowired
	SolutionService solutionService;

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

	@RequestMapping(method = RequestMethod.POST, path="/problem", produces = "application/json")
	@ApiOperation(value = "Saves a problem in the system")
	@Transactional
    public String save(@RequestBody Problem problem){
        repProblem.save(problem);
		return ("{message : “Problem created successfully”}");
    }

	@RequestMapping(method = RequestMethod.PUT, path="/problem/{id}", produces = "application/json")
	@ApiOperation(value = "Updates a problem by Id")
	@Transactional
    public String update(@PathVariable("id") Long id, @RequestBody Problem problem){
		problem.setId(id);
		repProblem.save(problem);
        return ("{message : “Problem edited successfully”}");
    }

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