package edu.uha.miage.web.controller;

import edu.uha.miage.core.repository.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cours")
public class CoursController {
    @Autowired
    private CoursRepository coursRepository;

    @GetMapping({"", "/"})
    public String findByCategory(Model model) {
        model.addAttribute("courses", coursRepository.findAll());
        return "cours/list";
    }

}
