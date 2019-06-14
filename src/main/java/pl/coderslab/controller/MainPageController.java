package pl.coderslab.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.repository.CommentRepository;
import pl.coderslab.repository.TweetRepository;
import pl.coderslab.service.CommentService;
import pl.coderslab.service.TweetService;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Controller
@Validated
public class MainPageController {

    private final TweetRepository tweetRepository;
    private final TweetService tweetService;
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @Autowired
    public MainPageController(TweetRepository tweetRepository, TweetService tweetService, CommentService commentService, CommentRepository commentRepository) {
        this.tweetRepository = tweetRepository;
        this.tweetService = tweetService;
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/main-page")
    public String mainPage(@SessionAttribute(required = false, name="sessionuser") User user, Model model){
        if(user==null){
            return "redirect:/";
        }
        List<Tweet> tweetList = tweetRepository.findAll();
        model.addAttribute("alltweets", tweetList);
        Tweet tweet = new Tweet();
        model.addAttribute("tweet", tweet);
        return "main-page";
    }

  /*  @GetMapping("get-all-tweets")

    public String getAllTweets(HttpServletRequest request, Model model){
        List<Tweet> tweetList = tweetRepository.findAll();
        model.addAttribute("alltweets", tweetList);
        return "all-tweets";
    }*/

    @PostMapping("/main-page")
    public String mainPageProcessing(@SessionAttribute(required = false, name="sessionuser") User user
                                        , @Valid Tweet tweet, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            tweetService.saveTweet(tweet);
        } else {
            List<ObjectError> errors =bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getObjectName() + error.getCode());
            }
        }


      /*  System.out.println("Autor komentarza: " + comment.getUser().getId());
        System.out.println("Tekst komentarza: " + comment.getText());
        System.out.println("Comment do tweeta: " + comment.getTweet().getId());*/

        /*List<Tweet> tweetList = tweetRepository.findAll();*/

        return "redirect:/main-page";
    }

    @GetMapping("find-by-user/{id}")
    @ResponseBody
    public List<Tweet> findAllUserTweets(@PathVariable Long id, Model mode){
        List<Tweet> list = tweetRepository.findAllByUserId(id);
        if(!list.isEmpty()){
            list.get(0).getCommentList();
        }
        return list;
    }


    @GetMapping("/get-all-tweets")
    @ResponseBody
    public List<Tweet> allComments(){
        return tweetRepository.findAll();
    }

    @PostMapping("/add-comment")

    public String addComment(@RequestParam Long id, /*@Size(min=1, max = 30, message = "should be between 1 and 30")*/ @RequestParam /*@Max(30) @Min(1)*/ String text
                    , @RequestParam Long user, RedirectAttributes attributes){
        System.out.println(text.length());
        String result = commentService.bindComment(id, text, user);
        if (result!=null){
            attributes.addFlashAttribute("comment", result );
            System.out.println(result);
        }
        return "redirect:main-page";

    }

}
