package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {


    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public LoginController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            return "home";
        } else {
            return "redirect:/main-page";
        }

    }

    @PostMapping("/")
    public String loginProcessing(@RequestParam String email, @RequestParam String password, Model model
            , HttpServletRequest request) {
        User user = userService.verify(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("sessionuser", user);
            return "redirect:/main-page";
        } else {
            model.addAttribute("error", "true");
            return "home";
        }
    }

    @GetMapping("/create")
    public String signup(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "signup";
    }

    @PostMapping("/create")
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

        @GetMapping("/logout")
        public String logout (HttpServletRequest request){
            HttpSession session = request.getSession();
            session.invalidate();
            return "redirect:/";
        }


    }
