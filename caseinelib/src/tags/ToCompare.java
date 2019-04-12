/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package tags;

/**
 *
 * @author yvan
 */
public @interface ToCompare {
    Class<?> value();
    int priority() default 0;
}
