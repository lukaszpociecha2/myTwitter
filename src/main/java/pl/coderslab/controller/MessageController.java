package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.UserRepository;
import pl.coderslab.service.MessageService;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private UserRepository userRepository;
    private MessageService messageService;
    private MessageRepository messageRepository;

    @Autowired
    public MessageController(UserRepository userRepository, MessageService messageService, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageService = messageService;
        this.messageRepository = messageRepository;
    }

    @GetMapping()
    public String getAllMessages(@SessionAttribute(name = "sessionuser", required = false) User user, Model model){
        List<Message> incomingMessageList = messageService.getAllMessages(user.getId());
        List<Message> sentMessageList = messageRepository.findAllByAuthorIdOrderByIdDesc(user.getId());

        model.addAttribute("incoming", incomingMessageList);
        model.addAttribute("sent", sentMessageList);
        return "messages";
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

    @GetMapping("/delete")
    public String deleteMessage(@RequestParam Long id){
        messageRepository.delete(messageRepository.findOne(id));
        return "redirect:/messages";
    }

    @GetMapping("/details")
    public String getDetails(@RequestParam Long id, Model model){
        Message message = messageRepository.findOne(id);
        message.setSeen(true);
        messageRepository.save(message);
        model.addAttribute(message);
        return "message-details";
    }

}
