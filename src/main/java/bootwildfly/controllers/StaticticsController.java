package bootwildfly.controllers;

import bootwildfly.dto.Statistics;
import bootwildfly.services.AuthService;
import bootwildfly.services.StatisticsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Api(value="statistics", description="Statistics of the system")
@RestController
public class StaticticsController {

	@Autowired
	StatisticsService statisticsService;

	@Autowired
	AuthService authService;

	@RequestMapping(method = RequestMethod.GET,path="/api/statistics",produces = "application/json")
	@ApiOperation(value = "Returns the general statistics about the system",
			notes = "Return the json object relative to statistics")
    public Statistics get(HttpSession session){
		if (authService.isAuth(session)) {
			return statisticsService.getStatistics(authService.getUserAuthenticated(session));
		} else {
			return null;
		}

    }
}