package robot;

import terrain.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Robot implements Cellule {

    private Cellule passage = null;
    private static Random random = new Random();

    private static final int NORD = 0;
    private static final int EST = 1;
    private static final int SUD = 2;
    private static final int OUEST = 3;

    private Orientation[] tOrientation = {new Orientation(0, 0,-1),new Orientation(1, 1,0),new Orientation(2, 0,1),new Orientation(3, -1,0)};
    private Orientation vers = tOrientation[0];
    private Terrain terrain;
    private int x, y;
    private Image image;
    private Image robotN, robotS, robotE, robotO, robotCasse;

    public Cellule quoiDessous() {
        return passage;
    }

    private class Orientation {
        public final int direction, pasx, pasy;
        Orientation(int ix, int px, int py) {
            pasx = px;
            pasy = py;
            direction = ix;
        }
    };

    private Image imageSelonOrientation() {

        if (vers.direction == EST)
            return robotE;
        else if (vers.direction == OUEST)
            return robotO;
        else if (vers.direction == SUD)
            return robotS;
        else
            return robotN;
    }

    private void init(Terrain terrain, int x, int y, int direction) {
        this.terrain = terrain;
        passage = terrain.get(x, y);
        this.x = x;
        this.y = y;
        vers = tOrientation[direction];
        terrain.set(x, y, this);
        try {
            robotE = ImageIO.read(new File("robotVersEst.bmp"));
            robotS = ImageIO.read(new File("robotVersSud.bmp"));
            robotO = ImageIO.read(new File("robotVersOuest.bmp"));
            robotN = ImageIO.read(new File("robotVersNord.bmp"));
            robotCasse = ImageIO.read(new File("robotCasse.bmp"));

        } catch (IOException ex) {
            Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
        }
        image = imageSelonOrientation();
        terrain.repaint();
    }
    public Robot(Terrain terrain, int x, int y) {
        init(terrain, x, y, random.nextInt(4));
    }

    public Cellule quoiDevant() {

        int xa = x + vers.pasx;
        int ya = y + vers.pasy;

        if (xa >= terrain.maxX) xa = 0;
        if (xa < 0) xa = terrain.maxX-1;

        if (ya >= terrain.maxY) ya = 0;
        if (ya < 0) ya = terrain.maxY-1;

        return terrain.get(xa, ya);
    }

    public void avance() {
        int xa = x;
        int ya = y;

        x += vers.pasx;
        y += vers.pasy;

        if (x >= terrain.maxX) x = 0;
        if (x < 0) x = terrain.maxX-1;

        if (y >= terrain.maxY) y = 0;
        if (y < 0) y = terrain.maxY-1;

        Cellule cellule = terrain.get(x, y);
        if (cellule != this) {
            if (cellule instanceof Robot || cellule instanceof Mur) {
                x = xa;
                y = ya;
                image = robotCasse;
                terrain.repaint();
            } else {
                terrain.set(xa, ya, passage);
                passage = terrain.get(x, y);
                terrain.set(x, y, this);
            }
        }


        terrain.repaint(x*terrain.tailleCelluleX, y*terrain.tailleCelluleX, terrain.tailleCelluleX, terrain.tailleCelluleY);
        terrain.repaint(xa*terrain.tailleCelluleX, ya*terrain.tailleCelluleY, terrain.tailleCelluleX, terrain.tailleCelluleY);

        try {

            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void tourne() {
        vers = tOrientation[(vers.direction+1)%4];
        image = imageSelonOrientation();
        terrain.repaint(x*terrain.tailleCelluleX, y*terrain.tailleCelluleY, terrain.tailleCelluleX, terrain.tailleCelluleY);

        try {

            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(Robot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Image getImage() {
        return image;
    }
}
