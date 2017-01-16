package projetGeneralise;


import java.awt.Color;
import java.util.Random;

public class Fourmi extends Thread implements Casier {
    private static Random random = new Random();
    private int pasx, pasy;
    private boolean bloque;
    private boolean vivant = true;
    private Color couleur;
    private Terrain terrain;
    private int x, y;

    synchronized void meurt() {
        synchronized(terrain) {
            vivant = false;
            notify();
            terrain.set(x, y, null);
        }
    }
    
    /**
     * Classe interne destinée à programmer l'endormissement
     * de la fourmi
     **/
    private class Endormeur implements Runnable {
        public void run() {
            
            try {
                // S'endort dans 10 e 20 sec
                int n = random.nextInt(10)+10;
                Thread.sleep(n*1000);
                dors();
            }
            catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }       
    }    
    private synchronized void dors() {
        bloque = true;
    }
    public synchronized void reveil() {
        if (bloque) {
            bloque = false;
            notify();
            new Thread(new Endormeur()).start();
        }
    }    

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
        bloque = false;       
        pasx = random.nextInt(3)-1;
        pasy = random.nextInt(3)-1;        
        couleur = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));     
        terrain.repaint();
        new Thread(new Endormeur()).start(); 
        start();
    } 
    public Fourmi(Terrain terrain, int x, int y, Color couleur) {
        this(terrain, x, y);
        this.couleur = couleur;
    }
    public Fourmi(Terrain terrain, Color couleur) {
        this(terrain);
        this.couleur = couleur;
    }    
    /**
     * Pour connaître la couleur de la fourmi
     * @return la couleur de la fourmi
     */
    public Color getCouleur() {
        return couleur;
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
            
        
        // Comme de nombreuses fourmis se partagent le même terrain,
        // Il faut synchroniser son accès. Si par exemple la fourmi
        // repère une place libre, il ne faut pas qu'elle devienne occupée
        // avant que celle-ci ne vienne s'y mettre.
        synchronized(terrain) {
            Casier casier = terrain.get(x, y);
            if (casier == null) {
                terrain.set(xa, ya, null);
                terrain.set(x, y, this);
            } else {
                if (casier != this) {
                    if (casier instanceof Fourmi) {
                        ((Fourmi)casier).reveil();
                        x = xa;
                        y = ya;                                 
                    }
                }
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
                    
                    Casier casier = terrain.get(xn, yn);
                    if (casier != null) 
                        if (casier instanceof Fourmi)
                            ((Fourmi)casier).reveil();
                }
            }
    }
   
    
    @Override
    // La fourmi avance d'un pas toutes les 100 ms, à moins qu'elle ne soit
    // bloquée.
    public void run() {
        while(vivant) {
           
            avance();            
            try {
                Thread.sleep(100);
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }            
            
            try {
                synchronized(this) {
                     while(bloque)
                        wait();
                }
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }
}
