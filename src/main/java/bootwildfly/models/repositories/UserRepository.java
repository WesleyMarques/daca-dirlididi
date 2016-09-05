package bootwildfly.models.repositories;

import bootwildfly.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findOneByEmail(String email);
    

    @Override
    List<User> findAll();
}
