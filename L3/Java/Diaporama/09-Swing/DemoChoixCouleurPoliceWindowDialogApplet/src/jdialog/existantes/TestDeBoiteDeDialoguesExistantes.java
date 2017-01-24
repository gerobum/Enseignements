/*
 * TestDeBoiteDeDialoguesExistantes.java
 */
package jdialog.existantes;

import java.awt.Color;
import java.io.File;
import javafx.stage.FileChooser;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Yvan
 */
public class TestDeBoiteDeDialoguesExistantes {

    public TestDeBoiteDeDialoguesExistantes() {
    }

    public static void main(String[] args) {
        if (JOptionPane.showConfirmDialog(null, "Confirmation", "Titre", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "OUI", "Vous avez dit", JOptionPane.OK_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "NON", "Vous avez dit", JOptionPane.OK_OPTION);
        }

        String réponse = JOptionPane.showInputDialog("Entrez un message");
        if (réponse == null) {
            JOptionPane.showMessageDialog(null, "Annulation", "Message saisi", JOptionPane.OK_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "Vous avez écrit : " + réponse, "Message saisi", JOptionPane.OK_OPTION);
        }

        Color couleur = JColorChooser.showDialog(null, "titre", Color.BLACK);

        if (couleur == null) {
            JOptionPane.showMessageDialog(null, "Annulation", "Couleur saisie", JOptionPane.OK_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "<html><font color=rgb(" + couleur.getRed() + "," + couleur.getGreen() + "," + couleur.getBlue() + ")>Vous avez choisi : " + couleur + "</font></html>", "Couleur saisie", JOptionPane.OK_OPTION);
        }

        JFileChooser filechooser;
        filechooser = new JFileChooser();

        if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(null, "Vous avez choisi : " + filechooser.getSelectedFile(), "Fichier choisi", JOptionPane.OK_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, "Annulation", "Aucun fichier choisi", JOptionPane.OK_OPTION);
        }
    }
}
