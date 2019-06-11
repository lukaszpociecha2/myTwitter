package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Tweet;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    public List<Tweet> findAllByUserId(Long id);

}
