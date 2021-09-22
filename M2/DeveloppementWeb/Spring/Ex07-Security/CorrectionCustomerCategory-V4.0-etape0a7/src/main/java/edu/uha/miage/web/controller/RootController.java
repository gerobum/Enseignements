
package edu.uha.miage.web.controller;

import edu.uha.miage.models.SessionHandler;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// #### V0.0 RootController handle l'url /
@Controller
@RequestMapping()
public class RootController {
    @RequestMapping(method = RequestMethod.GET)
    // #### V0.0 Une requête HTTP/GET sur / utilise le template src/main/resources/templates/home.html
    
    // #### V3.0 Just add a HttpSession parameter to get it
    public String home(HttpSession session) { 
        
        // #### V3.0 Store path in the paths list on the session
        SessionHandler.addPathToList(session, "/");
        
        return "home";
    }
}
