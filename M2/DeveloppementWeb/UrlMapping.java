package edu.uha.miage.web.controllers;

/*
 * Creative commons CC BY-NC-SA 2021 Yvan Maillot <yvan.maillot@uha.fr>
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import edu.uha.miage.models.PersonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class UrlMapping {
    
    private Logger logger = LoggerFactory.getLogger(UrlMapping.class);

    @GetMapping({"/datetime", "/dt"})
    @ResponseBody
    public String datetime() {
        logger.info("date time");
        return "<h1>Bonjour</h1>" + "<p>Nous sommes le " + LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")) + 
                "</p>" + "<p>Il est " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")) + "</p>";
    }
    
    @GetMapping({"/prettydatetime", "/pdt"})
    public String prettydate(Model model,
                             String nom,
                             String age
    ) {
        logger.info("pretty date time : " + nom + " : " + age);
        PersonModel personModel = new PersonModel();
        personModel.setAge(age);
        personModel.setNom(nom);
        logger.info("pretty date time : " + personModel);
        model.addAttribute("personModel", personModel);
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")));
        model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        return "pdt";
    }
    
    @GetMapping({"/inscription"})
    public String inscription(Model model) {
        model.addAttribute("personModel", new PersonModel());
        return "inscription";
    }
    
    @PostMapping({"/inscription"})
    public String inscrit(@ModelAttribute PersonModel personModel, Model model) {
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")));
        model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        return "pdt";
    }

    @GetMapping({"/validation"})
    public String validation(Model model) {
        model.addAttribute("personModel", new PersonModel());
        return "inscription";
    }

    // #### 04 :
    // #### 04 : https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments
    @PostMapping({"/validation"})
    public String valide(Model model, @Valid PersonModel personModel, BindingResult result) {
        if (result.hasErrors()) {
            return "inscription";
        }
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")));
        model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        return "pdt";
    }
    
}
