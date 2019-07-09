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
import java.util.Enumeration;

@Controller
@RequestMapping("/signin")
public class SignInController {


    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public SignInController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("")
    public String login(HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "sign-in";
        } else {
            return "redirect:/main-page";
        }
    }

    @PostMapping("")
    public String loginProcessing(@RequestParam String email, @RequestParam String password, Model model,
                                  HttpServletRequest request) {

        if (password.isEmpty() || email.isEmpty()) {
            model.addAttribute("error", "true");
            return "sign-in";
        }

        User user = userService.verify(email, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("sessionuser", user);
            return "redirect:/";
        } else {

            model.addAttribute("error", "true");
            return "sign-in";
        }
    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

}
