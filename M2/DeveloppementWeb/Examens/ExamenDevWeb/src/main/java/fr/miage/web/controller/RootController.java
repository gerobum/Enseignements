
package fr.miage.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Créer un contrôleur pour la racine (localhost:6789/) afin que la page affiche
 * quelque chose comme ci-dessous SANS PASSER PAR UN TEMPLATE (même si c'est mal)
 * 
 * Bienvenue 
 *     1. Pour faire des calculs (/calcul) 
       2. Pour gérer les anciens (/anciens)
 * @author yvan
 */
@Controller
@RequestMapping()
public class RootController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String home() {       
        return    "<html>"
                + "<head>"
                + "</head>"
                + "<body>"
                + "<h1>Bienvenue</h1>"
                + "<ol>"
                + "<li><a href=\"/calcul\">Pour faire des calculs (/calcul)</a></li>"
                + "<li><a href=\"/anciens\">Pour gérer les anciens (/anciens)</a></li>"
                + "</ol>"
                + "</body>"
                + "</html>";
    }
}
