/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.panneaux;

import instruction.Instruction;
import interfaces.Detachable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import robot.Initialisation;
import terrain.Terrain;

/**
 *
 * @author yvan
 */
public class PanneauDExecution extends JPanel {
    
    private static final long serialVersionUID = 1L;

    private JButton boutonExecuteProgramme = new JButton("Exécution du programme");
    private JButton boutonExecuteSelection = new JButton("Exécution de la sélection");
    //private JButton arret_reprise = new JButton("Démarrer le robot");
    private Detachable fenetreRobot;
    
    public void executeProgramme() {
        boutonExecuteProgramme.doClick();
    }
    
    public void executeSelection() {
        boutonExecuteSelection.doClick();
    }

    public PanneauDExecution(Detachable fenetreRobot) {
        this.fenetreRobot = fenetreRobot;
        //arret_reprise.setEnabled(false);
        //add(arret_reprise);
        add(boutonExecuteProgramme);
        add(boutonExecuteSelection);
        //arret_reprise.setSelected(false);
        /*arret_reprise.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (arret_reprise.isSelected()) {
                    arret_reprise.setSelected(false);
                    PanneauDExecution.this.fenetreRobot.getRobot().bloquer();
                    arret_reprise.setText("Démarrer le robot");
                } else {
                    arret_reprise.setSelected(true);                    
                    arret_reprise.setText("Arrêter le robot ");
                    PanneauDExecution.this.fenetreRobot.getRobot().deBloquer();
                }
            }
        });*/
        

        boutonExecuteProgramme.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        //arret_reprise.setEnabled(true);
                        Instruction instruction = PanneauDExecution.this.fenetreRobot.getProgramme().getProcedurePrincipal();
                        Initialisation.initialiser(PanneauDExecution.this.fenetreRobot.getProgramme().getInitialisation(), PanneauDExecution.this.fenetreRobot, true);
                        
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Terrain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        boutonExecuteProgramme.setEnabled(false);
                        boutonExecuteSelection.setEnabled(false);

                        if (PanneauDExecution.this.fenetreRobot.getSplitPane().getDividerLocation() > 1) {
                            PanneauDExecution.this.fenetreRobot.getSplitPane().setDividerLocation(0.0);
                        } else {
                            PanneauDExecution.this.fenetreRobot.getTerrain().revalidate();
                        }
                        PanneauDExecution.this.fenetreRobot.getRobot().execute(instruction);
                        try {

                            Thread.sleep(1000);

                            PanneauDExecution.this.fenetreRobot.getRobot().getProcessus().join();


                        } catch (InterruptedException ex) {
                        }
                        boutonExecuteProgramme.setEnabled(true);
                        boutonExecuteSelection.setEnabled(true);
                        
                    }
                }).start();
            }
        });
        
        boutonExecuteSelection.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                new Thread(new Runnable() {

                    @Override
                    public synchronized void run() {
                        //arret_reprise.setEnabled(true);
                        Instruction select = (Instruction) PanneauDExecution.this.fenetreRobot.getArbre().getSelectionPath().getLastPathComponent();
                        PanneauDExecution.this.fenetreRobot.getRobot().execute(select);
                        try {
                            if (PanneauDExecution.this.fenetreRobot.getSplitPane().getDividerLocation() > 2) {
                                PanneauDExecution.this.fenetreRobot.getSplitPane().setDividerLocation(0.0);
                            }
                            boutonExecuteProgramme.setEnabled(false);
                            boutonExecuteSelection.setEnabled(false);
                            Thread.sleep(1000);
                            PanneauDExecution.this.fenetreRobot.getRobot().getProcessus().join();
                            boutonExecuteProgramme.setEnabled(true);
                            boutonExecuteSelection.setEnabled(true);
                            //getSplitPane().setDividerLocation(dl);

                        } catch (InterruptedException ex) {
                            Logger.getLogger(PanneauPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).start();
            }
        });
    }
}
