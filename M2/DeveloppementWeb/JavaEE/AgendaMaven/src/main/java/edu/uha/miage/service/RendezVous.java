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

/**
 *
 * @author yvan
 */
public class RendezVous implements Serializable {
    
    private String title;
    private LocalDateTime datetime;
    private int durationInMinutes;

    public RendezVous(String title, LocalDateTime datetime, int durationInMinutes) {
        if (datetime.isBefore(LocalDateTime.now()))
            throw new DateTimeException("Passed");
        this.title = title;
        this.datetime = datetime;
        this.durationInMinutes = durationInMinutes;
    }

    public RendezVous(String title, LocalDateTime datetime) {
        this(title, datetime, 60);
    }

    public RendezVous(String title, LocalDate date, LocalTime time, int durationInMinutes) {
        this(title, LocalDateTime.of(date, time), durationInMinutes);
    }

    public RendezVous(String title, LocalDate date, LocalTime time) {
        this(title, LocalDateTime.of(date, time), 60);
    }

    public RendezVous() {
        this("", LocalDateTime.now(), 60);
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

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
    
    public String toString() {
        return title + " --> " + datetime;
    }
    
    
}
