package edu.uha.miage.web.controller;

import edu.uha.miage.models.SessionHandler;
import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.entity.Customer;
import edu.uha.miage.core.service.CategoryService;
import edu.uha.miage.core.service.CustomerService;
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

@RequestMapping("/customer")
@SessionAttributes(names = "stringList", types = List.class)
public class CustomerController {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(path = {"", "/{categoryname}"}, method = RequestMethod.GET)

    public String findAll(
            @PathVariable(required = false, name = "categoryname") String categoryname,
            Model model, HttpServletRequest request, List<String> stringList) {

        SessionHandler.addToStringList(stringList, request);
        //SessionHandler.addPathToList(session, "/customer");
        if (categoryname == null) {
            model.addAttribute("customers", customerService.findAll());
        } else {
            Category category = categoryService.findByName(categoryname);

            if (category == null) {
                model.addAttribute("customers", null);
            } else {

                model.addAttribute("customers", customerService.findByCategory(category));

                model.addAttribute("category", category.getName());
            }
        }
        return "customer/list";
    }

    @GetMapping("/create")

    public String create(Model model, HttpSession session) {

        //SessionHandler.addPathToList(session, "/customer/create");
        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        model.addAttribute("categories", categoryService.findAll());

        return "customer/edit";
    }

    @PostMapping("/create")
    public String created(@Valid Customer customer, BindingResult br, Model model) {

        if (br.hasErrors()) {

            model.addAttribute("categories", categoryService.findAll());
            return "customer/edit";
        }

        customerService.save(customer);

        return "redirect:/customer";
    }

    @GetMapping("/edit")

    public String edit(@RequestParam(name = "id") Long id, Model model, HttpSession session) {

        //SessionHandler.addPathToList(session, "/customer/edit?id="+id);
        model.addAttribute("customer", customerService.findById(id).get());

        model.addAttribute("categories", categoryService.findAll());
        return "customer/edit";
    }

    @PostMapping("/edit")
    public String edited(@Valid Customer customer, BindingResult br, Model model) {
        if (br.hasErrors()) {

            model.addAttribute("categories", categoryService.findAll());
            return "customer/edit";
        }

        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("/delete/{id}")

    public String delete(@PathVariable("id") Long id, HttpSession session) {

        //SessionHandler.addPathToList(session, "/customer/delete/"+id);
        customerService.delete(id);
        return "redirect:/customer";
    }
}
