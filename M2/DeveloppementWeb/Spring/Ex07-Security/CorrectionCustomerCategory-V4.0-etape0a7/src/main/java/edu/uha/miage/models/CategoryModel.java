// #### V3.3 A kind of model
package edu.uha.miage.models;

import edu.uha.miage.core.entity.Category;
import edu.uha.miage.core.service.CategoryService;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
// #### V3.3 We can use such a model (CategoryModel) in order to managed database 
// #### V3.3 exception. But there is a better way by using @ExceptionHandler as 
// #### V3.3 it is done in controllers.
public class CategoryModel {

    public static boolean tryToSave(CategoryService categoryService, Category category, String lang, BindingResult br, MessageSource messageSource) {
        try {
            categoryService.save(category);
            // #### V1.0 If all is good, the customer is stored in the database.
            return true;
        } catch (Exception ex) {
            // #### V3.3 But otherwise, it could be an integrity error 
            if (categoryService.findByName(category.getName()) != null) {
                Locale locale;
                if (lang == null) {
                    locale = Locale.FRENCH;
                } else {
                    locale = new Locale(lang);
                }
                // #### V3.3 Especially, it is already stored
                br.rejectValue("name", "alreadythere", messageSource.getMessage("EXISTANT", null, locale));
                // #### V3.3 or something else
            } else {
                br.rejectValue("name", "error", "Erreur");
            }
            return false;
        }
    }

    public static boolean tryToDelete(CategoryService categoryService, Long id, String lang, MessageSource messageSource) {
        try {
            categoryService.delete(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
