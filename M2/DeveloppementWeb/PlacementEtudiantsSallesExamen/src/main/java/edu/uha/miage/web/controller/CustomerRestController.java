package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Etudiant;
import edu.uha.miage.core.entity.Groupe;
import edu.uha.miage.core.service.CategoryService;
import edu.uha.miage.core.service.CustomerService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/customers")
    public List<Groupe> findAll() {
        //return customerService.findByCategoryOrderByName(categoryName);
        return customerService.findAll();
    }

    @GetMapping("/customers/{id}")
    public Groupe findACustomer(@PathVariable(name = "id", required = false) Long id) {
        return customerService.findById(id).get();

    }

    @GetMapping("/customers/{id}/category")
    public Etudiant findCategoryOfACustomer(@PathVariable(name = "id", required = false) Long id) {
        return customerService.findById(id).get().getCategory();

    }

    @PatchMapping("/customers")
    public void changeCustomer(@RequestBody Groupe groupe) {
        customerService.save(groupe);
    }

    @PostMapping("/customers")
    public void create(@RequestBody Groupe groupe, HttpServletResponse response) {
        try {
            customerService.save(groupe);
        } catch (Exception ex) {
            response.setStatus(400);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        customerService.delete(id);
    }
}
