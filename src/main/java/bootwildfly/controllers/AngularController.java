package bootwildfly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AngularController {

	@RequestMapping("/login")
	public String login() {
		return "../index";
	}

	@RequestMapping("/ide")
	public String ide() {
		return "../index";
	}

	@RequestMapping("/problems")
	public String problems() {
		return "../index";
	}

	@RequestMapping("/problem/create")
	public String createProblem() {
		return "../index";
	}

}