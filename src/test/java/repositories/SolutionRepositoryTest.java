package repositories;

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
import bootwildfly.models.Output;
import bootwildfly.models.Problem;
import bootwildfly.models.ProblemTest;
import bootwildfly.models.Role;
import bootwildfly.models.Solution;
import bootwildfly.models.User;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.ProblemTestRepository;
import bootwildfly.models.repositories.SolutionRepository;
import bootwildfly.models.repositories.UserRepository;

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

    @Autowired
    private ProblemTestRepository repTest;

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

        ProblemTest test = new ProblemTest();
        test.setName("Test 1");
        test.setInput("Input 1");
        test.setOutput("Output 1");
        p.getTests().add(test);
        repProblem.save(p);

        s.setProblem(repProblem.findAll().get(0));
        Output o = new Output();
        o.setValue("Output 1");
        o.setTest(repTest.findAll().get(0));
        s.getOutputs().add(o);

        repSol.save(s);
    }

    @Test
    public void createSolution() {
        Assert.assertTrue(repSol.count() == 1);
        Assert.assertTrue(repUser.count() == 1);
        Assert.assertTrue(repProblem.count() == 1);
        Assert.assertTrue(repTest.count() == 1);
        Assert.assertTrue(repSol.findAll().get(0).getOutputs().size() == 1);
        Assert.assertTrue(repSol.findAll().get(0).getProblem() != null);
        Assert.assertTrue(repSol.findAll().get(0).getOutputs().get(0).getTest() != null);
    }

    @Test
    public void updateSolution() {
        Solution solution = repSol.findAll().get(0);
        solution.setBody("Solution x");
        repSol.save(solution);
        Solution p = repSol.findAll().get(0);
        Assert.assertTrue(repTest.count() == 1);
        Assert.assertTrue(p.getBody().equals("Solution x"));
    }

    @Test
    public void deleteSolution() {
        Assert.assertTrue(repSol.count() == 1);
        Solution solution = repSol.findAll().get(0);
        repSol.delete(solution);
        Assert.assertTrue(repUser.count() == 1);
        Assert.assertTrue(repProblem.count() == 1);
        Assert.assertTrue(repTest.count() == 1);
        Assert.assertTrue(repSol.count() == 0);
    }

}