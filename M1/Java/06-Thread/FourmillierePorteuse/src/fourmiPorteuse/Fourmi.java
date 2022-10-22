package fourmiPorteuse;


import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

    
public class Fourmi extends Thread implements CaseDuTerrain {
    private static Random random = new Random();
    private int pasx, pasy;
    private Color couleur;
    private Terrain terrain;
    private int x, y;
    private Miette valise = null;
    private int dureeEntre2Pas = 100;

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
    @Override
    public Color getCouleur() {
        if (valise != null)
            return Color.BLUE;
        else
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
     * Cependant, pour simuler un déplacement, une fourmi a une chance sur deux
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
        //synchronized(terrain) {
            CaseDuTerrain casier = terrain.get(x, y);
            if (casier == null) {
                terrain.set(xa, ya, null);
                terrain.set(x, y, this);
            } else if (casier instanceof Miette) {
                if (valise == null) {
                    valise = (Miette)casier;
                    terrain.set(xa, ya, null);
                    terrain.set(x, y, this);
                } else {
                    terrain.set(xa, ya, valise);
                    valise = null;
                    trouverUnePlace(xa, ya);
                }
            } else {
                x = xa;
                y = ya;                 
            }
        
        
        //}  
        
        terrain.repaint(x*terrain.getTailleFourmiX(), y*terrain.getTailleFourmiY(), terrain.getTailleFourmiX(), terrain.getTailleFourmiY());    
        terrain.repaint(xa*terrain.getTailleFourmiX(), ya*terrain.getTailleFourmiY(), terrain.getTailleFourmiX(), terrain.getTailleFourmiY());                   
    } 
    
    public void setDuree(int duree) {
        dureeEntre2Pas = duree;
    }
   
    
    @Override
    // La fourmi avance d'un pas toutes les 100 ms, à moins qu'elle ne soit
    // bloquée.
    public void run() {
        while(true) {
           
            avance();            
            try {
                Thread.sleep(dureeEntre2Pas);
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }                        
        }
    }

    private void trouverUnePlace(int xa, int ya) {
        List<Integer> li = new LinkedList<>();
        li.add(-1);
        li.add(0);
        li.add(1);
        Collections.shuffle(li);
        List<Integer> lj = new LinkedList<>();
        lj.add(-1);
        lj.add(0);
        lj.add(1);
        Collections.shuffle(lj);
        for(int i : li)
            for(int j : lj) {
                x = xa+i;
                y = ya+j;
                if (x < 0) 
                    x = terrain.getMaxX()-1;
                else if (x >= terrain.getMaxX())
                    x = 0;
                if (y < 0) 
                    y = terrain.getMaxY()-1;
                else if (y >= terrain.getMaxY())
                    y = 0;
                if (terrain.get(x, y) == null) {                    
                    terrain.set(x, y, this);
                    return;
                }
            }
    }
}
