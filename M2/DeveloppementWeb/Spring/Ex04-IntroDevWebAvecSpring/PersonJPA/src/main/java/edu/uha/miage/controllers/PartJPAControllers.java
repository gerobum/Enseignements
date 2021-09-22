/*
 * Copyright (C) 2019 Yvan Maillot <yvan.maillot@uha.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.uha.miage.controllers;

import edu.uha.miage.metier.FilterForm;
import edu.uha.miage.metier.Person;
import edu.uha.miage.metier.interfaces.PersonService;
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
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
@Controller
public class PartJPAControllers {

    Logger logger = LoggerFactory.getLogger(PartJPAControllers.class);
    
    @Autowired
    private PersonService personService;

    @RequestMapping("/person/list")
    @ResponseBody
    public String listPersonPage() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("---------------------------------<br>");
            sb.append("         Tout le monde <br>");
            sb.append("---------------------------------<br>");
            for (Person person : personService.findAll()  /*personService.findAllOrderByAge()*/) {
                sb.append(person.toString()).append("<br>");
            }
            sb.append("---------------------------------<br>");
            return sb.toString();
        } catch (Exception ex) {
            //ex.get
            return "Erreur : " + ex.getMessage();
        }
    }

    @GetMapping("/person/add")
    public String addPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";
    }
    @PostMapping("/person/add")
    public String newPersonne(@Valid Person person, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            logger.info(bindingResult.toString());
            return "add-person";
        }
        personService.save(person);
        return "redirect:/person/list";
    }
    
    
    @GetMapping("/person/filter")
    public String filterListPersonPage(Model model) {
        model.addAttribute("filterForm", new FilterForm());
        return "filter-form";
    }
    
    @PostMapping("/person/filter")
    public String filtredListPersonPage(@Valid FilterForm filterForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            logger.info("Erreur "+bindingResult.toString());
            return "filter-form";
        }
        model.addAttribute("persons", personService.findByNomLikeAndAgeBetween(filterForm.getNom(), filterForm.getAgeMin(), filterForm.getAgeMax()));
        for(Person p : personService.findByNomLikeAndAgeBetween(filterForm.getNom(), filterForm.getAgeMin(), filterForm.getAgeMax())) 
            logger.info(p.toString());
        return "filtred-list";
    }
    
}
