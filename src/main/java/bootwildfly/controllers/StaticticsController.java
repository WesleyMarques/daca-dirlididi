package bootwildfly.controllers;

import bootwildfly.dto.Statistics;
import bootwildfly.services.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value="statistics", description="Statistics of the system")
@RestController
public class StaticticsController {

	@Autowired
	StatisticsService service;

	@RequestMapping(method = RequestMethod.GET,path="/api/statistics",produces = "application/json")
	@ApiOperation(value = "Returns the general statistics about the system",
			notes = "Return the json object relative to statistics")
    public Statistics get(){
		return service.getStatistics();
    }
}