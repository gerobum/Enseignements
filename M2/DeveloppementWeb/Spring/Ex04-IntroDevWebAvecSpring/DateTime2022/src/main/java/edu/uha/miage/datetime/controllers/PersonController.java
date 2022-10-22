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

import edu.uha.miage.datetime.data.services.PersonService;
import edu.uha.miage.datetime.models.FilterModel;
import edu.uha.miage.datetime.models.ValidationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping({"", "/"})
    public String accueil() {
        return "person/accueil";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("persons", personService.findAll());
        return "person/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("validationModel", new ValidationModel());
        return "validation";
    }

    @PostMapping("/add")
    public String add(@Valid ValidationModel validationModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "validation";
        personService.save(validationModel.getPerson());
        return "redirect:";
    }

    @GetMapping("/filter")
    public String filter(Model model) {
        model.addAttribute("filterModel", new FilterModel());
        return "person/filter";
    }

    @PostMapping("/filter")
    public String filter(@Valid FilterModel filterModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "person/filter";
        model.addAttribute("persons", personService.findByNameLikeAndAgeBetween(filterModel.getPattern(), filterModel.ageMiniAsInt(), filterModel.ageMaxiAsInt()));
        return "person/list";
    }
}
