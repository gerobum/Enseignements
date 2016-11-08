package demos_du_cours.exemple4;

/**
 *
 * @author maillot
 */
public @interface Version {
    int majeur() default 1;
    int mineur() default 0;
}
