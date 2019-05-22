package robot;


import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

import terrain.*;

public class EnvironnementRobot extends JFrame {
    private static Random random = new Random();

    private PanneauProgramme panneauProgramme;
    private Terrain terrain;
    private Robot robot;
    private JPanel panneauTerrain = new JPanel();

    public void init() {
        terrain = new Terrain();
        terrain.set(random.nextInt(terrain.maxX-2)+1, random.nextInt(terrain.maxY-2)+1, new Minerai());
        robot = new Robot(terrain, random.nextInt(terrain.maxX-2)+1, random.nextInt(terrain.maxY-2)+1);
        for(int x = 0; x < terrain.maxX; x++) {
            terrain.set(x, 0, new Mur());
            terrain.set(x, terrain.maxY-1, new Mur());
        }
        for(int y = 0; y < terrain.maxY; y++) {
            terrain.set(0, y, new Mur());
            terrain.set(terrain.maxX-1, y, new Mur());
        }
        panneauTerrain.removeAll();
        panneauTerrain.add(terrain);
    }

    public EnvironnementRobot() {
        super("Le monde de nono");

        init();
        panneauProgramme = new PanneauProgramme(robot);

        getContentPane().add(panneauProgramme, "West");

        panneauTerrain.add(terrain);
        getContentPane().add(panneauTerrain, "Center");

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
}
