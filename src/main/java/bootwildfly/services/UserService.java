package bootwildfly.services;

import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public UserService() {}

	@Autowired
	private UserRepository userRep;

	public String getErrorsUser(User user) {
		if (!isValidEmail(user.getEmail()) || !isValidPassword(user.getPassword())) {
			return "Email or Password invalid";
		} else if (!isValidRole(user.getRole())) {
			return "Role invalid";
		}
		return null;
	}

	private boolean isValidEmail(String email) {
		return email != null && !email.equals("") && userRep.findOneByEmail(email) == null;
	}

	private boolean isValidPassword(String password) {
		return password != null && !password.equals("");
	}

	private boolean isValidRole(Role role) {
		return role != null;
	}
}
