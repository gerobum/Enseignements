/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.

https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.0.2&packaging=jar&jvmVersion=17&groupId=edu.uha.miage&artifactId=DateTime&name=Date%20Time&description=Ma%20premi%C3%A8re%20application%20web%20avec%20Spring&packageName=edu.uha.miage&dependencies=devtools,web,thymeleaf,validation
 */
package edu.uha.miage.web.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UrlMapping {
    Logger logger = LoggerFactory.getLogger(UrlMapping.class);

    @GetMapping({"/datetime", "/dt"})
    @ResponseBody
    public String datetime() {
        logger.info("Requête DT");
        return "<h1>Bonjour</h1>" + "<p>Nous sommes le " + LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu"))
                + "</p>" + "<p>Il est " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")) + "</p>";
    }
    
    @GetMapping({"/prettydatetime", "/pdt"})
    public String prettydate(Model model, String name) {
        logger.info("Requête PDT");
        model.addAttribute("nom", name);
        model.addAttribute("time", LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm")));
        model.addAttribute("date", LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE d MMMM uuuu")));
        return "pdt";
    }
    
    @GetMapping({"/inscription"})
    public String inscription() {
        logger.info("Requête inscription");
        //model.addAttribute()
        return "inscription";
    }
}
