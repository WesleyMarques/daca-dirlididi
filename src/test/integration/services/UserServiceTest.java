package integration.services;

import bootwildfly.Application;
import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
import bootwildfly.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class UserServiceTest {

    @Autowired
    private UserRepository repUser;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        repUser.deleteAll();
        User u = new User();
        u.setEmail("teste@gmail.com");
        u.setPassword("12345");
        u.setRole(Role.USER);
        repUser.save(u);
    }

    @Test
    public void checkEmailIsValid() {
        User user = new User();
        Assert.assertTrue(userService.getErrorsUser(user) != null);
        user.setEmail("email");
        user.setPassword("pass");
        user.setRole(Role.ADMIN);
        Assert.assertTrue(userService.getErrorsUser(user) == null);
        user.setEmail("teste@gmail.com");
        Assert.assertTrue(userService.getErrorsUser(user) != null);
    }

    @Test
    public void checkUserIsValid() {
        User user = new User();
        user.setEmail("email");
        user.setPassword("pass");
        user.setRole(Role.ADMIN);
        Assert.assertTrue(userService.getErrorsUser(user) == null);
    }
}