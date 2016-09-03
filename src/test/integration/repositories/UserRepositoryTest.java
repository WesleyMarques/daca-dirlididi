package integration.repositories;

import bootwildfly.Application;
import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.UserRepository;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository repUser;

    @Before
    public void setup() {
        repUser.deleteAll();
        User u = new User();
        u.setEmail("teste@gmail.com");
        u.setPassword("1234");
        u.setRole(Role.USER);
        repUser.save(u);
    }

    @Test
    public void createUser() {
        List<User> users = repUser.findAll();
        Assert.assertTrue(users.size() == 1);
        Assert.assertTrue(users.get(0).getEmail().equals("teste@gmail.com"));
        Assert.assertTrue(users.get(0).getRole() == Role.USER);
    }

    @Test
    public void updateUser() {
        User user = repUser.findAll().get(0);
        user.setEmail("daca2");
        repUser.save(user);

        user = repUser.findAll().get(0);
        Assert.assertTrue(user.getEmail().equals("daca2"));
    }

    @Test
    public void deleteUser() {
        User user = repUser.findAll().get(0);
        repUser.delete(user);

        int numUsers = repUser.findAll().size();
        Assert.assertTrue(numUsers == 0);
    }

}