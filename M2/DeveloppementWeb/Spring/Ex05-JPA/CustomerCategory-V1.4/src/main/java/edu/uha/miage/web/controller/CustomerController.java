package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Customer;
import edu.uha.miage.core.service.CategoryService;
import edu.uha.miage.core.service.CustomerService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    
// #### V1.4 Pour pouvoir envoyer la liste de catégories dans certaines vues.  
// ######### Injection de dépendance (en l'occurrence CategoryServiceImpl)
    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customer/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
// #### V1.4 Pour pouvoir définir ou changer la catégorie d'un client, il faut
// ######### envoyer toutes la liste.
        model.addAttribute("categories", categoryService.findAll());
        return "customer/edit";
    }

    @PostMapping("/create")
    public String created(@Valid Customer customer, BindingResult br, Model model) {
       if (br.hasErrors()) {

// #### V1.4 On constate qu'en cas d'erreur, la liste de catégories envoyée 
// ######### initialement a disparu. Il faut la recréer.
            model.addAttribute("categories", categoryService.findAll());
            return "customer/edit";
        }
        customerService.save(customer);

        return "redirect:/customer";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id).get());
// #### V1.4 Pour pouvoir définir ou changer la catégorie d'un client, il faut
// ######### envoyer toutes la liste.
        model.addAttribute("categories", categoryService.findAll());
        return "customer/edit";
    }

    @PostMapping("/edit")
    public String edited(@Valid Customer customer, BindingResult br, Model model) {
        if (br.hasErrors()) {
// #### V1.4 On constate qu'en cas d'erreur, la liste de catégories envoyée 
// ######### initialement a disparu. Il faut la recréer.
            model.addAttribute("categories", categoryService.findAll());
            return "customer/edit";
        }
        customerService.save(customer);
        return "redirect:/customer";
    }

   @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return "redirect:/customer";
    }
}
