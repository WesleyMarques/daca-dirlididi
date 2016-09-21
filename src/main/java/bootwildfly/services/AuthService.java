package bootwildfly.services;

import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

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
}
