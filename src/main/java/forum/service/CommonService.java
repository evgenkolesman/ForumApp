package forum.service;

import forum.model.Post;
import forum.model.User;
import forum.repository.PostRepository;
import forum.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonService {

    private final PostRepository posts;

    public CommonService(PostRepository posts) {
        this.posts = posts;

    }

    public List<Post> getAllPosts() {
        List<Post> list = new ArrayList<>();
        posts.findAll().forEach(list::add);
        return list;
    }

//    public List<User> getAllUsers() {
//        List<User> list = new ArrayList<>();
//        users.findAll().forEach(list::add);
//        return list;
//    }

//    public static void main(String[] args) {
//        posts.findAll().forEach(System.out::println);
//        users.findAll().forEach(System.out::println);}
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