package pl.coderslab.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    void deleteAllByUserId(Long id);

}
