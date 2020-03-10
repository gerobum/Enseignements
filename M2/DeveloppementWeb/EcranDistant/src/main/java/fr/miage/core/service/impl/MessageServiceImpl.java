package fr.miage.core.service.impl;

import fr.miage.core.entity.Customer;
import fr.miage.core.entity.Message;
import fr.miage.core.repository.CustomerRepository;
import fr.miage.core.repository.MessageRepository;
import fr.miage.core.service.MessageService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    MessageRepository messageRepository;


    @Override
    public Message save(Message entity) {
        return messageRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<Message> findAll() {
        return (List<Message>) messageRepository.findAll();
    }

    @Override
    public Optional<Message> findById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> findByStarting(LocalDateTime starting) {
        return messageRepository.findByStarting(starting);
    }

    @Override
    public List<Message> findByStartingBetween(LocalDateTime d, LocalDateTime f) {
        return messageRepository.findByStartingBetween(d, f);
    }

    @Override
    public Message getOne(Long id) {
        return messageRepository.getOne(id);
    }
}
