
package edu.uha.miage.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping()
public class RootController {
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String home() {       
        StringBuilder sb = new StringBuilder();
        
        sb.append("<ol>");
        sb.append("<li>");
        sb.append("<a href='/regexp'>Pour tester les expressions régulières (/regexp)</a>");
        sb.append("</li>");
        sb.append("<li>");
        sb.append("<a href='/groupes'>Pour gérer des groupes d'étudiants (/grp)</a>");
        sb.append("</li>");
        sb.append("</ol>");
        
        return sb.toString();
    }
}
