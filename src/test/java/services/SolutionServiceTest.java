package services;

import bootwildfly.Application;
import bootwildfly.models.*;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.ProblemTestRepository;
import bootwildfly.models.repositories.SolutionRepository;
import bootwildfly.models.repositories.UserRepository;
import bootwildfly.services.SolutionService;
import bootwildfly.services.StatisticsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class SolutionServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private ProblemTestRepository problemTestRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private SolutionService solutionService;

    @Before
    public void setup() {
        userRepository.deleteAll();
        problemRepository.deleteAll();
        problemTestRepository.deleteAll();
        solutionRepository.deleteAll();

        User u = new User();
        u.setEmail("daca");
        u.setRole(Role.ADMIN);
        u.setPassword("daca");
        userRepository.save(u);

        Problem p = new Problem();
        p.setName("Name 1");
        p.setDescription("Description 1");
        problemRepository.save(p);

        ProblemTest test = new ProblemTest();
        test.setName("Test 1");
        test.setInput("Entrada 1");
        test.setOutput("Saida 1");
        p = problemRepository.findAll().get(0);
        p.getTests().add(test);
        problemRepository.save(p);
    }

    @Test
    public void checkSolutionValid() {

        Solution s = new Solution();
        s.setBody("Body Solution");
        s.setUser(userRepository.findAll().get(0));

        List<Output> outputs = new ArrayList<>();
        Output o = new Output();
        o.setValue("Saida 1");
        o.setTest(problemTestRepository.findAll().get(0));
        outputs.add(o);

        s.setOutputs(outputs);

        List<ProblemTest> result = solutionService.testSolution(s, problemRepository.findAll().get(0));
        System.out.println(result.size());
        Assert.assertTrue( result.size() == 0);
    }

    @Test
    public void checkSolutionInvalid() {

        Solution s = new Solution();
        s.setBody("Body Solution 2");
        s.setUser(userRepository.findAll().get(0));

        List<Output> outputs = new ArrayList<>();
        Output o = new Output();
        o.setValue("Saida 4");
        o.setTest(problemTestRepository.findAll().get(0));
        outputs.add(o);

        s.setOutputs(outputs);

        List<ProblemTest> result = solutionService.testSolution(s, problemRepository.findAll().get(0));
        System.out.println(result.size());
        Assert.assertTrue( result.size() == 1);
    }
}