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

    public SetTextCommand(JTextComponent bouton, String texte) {
        this.bouton = bouton;
        this.texte = texte;
    }

    @Override
    public void execute() {
        bouton.setText(texte);
    }
    
    
}
