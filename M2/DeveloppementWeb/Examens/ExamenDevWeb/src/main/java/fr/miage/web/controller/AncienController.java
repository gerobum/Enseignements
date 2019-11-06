/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package fr.miage.web.controller;

import fr.miage.core.entity.Ancien;
import fr.miage.core.metier.AncienModel;
import fr.miage.core.repository.AncienRepository;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yvan
 */
@Controller
@RequestMapping
public class AncienController {
    
    @Autowired
    private AncienRepository dao;

    private Logger LOGGER = LoggerFactory.getLogger(AncienController.class);

    @GetMapping("/anciens")
    public String calcul(Model model) {
        model.addAttribute("anciens", dao.findAll());
        return "anciens/list";
    }
    @GetMapping("/anciens/ajout")
    public String ajout(Model model) {
        model.addAttribute("ancien", new AncienModel());
        return "anciens/edit";
    }
    @PostMapping("/anciens/ajout")
    public String ajoute(@Valid AncienModel ancienModel, Model model, BindingResult br) {
    
        dao.save(ancienModel.getAncien());
        //System.out.println(ancien);
        return "redirect:/anciens";
    }

    /*@PostMapping("/calcul")
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
    }*/
}
