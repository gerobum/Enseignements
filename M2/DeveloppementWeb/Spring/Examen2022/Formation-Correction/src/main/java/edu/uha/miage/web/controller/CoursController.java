
package edu.uha.miage.web.controller;

import edu.uha.miage.dao.CoursRepo;
import edu.uha.miage.dao.EnseignantRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(path = "/cours")
public class CoursController {


    @Autowired
    private CoursRepo repo;

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("courses", repo.findAll());
        return "cours/list";
    }
}
