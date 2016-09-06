package repositories;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import bootwildfly.Application;
import bootwildfly.models.Problem;
import bootwildfly.models.Role;
import bootwildfly.models.User;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ProblemRepositoryTest {

    @Autowired
    private ProblemRepository repProb;

    @Autowired
    private UserRepository repUser;

    @Before
    public void setup() {
        repUser.deleteAll();
        repProb.deleteAll();
        User u = new User();
        u.setEmail("asda");
        u.setPassword("asda");
        u.setRole(Role.ADMIN);
        repUser.save(u);

        Problem problem = new Problem();
        problem.setName("Problema 1");
        problem.setDescription("Description 1");
        problem.setPublished(false);
        problem.setTip("Dica 1");
        repProb.save(problem);

        User user1 = repUser.findAll().get(0);
        user1.getResolvidos().add(repProb.findAll().get(0));
        repUser.save(user1);
    }

    @Test
    public void createProblem() {
        List<Problem> problems = repProb.findAll();
        Assert.assertTrue(problems.size() == 1);
    }

    @Test
    public void updateProblem() {
        Problem problem = repProb.findAll().get(0);
        problem.setName("Problema x");
        repProb.save(problem);

        Problem p = repProb.findAll().get(0);
        Assert.assertTrue(p.getName().equals("Problema x"));
    }

    @Test
    public void deleteProblem() {
        Problem problem = repProb.findAll().get(0);
        repProb.delete(problem);
        Assert.assertTrue(repProb.count()== 0);
        Assert.assertTrue(repUser.count() == 1);
    }

}