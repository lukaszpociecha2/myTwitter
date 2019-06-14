package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.User;
import pl.coderslab.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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

      /*model.addAttribute("user", new User());
        model.asMap().forEach((k,v)-> System.out.println(k + " ::: " + v));
      return "user-edit";*/

    }


    @PostMapping("/edit-user")
    public String editUserProcessing(@Valid User user, BindingResult result, Model model, HttpServletRequest request /*,@SessionAttribute User user, BindingResult bindingResult*/) {



        if (result.hasErrors()) {

            model.asMap().forEach((k,v)-> System.out.println(k + " ::: " + v));


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
       /* } else {
            List<ObjectError> allErrors = result.getAllErrors();
            allErrors.forEach(e -> System.out.println(e.getDefaultMessage() + " :: " + e.getCode()));

            model.addAttribute("update", update);
            return "user-edit";
        }*/
    }
}
