package demos_du_cours.exemple4;

import java.lang.annotation.Repeatable;

/**
 *
 * @author maillot
 */
@Repeatable(PreambuleClasses.class)
public @interface PreambuleClasse {
    String value(); 
    String auteur() default "maillot";  
    Version version() default @Version;
}
