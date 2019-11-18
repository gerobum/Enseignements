package fr.miage.introiocavecspring.interfaces.impl;

import fr.miage.introiocavecspring.interfaces.I;
import org.springframework.stereotype.Component;

@Component("b2")
public class B2 implements I{

    @Override
    public void go() {
        System.out.println("V2");
    }

    @Override
    public void setI(I i) {
        
    }
    
}
