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
package edu.uha.miage.exosdevweb.controllers;

import edu.uha.miage.exosdevweb.models.FilterModel;
import edu.uha.miage.exosdevweb.dao.PersonRepository;
import edu.uha.miage.exosdevweb.core.Person;
import edu.uha.miage.exosdevweb.core.interfaces.PersonService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
@Controller
@RequestMapping("/person")
public class Repo {
    
    @Autowired
    PersonService repo;
    
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("persons", repo.findAll());
        return "list";
    }
    
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("person", new Person());
        return "valid";
    }
    
    @PostMapping("/add")
    public String addPost(@Valid Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "valid";
        }
        repo.save(person);
        return "redirect:/person/list";
    }
    
    @GetMapping("/filter")
    public String filter(Model model) {
        model.addAttribute("filterModel", new FilterModel());
        return "filter";
    }
    
    @PostMapping("/filter")
    public String filterPost(@Valid FilterModel filterModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "valid";
        }
        model.addAttribute("persons", 
                repo.findByNameLikeAndAgeBetween(
                        filterModel.getNom(), 
                        filterModel.getAgeMini(), 
                        filterModel.getAgeMaxi())
        );
        return "list";
    }
}
