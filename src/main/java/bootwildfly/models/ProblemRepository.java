package bootwildfly.models;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Claudivan on 02/09/2016.
 */
public interface ProblemRepository extends CrudRepository<Problem, Long> {

    List<Problem> findByName(String name);
}
