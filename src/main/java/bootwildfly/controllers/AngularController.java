package bootwildfly.controllers;

import bootwildfly.models.User;
import bootwildfly.services.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Api(value = "pages", description = "Routes to all the pages of the system")
@Controller
public class AngularController {

	@Autowired
	AuthService authService;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		if (authService.isAuth(session)) {
			return "redirect:";
		} else {
			return "../static/index";
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/facebook/callback")
	public String loginFacebook(Principal principal, HttpSession session) {
		User user = authService.findOrCreateFacebookUser(principal);
		session.setAttribute("username", user.getEmail());
		System.out.println(session.getAttribute("username"));
		return "redirect:/";
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

	@RequestMapping(path = "/problem/{id}/edit", method = RequestMethod.GET)
	public String editProblem() {
		return "../static/index";
	}

}