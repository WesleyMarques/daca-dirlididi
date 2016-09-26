package bootwildfly.services;

import bootwildfly.dto.Statistics;
import bootwildfly.models.User;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    UserRepository repUser;

    @Autowired
    ProblemRepository repProblem;

    public StatisticsService() {}

    public Statistics getStatistics(User user) {
        Statistics s = new Statistics();
        s.problems_you_solved = getTotalProblemsYouSolved(user);
        s.total_problems = getTotalProblem();
        s.total_users = getTotalUsers();
        return s;
    }

    private int getTotalUsers() {
        return (int) repUser.count();
    }

    private int getTotalProblem() {
        return (int) repProblem.count();
    }

    private int getTotalProblemsYouSolved(User user) {
        return user.getResolvidos().size();
    }
}
