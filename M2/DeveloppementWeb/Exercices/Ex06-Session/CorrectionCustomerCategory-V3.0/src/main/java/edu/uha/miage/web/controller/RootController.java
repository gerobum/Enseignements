
package edu.uha.miage.web.controller;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// #### V0.0 RootController permet de gérer les appels à l'url /
@Controller
@RequestMapping()
public class RootController {
    @RequestMapping(method = RequestMethod.GET)
// #### V0.0 Une requête HTTP/GET sur / utilise le template src/main/resources/templates/home.html
    public String home(HttpSession session) {   
        session.setMaxInactiveInterval(10000);
        List<String> list = (List<String>) session.getAttribute("list");
        if (list == null) {
            list = new LinkedList<>();
            session.setAttribute("list", list);
        }
        list.add("/");
        return "home";
    }
}
