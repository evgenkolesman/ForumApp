package forum.controller;

import forum.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final PostRepository posts;

    public IndexController(PostRepository posts) {
        this.posts = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", posts.findAll());
        return "index";
    }
}
