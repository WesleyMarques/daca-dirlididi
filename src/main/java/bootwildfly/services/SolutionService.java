package bootwildfly.services;

import bootwildfly.models.*;
import bootwildfly.models.repositories.ProblemRepository;
import bootwildfly.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolutionService {

	public SolutionService() {}

	@Autowired
	private ProblemRepository problemRepository;

	@Autowired
	private UserRepository userRepository;

	public List<ProblemTest> testSolution(Solution solution, Problem problem) {
		List<ProblemTest> fails = new ArrayList<>();
		for (Output o : solution.getOutputs()) {
			if (!o.getTest().getOutput().equals(o.getValue())) {
				fails.add(o.getTest());
			}
		}
		return fails;
	}

	@Transactional
	public void pushSolution(Problem problem, Solution solution) {
		User u = userRepository.findAll().get(0);
		solution.setProblem(problem);
		solution.setUser(u);
		u.getResolvidos().add(problem);
		u.getSolutions().add(solution);
		userRepository.save(u);
	}
}
