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
public class C extends B {
    
    @Override
    public String quiSuisJe() {
        return "C";
    }
    
    @Override
    public String quiSuisEtdOuViensJe() {
        return  "C -> " + super.quiSuisEtdOuViensJe();
    }
}
