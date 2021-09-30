package forum.controller;

import forum.model.Post;
import forum.model.User;
import forum.service.CommonService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegController {

    private final PasswordEncoder encoder;
    private final CommonService service;

    public RegController(PasswordEncoder encoder, CommonService service) {
        this.encoder = encoder;
        this.service = service;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(service.findByAuthority("ROLE_USER"));
        if (service.findUserById(user.getId()) == null && service.findUserByName(user.getName()) == null) {
            service.saveOrEdit(user);
        } else {
            model.addAttribute("errorMessage", "Please try to register with another Username");
            return "reg";
        }
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg(@ModelAttribute Post post) {
        return "reg";
    }
}