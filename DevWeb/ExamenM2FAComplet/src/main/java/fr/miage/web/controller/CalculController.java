/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package fr.miage.web.controller;

import fr.miage.core.entity.Operations;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author yvan
 */
@Controller
@RequestMapping
public class CalculController {

    private Logger LOGGER = LoggerFactory.getLogger(CalculController.class);

    @GetMapping("/calcul")
    public String calcul(Model model) {
        LOGGER.info("\n---------------PRE-CALCUL------------------------");
        model.addAttribute("operations", new Operations());
        return "calcul/list";
    }

    @PostMapping("/calcul")
    public String postCalcul(@Valid Operations operations, BindingResult br, Model model) {
        LOGGER.info("\n---------------POST-CALCUL------------------------");
        LOGGER.info("\n " + operations.getSelectedOpId() + " --> " + operations.getOperand());
         if (br.hasErrors()) {
            return "calcul/list";
        }
        try {
            System.out.println("Lance le calcul");
            operations.calculWithId();
            System.out.println("Trouvé : " + operations.getResult());
            model.addAttribute("operations", operations);
        } catch (Exception ex) {
            model.addAttribute("operations", new Operations());
        }
        return "calcul/list";
    }
}
