/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package selecteur;

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

/**
 *
 * @author yvan
 */
public class SelecteurDeFonte extends JFrame {
    private JLabel fonteExemple, famille, poids, inclinaison, taille;
    private JComboBox comboBoxFamille;
    private JRadioButton normal, bold, demibold, roman, italique, oblique;
    private JSpinner spinnerSize;
    private String phrase = "PORTEZ CE VIEUX WHISKY AU JUGE BLOND QUI FUME";
    private JButton cancel, ok;
    private JPanel nord, centre, sud;
    private final JCheckBox condense;

    public SelecteurDeFonte() {
        super("Appelez la police");
        nord = new JPanel();
        centre = new JPanel();
        sud = new JPanel();

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(nord, "North");
        getContentPane().add(centre, "Center");
        //getContentPane().add(sud, "South");
        
        ///////////////////////////////////////////////
        fonteExemple = new JLabel(phrase, JLabel.CENTER);
        nord.add(fonteExemple);
        ///////////////////////////////////////////////
        famille = new JLabel("Famille", JLabel.RIGHT);
        comboBoxFamille = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

        // POIDS
        JPanel droiteDePoids = new JPanel();
        normal = new JRadioButton("Normal");
        bold = new JRadioButton("Gras");
        demibold = new JRadioButton("Mi-gras");
        ButtonGroup groupePoids = new ButtonGroup();
        groupePoids.add(normal);
        groupePoids.add(bold);
        groupePoids.add(demibold);

        poids = new JLabel("Poids", JLabel.RIGHT);

        droiteDePoids.add(normal);
        droiteDePoids.add(bold);
        droiteDePoids.add(demibold);

        // SLANT
        JPanel droiteDeInclinaison = new JPanel();
        roman = new JRadioButton("Roman");
        italique = new JRadioButton("Italique");
        oblique = new JRadioButton("Oblique");
        ButtonGroup groupeSlant = new ButtonGroup();
        groupeSlant.add(roman);
        groupeSlant.add(italique);
        groupeSlant.add(oblique);


        inclinaison = new JLabel("Inclinaison", JLabel.RIGHT);

        droiteDeInclinaison.add(roman);
        droiteDeInclinaison.add(italique);
        droiteDeInclinaison.add(oblique);

        String[] listeTaille = new String[50];
        for(int i = 1; i <= 9; i++)
            listeTaille[i-1] = "  "+i+"pt" ;
        for(int i = 10; i <= 50; i++)
            listeTaille[i-1] = i+"pt" ;
        // SIZE
        JPanel droiteDeSize = new JPanel();
        spinnerSize = new JSpinner(new SpinnerListModel(listeTaille));
        condense = new JCheckBox("Oblique");

        taille = new JLabel("Taille", JLabel.RIGHT);

        droiteDeSize.add(spinnerSize);
        droiteDeSize.add(condense);
        centre.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        int py = 1;
        gbc.gridy = py; gbc.gridx = 1; gbc.weightx = 1;
        centre.add(famille, gbc);
        gbc.gridy = py++; gbc.gridx = 2; gbc.weightx = 5;
        centre.add(comboBoxFamille, gbc);

        gbc.gridy = py; gbc.gridx = 1; gbc.weightx = 1;
        centre.add(poids, gbc);
        gbc.gridy = py++; gbc.gridx = 2; gbc.weightx = 5;
        centre.add(droiteDePoids, gbc);

        gbc.gridy = py; gbc.gridx = 1; gbc.weightx = 1;
        centre.add(inclinaison, gbc);
        gbc.gridy = py++; gbc.gridx = 2; gbc.weightx = 5;
        centre.add(droiteDeInclinaison, gbc);

        gbc.gridy = py; gbc.gridx = 1; gbc.weightx = 1;
        centre.add(taille, gbc);
        gbc.gridy = py++; gbc.gridx = 2; gbc.weightx = 5; 
        centre.add(droiteDeSize, gbc);
        ///////////////////////////////////////////////
        cancel = new JButton("Cancel");
        ok = new JButton("Ok");
        sud.add(cancel);
        sud.add(ok);

        pack();


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new SelecteurDeFonte();
    }
}



