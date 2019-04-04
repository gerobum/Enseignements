/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cf.ptm_pgm.geometrie;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.sqrt;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Random;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yvan
 */
public class PointTest {

    private static Random random = new Random();

    private static double getRandomValue() {
        int n = 500 - random.nextInt(1001);
        return n * random.nextDouble();
    }

    private static void attributPresence(String nom) {
        try {
            Field coordonnee = Point.class.getDeclaredField(nom);
            assertTrue(String.format("%s doit être un double", nom),
                    coordonnee.getType().equals(double.class)
                    || coordonnee.getType().equals(Double.class)
            );

            assertTrue(String.format("Revoir les modificateurs de %s", nom),
                    !Modifier.isStatic(coordonnee.getModifiers())
                    && Modifier.isPrivate(coordonnee.getModifiers())
            );
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("Vérifiez d'avoir un attribut nommé " + nom);
        }
    }

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
    public void checkConstructorsPresence() {
        System.out.println("Vérification de la présence des constructeurs");
        defaultConstructorPresence();
        xyConstructorPresence();
    }

    @Test
    public void checkGettersPresence() {
        System.out.println("Vérification de la présence des getters");
        getterPresence("x");
        getterPresence("y");
        getterPresence("rho");
        getterPresence("theta");
    }

    @Test
    public void checkSettersPresence() {
        System.out.println("Vérification de la présence des setters");
        setterPresence("x");
        setterPresence("y");
        setterPresence("rho");
        setterPresence("theta");
    }

    @Test
    public void checkAttributPresence() {
        System.out.println("Vérification des attributs");
        attributPresence("x");
        attributPresence("y");
        attributPresence("rho");
        attributPresence("theta");
    }

    @Test
    public void testDefaultConstructor() {
        System.out.println("Constructeur par défaut");
        try {
            Point p = Point.class.newInstance();
            Field x = Point.class.getDeclaredField("x");
            x.setAccessible(true);
            assertTrue(x.getDouble(p) == 0.0);

            Field y = Point.class.getDeclaredField("y");
            y.setAccessible(true);
            assertTrue(y.getDouble(p) == 0.0);

            Field rho = Point.class.getDeclaredField("rho");
            rho.setAccessible(true);
            assertTrue(rho.getDouble(p) == 0.0);

            Field theta = Point.class.getDeclaredField("theta");
            theta.setAccessible(true);
            assertTrue(theta.getDouble(p) == 0.0);

        } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException ex) {
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

    @Test
    public void testXYConstructor() {
        System.out.println("Constructeur Point(x,y)");
        try {
            Constructor c = Point.class.getConstructor(double.class, double.class);

            for (int i = 0; i < 100; ++i) {
                Point p = (Point) c.newInstance(getRandomValue(), getRandomValue());
                testCoherenceDonnees(p);
            }
        } catch (SecurityException | IllegalArgumentException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
        }
    }

    @Test
    public void testGetters() {
        System.out.println("Getters");
        try {
            Constructor c = Point.class.getConstructor(double.class, double.class);
            for (int i = 0; i < 100; i++) {
                double x = getRandomValue();
                double y = getRandomValue();
                Point p = (Point) c.newInstance(x, y);
                Method getX = Point.class.getDeclaredMethod("getX");
                Method getY = Point.class.getDeclaredMethod("getY");
                Method getRho = Point.class.getDeclaredMethod("getRho");
                Method getTheta = Point.class.getDeclaredMethod("getTheta");

                assertEquals(x, (double) getX.invoke(p), 0.0);
                assertEquals(y, (double) getY.invoke(p), 0.0);

                assertEquals(sqrt(x * x + y * y), (double) getRho.invoke(p), 0.0);
                double computedTheta = atan2(y, x);
                if (computedTheta < 0) {
                    computedTheta += 2 * PI; // Pour l'avoir dans [0, 2*PI]
                }
                assertEquals(computedTheta, (double) getTheta.invoke(p), 0.0);
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            fail("Revoir vos getters");
        }
    }

    @Test
    public void testSetters() {
        System.out.println("Setters");
        try {
            Point p = (Point) Point.class.newInstance();
            for (int i = 0; i < 100; i++) {

                Method setX = Point.class.getDeclaredMethod("setX", double.class);
                Method setY = Point.class.getDeclaredMethod("setY", double.class);
                Method setRho = Point.class.getDeclaredMethod("setRho", double.class);
                Method setTheta = Point.class.getDeclaredMethod("setTheta", double.class);

                Method getX = Point.class.getDeclaredMethod("getX");
                Method getY = Point.class.getDeclaredMethod("getY");
                Method getRho = Point.class.getDeclaredMethod("getRho");
                Method getTheta = Point.class.getDeclaredMethod("getTheta");

                double x = getRandomValue();
                setX.invoke(p, x);
                assertEquals(x, (double) getX.invoke(p), 0.0);
                testCoherenceDonnees(p);

                double y = getRandomValue();
                setY.invoke(p, y);
                assertEquals(y, (double) getY.invoke(p), 0.0);
                testCoherenceDonnees(p);

                double rho = getRandomValue();
                setRho.invoke(p, rho);
                assertEquals(abs(rho), (double) getRho.invoke(p), 0.0);
                testCoherenceDonnees(p);

                double theta = getRandomValue();
                setTheta.invoke(p, theta);
                theta %= (2 * PI);
                if (theta < 0) {
                    theta += 2 * PI;
                }
                assertEquals(theta, (double) getTheta.invoke(p), 0.0);
                testCoherenceDonnees(p);
            }
        } catch (IllegalArgumentException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            fail("Revoir vos setters");
        }
    }

    /**
     * Test of translation method, of class Point.
     */
    @Test
    public void testTranslation() {
        System.out.println("Translation");
        try {
            Point p = Point.class.newInstance();
            double x = get("x", p);
            double y = get("y", p);
            for (int i = 0; i < 100; ++i) {
                double dx = getRandomValue();
                double dy = getRandomValue();
                p.translation(dx, dy);
                x += dx;
                y += dy;
                assertTrue("La translation s'est mal passée",
                        x == get("x", p) && y == get("y", p)
                );
                testCoherenceDonnees(p);
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            fail("La translation s'est mal passée");
        }
    }

    /**
     * Test of rotation method, of class Point.
     */
    @Test
    public void testRotation() {
        System.out.println("Rotation");
        try {
            Point p = Point.class.newInstance();
            double theta = get("theta", p);
            set("rho", 1.0, p); // Pour éviter le vecteur nul
            for (int i = 0; i < 100; ++i) {
                double dtheta = getRandomValue();
                p.rotation(dtheta);
                theta += dtheta;
                theta = theta % (2 * PI);
                if (theta < 0) {
                    theta += 2 * PI;
                }
                assertTrue("La rotation s'est mal passée",
                        theta == get("theta", p)
                );
                testCoherenceDonnees(p);
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            fail("La rotation s'est mal passée");
        }
    }
}
