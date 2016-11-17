
package interfaces;

/**
 *
 * @author maillot
 */
public interface I12 extends I1, I2 {
    @Override
    // Les deux méthodes par défauts définies dans I1 et I2 deviennent abstraites.
    String md();
    // La méthode par défaut md1() définies dans I1 est inchangée. I12 en hérite.
    // ....
    
    // La méthode par défaut md2() définies dans I2 est redéfinie dans I12.
    @Override
    default String md2() {
        return "defaut I2 dans I12";
    }
}
