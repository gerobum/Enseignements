package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.service.CategoryService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/category")
public class CategoryController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        LOGGER.info("HTTP/GET - /category");
        LOGGER.info("TEMPLATE - category/list.html");
        return "category/list";
    }
    
    @GetMapping("/create")
    public String create(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        LOGGER.info("HTTP/GET - /category/create");
        LOGGER.info("TEMPLATE - category/edit.html");
        return "category/edit";
    }
    
    @PostMapping("/create")
    public String created(@Valid Category category, BindingResult br) {
        if (br.hasErrors()) {
            return "category/edit";
        }
        categoryService.save(category);
        return "redirect:/category";
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
    }
}
