package integration.services;

import bootwildfly.Application;
import bootwildfly.models.*;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.SolutionRepository;
import bootwildfly.models.repositories.UserRepository;
import bootwildfly.services.StatisticsService;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class StatisticsServiceTest {

    @Autowired
    private UserRepository repUser;

    @Autowired
    private ProblemRepository repProblem;

    @Autowired
    private StatisticsService statisticsService;

    @Before
    public void setup() {
        repUser.deleteAll();
        repProblem.deleteAll();
        Assert.assertTrue(statisticsService.getStatistics().total_users == 0);
        Assert.assertTrue(statisticsService.getStatistics().total_problems == 0);
        Assert.assertTrue(statisticsService.getStatistics().problems_you_solved == 0);
    }

    @Test
    public void checkGetStatisticsIsCorrect() {
        User u = new User();
        u.setEmail("teste@gmail.com");
        u.setPassword("123456");
        u.setRole(Role.USER);
        repUser.save(u);
        Assert.assertTrue(statisticsService.getStatistics().total_users == 1);

        Problem p = new Problem();
        p.setName("Name 1");
        p.setDescription("Description 1");
        repProblem.save(p);
        Assert.assertTrue(statisticsService.getStatistics().total_problems == 1);

        u = repUser.findAll().get(0);
        u.getResolvidos().add(repProblem.findAll().get(0));
        repUser.save(u);
        Assert.assertTrue(statisticsService.getStatistics().problems_you_solved == 1);
    }
}