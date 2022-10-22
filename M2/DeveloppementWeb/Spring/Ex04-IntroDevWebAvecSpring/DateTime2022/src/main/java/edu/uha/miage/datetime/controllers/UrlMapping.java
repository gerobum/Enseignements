package edu.uha.miage.datetime.controllers;/*
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

import edu.uha.miage.datetime.models.InscriptionModel;
import edu.uha.miage.datetime.models.ValidationModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * UrlMapping : une classe contrôleur (@Controller)
 */
@Controller
public class UrlMapping {
    // Utile par exemple pour afficher des messages dans la console afin de déboguer
    Logger logger = LoggerFactory.getLogger(UrlMapping.class);

    /**
     * Correspond au "1.2.1 UrlMapping" du fascicule intro-spring-fascicule.pdf
     *
     * Grâce à @GetMapping, associe aux url "/DateTime", "/datetime", "/dt" le comportement suivant :
     * Écrire dans le corps de la réponse HTTP (grâce à @ResponseBody) la chaîne retournée.
     * @return le texte à écrire dans le corps de la réponse HTTP
     */
    @GetMapping({"/DateTime", "/datetime", "/dt"})
    @ResponseBody
    public String datetime() {
        // Juste pour voir un usage de logger
        logger.info("DateTime => Get");
        return "<h1>Bonjour</h1>" +
                "<p>Nous sommes le " + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)+"</p>" +
                "<p>Il est " + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)+ "</p>";
    }

    /**
     * Correspond au "2. Utilisation d'un template avec thymeleaf" du fascicule intro-spring-fascicule.pdf
     *
     * Nécessite la dépendance thymeleaf dans pom.xml
     *
     * Grâce à @GetMapping, associe aux url "/PrettyDateTime", "/prettydatetime", "/pdt" le comportement suivant :
     * Appeler le template "resource/templates/pdt.html" (parce que return "pdt") en ayant au préalable ajouter
     * les variables name, age, date, time grâce à model.addAttribute(...)
     *
     * Les paramètres de la méthode sont interprétés par Spring :
     * @RequestParam(name = "name", required = false, defaultValue = "tout le monde") String name
     * signifie que name est un paramètre éventuel de l'url /pdt?name=MIAGE qui est extrait automatiquement
     * s'il est présent ou remplacé par "tout le monde" sinon.
     *
     * C'est identique pour le paramètre age. Remarquez que age aurait pu être déclaré entier. Mais on ne préfère pas
     * car si le paramètre ne se convertit pas, il y aurait une erreur (essayez pour voir).
     *
     * Par ailleurs, l'annotation @RequestParam est optionnelle.
     *
     * Enfin le paramètre sert à passer des variables au template
     *
     * Tout ça est expliqué dans le lien suivant :
     *
     * <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments">Doc à lire absolutemnt</a>
     *
     * @return le nom de base du template voulu
     */
    @GetMapping({"/PrettyDateTime", "/prettydatetime", "/pdt"})
    public String prettydatetime(
            @RequestParam(name = "name", required = false, defaultValue = "tout le monde") String name,
            @RequestParam(name = "age", required = false, defaultValue = "0") int age,
            Model model) {
        logger.info("PrettyDateTime => Get");
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")));
        model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("H:mm")));
        return "pdt";
    }

    /**
     * Correspond à l'exercice 3 du fascicule intro-spring-fascicule.pdf
     *
     * Ce qui change dans le contrôleur par rapport au précédent c'est
     * 1. l'utilisation du modèle InscriptionModel.java (à voir)
     * 2. un template inscription qui utilise un formulaire (à voir)
     */
    @GetMapping({"/inscription"})
    public String inscription(Model model) {
        // Un modèle d'inscription vierge est envoyé à la vue
        model.addAttribute("inscriptionModel", new InscriptionModel());
        return "inscription";
    }

    /**
     * L'appui sur bouton Submit du formulaire appelé précédemment envoie une requête Post ici
     *
     * Grâce à l'annotation @ModelAttribute, le paramètre inscriptionModel est récupéré depuis le formulaire où il a
     * été rempli.
     *
     * Il est envoyé par le model à un autre template (le template inscrit.html)
     *
     * Remarque : l'annotation @ModelAttribute est optionnel.
     *
     * @param inscriptionModel pour récupérer le modèle rempli dans le formulaire
     * @return le nom de base du template voulu
     */
    @PostMapping({"/inscription"})
    public String inscrit(@ModelAttribute InscriptionModel inscriptionModel) {
        return "inscrit";
    }

    /**
     * Correspond à l'exercice 4 du fascicule intro-spring-fascicule.pdf
     *
     * Nécessite la dépendance hibernate-validator dans pom.xml
     *
     * La chose nouvelle est le modèle.
     */
    @GetMapping({"/validation"})
    public String validation(Model model) {
        logger.info("GET => /validation");
        model.addAttribute("validationModel", new ValidationModel());
        return "validation";
    }
    @PostMapping({"/validation"})
    public String validated(@Valid ValidationModel validationModel, BindingResult bindingResult, Model model) {
        logger.info("POST => /validation");
        if (bindingResult.hasErrors())
            return "validation";
        model.addAttribute("inscriptionModel", validationModel.getPerson());
        return "inscrit";
    }
}
