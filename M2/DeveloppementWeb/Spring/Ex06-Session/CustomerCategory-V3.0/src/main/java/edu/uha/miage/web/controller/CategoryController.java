package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.models.CategoryModel;
import edu.uha.miage.core.models.SessionHandler;
import edu.uha.miage.core.service.CategoryService;
import java.sql.SQLException;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
   
    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model, /* #### V3.0 #### */HttpSession session) {

        // #### V3.0
        //SessionHandler.addPathToList(session, "/category");
        model.addAttribute("categories", categoryService.findAll());

        return "category/list";
    }

    @GetMapping("/create")
    public String create(Model model,  /* #### V3.0 #### */HttpSession session,  /* #### V3.0 #### */HttpServletRequest request) {
        Category category = new Category();
        model.addAttribute("category", category);
        // #### V3.0
        //SessionHandler.addPathToList(session, request.getRequestURI());
        return "category/edit";
    }

    @PostMapping("/create")
    public String created(@Valid Category category, BindingResult br, String lang) {

        if (br.hasErrors()) {
            return "category/edit";
        }
        if (!CategoryModel.tryToSave(categoryService, category, lang, br, messageSource)) {
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
    public String edited(@Valid Category category, BindingResult br, String lang) {
        if (br.hasErrors()) {
            return "category/edit";
        }

        if (!CategoryModel.tryToSave(categoryService, category, lang, br, messageSource)) {
            return "category/edit";
         }

        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, String lang) {

        categoryService.delete(id);
        return "redirect:/category";
    }

    @ExceptionHandler({SQLException.class, DataAccessException.class, DataIntegrityViolationException.class})
    public String databaseError(Exception exception, Model model) {
        
        model.addAttribute("exception", exception);
        
        return "category/databaseerror";
    }

    @ExceptionHandler(Exception.class)
    public String otherError(HttpServletRequest req, Exception exception, Model model) {

        model.addAttribute("exception", exception);

        return "otherError";
    }
}
