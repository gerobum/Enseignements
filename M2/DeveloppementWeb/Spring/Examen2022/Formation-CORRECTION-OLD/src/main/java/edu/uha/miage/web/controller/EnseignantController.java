package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Enseignant;
import edu.uha.miage.core.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/enseignant")
public class EnseignantController {
    @Autowired
    private EnseignantRepository enseignantRepository;

    @GetMapping({"", "/"})
    public String find(Model model) {
        model.addAttribute("enseignants", enseignantRepository.findAll());
        return "enseignant/list";
    }
    @GetMapping({"/add/", "/add"})
    public String add(Model model) {
        model.addAttribute("enseignant", new Enseignant());
        return "enseignant/add";
    }
    @PostMapping({"/add/", "/add"})
    public String added(@Valid Enseignant enseignant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "enseignant/add";
        }
        enseignantRepository.save(enseignant);
        return "redirect:/enseignant";
    }

}
