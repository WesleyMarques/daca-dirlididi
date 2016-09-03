package bootwildfly.models.repositories;

import bootwildfly.models.ProblemTest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Claudivan on 02/09/2016.
 */
public interface ProblemTestRepository extends CrudRepository<ProblemTest, Long> {

    List<ProblemTest> findByName(String name);

    @Override
    List<ProblemTest> findAll();
}
