
package edu.uha.miage.web.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SecurityController {

    @GetMapping(path="/access-denied")
    public String deny() {
        return "access-denied.html";
    }
}
