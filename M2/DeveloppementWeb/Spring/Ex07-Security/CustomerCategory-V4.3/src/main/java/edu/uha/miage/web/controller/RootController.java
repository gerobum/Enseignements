
package edu.uha.miage.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping()
public class RootController {
    @RequestMapping(method = RequestMethod.GET)
    public String home() {       
        return "home";
    }

    @GetMapping(path="access-denied")
    public String deny() {
        return "access-denied";
    }


}
