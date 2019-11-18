/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.exosdevweb.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author yvan
 */
public class PrettyDateTimeModel implements Serializable {

    private String name;
    private Integer age;
    private String date;
    private String time;

    public PrettyDateTimeModel(String name, String age) {
        this.name = name;
        try {
            this.age = Integer.parseInt(age);
        } catch (NumberFormatException ex) {
            this.age = null;
        }
        date = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu"));
        time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"));
    }

    public PrettyDateTimeModel() {
        this("", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
}
