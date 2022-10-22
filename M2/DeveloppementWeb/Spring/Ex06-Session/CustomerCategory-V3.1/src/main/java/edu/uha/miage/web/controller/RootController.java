
package edu.uha.miage.web.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping()
// #### V3.1 Indique que "stringList" est une variable de session de type liste
//@SessionAttributes(names = "stringList", types= List.class)
public class RootController {
    @RequestMapping(method = RequestMethod.GET)
    public String home() {       
        return "home";
    }
}
