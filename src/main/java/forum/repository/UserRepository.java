package forum.repository;

import forum.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByName(String name);

    User findUserById(Integer id);
}
