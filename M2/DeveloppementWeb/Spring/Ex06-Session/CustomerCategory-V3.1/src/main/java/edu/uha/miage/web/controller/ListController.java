package edu.uha.miage.web.controller;

import edu.uha.miage.core.models.SessionHandler;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/list")
// #### V3.1 Indique que "stringList" est une variable de session de type liste
//@SessionAttributes(names = "stringList", types= List.class)
public class ListController {
    @RequestMapping(method = RequestMethod.GET)
    public String home() { 
        return "visited";
    }
    
    // #### V3.0 Il suffit d'ajouter un paramètre de type HttpSession pour récupérer la session
    @GetMapping("/erase")
    public String erase(HttpSession session) {  
        // #### V3.0 Effacement de la liste
        SessionHandler.clearList(session);
        return "redirect:/list";
    }
}
