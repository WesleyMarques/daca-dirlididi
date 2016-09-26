package bootwildfly.services;

import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    public AuthService() {}

    public boolean isAuth(HttpSession session) {
        return session.getAttribute("username") != null;
    }

    public User getUserAuthenticated(HttpSession session) {
        return userRepository.findOneByEmail( (String) session.getAttribute("username"));
    }

    public User findOrCreateFacebookUser(Principal principal) {
        User user = userRepository.findOneBySocialId(principal.getName());
        if (userRepository.findOneBySocialId(principal.getName()) != null) {
            return user;
        } else {
            user = new User();
            user.setEmail(principal.getName());
            user.setPassword("a3kd09f809s8fa0d9fa8f0d8sa9fda09s8dfn");
            user.setRole(Role.USER);
            user.setSocialId(principal.getName());
            userRepository.save(user);
            return user;
        }
    }
}
