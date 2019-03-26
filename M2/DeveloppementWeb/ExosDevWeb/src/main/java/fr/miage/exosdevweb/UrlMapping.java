package fr.miage.exosdevweb;

import fr.miage.exosdevweb.dao.PersonDAO;
import fr.miage.exosdevweb.models.Person;
import fr.miage.exosdevweb.models.PrettyDateTimeModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class UrlMapping {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    @Autowired
    private PersonDAO dao;

    @GetMapping("/datetime")
    @ResponseBody
    public String datetime() {
        return "<h1>Bonjour</h1>"
                + "<p>Nous sommes le " + LocalDate.now().
                        format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")) + "</p >"
                + "<p>Il est " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")) + "</p >";
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
    public String greetingSubmit(@ModelAttribute Person person, Model model) {
        model.addAttribute("model", new PrettyDateTimeModel()); 
        return "result";
    }

    
    @GetMapping("/validation")
    public String validation(Model model) {
        model.addAttribute("person", new Person());        
        return "valid";
    }
    
    
    @PostMapping("/validation")
    public String validationSubmit(@Valid Person person, BindingResult bindingResult, Model model) {
        model.addAttribute("model", new PrettyDateTimeModel()); 
        if (bindingResult.hasErrors()) {
            return "valid";
        }
        return "result";  
    }
    
    @GetMapping("/list")
    public String list(Model model) {
        List<Person> list = dao.findAll();
        model.addAttribute("list", list);        
        return "list";
    }
    
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("person", new Person());        
        return "inscrire";
    }
    
    @PostMapping("/add")
    public String added(@ModelAttribute Person person, Model model) {
        dao.save(person);
        return "redirect:/list";
    }

}
