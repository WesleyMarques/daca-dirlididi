package bootwildfly.services;

import bootwildfly.models.*;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionService {

	public SolutionService() {}

	@Autowired
	private ProblemRepository problemRepository;

	@Autowired
	private UserRepository userRepository;

	public List<ProblemTest> testSolution(Problem problem, Solution solution) {
		return null;
	}

	public void pushSolution(Problem problem, Solution solution) {
		User u = userRepository.findAll().get(0);
		solution.setProblem(problem);
		u.getResolvidos().add(problem);
		u.getSolutions().add(solution);
		userRepository.save(u);
	}
}
