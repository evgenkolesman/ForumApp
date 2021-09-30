package forum.controller;

import forum.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final CommonService posts;

    public IndexController(CommonService posts) {
        this.posts = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", posts.getAllPosts());
//        model.addAttribute("posts", posts.getAllUsers());
        return "index";
    }
}
