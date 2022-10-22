package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.models.CategoryModel;
import edu.uha.miage.core.service.CategoryService;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
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
    
 
    // #### V1.1 LOGGER Pour faire du Log (qui s'applique à cette classe) 
    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;
    
    
    // #### V2.2
    @Autowired
    MessageSource messageSource;

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
    // #### V2.2 Le paramètre sert à l'internationalisation des messages d'erreurs 
    public String created(@Valid Category category, BindingResult br, String lang) {

        if (br.hasErrors()) {
            return "category/edit";
        }
        // #### V2.2 
        // Pour utiliser CategoryModel (mais il y a une solution préférable)
        if (!CategoryModel.tryToSave(categoryService, category, lang, br, messageSource)) {
          return "category/edit";
        }
        // #### V2.2 
        // Sinon, les exceptions sont gérés par @ExceptionHandler
        // categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        
        model.addAttribute("category", categoryService.findById(id).get());
        return "category/edit";
    }

    @PostMapping("/edit")
    // #### V2.2 Le paramètre sert à l'internationalisation des messages d'erreurs 
    public String edited(@Valid Category category, BindingResult br, String lang) {
        if (br.hasErrors()) {
            return "category/edit";
        }


        // #### V2.2 Si utilisation de CategoryModel
        if (!CategoryModel.tryToSave(categoryService, category, lang, br, messageSource)) {
            return "category/edit";
         }
        // #### V2.2 
        // Sinon, les exceptions sont gérés par @ExceptionHandler
        categoryService.save(category);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    // #### V2.2 Le paramètre sert à l'internationalisation des messages d'erreurs 
    public String delete(@PathVariable("id") Long id, String lang) {

        categoryService.delete(id);
        return "redirect:/category";
        // #### V2.2 Les exceptions sont gérés par @ExceptionHandler
    }
    
    // #### V2.2 
    // Les exceptions peuvent être gérées par type
    // https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc#sample-application
    
    // #### V2.2 Database exceptions handling
    // This method is invoked if such Exception are thrown
    @ExceptionHandler({SQLException.class, DataAccessException.class, DataIntegrityViolationException.class})
    public String databaseError(Exception exception, Model model) {
        
        model.addAttribute("exception", exception);
        
        return "category/databaseerror";
    }

    // #### V2.2 Other exceptions handling
    @ExceptionHandler(Exception.class)
    public String otherError(HttpServletRequest req, Exception exception, Model model) {

        model.addAttribute("exception", exception);

        return "otherError";
    }
}
