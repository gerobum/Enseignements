package fr.miage.introiocavecspring.interfaces.impl;

import fr.miage.introiocavecspring.interfaces.I;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("a")
public class A implements I {
    @Autowired
    @Qualifier("b2")
    private I i;
    public A() {
    }
    @Override
    public void go() {
        i.go();
    }

    @Override
    public void setI(I i) {
        this.i = i;
    }
}
