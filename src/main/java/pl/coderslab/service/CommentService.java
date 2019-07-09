package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentService {

    private UserRepository userRepository;
    private TweetRepository tweetRepository;
    private CommentRepository commentRepository;
    private Validator validator;

    @Autowired
    public CommentService(UserRepository userRepository, TweetRepository tweetRepository, CommentRepository commentRepository, Validator validator) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.commentRepository = commentRepository;
        this.validator = validator;
    }

    public String bindComment(Long id, String text, Long user) {

        Comment comment = new Comment();
        Tweet tweet = tweetRepository.findOne(id);
        comment.setTweet(tweet);
        User author = userRepository.findOne(user);
        comment.setUser(author);
        comment.setText(text);

        //TODO validate
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        if (violations.isEmpty()) {
            commentRepository.save(comment);
            return null;
        } else {
            ConstraintViolation<Comment> violation = violations.stream().findFirst().get();
            return violation.getMessage();
        }
    }
}
