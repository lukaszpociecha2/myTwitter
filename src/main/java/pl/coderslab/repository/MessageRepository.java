package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Message;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByRecepientIdOrderByIdDesc(Long id);
    List<Message> findAllByAuthorIdOrderByIdDesc(Long id);
    void deleteAllByAuthorId(Long id);
    void deleteAllByRecepientId(Long id);

}
