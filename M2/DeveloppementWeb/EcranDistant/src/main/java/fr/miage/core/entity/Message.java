package fr.miage.core.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;


@Entity
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String msg;
    
    @NotNull//@FutureOrPresent
    private LocalDateTime starting;
    
    @NotNull@FutureOrPresent
    private LocalDateTime ending;

    public Message(String msg, LocalDateTime starting, LocalDateTime ending) {
        this.msg = msg;
        this.starting = starting;
        this.ending = ending;
    }

    public Message(String msg, LocalDateTime starting) {
        this(msg, starting, starting.withHour(23).withMinute(59));
    }

    public Message(String msg) {
        this(msg, LocalDateTime.now());  
    }
    
    public Message() {
        this("");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getStarting() {
        return starting;
    }

    public void setStarting(LocalDateTime starting) {
        this.starting = starting;
    }

    public LocalDateTime getEnding() {
        return ending;
    }

    public void setEnding(LocalDateTime ending) {
        this.ending = ending;
    }
    
}
