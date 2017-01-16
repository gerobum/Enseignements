/*
 * Missile.java
 *
 * Created on 2 avril 2008, 16:20
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package fourmiTueuse;

import java.awt.Color;

/**
 *
 * @author Yvan
 */
public class Missile extends Thread implements Casier {
    private int x;
    private int y;
    private int pasx;
    private int pasy;
    private Terrain terrain;
    
    /** Creates a new instance of Missile */
    public Missile(Terrain terrain, int x, int y) {
        this.terrain = terrain;
        this.x = x;
        this.y = y;        
        terrain.set(x, y, this);    
        pasx = 0;
        pasy = -1;        
    
        terrain.repaint();
        start();
    } 
    @Override
    public Color getCouleur() {
        return Color.ORANGE;
    }
      private void avance() {
        int xa = x;
        int ya = y;    

        x += pasx;
        y += pasy;

        if (x >= terrain.getMaxX()) x = 0;
        if (x < 0) x = terrain.getMaxX()-1;

        if (y >= terrain.getMaxY()) y = 0;
        if (y < 0) y = 0;
            
        
        // Comme de nombreuses fourmis se partagent le même terrain,
        // Il faut synchroniser son accès. Si par exemple la fourmi
        // repère une place libre, il ne faut pas qu'elle devienne occupée
        // avant que celle-ci ne vienne s'y mettre.
        synchronized(terrain) {
            Casier casier = terrain.get(x, y);
            //if (casier == null) {
                terrain.set(xa, ya, null);
                terrain.set(x, y, this);
            //} else {
                if (casier instanceof Fourmi) {
                    Fourmi fourmi = (Fourmi) casier;
                    fourmi.enVie = false;
            //    }
            }
        
        
        }  
        
        terrain.repaint(x*terrain.getTailleFourmiX(), y*terrain.getTailleFourmiY(), terrain.getTailleFourmiX(), terrain.getTailleFourmiY());    
        terrain.repaint(xa*terrain.getTailleFourmiX(), ya*terrain.getTailleFourmiY(), terrain.getTailleFourmiX(), terrain.getTailleFourmiY());                   
    }  

    @Override
    public void run() {

        while(y > 0) {
            avance();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
        }
        System.out.println("Sortie");
        terrain.set(x, y, null);
    }
      
      
}
