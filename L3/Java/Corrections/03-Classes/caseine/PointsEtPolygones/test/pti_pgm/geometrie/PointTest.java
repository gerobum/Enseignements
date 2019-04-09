/**
 * @author Yvan Maillot (yvan.maillot@uha.fr)
 */
package pti_pgm.geometrie;

import caseinedev.IntrospectionUtilities;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;
import static org.junit.Assert.*;
import rf.pti_pgm.geometrie.Point;
import java.io.FileNotFoundException;
import static java.lang.Math.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author yvan
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PointTest {

    private static final Random R = new Random();

    private static double getRandomValue() {
        int n = 500 - R.nextInt(1001);
        return n * R.nextDouble();
    }

    @Test
    public void t0100_checkAttributPresence() {
        System.out.println("Vérification des attributs");
        attributPresence("x");
        attributPresence("y");
        attributPresence("rho");
        attributPresence("theta");
    }

    private static void attributPresence(String nom) {
        try {
            Field coordonnee = Point.class.getDeclaredField(nom);
            assertTrue(String.format("%s doit être un double", nom),
                    coordonnee.getType().equals(double.class)
                    || coordonnee.getType().equals(Double.class)
            );
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("Vérifiez d'avoir un attribut nommé " + nom);
        }
    }

    @Test
    public void t0150_checkAttributsForMutability() {
        System.out.println("Vérification des attributs (immuables)");
        assertFalse("Revoir les attributs de Point pour assurer son caractère immuable", IntrospectionUtilities.checkAttributsForMutability(Point.class));
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

    private static void xyConstructorPresence() {
        try {
            Constructor c = Point.class.getConstructor(double.class, double.class);
            assertTrue("Vérifiez les modificateurs d'accès du constructeur Point(x, y)",
                    Modifier.isPublic(c.getModifiers())
            );
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Vérifiez d'avoir le constructeur Point(double x, double y)");
        }
    }

    @Test
    public void t0200_checkConstructorsPresence() {
        System.out.println("Vérification de la présence des constructeurs");
        defaultConstructorPresence();
        xyConstructorPresence();
    }

    @Test
    public void t0300_checkMethodtranslationdoubledouble() {
        System.out.println("Vérifier la présence de la méthode translation");
        try {
            Method c = Point.class.getDeclaredMethod("translation",
                    double.class,
                    double.class);
            assertTrue("Revoir la méthode translation (Modificateurs)", c.getModifiers() == 1);
            assertTrue("Revoir la méthode translation (Retour)", c.getReturnType().equals(Point.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir l'en-tête de la méthode translation");
        }
    }

    @Test
    public void t0400_checkMethodrotationdouble() {
        System.out.println("Vérifier la présence de la méthode rotation");
        try {
            Method c = Point.class.getDeclaredMethod("rotation",
                    double.class);
            assertTrue("Revoir la méthode rotation (Modificateurs)", c.getModifiers() == 1);
            assertTrue("Revoir la méthode rotation (Retour)", c.getReturnType().equals(Point.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir l'en-tête de la méthode rotation");
        }
    }

    @Test
    public void t0500_isImmuable() throws FileNotFoundException {
        System.out.println("Vérification immuabilité");
        try {
            assertFalse("La classe Point devrait être immuable", IntrospectionUtilities.checkIfMutable(Point.class));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            fail();
        }
    }

    /**
     * Test of translation method, of class Point.
     */
    @Test
    public void t0600_testTranslation() {
        System.out.println("Test translation");
        try {
            Point p = Point.class.newInstance();
            double x = get("x", p);
            double y = get("y", p);
            for (int i = 0; i < 100; ++i) {
                double dx = getRandomValue();
                double dy = getRandomValue();
                p = (Point) IntrospectionUtilities.getFromMethodTA(Point.class, p, "translation", double.class, dx, double.class, dy);
                x += dx;
                y += dy;
                assertTrue(String.format("La translation s'est mal passée (%f, %f) <> (%f, %f)",x,y,get("x", p),get("y", p)),
                        x == get("x", p) && y == get("y", p)
                );
                testCoherenceDonnees(p);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
            fail("La translation s'est mal passée " + ex);
        }
    }

    /**
     * Test of rotation method, of class Point.
     */
    @Test
    public void t0700_testRotation() {
        System.out.println("Test rotation");
        try {
            Point p = Point.class.newInstance();
            double theta = get("theta", p);
            set("rho", 1.0, p); // Pour éviter le vecteur nul
            for (int i = 0; i < 100; ++i) {
                double dtheta = getRandomValue();
                p = (Point) IntrospectionUtilities.getFromMethodTA(Point.class, p, "rotation", double.class, dtheta);

                theta += dtheta;
                theta = theta % (2 * PI);
                if (theta < 0) {
                    theta += 2 * PI;
                }
                assertTrue(String.format("La rotation s'est mal passée (%f) <> (%f)",theta,get("theta", p)),
                        theta == get("theta", p)
                );
                testCoherenceDonnees(p);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
            fail("La rotation s'est mal passée");
        }
    }

    static double get(String nom, Point p) {
        try {
            Field fnom = Point.class.getDeclaredField(nom);
            fnom.setAccessible(true);
            return fnom.getDouble(p);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            fail(String.format("Attribut %s absent", nom));
            return 0;
        }
    }

    static void set(String nom, double v, Point p) {
        try {
            Field fnom = Point.class.getDeclaredField(nom);
            fnom.setAccessible(true);
            fnom.setDouble(p, v);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            fail(String.format("Attribut %s absent", nom));
        }
    }

    private void testCoherenceDonnees(Point p) {
        try {

            Field fx = Point.class.getDeclaredField("x");
            fx.setAccessible(true);

            Field fy = Point.class.getDeclaredField("y");
            fy.setAccessible(true);

            Field frho = Point.class.getDeclaredField("rho");
            frho.setAccessible(true);

            Field ftheta = Point.class.getDeclaredField("theta");
            ftheta.setAccessible(true);

            double x = fx.getDouble(p);
            double y = fy.getDouble(p);
            double rho = frho.getDouble(p);
            double theta = ftheta.getDouble(p);
            double computedTheta = atan2(y, x);
            if (computedTheta < 0) {
                computedTheta += 2 * PI; // Pour l'avoir dans [0, 2*PI]
            }
            assertEquals(String.format("(%f, %f) [%f:%f]", x, y, rho, theta), sqrt(x * x + y * y), rho, 1e-6);
            assertEquals(String.format("(%f, %f) [%f:%f]", x, y, rho, theta), computedTheta, theta, 1e-6);
        } catch (SecurityException | NoSuchFieldException | IllegalAccessException | IllegalArgumentException ex) {

        }
    }
}
