package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.MessageService;

import java.util.Map;

@Controller
public class MessageController {

    private UserRepository userRepository;
    private MessageService messageService;

    @Autowired
    public MessageController(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    @GetMapping("/send/{recepientId}")
    public String sendMessage(Model model, @SessionAttribute User sessionuser, @PathVariable(required = false) Long recepientId) {

        Message message = new Message();

        model.addAttribute("recepient", recepientId);
        //model.addAttribute("message", message);
        System.out.println(sessionuser.getFirstName());
        System.out.println(userRepository.findOne(recepientId).getFirstName());
        return "message";
    }

    @PostMapping("/send")
    @ResponseBody
    public String processMessage(@RequestParam Long recepientId, @SessionAttribute User sessionuser, @RequestParam String text) {

        System.err.println("W kontrolerze POST");
        System.out.println(recepientId);
        messageService.saveMessage(text, sessionuser.getId(), recepientId);
        return "success";
    }

}
