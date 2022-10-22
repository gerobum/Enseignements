package edu.uha.miage.web.controller;

import edu.uha.miage.core.Testregexp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/regexp")
public class RegExpController {


    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model) {
        model.addAttribute("testregexp", new Testregexp());
        return "regexp/form";
    }

   
    @RequestMapping(method = RequestMethod.POST)
    public String match(Testregexp testregexp, BindingResult br) {
        System.out.println(testregexp.getRegexp());
        System.out.println(testregexp.getStr());
        testregexp.setTested(true);
        return "regexp/form";
    }
}
