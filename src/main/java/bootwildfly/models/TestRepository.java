package bootwildfly.models;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Claudivan on 02/09/2016.
 */
public interface TestRepository extends CrudRepository<Test, Long> {

    List<Test> findByName(String name);
}
