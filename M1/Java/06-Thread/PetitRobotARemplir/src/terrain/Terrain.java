package terrain;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

public class Terrain extends Canvas {
    private Cellule[][] terrain;
    public final int maxX = 16;
    public final int maxY = 12;
    public final int tailleCelluleX = 45;
    public final int tailleCelluleY = 45;

    public Terrain() {
        terrain = new Cellule[maxX][maxY];
        setPreferredSize(new Dimension(maxX*tailleCelluleX, maxY*tailleCelluleY));
        setBackground(Color.WHITE);
    }
    public void set(int x, int y, Cellule cellule) {
        terrain[x][y] = cellule;
    }
    public Cellule get(int x, int y) {
            return terrain[x][y];
    }
    @Override
    public void paint(java.awt.Graphics g) {
        int n = 0;
        for(int x = 0; x < maxX; x++)
            for(int y = 0; y < maxY; y++)
                if (terrain[x][y] != null) {
                    g.drawImage(terrain[x][y].getImage(), x*tailleCelluleX, y*tailleCelluleY, this);
                    n++;
                }
        int derY = tailleCelluleY*maxY;
        int derX = tailleCelluleY*maxX;
        g.setColor(Color.GRAY);
        for(int y = tailleCelluleY; y < derY; y+=tailleCelluleY)
            g.drawLine(0, y, derX, y);
        for(int x = tailleCelluleX; x < derX; x+=tailleCelluleX)
            g.drawLine(x, 0, x, derY);
    }
    public void ajouterMur(int x, int y) {
        terrain[x][y] = new Mur();
    }
}
