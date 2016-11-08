
package principal;

import classes.Extreme;
import classes.ExtremeAvecMesureur;
import implementation.MesureurRectangleSurface;
import implementation.RectanglePerimetre;
import implementation.RectangleSurface;
import interfaces.Mesureur;
import java.awt.Rectangle;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        RectangleSurface rs;
        Random random = new Random();
        Extreme extreme = new Extreme(new RectangleSurface(random.nextInt(100), random.nextInt(100)));

        for(int i = 0; i < 100; i++) {
            extreme.ajouter(new RectangleSurface(random.nextInt(100), random.nextInt(100)));
        }

        System.out.println(extreme.getMinimum() + " <= " + extreme.getMaximum());

        RectanglePerimetre rp;
        extreme = new Extreme(new RectanglePerimetre(random.nextInt(100), random.nextInt(100)));

        for(int i = 0; i < 100; i++) {
            extreme.ajouter(new RectanglePerimetre(random.nextInt(100), random.nextInt(100)));
        }

        System.out.println(extreme.getMinimum() + " <= " + extreme.getMaximum());
        random = new Random();

        ExtremeAvecMesureur extremeAm = new ExtremeAvecMesureur(new MesureurRectangleSurface(), new RectanglePerimetre(random.nextInt(100), random.nextInt(100)));

        for(int i = 0; i < 100; i++) {
            extremeAm.ajouter(new RectangleSurface(random.nextInt(100), random.nextInt(100)));
        }

        System.out.println(extreme.getMinimum() + " <= " + extreme.getMaximum());

        extremeAm = new ExtremeAvecMesureur(new Mesureur() {
            @Override
            public int mesure(Object o) {
                return 2*(((Rectangle)o).height+((Rectangle)o).width);
            }
        }, new RectanglePerimetre(random.nextInt(100), random.nextInt(100)));

        for(int i = 0; i < 100; i++) {
            extremeAm.ajouter(new RectanglePerimetre(random.nextInt(100), random.nextInt(100)));
        }

        System.out.println(extremeAm.getMinimum() + " <= " + extremeAm.getMaximum());
        
        extremeAm = new ExtremeAvecMesureur(new Mesureur() {

            @Override
            public int mesure(Object o) {
                Integer i = (Integer) o;
                return Integer.MAX_VALUE - i;
            }
        }, random.nextInt(1000));
        
        for(int i = 0; i < 100; i++) {
            extremeAm.ajouter(random.nextInt(1000));
        }

        System.out.println(extremeAm.getMinimum() + " <= " + extremeAm.getMaximum());
        
    }
}
