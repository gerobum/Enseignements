package avec_decorateur.pattern;

import javax.swing.JLabel;

public class Decorateur extends Animateur {
    private Animateur composant;
    
    public Decorateur(Animateur composant) {
        this.composant = composant;
    }

    @Override
    public void animer() {
        composant.animer();
    }

  @Override
  public JLabel getJLabel() {
    return composant.getJLabel();
  }

}
