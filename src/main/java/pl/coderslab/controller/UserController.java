package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
public class UserController {


    private UserService userService;
    private TweetRepository tweetRepository;
    private UserRepository userRepository;
    private MessageRepository messageRepository;
    private CommentRepository commentRepository;

    @Autowired
    public UserController(UserService userService, TweetRepository tweetRepository, UserRepository userRepository, MessageRepository messageRepository, CommentRepository commentRepository) {
        this.userService = userService;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.commentRepository = commentRepository;
    }


    @GetMapping("/user-panel")
    public String userPanel(@SessionAttribute(name = "sessionuser") User user, Model model) {

        model.addAttribute("usertweets", tweetRepository.findAllByUserId(user.getId()));

        return "user-panel";
    }

    @GetMapping("/edit-user")
    public String userEdit(@SessionAttribute(required = false, name = "sessionuser") User sessionUser, Model model) {

        if (sessionUser != null) {
            System.out.println(sessionUser.getFirstName());

            User tempUser = new User();
            //tempUser.setId(sessionUser.getId());
            tempUser.setFirstName(sessionUser.getFirstName());
            tempUser.setLastName(sessionUser.getLastName());
            tempUser.setEmail(sessionUser.getEmail());
            model.addAttribute("user", tempUser);

            return "user-edit";
        } else {
            return "redirect:/";
        }
    }


    @PostMapping("/edit-user")
    public String editUserProcessing(@Valid User user, BindingResult result, Model model, HttpServletRequest request /*,@SessionAttribute User user, BindingResult bindingResult*/) {

        if (result.hasErrors()) {
            model.asMap().forEach((k, v) -> System.out.println(k + " ::: " + v));
            return "user-edit";
        }
        if (userService.editUser(user)) {
            request.getSession().setAttribute("sessionuser", user);
            return "redirect:/";
        } else {

            model.addAttribute("notunique", true);
            //model.addAttribute("user", user);
            return "user-edit";
        }
    }

    @GetMapping("delete-account")
    @Transactional
    public String deleteAccount(@SessionAttribute(name = "sessionuser") User user, HttpServletRequest request) {
        User userToDelete = userRepository.findOne(user.getId());
        messageRepository.deleteAllByAuthorId(user.getId());
        messageRepository.deleteAllByRecepientId(user.getId());
        commentRepository.deleteAllByUserId(user.getId());
        tweetRepository.deleteAllByUserId(user.getId());
        userRepository.delete(userToDelete);
        if (userRepository.findOne(user.getId()) == null) {
            request.getSession().invalidate();
        }

        return "redirect:/";

    }
}
