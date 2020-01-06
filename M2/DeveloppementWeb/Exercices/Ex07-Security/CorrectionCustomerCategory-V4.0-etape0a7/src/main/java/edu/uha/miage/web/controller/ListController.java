
package edu.uha.miage.web.controller;

import edu.uha.miage.models.SessionHandler;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// #### V3.0 This controller to manage the page of paths list of a session
@Controller
@RequestMapping("/list")
public class ListController {
    @RequestMapping(method = RequestMethod.GET)
    // #### V3.0 Just add a HttpSession parameter to get it
    public String home(HttpSession session) { 
        return "visited";
    }
    
    // #### V3.0 Just add a HttpSession parameter to get it
    @GetMapping("/erase")
    public String erase(HttpSession session) {  
        // #### V3.0 Clearing the paths list of this session
        SessionHandler.clearList(session);
        return "redirect:/list";
    }
}
