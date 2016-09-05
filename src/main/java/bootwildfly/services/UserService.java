package bootwildfly.services;

import java.util.Map;

import bootwildfly.models.Role;
import bootwildfly.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public UserService() {}

	public User mountUserByParams(Map<String, String> params) {
		User u = new User();
		u.setEmail(params.get("email"));
		u.setPassword(params.get("password"));
		u.setRole(Role.valueOf(params.get("role")));
		return u;
	}

	public String getErrorsUser(User user) {
		if (!isValidEmail(user.getEmail()) || !isValidPassword(user.getPassword())) {
			return "Email or Password required";
		} else if (!isValidRole(user.getRole())) {
			return "Role required";
		}
		return null;
	}

	private boolean isValidEmail(String email) {
		return email != null && !email.equals("");
	}

	private boolean isValidPassword(String password) {
		return password != null && !password.equals("");
	}

	private boolean isValidRole(Role role) {
		return role != null;
	}
}
