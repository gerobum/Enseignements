package robot;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class PanneauProgramme extends JPanel {
        
    private JButton boutonAvance = new JButton("Avance");    
    private JButton boutonTourne = new JButton("Tourne");
    private JButton boutonExecute = new JButton("Execute");

    private Robot robot;
    
    public PanneauProgramme(Robot robot) {

        this.robot = robot;
        
        JPanel panneauProg = new JPanel();
        panneauProg.setLayout(new GridLayout(0, 1));
        panneauProg.add(boutonAvance);
        panneauProg.add(boutonTourne);


   
        panneauProg.add(boutonExecute);

        JPanel panneauArbre = new JPanel();
        
        panneauArbre.add(panneauProg);
                
        add(panneauArbre, "Center");
        
        boutonAvance.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PanneauProgramme.this.robot.avance();
            }
        });
        boutonTourne.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PanneauProgramme.this.robot.tourne();
            }
        });


    }
}
