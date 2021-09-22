
package edu.uha.miage.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// #### V0.0 RootController permet de gérer les appels à l'url /
@RestController
@RequestMapping("/api")
public class RootRestController {
    @RequestMapping
    public String home() {       
        return "home";
    }
}
