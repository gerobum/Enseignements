/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.uha.miage.service;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author yvan
 */
public class RendezVous implements Serializable {
    
    private String title;
    private LocalDateTime datetime;

    public RendezVous(String title, LocalDateTime datetime) {
        if (datetime.isBefore(LocalDateTime.now()))
            throw new DateTimeException("Passed");
        this.title = title;
        this.datetime = datetime;
    }

    public RendezVous(String title, LocalDate date, LocalTime time) {
        this(title, LocalDateTime.of(date, time));
    }

    public RendezVous() {
        this("", LocalDateTime.now());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
    
    @Override
    public String toString() {
        return title + " le " + DateTimeFormatter.ofPattern("d/M/u à H:m").format(datetime) ;
    }
    
    
}
