package bootwildfly.services;

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

    public int getTotalUsers() {
        return (int) repUser.count();
    }

    public int getTotalProblem() {
        return (int) repProblem.count();
    }

    public int getTotalProblemsYouSolved() {
        return (int) repUser.findAll().get(0).getResolvidos().size();
    }
}
