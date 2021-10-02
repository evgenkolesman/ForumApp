package forum.repository;

import forum.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {

    Post findPostById(Integer id);
}
