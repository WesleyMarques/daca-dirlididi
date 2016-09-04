package integration.repositories;

import bootwildfly.Application;
import bootwildfly.models.*;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.ProblemTestRepository;
import bootwildfly.models.repositories.SolutionRepository;
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
public class SolutionRepositoryTest {

    @Autowired
    private SolutionRepository repSol;

    @Autowired
    private UserRepository repUser;

    @Autowired
    private ProblemRepository repProblem;

    @Before
    public void setup() {
        repSol.deleteAll();
        repUser.deleteAll();
        repProblem.deleteAll();
        User u = new User();
        u.setEmail("asda");
        u.setPassword("asda");
        u.setRole(Role.ADMIN);
        repUser.save(u);

        Solution s = new Solution();
        s.setBody("Body solution");
        s.setUser(repUser.findAll().get(0));

        Problem p = new Problem();
        p.setName("Name 1");
        p.setDescription("Description 1");
        repProblem.save(p);
        s.setProblem(repProblem.findAll().get(0));

        Output o = new Output();
        o.setValue("17");
        s.getOutputs().add(o);

        repSol.save(s);
    }

    @Test
    public void createSolution() {
        List<Solution> solutions = repSol.findAll();
        Assert.assertTrue(repSol.findAll().size() == 1);
        Assert.assertTrue(repUser.findAll().size() == 1);
        Assert.assertTrue(repProblem.findAll().size() == 1);
    }

    @Test
    public void updateSolution() {
        Solution solution = repSol.findAll().get(0);
        solution.setBody("Solution x");
        repSol.save(solution);
        Solution p = repSol.findAll().get(0);
        Assert.assertTrue(p.getBody().equals("Solution x"));
    }

    @Test
    public void deleteSolution() {
        Assert.assertTrue(repSol.findAll().size() == 1);
        Solution solution = repSol.findAll().get(0);
        repSol.delete(solution);
        Assert.assertTrue(repUser.findAll().size() == 1);
        Assert.assertTrue(repProblem.findAll().size() == 1);
        Assert.assertTrue(repSol.findAll().size() == 0);
    }

}