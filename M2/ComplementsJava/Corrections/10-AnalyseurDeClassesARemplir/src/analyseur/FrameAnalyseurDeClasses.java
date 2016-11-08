package analyseur;

import panneaux.PanneauAnalyseurDeClasses;
import javax.swing.JFrame;
/** 
 * Une fenêtre graphique qui permet d'instancier une classe de l'API ou 
 * d'invoquer une de ses méthodes.
 * 
 * @author yvan
 */
public class FrameAnalyseurDeClasses extends JFrame {

  public FrameAnalyseurDeClasses() {
    super("Analyseur de classes");
    getContentPane().add(new PanneauAnalyseurDeClasses(this));

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }

  public static void main(String[] args) {
    new FrameAnalyseurDeClasses();
  }
}
