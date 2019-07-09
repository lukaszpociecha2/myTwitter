package pl.coderslab.service;

import org.springframework.stereotype.Service;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.repository.UserRepository;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public void saveTweet(Tweet tweet) {
        User user = userRepository.findOne(tweet.getUser().getId());
        tweet.setUser(user);
        tweetRepository.save(tweet);
    }


}
