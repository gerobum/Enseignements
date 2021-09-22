package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Etudiant;
import edu.uha.miage.core.service.CategoryService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryRestController.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Etudiant> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public Etudiant findACategory(@PathVariable(name = "id", required = false) long id, HttpServletResponse response) {
        try {
            return categoryService.findById(id).get();
        } catch (Exception ex) {
            response.setStatus(400);
            return new Etudiant("X");
        }
    }

    @PutMapping("/categories")
    public String put(@Valid Etudiant etudiant, BindingResult br) {
        if (br.hasErrors()) {
            return br.toString();
        }
        categoryService.save(etudiant);
        return "redirect:/category";
    }

    @PostMapping("/categories")
    public String created(@RequestBody Etudiant etudiant, HttpServletResponse response) {
        try {
            categoryService.save(etudiant);
           return "OK";
        } catch (Exception ex) {
            response.setStatus(400);
            return ex.toString();
        }
    }

    @DeleteMapping("/categories/{id}")
    public String delete(@PathVariable(name = "id", required = false) long id, HttpServletResponse response) {
        try {
            categoryService.delete(id);
            return "OK";
        } catch (Exception ex) {
            response.setStatus(400);
            return ex.toString();
        }
    }

    /*@GetMapping("/create")
    public String create(Model model) {

        Category category = new Category();

        model.addAttribute("category", category);

        LOGGER.info("HTTP/GET - /category/create");

        LOGGER.info("TEMPLATE - category/edit.html");
        return "category/edit";
    }


    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("category", categoryService.findById(id).get());
        return "category/edit";
    }

    @PostMapping("/edit")
    public String edited(@Valid Category category, BindingResult br) {
        if (br.hasErrors()) {
            return "category/edit";
        }
        categoryService.save(category);
        return "redirect:/category";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return "redirect:/category";
    }*/
}
