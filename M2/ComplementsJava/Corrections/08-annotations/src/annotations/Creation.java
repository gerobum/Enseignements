package annotations;

import static types.Jour.*;
import static types.Mois.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 7.2 Création et révisions
 * On aimerait qu’une classe puisse être munie d’un préambule de création et 
 * d’autant de préambules de révision qu’elle a subi de révisions.
 * 
 * 1. Créer un type de notation Création à l’image de PreambuleClasse de 
 *    l’exercice précédent sauf qu’il n’y a qu’une seule date (celle de création).
 * 
 * 2. Créer un type de notation Révision à l’image de PreambuleClasse de 
 *    l’exercice précédent sauf qu’il n’y a qu’une seule date (celle de révision).
 * 
 * 3. Faire pour que Révision soit répétable.
 * 
 * 4. Faire que Création et Révision ne s’appliquent qu’à la déclaration de type.
 * 
 * 5. Faire que Création et Révision soient visibles dans le bytecode.
 * 
 * 6. Écrire quelques exemples de classe pour tester les annotations 
 *    Création et Révision.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Creation {

    String auteur() default "Maillot";

    Date date() default @Date(j=_1, m=JANVIER, a=1);

    Version version() default @Version; // Un type d’annotation

}
