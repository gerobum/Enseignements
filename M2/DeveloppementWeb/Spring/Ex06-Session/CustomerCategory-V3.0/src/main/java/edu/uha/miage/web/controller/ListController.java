package edu.uha.miage.web.controller;

import edu.uha.miage.core.models.SessionHandler;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// #### V3.0 Un contrôleur pour la liste des pages visitées
@Controller
@RequestMapping("/list")
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
