package edu.uha.miage.exosdevweb.controllers;

import edu.uha.miage.exosdevweb.Application;
import edu.uha.miage.exosdevweb.core.Person;
import edu.uha.miage.exosdevweb.models.PrettyDateTimeModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UrlMapping {
    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    
    @GetMapping("/")
    public String welcome() {
        return "accueil";
    }
    
    @GetMapping("/datetime")
    @ResponseBody
    public String datetime() {
        LOG.info("Requête datetime");
        return "<h1>Bonjour</h1>"
                + "<p>Nous sommes le " + LocalDate.now().
                        format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")) + "</p>"
                + "<p>Il est " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")) + "</p>"
                + "<p>"
                + "<a href=\"/\">Accueil</a>"
                + "</p>";
    }

    @GetMapping("/prettydatetime")
    public String prettydatetime(
            @RequestParam(name="name", 
                    required=false, 
                    defaultValue="cher visiteur") String name, 
            
            @RequestParam(name="age", 
                    required=false, 
                    defaultValue="") String age, 
            
            Model model) {
        model.addAttribute("model", new PrettyDateTimeModel(name, age));        
        return "pdt";
    }
    
    @GetMapping("/inscription")
    public String inscription(Model model) {
        model.addAttribute("person", new Person());        
        return "inscrire";
    }
    
    
    @PostMapping("/inscription")
    public String inscriptionSubmit(@ModelAttribute Person person, Model model) {
        model.addAttribute("datetime", new PrettyDateTimeModel()); 
        return "result";
    }

    
    @GetMapping("/validation")
    public String validation(Model model) {
        model.addAttribute("person", new Person());        
        return "valid";
    }
    
    
    @PostMapping("/validation")
    public String validationSubmit(@Valid Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "valid";
        }
        model.addAttribute("model", new PrettyDateTimeModel()); 
        return "result";  
    }
}
