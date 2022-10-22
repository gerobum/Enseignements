/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package edu.uha.miage.urlmapping.controllers;

import edu.uha.miage.urlmapping.models.PersonModel;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
@Controller
public class UrlMapping {

    @GetMapping("/")
    public String accueil() {
        return "accueil";
    }

    @GetMapping("/datetime")
    @ResponseBody
    public String datetime() {
        return "<h1>Bonjour</h1>"
                + "<p>Nous sommes le " + LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")) + "</p>"
                + "<p>Il est " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")) + "</p>";
    }

    // #### 02 : https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments
    @GetMapping({"/prettydatetime", "/pdt"})
    public String pdt(Model model, String nom, String age) {
        model.addAttribute("nom", nom);
        model.addAttribute("age", age);
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")));
        model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        // #### 02
        return "pdt";
    }

    // #### 03 : 
    @GetMapping({"/inscription"})
    public String inscription(Model model) {
        model.addAttribute("personModel", new PersonModel());
        return "inscription";
    }
    
    // #### 03 : 
    @PostMapping({"/inscription"})
    public String inscrit(Model model, PersonModel personModel) {
        model.addAttribute("person", personModel.getPerson());
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")));
        model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        return "result";
    }

    /*@PostMapping({"/inscription"})
    public String inscrit(Model model, PersonModel personModel) {
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")));
        model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        return "redirect:pdt?nom="+personModel.getNom()+"&age="+personModel.getAge();
    }*/
    
    // #### 04 : 
    @GetMapping({"/validation"})
    public String validation(Model model) {
        model.addAttribute("personModel", new PersonModel());
        return "inscription";
    }
    
    // #### 04 :
    // #### 04 : https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments
    @PostMapping({"/validation"})
    public String valide(Model model, @Valid PersonModel personModel, Errors result) {
        if (result.hasErrors()) {
            return "inscription";
        }
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")));
        model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        return "redirect:pdt?nom="+personModel.getNom()+"&age="+personModel.getAge();
    }
}
