/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package vocabulaire;

import tags.CheckModifier;
import tags.ToCheck;

/**
 *
 * @author yvan
 */
@ToCheck(modifiers = {CheckModifier.isAbstract, CheckModifier.isFinal, CheckModifier.isPublic})
public class D extends C {
    private double d;
    
    @Override
    public String quiSuisJe() {
        return "D";
    }
    
    @Override
    public String quiSuisEtdOuViensJe() {
        return  "D -> " + super.quiSuisEtdOuViensJe();
    }
}
