package edu.uha.miage.web.controller;

import edu.uha.miage.models.SessionHandler;
import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.service.CategoryService;
import edu.uha.miage.models.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller

@RequestMapping("/category")
@SessionAttributes(names = "stringList", types = List.class)
public class CategoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)

    public String findAll(Model model, HttpServletRequest request, List<String> stringList) {

        // SessionHandler.addPathToList(session, "/category");
        SessionHandler.addToStringList(stringList, request);
        model.addAttribute("categories", categoryService.findAll());

        LOGGER.info("HTTP/GET - /category");

        LOGGER.info("TEMPLATE - category/list.html");
        return "category/list";
    }

    @GetMapping("/create")
    public String create(Model model, HttpSession session) {

        //SessionHandler.addPathToList(session, "/category/create");
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
    public String edit(@RequestParam(name = "id") Long id, Model model, HttpSession session) {

        //SessionHandler.addPathToList(session, "/category/edit?id=" + id);
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

    public String delete(@PathVariable("id") Long id, HttpSession session) {

        //SessionHandler.addPathToList(session, "/category/delete/" + id);
        categoryService.delete(id);
        return "redirect:/category";
    }
}
