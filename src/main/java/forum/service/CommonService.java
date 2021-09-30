package forum.service;

import forum.model.Authority;
import forum.model.Post;
import forum.model.User;
import forum.repository.AuthorityRepository;
import forum.repository.PostRepository;
import forum.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonService {

    private final PostRepository posts;
    private final AuthorityRepository authorities;
    private final UserRepository users;

    public CommonService(PostRepository posts, AuthorityRepository authorities, UserRepository users) {
        this.posts = posts;
        this.authorities = authorities;
        this.users = users;
    }

    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();
        posts.findAll().forEach(list::add);
        return list;
    }
    public Authority findByAuthority (String authority) {
        return authorities.findByAuthority(authority);
    }

    public User findUserByName(String name) {
        return users.findUserByName(name);
    }

    public User findUserById(int id) {
        return users.findUserById(id);
    }

    public void saveOrEdit(User user) {
        users.save(user);
    }
}

/**
 * private final List<Post> posts = new ArrayList<>();
 * private final List<User> users = new ArrayList<>();
 * <p>
 * public CommonService() {
 * posts.add(Post.of("Продаю машину ладу 01.", User.of("Михаил")));
 * }
 * <p>
 * public List<Post> getAllPosts() {
 * return posts;
 * }
 * <p>
 * public List<User> getAllUsers() {
 * return users;
 * }
 */