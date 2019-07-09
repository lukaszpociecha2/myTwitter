package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String signup(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "signup";
    }

    @PostMapping()
    public String signupProceessing(@Valid User user, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            boolean success = userService.saveUser(user);
            if (success) {
                return "redirect:/";
            } else {
                model.addAttribute("notunique", true);
                return "signup";
            }
        } else {
            return "signup";
        }
    }

}
