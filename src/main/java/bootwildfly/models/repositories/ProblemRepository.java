package bootwildfly.models.repositories;

import bootwildfly.models.Problem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Claudivan on 02/09/2016.
 */
@Repository
public interface ProblemRepository extends CrudRepository<Problem, Long> {

    List<Problem> findByName(String name);

    @Override
    List<Problem> findAll();
}
