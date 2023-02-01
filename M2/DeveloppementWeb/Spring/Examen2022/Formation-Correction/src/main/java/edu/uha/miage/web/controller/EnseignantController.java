
package edu.uha.miage.web.controller;

import edu.uha.miage.dao.EnseignantRepo;
import edu.uha.miage.web.core.entity.Enseignant;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping(path = "/enseignant")
public class EnseignantController {


    @Autowired
    private EnseignantRepo repo;

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("enseignants", repo.findAll());
        return "enseignant/list";
    }
    
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("enseignant", new Enseignant());
        return "enseignant/add";
    }
    
    @PostMapping("/add")
    public String add(@Valid Enseignant enseignant, BindingResult br) {
        if (br.hasErrors()) {
            return "enseignant/add";
        }
        repo.save(enseignant);
        return "redirect:/enseignant";
    }
}
