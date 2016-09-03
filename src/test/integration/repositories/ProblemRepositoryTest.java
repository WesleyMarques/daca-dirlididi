package integration.repositories;

import bootwildfly.Application;
import bootwildfly.models.Problem;
import bootwildfly.models.repositories.ProblemRepository;
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
public class ProblemRepositoryTest {

    @Autowired
    private ProblemRepository repProb;

    @Before
    public void setup() {
        repProb.deleteAll();
        Problem problem = new Problem();
        problem.setName("Problema 1");
        problem.setDescription("Description 1");
        problem.setCode("Code 1");
        problem.setPublished(false);
        problem.setTip("Dica 1");
        repProb.save(problem);
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

        int numProbs = repProb.findAll().size();
        Assert.assertTrue(numProbs == 0);
    }

}