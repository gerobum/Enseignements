package edu.uha.miage.web.controller;

import edu.uha.miage.core.models.SessionHandler;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/list")
public class ListController {
    @RequestMapping(method = RequestMethod.GET)
    public String home() { 
        return "visited";
    }
    
    @GetMapping("/erase")
    public String erase(HttpSession session) {  
        SessionHandler.clearList(session);
        return "redirect:/list";
    }
}
