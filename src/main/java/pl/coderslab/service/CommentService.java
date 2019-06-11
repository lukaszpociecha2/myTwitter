package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

@Service
public class CommentService {

    private UserRepository userRepository;
    private TweetRepository tweetRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(UserRepository userRepository, TweetRepository tweetRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.commentRepository = commentRepository;
    }

    public Comment bindComment(Long id, String text, Long user){
        Comment comment = new Comment();
        Tweet tweet = tweetRepository.findOne(id);

        comment.setTweet(tweet);
        User author = userRepository.findOne(user);
        comment.setUser(author);
        comment.setText(text);
        commentRepository.save(comment);
        return comment;
    }
}
