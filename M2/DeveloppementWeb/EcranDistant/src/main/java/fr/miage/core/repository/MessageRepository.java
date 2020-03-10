package fr.miage.core.repository;

import fr.miage.core.entity.Message;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {


    List<Message> findByStartingBetween(LocalDateTime a, LocalDateTime b);
    List<Message> findByStarting(LocalDateTime a);
}
