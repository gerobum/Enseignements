package lancement;

import robot.EnvironnementRobot;

public class Test {
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EnvironnementRobot();
            }
        });
    }
}
