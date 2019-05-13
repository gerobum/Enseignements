package avec_decorateur.pattern;

// TODO établir une généralisation si nécessaire
public class Tourneur extends Decorateur {

    public Tourneur(Animateur composant) {
        super(composant);
    }
    
   

    
    @Override
    public void animer() {
        // TODO redéfinir correctement cette méthode
        super.animer();
        faireTourner();
    }  
    
    
    private void faireTourner() {
        // TODO faire tourner (défiler) le JLabel. S'inspirer de sans_decorateur.JLabelTournant
        // Le thread qui fait tourner
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getJLabel().setText(getJLabel().getText().substring(1) + getJLabel().getText().substring(0, 1));
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {                        
                    }
                }
            }
        }).start();
    }
}
