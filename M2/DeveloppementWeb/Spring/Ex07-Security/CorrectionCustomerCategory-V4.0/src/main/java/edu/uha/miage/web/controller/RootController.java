
package edu.uha.miage.web.controller;

import edu.uha.miage.models.SessionHandler;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// #### V0.0 RootController permet de gérer les appels à l'url /
@Controller
@RequestMapping()
public class RootController {
    // #### V1.1 LOGGER Pour faire du Log (qui s'applique à cette classe) 
    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    
    @RequestMapping(method = RequestMethod.GET)
    // #### V0.0 Une requête HTTP/GET sur / utilise le template src/main/resources/templates/home.html
    
    // #### V3.0 Just add a HttpSession parameter to get it
    public String home(HttpSession session) { 
        
        // #### V3.0 Store path in the paths list on the session
        SessionHandler.addPathToList(session, "/");
        
        return "home";
    }
    
}
