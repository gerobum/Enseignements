package edu.uha.miage.web.controller;

import edu.uha.miage.models.SessionHandler;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// #### V3.0 This controller to manage the page of paths list of a session
@Controller
@RequestMapping("/list")
public class ListController {
    // #### V1.1 LOGGER Pour faire du Log (qui s'applique à cette classe) 
    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

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
