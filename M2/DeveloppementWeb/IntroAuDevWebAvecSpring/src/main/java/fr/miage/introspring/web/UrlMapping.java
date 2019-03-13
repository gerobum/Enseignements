/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.introspring.web;

import fr.miage.introspring.model.PrettyDateTimeModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author yvan
 */
@Controller
public class UrlMapping {
    @GetMapping("/datetime")
    @ResponseBody
    public String datetime() {
        return "<h1>Bonjour</h1>"
                + "<p>Nous sommes le " + LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu"))+"</p>"
                + "<p>Il est " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm"))+"</p>";
    }
    @GetMapping("/prettydatetime")
    public String prettydatetime(Model model) {
        model.addAttribute("modele", new PrettyDateTimeModel());
        return "prettydatetime";
    }
    
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "vous");
        return "hello";
    }
    
        @RequestMapping("/")
    public String home(Model model) {        
        return "index";
    }
}
