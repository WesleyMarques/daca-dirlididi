package bootwildfly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AngularController {

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "../index";
	}

	@RequestMapping(path = "/ide", method = RequestMethod.GET)
	public String ide() {
		return "../index";
	}

	@RequestMapping(path = "/problems", method = RequestMethod.GET)
	public String problems() {
		return "../index";
	}

	@RequestMapping(path = "/problem/create", method = RequestMethod.GET)
	public String createProblem() {
		return "../index";
	}

}