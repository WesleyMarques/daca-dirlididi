package integration.repositories;

import bootwildfly.Application;
import bootwildfly.models.Problem;
import bootwildfly.models.ProblemTest;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.ProblemTestRepository;
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
public class ProblemTestRepositoryTest {

    @Autowired
    private ProblemTestRepository repTeste;

    @Autowired
    private ProblemRepository repProblem;

    @Before
    public void setup() {
        repTeste.deleteAll();
        repProblem.deleteAll();

        Problem p = new Problem();
        p.setName("Name 1");
        p.setDescription("Description 1");
        repProblem.save(p);

        ProblemTest test = new ProblemTest();
        test.setName("Test 1");
        test.setProblem(repProblem.findAll().get(0));
        repTeste.save(test);

        Assert.assertTrue(repTeste.findAll().get(0).getProblem().getName().equals("Name 1"));
    }

    @Test
    public void createTest() {
        Assert.assertTrue(repTeste.findAll().size() == 1);
        Assert.assertTrue(repProblem.findAll().size() == 1);
    }

    @Test
    public void updateTest() {
        ProblemTest test = repTeste.findAll().get(0);
        test.setName("Test x");
        repTeste.save(test);

        ProblemTest p = repTeste.findAll().get(0);
        Assert.assertTrue(p.getName().equals("Test x"));
    }

    @Test
    public void deleteTest() {
        Assert.assertTrue(repTeste.findAll().size() == 1);
        ProblemTest test = repTeste.findAll().get(0);
        repTeste.delete(test);
        Assert.assertTrue(repTeste.findAll().size() == 0);
        Assert.assertTrue(repProblem.findAll().size() == 1);
    }

}