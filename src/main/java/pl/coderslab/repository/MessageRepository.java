package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Message;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {



}
