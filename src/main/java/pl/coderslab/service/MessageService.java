package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;
import pl.coderslab.repository.MessageRepository;
import pl.coderslab.repository.UserRepository;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public List<Message> getAllMessages(Long id) {
        return messageRepository.findAllByRecepientIdOrderByIdDesc(id);
    }

    public void saveMessage(String text, Long authorId, Long recepientId) {
        Message message = new Message();
        User author = userRepository.findOne(authorId);
        User recepient = userRepository.findOne(recepientId);
        message.setText(text);
        message.setAuthor(author);
        message.setRecepient(recepient);
        messageRepository.save(message);
    }
}
