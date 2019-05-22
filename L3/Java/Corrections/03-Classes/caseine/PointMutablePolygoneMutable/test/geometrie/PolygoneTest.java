/**
 * @author Yvan Maillot (yvan.maillot@uha.fr)
 */
package geometrie;

import caseinedev.IntrospectionUtilities;
import cf.geometrie.Point;
import cf.geometrie.Polygone;
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

    private static final Random R = new Random();

    private static double getRandomValue() {
        int n = 500 - R.nextInt(1001);
        return n * R.nextDouble();
    }

    private static Point getRandomPoint() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?> c = Point.class.getConstructor();
        c.setAccessible(true);
        return PointTest.newInstance(getRandomValue(), getRandomValue());
    }

    public static Polygone newInstance(Point p0, Point p1, Point p2, Point[] tp) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor<?> c = Polygone.class.getDeclaredConstructor(Point.class, Point.class, Point.class, Point[].class);
        c.setAccessible(true);
        return (Polygone) c.newInstance(p0, p1, p2, tp);
    }

    /**
     * Retourne par introspection le résultat de l'invocation d'une méthode avec
     * ses arguments.
     *
     * @param p
     * @param methodName
     * @param pv
     * @return
     */
    static Object getFromMethod(Polygone p, String methodName, Object... pv) {
        try {
            return IntrospectionUtilities.getFromMethod(p.getClass(), p, methodName, pv);
        } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException ex) {
            fail("");
            return null;
        }
    }

    /**
     * Retourne par introspection le résultat de l'invocation d'une méthode avec
     * ses arguments.
     *
     * @param p
     * @param methodName
     * @param pv
     * @return
     */
    static Object getFromMethodTA(Polygone p, String methodName, Object... pv) {
        try {
            return IntrospectionUtilities.getFromMethodTA(p.getClass(), p, methodName, pv);
        } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException ex) {
            fail("");
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

    static Point[] getCopyOfSommets(Polygone p) {
        try {
            Field fnom = Polygone.class.getDeclaredField("sommets");
            fnom.setAccessible(true);
            Point[] sommets = (Point[]) fnom.get(p);
            Point[] cp = new Point[sommets.length];
            int i = 0;
            for (Point pi : sommets) {
                cp[i++] = PointTest.newInstance(PointTest.get("x", pi), PointTest.get("y", pi));
            }
            return cp;
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            fail(String.format("Attribut sommets absent"));
            return null;
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException ex) {
            fail();
            return null;
        }
    }

    // Fin des méthodes utilitaires
    @Test
    public void checkClassPolygone() {
        System.out.println("check class Polygone");
        assertTrue("Revoir la classe Polygone (Héritage)", Polygone.class.getSuperclass().equals(Object.class));
        assertTrue("Revoir la classe Polygone (Modificateurs)", Polygone.class.getModifiers() == 1);
    }

    /*@Test
    public void checkFieldsommets() {
        System.out.println("Check attribut sommets");
        try {
            Field f = Polygone.class.getDeclaredField("sommets");
            assertTrue("Revoir l'attribut sommets", f.getModifiers() == 2);
            assertTrue("Revoir l'attribut sommets", f.getType().equals(Point[].class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("Revoir l'attribut sommets");
        }
    }*/
    @Test
    public void checkFieldsommets() {
        System.out.println("Check attribut sommets");
        try {
            Field x = Polygone.class.getDeclaredField("sommets");
            assertTrue("Revoir Revoir l'attribut sommets (Modificateurs)", Modifier.isPrivate(18) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir Revoir l'attribut sommets (Modificateurs)", Modifier.isProtected(18) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir Revoir l'attribut sommets (Modificateurs)", Modifier.isPublic(18) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir Revoir l'attribut sommets (Modificateurs)", Modifier.isStatic(18) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir l'attribut sommets", x.getType().equals(Point[].class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("Revoir l'attribut sommets");
        }
    }

    @Test
    public void checkConstructorPolygonePointPointPointPointArray() {
        System.out.println("");
        try {
            Constructor c = Polygone.class.getDeclaredConstructor(
                    Point.class,
                    Point.class,
                    Point.class,
                    Point[].class
            );
            assertTrue("", c.getModifiers() == 129);
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void checkMethodnbSommets() {
        System.out.println("La méthode qui retourne le nombre de sommets");
        try {
            Method c = Polygone.class.getDeclaredMethod("nbSommets"
            );
            assertTrue("Revoir La méthode qui retourne le nombre de sommets (Modificateurs)", c.getModifiers() == 1);
            assertTrue("Revoir La méthode qui retourne le nombre de sommets (Retour)", c.getReturnType().equals(int.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir La méthode qui retourne le nombre de sommets");
        }
    }

    @Test
    public void checkMethodgetSommetint() {
        System.out.println("public cf.ptm_pgm.geometrie.Point cf.ptm_pgm.geometrie.Polygone.getSommet(int)");
        try {
            Method c = Polygone.class.getDeclaredMethod("getSommet",
                    int.class);
            assertTrue("Revoir public cf.ptm_pgm.geometrie.Point cf.ptm_pgm.geometrie.Polygone.getSommet(int) (Modificateurs)", c.getModifiers() == 1);
            assertTrue("Revoir public cf.ptm_pgm.geometrie.Point cf.ptm_pgm.geometrie.Polygone.getSommet(int) (Retour)", c.getReturnType().equals(Point.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir public cf.ptm_pgm.geometrie.Point cf.ptm_pgm.geometrie.Polygone.getSommet(int)");
        }
    }

    @Test
    public void checkMethodsetSommetintPoint() {
        System.out.println("La méthode qui affecte le ième sommet");
        try {
            Method c = Polygone.class.getDeclaredMethod("setSommet",
                    int.class,
                    Point.class);
            assertTrue("Revoir La méthode qui affecte le ième sommet (Modificateurs)", c.getModifiers() == 1);
            assertTrue("Revoir La méthode qui affecte le ième sommet (Retour)", c.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir La méthode qui affecte le ième sommet");
        }
    }

    @Test
    public void checkMethodtranslationdoubledouble() {
        System.out.println("public void cf.ptm_pgm.geometrie.Polygone.translation(double,double)");
        try {
            Method c = Polygone.class.getDeclaredMethod("translation",
                    double.class,
                    double.class);
            assertTrue("Revoir public void cf.ptm_pgm.geometrie.Polygone.translation(double,double) (Modificateurs)", c.getModifiers() == 1);
            assertTrue("Revoir public void cf.ptm_pgm.geometrie.Polygone.translation(double,double) (Retour)", c.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir public void cf.ptm_pgm.geometrie.Polygone.translation(double,double)");
        }
    }

    @Test
    public void checkMethodrotationdouble() {
        System.out.println("public void cf.ptm_pgm.geometrie.Polygone.rotation(double)");
        try {
            Method c = Polygone.class.getDeclaredMethod("rotation",
                    double.class);
            assertTrue("Revoir public void cf.ptm_pgm.geometrie.Polygone.rotation(double) (Modificateurs)", c.getModifiers() == 1);
            assertTrue("Revoir public void cf.ptm_pgm.geometrie.Polygone.rotation(double) (Retour)", c.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir public void cf.ptm_pgm.geometrie.Polygone.rotation(double)");
        }
    }

    @Test
    public void checkMethodafficherboolean() {
        System.out.println("La méthode pour afficher soit en \"polaire\" soit en \"cartésien\"");
        try {
            Method c = Polygone.class.getDeclaredMethod("afficher",
                    boolean.class);
            assertTrue("Revoir La méthode pour afficher soit en \"polaire\" soit en \"cartésien\" (Modificateurs)", c.getModifiers() == 1);
            assertTrue("Revoir La méthode pour afficher soit en \"polaire\" soit en \"cartésien\" (Retour)", c.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir La méthode pour afficher soit en \"polaire\" soit en \"cartésien\"");
        }
    }

    @Test
    public void checkMethodafficher() {
        System.out.println("La méthode afficher en cartésien (par défaut)");
        try {
            Method c = Polygone.class.getDeclaredMethod("afficher"
            );
            assertTrue("Revoir La méthode afficher en cartésien (par défaut) (Modificateurs)", c.getModifiers() == 1);
            assertTrue("Revoir La méthode afficher en cartésien (par défaut) (Retour)", c.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir La méthode afficher en cartésien (par défaut)");
        }
    }

    @Test
    public void checkMethodtoString() {
        System.out.println("public java.lang.String cf.ptm_pgm.geometrie.Polygone.toString()");
        try {
            Method c = Polygone.class.getDeclaredMethod("toString"
            );
            assertTrue("Revoir public java.lang.String cf.ptm_pgm.geometrie.Polygone.toString() (Modificateurs)", c.getModifiers() == 1);
            assertTrue("Revoir public java.lang.String cf.ptm_pgm.geometrie.Polygone.toString() (Retour)", c.getReturnType().equals(String.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir public java.lang.String cf.ptm_pgm.geometrie.Polygone.toString()");
        }
    }

    @Test
    public void testNbSommets() {
        System.out.println("nbSommets");
        try {
            for (int i = 0; i < 100; ++i) {
                Point[] tp = new Point[R.nextInt(5)];
                for (int j = 0; i < tp.length; ++i) {
                    tp[j] = getRandomPoint();
                }
                Polygone p = newInstance(getRandomPoint(), getRandomPoint(), getRandomPoint(), tp);

                assertTrue("revoir nbSommet()", 3 + tp.length == (int) getFromMethod(p, "nbSommets"));

            }
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | IllegalArgumentException | InvocationTargetException ex) {
            fail("");
        }
    }

    /**
     * Test of getSommet method, of class Polygone.
     */
    @Test
    public void testGetSommet() {
        System.out.println("getSommet");
        try {
            for (int i = 0; i < 100; ++i) {
                Point[] tp = new Point[R.nextInt(5)];
                for (int j = 0; j < tp.length; ++j) {
                    tp[j] = getRandomPoint();
                }
                Polygone p = newInstance(getRandomPoint(), getRandomPoint(), getRandomPoint(), tp);
                for (int j = 0; j < (int) getFromMethod(p, "nbSommets"); ++j) {
                    Point p1 = (Point) getFromMethodTA(p, "getSommet", int.class, j);
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
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | IllegalArgumentException | InvocationTargetException ex) {
            fail("");
        }
    }

    /**
     * Test of setSommet method, of class Polygone.
     */
    @Test
    public void testSetSommet() {
        System.out.println("setSommet");

        try {
            for (int i = 0; i < 100; ++i) {
                Point[] tp = new Point[R.nextInt(5)];
                for (int j = 0; j < tp.length; ++j) {
                    tp[j] = getRandomPoint();
                }
                Polygone p = newInstance(getRandomPoint(), getRandomPoint(), getRandomPoint(), tp);
                for (int j = 0; j < (int) getFromMethod(p, "nbSommets"); ++j) {
                    Point p1 = getRandomPoint();
                    getFromMethodTA(p, "setSommet", int.class, j, Point.class, p1);
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
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | IllegalArgumentException | InvocationTargetException ex) {
            fail("");
        }
    }

    /**
     * Test of translation method, of class Polygone.
     */
    @Test
    public void testTranslation() {
        System.out.println("translation");
        try {
            for (int i = 0; i < 100; ++i) {
                Point[] tp = new Point[R.nextInt(5)];
                for (int j = 0; j < tp.length; ++j) {
                    tp[j] = getRandomPoint();
                }
                Polygone p = newInstance(getRandomPoint(), getRandomPoint(), getRandomPoint(), tp);
                double dx = getRandomValue();
                double dy = getRandomValue();
                Point[] sommetsAvant = getCopyOfSommets(p);
                getFromMethodTA(p, "translation", double.class, dx, double.class, dy);
                for (int j = 0; j < (int) getFromMethod(p, "nbSommets"); ++j) {

                    Point p1 = sommetsAvant[j];
                    Point p2 = getSommets(p)[j];
                    try {
                        assertTrue("revoir translation() " + j,
                                PointTest.get("x", p1) + dx == PointTest.get("x", p2)
                                && PointTest.get("y", p1) + dy == PointTest.get("y", p2)
                        );
                    } catch (NullPointerException ex) {
                        fail("revoir translation() ");
                    }
                }
            }
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | IllegalArgumentException | InvocationTargetException ex) {
            fail("");
        }
    }

    /**
     * Test of rotation method, of class Polygone.
     */
    @Test
    public void testRotation() {
        System.out.println("rotation");
        try {

            for (int i = 0; i < 100; ++i) {
                Point[] tp = new Point[R.nextInt(5)];
                for (int j = 0; j < tp.length; ++j) {
                    tp[j] = getRandomPoint();
                }
                Polygone p = newInstance(getRandomPoint(), getRandomPoint(), getRandomPoint(), tp);
                double dtheta = getRandomValue();
                Point[] sommetsAvant = getCopyOfSommets(p);
                getFromMethodTA(p, "rotation", double.class, dtheta);
                for (int j = 0; j < (int) getFromMethod(p, "nbSommets"); ++j) {

                    Point p1 = sommetsAvant[j];
                    Point p2 = getSommets(p)[j];
                    try {
                        assertTrue("revoir rotation() " + j,
                                PointTest.get("theta", p1) != PointTest.get("theta", p2)
                                && PointTest.get("rho", p1) == PointTest.get("rho", p2)
                        );
                    } catch (NullPointerException ex) {
                        fail("revoir rotation() " + ex);
                    }
                }
            }
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | IllegalArgumentException | InvocationTargetException ex) {
            fail("");
        }

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

 /*
    @Test
    public void testPresenceAttributSommet() {
        System.out.println("Présence attribut sommet");
        try {
            Field sommets = Polygone.class.getDeclaredField("sommets");
            assertTrue("Vérifiez le type de sommets",
                    sommets.getType().equals(Point[].class)
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
                    Point[].class);
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
     */
}
