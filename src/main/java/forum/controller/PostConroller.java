package forum.controller;


import forum.model.Post;
import forum.service.CommonService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@Controller
public class PostConroller {

    private final CommonService service;

    public PostConroller(CommonService service) {
        this.service = service;
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("post", service.findPostById(id));
        return "post";
    }

    @GetMapping(value = "/create")
    public String create() {
        return "/create";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", service.findPostById(id));
        return "/update";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post) {
        post.setCreated(Calendar.getInstance());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        post.setUser(service.findUserByName(auth.getName()));
        service.saveOrEditPost(post);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletePost(@RequestParam("id") int id) {
        service.deletePostById(id);
        return "redirect:/";
    }
}
