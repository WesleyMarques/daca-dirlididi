package bootwildfly.services;

import java.util.List;
import java.util.Optional;

import bootwildfly.models.User;
import bootwildfly.models.UserCreateForm;

public interface UserService {
	
	Optional<User> getUserById(long id);
	
	List<User> getUserByEmail(String email);
	
	List<User> getAllUsers();
	
	User create(UserCreateForm form);	

}
