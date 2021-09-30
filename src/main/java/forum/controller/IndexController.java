package forum.controller;

import forum.repository.PostRepository;
import forum.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final CommonService service;

    public IndexController(CommonService service) {
        this.service = service;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", service.getAllPosts());
        return "index";
    }
}
