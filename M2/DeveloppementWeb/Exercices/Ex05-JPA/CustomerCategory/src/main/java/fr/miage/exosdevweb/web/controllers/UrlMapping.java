package fr.miage.exosdevweb.web.controllers;

import fr.miage.exosdevweb.Application;
import fr.miage.exosdevweb.core.Customer;
import fr.miage.exosdevweb.core.FilterForm;
import fr.miage.exosdevweb.core.Person;
import fr.miage.exosdevweb.core.interfaces.CustomerService;
import fr.miage.exosdevweb.core.interfaces.PersonService;
import fr.miage.exosdevweb.dao.PersonDao;
import java.time.LocalDate;
import java.time.LocalTime;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author yvan
 */
@Controller
public class UrlMapping {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PersonService personService;
    // Ca marche sans problème si on saute la couche service. 
    // À voir avec Stéphane
    //@Autowired
    //private PersonDao personService;

    @GetMapping("/datetime")
    @ResponseBody
    public String helloInBody() {
        return "<h1>Bonjour</h1><p>Nous sommes le " + LocalDate.now() + ".<p></p>Il est " + LocalTime.now() + "</p>";
    }

    @GetMapping("/prettydatetime")
    public String helloInPretty(@RequestParam(name = "name", required = false, defaultValue = " cher visiteur") String name, @RequestParam(name = "age", required = false) String age, Model model) {
        model.addAttribute("name", name);
        if (age != null) {
            try {
                age = Integer.parseInt(age) + "";
            } catch (NumberFormatException ex) {
                age = null;
            }
        }
        model.addAttribute("age", age);
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("time", LocalTime.now());
        return "prettydatetime";
    }

    @GetMapping("/inscription")
    public String inscription(Model model) {
        model.addAttribute("person", new Person());
        return "inscription";
    }

    @PostMapping("/inscription")
    public String inscrit(@ModelAttribute Person person, Model model) {
        //model.addAttribute("date", LocalDate.now());
        //model.addAttribute("time", LocalTime.now());
        //return "result";
        return String.format("redirect:/prettydatetime?name=%s&age=%d", person.getNom(), person.getAge());
    }

    @GetMapping("/validation")
    public String validation(Model model) {
        model.addAttribute("person", new Person());
        return "validation";
    }

    @PostMapping("/validation")
    public String tovalidate(@Valid Person person, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.info(bindingResult.toString());
            return "validation";
        }
        log.info(String.format("redirect:/prettydatetime?name=%s&age=%d", person.getNom(), person.getAge()));
        return String.format("redirect:/prettydatetime?name=%s&age=%d", person.getNom(), person.getAge());
    }


    /*@RequestMapping("/error")
    public String error() {
        return "error";
    }*/
    @GetMapping("/")
    public String accueil() {
        return "accueil";
    }


    @RequestMapping("/customer/add")
    @ResponseBody
    public String addCustomerPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-customer-form";
    }
    @RequestMapping("/customer/list")
    @ResponseBody
    public String listCustomerPage() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("---------------------------<br>");
            for (Customer customer : customerService.findAll()) {
                sb.append(customer.toString()).append("<br>");
            }
            sb.append("---------------------------<br>");
            for (Customer customer : customerService.findByLastName("Bauer")) {
                sb.append(customer.toString()).append("<br>");
            }
            sb.append("---------------------------<br>");
            sb.append(customerService.findById(3L));
            sb.append("---------------------------<br>");
            return sb.toString();
        } catch (Exception ex) {
            return "Erreur : " + ex.getMessage() + " - " + ex.getClass();
        }
    }
    @RequestMapping("/person/list")
    @ResponseBody
    public String listPersonPage() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("---------------------------------<br>");
            sb.append("         Tout le monde <br>");
            sb.append("---------------------------------<br>");
            for (Person person : personService.findAll()) {
                sb.append(person.toString()).append("<br>");
            }

            /*sb.append("---------------------------<br>");
            for (Person person : personService.findByNom("Jack")) {
                sb.append(person.toString()).append("<br>");
            }

            sb.append("---------------------------<br>");
            for (Person person : personService.findByAge(20)) {
                sb.append(person.toString()).append("<br>");
            }
            sb.append("---------------------------<br>");
            /*sb.append(customerService.findById(1L));
            sb.append("---------------------------<br>");*/
            sb.append("---------------------------------<br>");
            return sb.toString();
        } catch (Exception ex) {
            //ex.get
            return "Erreur : " + ex.getMessage();
        }
    }

    @GetMapping("/person/add")
    public String addPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";
    }
    @PostMapping("/person/add")
    public String newPersonne(@Valid Person person, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.info(bindingResult.toString());
            return "add-person";
        }
        personService.save(person);
        return "redirect:/person/list";
    }
    
    
    @GetMapping("/person/filter")
    public String filterListPersonPage(Model model) {
        model.addAttribute("filterForm", new FilterForm());
        return "filter-form";
    }
    // Je n'ai pas réussi à faire autrement que de passer un objet de type FilterForm... A voir avec Stéphane.
    @PostMapping("/person/filter")
    public String filtredListPersonPage(@Valid FilterForm filterForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("Erreur "+bindingResult.toString());
            return "filter-form";
        }
        model.addAttribute("persons", personService.findByNomLikeAndAgeBetween(filterForm.getNom(), filterForm.getAgeMin(), filterForm.getAgeMax()));
        for(Person p : personService.findByNomLikeAndAgeBetween(filterForm.getNom(), filterForm.getAgeMin(), filterForm.getAgeMax())) 
            log.info(p.toString());
        return "filtred-list";
    }
}
