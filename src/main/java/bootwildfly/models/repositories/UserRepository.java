package bootwildfly.models.repositories;

import bootwildfly.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByEmail(String email);

    User findOneBySocialId(String id);

    @Override
    List<User> findAll();
}
