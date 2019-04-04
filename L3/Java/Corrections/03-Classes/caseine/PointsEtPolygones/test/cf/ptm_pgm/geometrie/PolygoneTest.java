/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.ptm_pgm.geometrie;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yvan
 */
public class PolygoneTest {

    private static Random random = new Random();
    private static Class<?> tpoints = new Point[]{}.getClass();

    private static double getRandomValue() {
        int n = 500 - random.nextInt(1001);
        return n * random.nextDouble();
    }

    private static Point getRandomPoint() {
        return new Point(getRandomValue(), getRandomValue());
    }

    @Test
    public void testPresenceAttributSommet() {
        System.out.println("Présence attribut sommet");
        try {
            Field sommets = Polygone.class.getDeclaredField("sommets");
            assertTrue("Vérifiez le type de sommets",
                    sommets.getType().equals(tpoints)
            );

            assertTrue(String.format("Vérifiez les modificateurs de sommets"),
                    !Modifier.isStatic(sommets.getModifiers())
                    && Modifier.isPrivate(sommets.getModifiers())
            );
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("Vérifiez la présence d'un attribut sommets");
        }
    }

    @Test
    public void testPresenceConstructor() {
        System.out.println("Présence constructeur");
        try {
            Constructor c = Polygone.class.getConstructor(
                    Point.class,
                    Point.class,
                    Point.class,
                    tpoints);
            assertTrue("Vérifiez les modificateurs d'accès du constructeur",
                    Modifier.isPublic(c.getModifiers())
            );
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez la présence d'un constructeur qui permet de créer des polygones d'au moins"
                    + " trois points et éventuellement davantage.");
        }
    }

    @Test
    public void testPresenceNbSommets() {
        System.out.println("Présence méthode nbSommets");
        try {
            Method getter = Polygone.class.getDeclaredMethod("nbSommets");

            assertTrue("Vérifiez la visibilité de nbSommets",
                    Modifier.isPublic(getter.getModifiers())
                    && !Modifier.isStatic(getter.getModifiers())
            );

            assertTrue("Vérifiez le retour de nbSommets",
                    getter.getReturnType().equals(int.class)
                    || getter.getReturnType().equals(Integer.class)
            );

        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez la présence de la méthode nbSommets");
        }
    }

    @Test
    public void testPresenceGetSommet() {
        System.out.println("Présence méthode getSommet");
        try {
            Method getter = Polygone.class.getDeclaredMethod("getSommet", int.class);

            assertTrue("Vérifiez la visibilité de getSommet",
                    Modifier.isPublic(getter.getModifiers())
                    && !Modifier.isStatic(getter.getModifiers())
            );

            assertTrue("Vérifiez le retour de getSommet",
                    getter.getReturnType().equals(Point.class)
            );

        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez la présence de la méthode getSommet demandée");
        }
    }

    @Test
    public void testPresenceSetSommet() {
        System.out.println("Présence méthode setSommet");
        try {
            Method getter = Polygone.class.getDeclaredMethod("setSommet", int.class, Point.class);

            assertTrue("Vérifiez la visibilité de setSommet",
                    Modifier.isPublic(getter.getModifiers())
                    && !Modifier.isStatic(getter.getModifiers())
            );

            assertTrue("Vérifiez le retour de setSommet",
                    getter.getReturnType().equals(void.class)
            );

        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez la présence de la méthode setSommet(int i, Point p) demandée");
        }
    }

    @Test
    public void testPresenceTranslation() {
        System.out.println("Présence méthode translation");
        try {
            Method getter = Polygone.class.getDeclaredMethod("translation", double.class, double.class);

            assertTrue("Vérifiez la visibilité de translation",
                    Modifier.isPublic(getter.getModifiers())
                    && !Modifier.isStatic(getter.getModifiers())
            );

            assertTrue("Vérifiez le retour de translation",
                    getter.getReturnType().equals(void.class)
            );

        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez la présence de la méthode translation(double dx, double dy) demandée");
        }
    }

    @Test
    public void testPresenceRotation() {
        System.out.println("Présence méthode rotation");
        try {
            Method getter = Polygone.class.getDeclaredMethod("rotation", double.class);

            assertTrue("Vérifiez la visibilité de rotation",
                    Modifier.isPublic(getter.getModifiers())
                    && !Modifier.isStatic(getter.getModifiers())
            );

            assertTrue("Vérifiez le retour de rotation",
                    getter.getReturnType().equals(void.class)
            );

        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez la présence de la méthode rotation(double dtheta) demandée");
        }
    }

    @Test
    public void testPresenceAfficherBoolean() {
        System.out.println("Présence méthode afficher(boolean polaire)");
        try {
            Method getter = Polygone.class.getDeclaredMethod("afficher", boolean.class);

            assertTrue("Vérifiez la visibilité de afficher(boolean polaire)",
                    Modifier.isPublic(getter.getModifiers())
                    && !Modifier.isStatic(getter.getModifiers())
            );

            assertTrue("Vérifiez le retour de afficher(boolean polaire)",
                    getter.getReturnType().equals(void.class)
            );

        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez la présence de la méthode afficher(boolean polaire) demandée");
        }
    }

    @Test
    public void testPresenceAfficher() {
        System.out.println("Présence méthode afficher()");
        try {
            Method getter = Polygone.class.getDeclaredMethod("afficher");

            assertTrue("Vérifiez la visibilité de afficher()",
                    Modifier.isPublic(getter.getModifiers())
                    && !Modifier.isStatic(getter.getModifiers())
            );

            assertTrue("Vérifiez le retour de afficher()",
                    getter.getReturnType().equals(void.class)
            );

        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez la présence de la méthode afficher() demandée");
        }
    }

    static Object getFromMethod(Polygone p, String methodName, Object... pv) {
        try {
            Class<?>[] params = new Class<?>[pv.length / 2];
            Object[] values = new Object[pv.length / 2];
            int k = 0;
            for (int i = 0; i < pv.length; i += 2) {
                params[k] = (Class<?>) pv[i];
                values[k++] = pv[i + 1];
            }
            Method method = Polygone.class.getDeclaredMethod(methodName, params);
            return method.invoke(p, values);
        } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez la présence de la méthode getSommet demandée");
            return null;
        }
    }

    static Point[] getSommets(Polygone p) {
        try {
            Field fnom = Polygone.class.getDeclaredField("sommets");
            fnom.setAccessible(true);
            return (Point[]) fnom.get(p);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            fail(String.format("Attribut sommets absent"));
            return null;
        }
    }

    /*static void set(String nom, double v, Point p) {
        try {
            Field fnom = Point.class.getDeclaredField(nom);
            fnom.setAccessible(true);
            fnom.setDouble(p, v);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            fail(String.format("Attribut %s absent", nom));
        }
    }*/
    /**
     * Test of nbSommets method, of class Polygone.
     */
    @Test
    public void testNbSommets() {
        System.out.println("nbSommets");

        for (int i = 0; i < 100; ++i) {
            Point[] tp = new Point[random.nextInt(5)];
            for (int j = 0; i < tp.length; ++i) {
                tp[j] = getRandomPoint();
            }
            Polygone p = new Polygone(getRandomPoint(), getRandomPoint(), getRandomPoint(), tp);

            assertTrue("revoir nbSommet()", 3 + tp.length == (int) getFromMethod(p, "nbSommets"));

        }
    }

    /**
     * Test of getSommet method, of class Polygone.
     */
    @Test
    public void testGetSommet() {
        System.out.println("getSommet");

        for (int i = 0; i < 100; ++i) {
            Point[] tp = new Point[random.nextInt(5)];
            for (int j = 0; j < tp.length; ++i) {
                tp[j] = getRandomPoint();
            }
            Polygone p = new Polygone(getRandomPoint(), getRandomPoint(), getRandomPoint(), tp);
            for (int j = 0; j < p.nbSommets(); ++j) {
                Point p1 = (Point) getFromMethod(p, "getSommet", int.class, j);
                Point p2 = getSommets(p)[j];
                try {
                    assertTrue("revoir getSommet() ",
                            PointTest.get("x", p1) == PointTest.get("x", p2)
                            && PointTest.get("y", p1) == PointTest.get("y", p2)
                    );
                } catch (NullPointerException ex) {
                    fail("revoir getSommet() ");
                }
            }
        }
    }

    /**
     * Test of setSommet method, of class Polygone.
     */
    @Test
    public void testSetSommet() {
        System.out.println("setSommet");
    }

    /**
     * Test of translation method, of class Polygone.
     */
    @Test
    public void testTranslation() {
        System.out.println("translation");
    }

    /**
     * Test of rotation method, of class Polygone.
     */
    @Test
    public void testRotation() {
        System.out.println("rotation");
    }

    /**
     * Test of afficher method, of class Polygone.
     */
    @Test
    public void testAfficher_boolean() {
    }

    /**
     * Test of afficher method, of class Polygone.
     */
    @Test
    public void testAfficher_0args() {
    }

    /*
    private static void getterPresence(String nom) {
        nom = "get" + nom.substring(0, 1).toUpperCase() + nom.substring(1);
        try {
            Method getter = Point.class.getDeclaredMethod(nom);

            assertTrue("Vérifiez la visibilité de " + nom,
                    Modifier.isPublic(getter.getModifiers())
                    && !Modifier.isStatic(getter.getModifiers())
            );

            assertTrue("Vérifiez le retour de " + nom,
                    getter.getReturnType().equals(double.class)
                    || getter.getReturnType().equals(Double.class)
            );

        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez d'avoir un getter nommé " + nom
                    + " et dont la signature est correcte.");
        }
    }

    private static void setterPresence(String nom) {
        nom = "set" + nom.substring(0, 1).toUpperCase() + nom.substring(1);
        Method setter = null;
        try {
            setter = Point.class.getDeclaredMethod(nom, double.class);

        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez d'avoir un setter nommé " + nom
                    + " et dont la signature est correcte.");
        }
        if (setter == null) {
            try {
                setter = Point.class.getDeclaredMethod(nom, Double.class);

            } catch (NoSuchMethodException | SecurityException ex) {
                fail("Vérifiez d'avoir un setter nommé " + nom
                        + " et dont la signature est correcte.");
            }
        }

        if (setter != null) {

            assertTrue("Vérifiez la visibilité de " + nom,
                    Modifier.isPublic(setter.getModifiers())
                    && !Modifier.isStatic(setter.getModifiers())
            );

            assertTrue("Vérifiez le retour de " + nom,
                    setter.getReturnType().equals(void.class)
            );
        }
    }

    private static void defaultConstructorPresence() {
        try {
            Constructor c = Point.class.getConstructor();
            assertTrue("Vérifiez les modificateurs d'accès du constructeur par défaut",
                    Modifier.isPublic(c.getModifiers())
            );

        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez d'avoir le constructeur par defaut");
        }
    }

     */
}
