package bootwildfly.controllers;

import bootwildfly.dto.Statistics;
import bootwildfly.services.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value="statistics", description="Operations about problem")
@RestController
public class StaticticsController {

	@Autowired
	StatisticsService service;

	@RequestMapping(method = RequestMethod.GET,path="/statistics",produces = "application/json")
    public Statistics get(){
		return service.getStatistics();
    }
}