package labelAnime;

import javax.swing.JLabel;


public class Porteur extends Animateur {
    private final JLabel label;
    public Porteur(JLabel label) {
        this.label = label;
    }

    @Override
    public void animer() {       
    }

  @Override
  public JLabel getJLabel() {
    return label;
  }
}
