
package edu.uha.miage.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author yvan
 */
@Controller
public class AnErrorController implements ErrorController{    
    
    @RequestMapping("/error")
    @ResponseBody
    public String handleError() {
        //do something like logging
        return "<h1>Erreur : page absente</h1>";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
    
}
