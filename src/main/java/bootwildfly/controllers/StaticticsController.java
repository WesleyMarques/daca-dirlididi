package bootwildfly.controllers;

import bootwildfly.dto.Statistics;
import bootwildfly.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaticticsController {

	@Autowired
	StatisticsService service;

	@RequestMapping(method = RequestMethod.GET,path="/statistics",produces = "application/json")
    public Statistics get(){
		Statistics s = new Statistics();
		s.problems_you_solved = service.getTotalProblemsYouSolved();
		s.total_problems = service.getTotalProblem();
		s.total_users = service.getTotalUsers();
		return s;
    }
}