package edu.uha.miage.web.controller;

import edu.uha.miage.core.service.CategoryService;
import edu.uha.miage.core.service.CustomerService;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Profile("prod")
@Controller
@RequestMapping("/inscription")
public class InscriptionController {

    private final Logger LOGGER = LoggerFactory.getLogger(InscriptionController.class);

    @Autowired
    CustomerService customerService;

    @Autowired
    CategoryService categoryService;
        
    @RequestMapping(method = RequestMethod.GET)
    public String home(HttpSession session) {
        return "inscription";
    }
}
