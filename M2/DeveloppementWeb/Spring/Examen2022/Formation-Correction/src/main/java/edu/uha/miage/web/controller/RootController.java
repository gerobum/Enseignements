
package edu.uha.miage.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedHashMap;
import java.util.Map;


@Controller
@RequestMapping()
public class RootController {

    private static Map<String, String> urlName = new LinkedHashMap<>();

    static {
        urlName.put("/enseignant", "Les enseignants");
        urlName.put("/cours", "Les cours");
        urlName.put("/enseignant/add", "Ajout");
    }

    /*
     * À modifier
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("urls", urlName);
        return "home";
    }
}
