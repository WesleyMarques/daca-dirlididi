package bootwildfly.controllers;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "pages", description = "Routes to all the pages of the system")
@Controller
public class AngularController {

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "../static/index";
	}

	@RequestMapping(path = "/ide", method = RequestMethod.GET)
	public String ide() {
		return "../static/index";
	}

	@RequestMapping(path = "/problems", method = RequestMethod.GET)
	public String problems() {
		return "../static/index";
	}

	@RequestMapping(path = "/problem/create", method = RequestMethod.GET)
	public String createProblem() {
		return "../static/index";
	}

}