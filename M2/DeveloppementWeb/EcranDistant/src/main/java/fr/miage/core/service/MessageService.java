package fr.miage.core.service;

import java.util.List;
import java.util.Optional;

import fr.miage.core.entity.Message;
import java.time.LocalDateTime;

public interface MessageService {
    Message save(Message entity);
    void delete(Long id);
    List<Message> findAll();
    Optional<Message> findById(Long id);
   
    List<Message> findByStartingBetween(LocalDateTime a, LocalDateTime b);
    List<Message> findByStarting(LocalDateTime a);
    Message getOne(Long id);
}
