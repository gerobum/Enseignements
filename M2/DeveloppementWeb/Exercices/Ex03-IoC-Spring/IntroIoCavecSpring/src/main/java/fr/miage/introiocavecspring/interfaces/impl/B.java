/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.introiocavecspring.interfaces.impl;

import fr.miage.introiocavecspring.interfaces.I;
import org.springframework.stereotype.Component;

/**
 *
 * @author yvan
 */
@Component
public class B implements I {

    @Override
    public void go() {
        System.out.println("V1");
    }

    @Override
    public void setI(I i) {
        
    }
}
