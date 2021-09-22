package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.service.CategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category/list";
    }
    
    @GetMapping("/create")
    public String create(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
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
    public String edit(Model model, String name) {
        Category c = categoryService.findByName(name);
        model.addAttribute("category", categoryService.findByName(name));
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
    
    
    @GetMapping("/delete/{name}")
    public String delete(@PathVariable("name") String name) {
        categoryService.delete(name);
        return "redirect:/category";
    }
}
