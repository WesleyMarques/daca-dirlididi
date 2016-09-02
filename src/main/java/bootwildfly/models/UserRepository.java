package bootwildfly.models;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Claudivan on 02/09/2016.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsername(String username);
    
    List<User> findOneByEmail(String email);

    @Override
    List<User> findAll();
}
