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
public class B extends A {
    
    @Override
    public String quiSuisJe() {
        return "B";
    }
    
    @Override
    public String quiSuisEtdOuViensJe() {
        return  "B -> " + super.quiSuisEtdOuViensJe();
    }
}
