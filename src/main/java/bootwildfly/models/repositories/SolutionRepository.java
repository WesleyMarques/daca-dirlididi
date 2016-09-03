package bootwildfly.models.repositories;

import bootwildfly.models.ProblemTest;
import bootwildfly.models.Solution;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Claudivan on 02/09/2016.
 */
public interface SolutionRepository extends CrudRepository<Solution, Long> {

    @Override
    List<Solution> findAll();
}
