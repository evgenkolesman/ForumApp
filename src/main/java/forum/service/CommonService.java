package forum.service;

import forum.model.Post;
import forum.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonService {

    private final List<Post> posts = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    public CommonService() {
        posts.add(Post.of("Продаю машину ладу 01.", User.of("Михаил")));
    }

    public List<Post> getAllPosts() {
        return posts;
    }

    public List<User> getAllUsers() {
        return users;
    }
}
