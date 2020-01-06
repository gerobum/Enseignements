
package edu.uha.miage.web.controller;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// #### V3.0
@Controller
@RequestMapping("/list")
public class ListController {
    @RequestMapping(method = RequestMethod.GET)
    public String home() {      
        return "visited";
    }
    
    @GetMapping("/erase")
    public String erase(HttpSession session) {  
            
        session.setMaxInactiveInterval(10000);
        List<String> list = (List<String>) session.getAttribute("list");
        if (list == null) {
            list = new LinkedList<>();
            session.setAttribute("list", list);
        }
        list.clear();
        return "visited";
    }
}
