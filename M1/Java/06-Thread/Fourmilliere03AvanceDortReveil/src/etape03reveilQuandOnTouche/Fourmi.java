package etape03reveilQuandOnTouche;


import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fourmi extends Thread {
    private static Random random = new Random();
    private int pasx, pasy;
    private Color couleur;
    private Terrain terrain;
    private int x, y;
    private boolean bloque = false;

     

    /**
     * Créer une fourmi "quelque part" sur le terrain.     
     * @param terrain : terrain partagé par toutes les fourmis
     * @see public Fourmi(Terrain terrain, int x, int y)
     */
    public Fourmi(Terrain terrain) {
        this(terrain, random.nextInt(terrain.getMaxX()), random.nextInt(terrain.getMaxY()));
    }
    /**
     * Créer une fourmi à une position donnée (x, y) sur le terrain. Un couleur est 
     * attribuée au hasard. La fourmi est "lancée" et son endormissement est
     * programmé.
     * @param terrain : terrain partagé par toutes les fourmis
     * @param x : absisse où apparait la fourmi
     * @param y : ordonnée où apparait la fourmi
     */
    public Fourmi(Terrain terrain, int x, int y) {
        this.terrain = terrain;
        this.x = x;
        this.y = y;        
        terrain.set(x, y, this);      
        pasx = random.nextInt(3)-1;
        pasy = random.nextInt(3)-1;        
        couleur = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));     
        terrain.repaint();
        // Pour s'endormir
        new Thread(new Runnable() {

            public void run() {
                try {
                    int nbs = 10 + random.nextInt(10);
                    Thread.sleep(nbs * 1000);
                    bloque = true;
                } catch (InterruptedException ex) {
                    Logger.getLogger(Fourmi.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }
        }).start();   
        start();
    } 
    /**
     * Pour connaître la couleur de la fourmi
     * @return la couleur de la fourmi
     */
    public Color getCouleur() {
        return couleur;
    }
    
    //####03 pour réveiller la fourmi
    public synchronized void reveil() {
        if (bloque) {
            notify(); 
            bloque = false;
            new Thread(new Runnable() {

                public void run() {
                    try {
                        int nbs = 10 + random.nextInt(10);
                        Thread.sleep(nbs * 1000);
                        bloque = true;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Fourmi.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }).start();      
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                avance();
                Thread.sleep(100);
                while(bloque) 
                    //####03 pour réveiller la fourmi
                    synchronized(this) { wait(); }
            } catch (InterruptedException ex) {
                Logger.getLogger(Fourmi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Avance d'un pas (ou non) sur le terrain. Un pas représente une case
     * parmi les neuf cases voisines si elle n'est pas occupée par une autre 
     * fourmi. C'est à dire que si la fourmi se trouve
     * sur la case 5 représentée ci-dessous, ses cases voisines sont toutes 
     * celles de 1 à 9, y compris la 5.
     * +-+-+-+
     * |1|2|3|
     * +-+-+-+
     * |4|5|6|
     * +-+-+-+
     * |7|8|9|
     * +-+-+-+
     * Cependant, pour simpuler un déplacement, une fourmi a une chance sur deux
     * de changer de direction. 
     * Une fourmi 
     */
    private void avance() {
        int xa = x;
        int ya = y;    
        if (random.nextBoolean()) { // Une change sur deux de changer de sens e moins que la fourmi ne soit arretee
            pasx = random.nextInt(3)-1;       
            pasy = random.nextInt(3)-1;      
            while(pasx == 0 && pasy == 0) {
                pasx = random.nextInt(3)-1;       
                pasy = random.nextInt(3)-1;      
            }
        }

        x += pasx;
        y += pasy;

        if (x >= terrain.getMaxX()) x = 0;
        if (x < 0) x = terrain.getMaxX()-1;

        if (y >= terrain.getMaxY()) y = 0;
        if (y < 0) y = terrain.getMaxY()-1;
        
        Fourmi voisin = terrain.get(x, y);
        if (voisin == null) {
            terrain.set(xa, ya, null);
            terrain.set(x, y, this);
            
        } else {
            if (voisin != this) {
                voisin.reveil();
                x = xa;
                y = ya;
            }
        }             
        
        terrain.repaint(x*terrain.getTailleFourmiX(), y*terrain.getTailleFourmiY(), terrain.getTailleFourmiX(), terrain.getTailleFourmiY());    
        terrain.repaint(xa*terrain.getTailleFourmiX(), ya*terrain.getTailleFourmiY(), terrain.getTailleFourmiX(), terrain.getTailleFourmiY());                   
        reveillerTousLesVoisins(x, y);
    }  
    
    private void reveillerTousLesVoisins(int x, int y) {
        int xn;
        int yn;
        for(int i = -1; i <= 1; i++)
            for(int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    xn = x+i;
                    yn = y+j;
                    if (xn < 0)
                        xn = terrain.getMaxX()-1;
                    else if (xn >= terrain.getMaxX())
                        xn = 0;
                    if (yn < 0)
                        yn = terrain.getMaxY()-1;
                    else if (yn >= terrain.getMaxY())
                        yn = 0;  
                    
                    Fourmi fourmi = terrain.get(xn, yn);
                    if (fourmi != null)
                        fourmi.reveil();
                }
            }
    }
   
}
