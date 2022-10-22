// #### V2.2 Une sorte de modèle pour gérer les erreurs
package edu.uha.miage.core.models;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.service.CategoryService;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
// #### V2.2
// On peut utiliser ce genre de modèle (CategoryModel) pour gérer les exceptions
// de la BDD. C'est une solution.
//
// Il y en a d'autres (préférables parce qu'intégrés) à l'aide du tag 
// @ExceptionHandler comme c'est montré aussi dans le contrôleur de customer.
public class CategoryModel {

    public static boolean tryToSave(CategoryService categoryService, Category category, String lang, BindingResult br, MessageSource messageSource) {
        try {
            categoryService.save(category);
            return true;
        } catch (Exception ex) {
            Locale locale;
            if (lang == null) {
                locale = Locale.FRENCH;
            } else {
                locale = new Locale(lang);
            }
            if (categoryService.findByName(category.getName()) != null) {
                br.rejectValue("name", "error", messageSource.getMessage("EXISTANT", null, locale));
            } else {
                br.rejectValue("name", "error", messageSource.getMessage("AJOUT IMPOSSIBLE", null, locale));
                
            }
            return false;
        }
    }

    public static boolean tryToDelete(CategoryService categoryService, Long id, String lang, BindingResult br, MessageSource messageSource) {
        try {
            categoryService.delete(id);
            return true;
        } catch (Exception ex) {
            Locale locale;
            if (lang == null) {
                locale = Locale.FRENCH;
            } else {
                locale = new Locale(lang);
            }
                br.rejectValue("name", "error", messageSource.getMessage("EXISTANT", null, locale));
    
            return false;
        }
    }
}
