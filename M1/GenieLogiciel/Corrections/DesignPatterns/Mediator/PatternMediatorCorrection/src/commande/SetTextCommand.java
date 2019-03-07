/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commande;

import javax.swing.AbstractButton;
import javax.swing.text.JTextComponent;

/**
 *
 * @author yvan
 */
public class SetTextCommand implements Command {
    private final JTextComponent bouton;
    private final String texte;
    private final String prec;

    public SetTextCommand(JTextComponent bouton, String texte, String prec) {
        this.bouton = bouton;
        this.texte = texte;
        this.prec = prec;
    }

    @Override
    public void refaire() {
        bouton.setText(texte);
        System.out.println("Refaire " + texte);
    }

    @Override
    public void defaire() {
        bouton.setText(prec);
        System.out.println("Defaire " + prec);
    }
    
    @Override
    public String toString() {
        return String.format("setText(%s)", texte) ;
    }
}
