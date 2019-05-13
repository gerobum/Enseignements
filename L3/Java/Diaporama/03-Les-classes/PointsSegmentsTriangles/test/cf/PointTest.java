/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package cf;

import reflect.ReflectUtilities;
import java.lang.reflect.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;
// IMPORTER EXPLICITEMENT LES CLASSES A TESTER
import ec.Point;
import ec.Segment;
import ec.Triangle;

/**
 *
 * @author yvan
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PointTest {
// ------------ Point -------------

    @Test
    public void p000001000_checkFieldPointx() {
        System.out.println("Check attribut x");
        try {
            Field x = Point.class.getDeclaredField("x");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(double.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p000001000_checkFieldPointy() {
        System.out.println("Check attribut y");
        try {
            Field x = Point.class.getDeclaredField("y");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(double.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p000002000_checkConstructorPointdoubledouble() {
        System.out.println("Présence Constructeur de cf.Point");
        try {
            Constructor x = Point.class.getDeclaredConstructor(
                    double.class,
                    double.class
            );
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Constructeur de cf.Point");
        }
    }

    @Test
    public void p000003000_checkMethodPointgetY() {
        System.out.println("getY");
        try {
            Method x = Point.class.getDeclaredMethod("getY"
            );
            assertTrue("Revoir getY (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getY (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getY (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getY (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getY (Retour)", x.getReturnType().equals(double.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getY");
        }
    }

    @Test
    public void p000004000_checkMethodPointsetYdouble() {
        System.out.println("setY");
        try {
            Method x = Point.class.getDeclaredMethod("setY",
                     double.class);
            assertTrue("Revoir setY (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setY (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setY (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setY (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setY (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setY");
        }
    }

    @Test
    public void p000003000_checkMethodPointgetX() {
        System.out.println("getX");
        try {
            Method x = Point.class.getDeclaredMethod("getX"
            );
            assertTrue("Revoir getX (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getX (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getX (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getX (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getX (Retour)", x.getReturnType().equals(double.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getX");
        }
    }

    @Test
    public void p000004000_checkMethodPointsetXdouble() {
        System.out.println("setX");
        try {
            Method x = Point.class.getDeclaredMethod("setX",
                     double.class);
            assertTrue("Revoir setX (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setX (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setX (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setX (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setX (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setX");
        }
    }

    @Test
    public void p000005000_checkMethodPointtoString() {
        System.out.println("toString");
        try {
            Method x = Point.class.getDeclaredMethod("toString"
            );
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir toString (Retour)", x.getReturnType().equals(String.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir toString");
        }
    }

    @Test
    public void p000007000_checkSetterPointx() {
        System.out.println("Check setter x");
        try {
            Method x = Point.class.getDeclaredMethod("setX", double.class);
            assertTrue("Revoir setX", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Point.class);
            for (int i = 0; i < 100; ++i) {
                double v = (double) ReflectUtilities.randomValue(double.class);
                ReflectUtilities.getFromMethodTA(
                        Point.class,
                        o,
                        "setX",
                        double.class,
                        v
                );
                assertTrue("Revoir setX", ReflectUtilities.getAttribut(
                        Point.class,
                        o,
                        "x"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setX");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setX");
        }
    }

    @Test
    public void p000007000_checkSetterPointy() {
        System.out.println("Check setter y");
        try {
            Method x = Point.class.getDeclaredMethod("setY", double.class);
            assertTrue("Revoir setY", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Point.class);
            for (int i = 0; i < 100; ++i) {
                double v = (double) ReflectUtilities.randomValue(double.class);
                ReflectUtilities.getFromMethodTA(
                        Point.class,
                        o,
                        "setY",
                        double.class,
                        v
                );
                assertTrue("Revoir setY", ReflectUtilities.getAttribut(
                        Point.class,
                        o,
                        "y"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setY");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setY");
        }
    }

    @Test
    public void p000006000_checkGetterPointX() {
        System.out.println("Check getter x");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Point.class);
                Object attr = ReflectUtilities.getAttribut(Point.class, o, "x");
                Object getAttr = ReflectUtilities.getFromMethod(Point.class, o, "getX");
                assertTrue("Revoir getX", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getX");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getX");
        }
    }

    @Test
    public void p000006000_checkGetterPointY() {
        System.out.println("Check getter y");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Point.class);
                Object attr = ReflectUtilities.getAttribut(Point.class, o, "y");
                Object getAttr = ReflectUtilities.getFromMethod(Point.class, o, "getY");
                assertTrue("Revoir getY", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getY");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getY");
        }
    }

    @Test
    public void p000010000_testMethodPointtoString() {
        System.out.println("Test Method Point.toString");
        Class<?> classRef = cf.Point.class;
        Class<?> classToTest = Point.class;
        for (int i = 0; i < 100; ++i) {
            try {
                StringBuilder msg = new StringBuilder("revoir Point.toString() --> ");
                boolean result = ReflectUtilities.sameResult(msg, classRef, classToTest, "toString");
                assertTrue(msg.toString(), result);
            } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException ex) {
                fail("revoir Point.toString()");
            }
        }
    }

// ------------ Segment -------------
    @Test
    public void p000020000_checkFieldSegmentp1() {
        System.out.println("Check attribut p1");
        try {
            Field x = Segment.class.getDeclaredField("p1");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(Point.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p000020000_checkFieldSegmentp2() {
        System.out.println("Check attribut p2");
        try {
            Field x = Segment.class.getDeclaredField("p2");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(Point.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p000021000_checkConstructorSegmentPointPoint() {
        System.out.println("Présence Constructeur de cf.Segment");
        try {
            Constructor x = Segment.class.getDeclaredConstructor(
                    Point.class,
                    Point.class
            );
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Constructeur de cf.Segment");
        }
    }

    @Test
    public void p000022000_checkMethodSegmentgetP2() {
        System.out.println("getP2");
        try {
            Method x = Segment.class.getDeclaredMethod("getP2"
            );
            assertTrue("Revoir getP2 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getP2 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getP2 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getP2 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getP2 (Retour)", x.getReturnType().equals(Point.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getP2");
        }
    }

    @Test
    public void p000023000_checkMethodSegmentsetP1Point() {
        System.out.println("setP1");
        try {
            Method x = Segment.class.getDeclaredMethod("setP1",
                     Point.class);
            assertTrue("Revoir setP1 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setP1 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setP1 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setP1 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setP1 (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setP1");
        }
    }

    @Test
    public void p000022000_checkMethodSegmentgetP1() {
        System.out.println("getP1");
        try {
            Method x = Segment.class.getDeclaredMethod("getP1"
            );
            assertTrue("Revoir getP1 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getP1 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getP1 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getP1 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getP1 (Retour)", x.getReturnType().equals(Point.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getP1");
        }
    }

    @Test
    public void p000023000_checkMethodSegmentsetP2Point() {
        System.out.println("setP2");
        try {
            Method x = Segment.class.getDeclaredMethod("setP2",
                     Point.class);
            assertTrue("Revoir setP2 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setP2 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setP2 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setP2 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setP2 (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setP2");
        }
    }

    @Test
    public void p000024000_checkMethodSegmentgetLongueur() {
        System.out.println("getLongueur");
        try {
            Method x = Segment.class.getDeclaredMethod("getLongueur"
            );
            assertTrue("Revoir getLongueur (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getLongueur (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getLongueur (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getLongueur (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getLongueur (Retour)", x.getReturnType().equals(double.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getLongueur");
        }
    }

    @Test
    public void p000025000_checkMethodSegmenttoString() {
        System.out.println("toString");
        try {
            Method x = Segment.class.getDeclaredMethod("toString"
            );
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir toString (Retour)", x.getReturnType().equals(String.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir toString");
        }
    }

    @Test
    public void p000027000_checkSetterSegmentp1() {
        System.out.println("Check setter p1");
        try {
            Method x = Segment.class.getDeclaredMethod("setP1", Point.class);
            assertTrue("Revoir setP1", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Segment.class);
            for (int i = 0; i < 100; ++i) {
                Point v = (Point) ReflectUtilities.randomValue(Point.class);
                ReflectUtilities.getFromMethodTA(
                        Segment.class,
                        o,
                        "setP1",
                        Point.class,
                        v
                );
                assertTrue("Revoir setP1", ReflectUtilities.getAttribut(
                        Segment.class,
                        o,
                        "p1"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setP1");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setP1");
        }
    }

    @Test
    public void p000027000_checkSetterSegmentp2() {
        System.out.println("Check setter p2");
        try {
            Method x = Segment.class.getDeclaredMethod("setP2", Point.class);
            assertTrue("Revoir setP2", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Segment.class);
            for (int i = 0; i < 100; ++i) {
                Point v = (Point) ReflectUtilities.randomValue(Point.class);
                ReflectUtilities.getFromMethodTA(
                        Segment.class,
                        o,
                        "setP2",
                        Point.class,
                        v
                );
                assertTrue("Revoir setP2", ReflectUtilities.getAttribut(
                        Segment.class,
                        o,
                        "p2"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setP2");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setP2");
        }
    }

    @Test
    public void p000026000_checkGetterSegmentP1() {
        System.out.println("Check getter p1");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Segment.class);
                Object attr = ReflectUtilities.getAttribut(Segment.class, o, "p1");
                Object getAttr = ReflectUtilities.getFromMethod(Segment.class, o, "getP1");
                assertTrue("Revoir getP1", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getP1");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getP1");
        }
    }

    @Test
    public void p000026000_checkGetterSegmentP2() {
        System.out.println("Check getter p2");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Segment.class);
                Object attr = ReflectUtilities.getAttribut(Segment.class, o, "p2");
                Object getAttr = ReflectUtilities.getFromMethod(Segment.class, o, "getP2");
                assertTrue("Revoir getP2", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getP2");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getP2");
        }
    }

    @Test
    public void p000028000_testMethodSegmentgetLongueur() {
        System.out.println("Test Method Segment.getLongueur");
        Class<?> classRef = cf.Segment.class;
        Class<?> classToTest = Segment.class;
        for (int i = 0; i < 100; ++i) {
            try {
                StringBuilder msg = new StringBuilder("revoir Segment.getLongueur() --> ");
                boolean result = ReflectUtilities.sameResult(msg, classRef, classToTest, "getLongueur");
                assertTrue(msg.toString(), result);
            } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException ex) {
                fail("revoir Segment.getLongueur()");
            }
        }
    }

    @Test
    public void p000028000_testMethodSegmenttoString() {
        System.out.println("Test Method Segment.toString");
        Class<?> classRef = cf.Segment.class;
        Class<?> classToTest = Segment.class;
        for (int i = 0; i < 100; ++i) {
            try {
                StringBuilder msg = new StringBuilder("revoir Segment.toString() --> ");
                boolean result = ReflectUtilities.sameResult(msg, classRef, classToTest, "toString");
                assertTrue(msg.toString(), result);
            } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException ex) {
                fail("revoir Segment.toString()");
            }
        }
    }

// ------------ Triangle -------------
    @Test
    public void p000030000_checkFieldTrianglec1() {
        System.out.println("Check attribut c1");
        try {
            Field x = Triangle.class.getDeclaredField("c1");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(Segment.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p000030000_checkFieldTrianglec2() {
        System.out.println("Check attribut c2");
        try {
            Field x = Triangle.class.getDeclaredField("c2");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(Segment.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p000030000_checkFieldTrianglec3() {
        System.out.println("Check attribut c3");
        try {
            Field x = Triangle.class.getDeclaredField("c3");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(Segment.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p000031000_checkConstructorTriangleSegmentSegmentSegment() {
        System.out.println("Présence Constructeur de cf.Triangle");
        try {
            Constructor x = Triangle.class.getDeclaredConstructor(
                    Segment.class,
                    Segment.class,
                    Segment.class
            );
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Constructeur de cf.Triangle");
        }
    }

    @Test
    public void p000032000_checkMethodTrianglegetC2() {
        System.out.println("getC2");
        try {
            Method x = Triangle.class.getDeclaredMethod("getC2"
            );
            assertTrue("Revoir getC2 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getC2 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getC2 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getC2 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getC2 (Retour)", x.getReturnType().equals(Segment.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getC2");
        }
    }

    @Test
    public void p000033000_checkMethodTrianglesetC2Segment() {
        System.out.println("setC2");
        try {
            Method x = Triangle.class.getDeclaredMethod("setC2",
                     Segment.class);
            assertTrue("Revoir setC2 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setC2 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setC2 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setC2 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setC2 (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setC2");
        }
    }

    @Test
    public void p000032000_checkMethodTrianglegetC3() {
        System.out.println("getC3");
        try {
            Method x = Triangle.class.getDeclaredMethod("getC3"
            );
            assertTrue("Revoir getC3 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getC3 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getC3 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getC3 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getC3 (Retour)", x.getReturnType().equals(Segment.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getC3");
        }
    }

    @Test
    public void p000033000_checkMethodTrianglesetC3Segment() {
        System.out.println("setC3");
        try {
            Method x = Triangle.class.getDeclaredMethod("setC3",
                     Segment.class);
            assertTrue("Revoir setC3 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setC3 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setC3 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setC3 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setC3 (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setC3");
        }
    }

    @Test
    public void p000034000_checkMethodTrianglegetPerimetre() {
        System.out.println("getPerimetre");
        try {
            Method x = Triangle.class.getDeclaredMethod("getPerimetre"
            );
            assertTrue("Revoir getPerimetre (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getPerimetre (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getPerimetre (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getPerimetre (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getPerimetre (Retour)", x.getReturnType().equals(double.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getPerimetre");
        }
    }

    @Test
    public void p000034000_checkMethodTrianglegetBaryCentre() {
        System.out.println("getBaryCentre");
        try {
            Method x = Triangle.class.getDeclaredMethod("getBaryCentre"
            );
            assertTrue("Revoir getBaryCentre (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getBaryCentre (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getBaryCentre (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getBaryCentre (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getBaryCentre (Retour)", x.getReturnType().equals(Point.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getBaryCentre");
        }
    }

    @Test
    public void p000034000_checkMethodTrianglegetSurface() {
        System.out.println("getSurface");
        try {
            Method x = Triangle.class.getDeclaredMethod("getSurface"
            );
            assertTrue("Revoir getSurface (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getSurface (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getSurface (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getSurface (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getSurface (Retour)", x.getReturnType().equals(double.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getSurface");
        }
    }

    @Test
    public void p000032000_checkMethodTrianglegetC1() {
        System.out.println("getC1");
        try {
            Method x = Triangle.class.getDeclaredMethod("getC1"
            );
            assertTrue("Revoir getC1 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getC1 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getC1 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getC1 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getC1 (Retour)", x.getReturnType().equals(Segment.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getC1");
        }
    }

    @Test
    public void p000033000_checkMethodTrianglesetC1Segment() {
        System.out.println("setC1");
        try {
            Method x = Triangle.class.getDeclaredMethod("setC1",
                     Segment.class);
            assertTrue("Revoir setC1 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setC1 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setC1 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setC1 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setC1 (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setC1");
        }
    }

    @Test
    public void p000036000_checkSetterTrianglec1() {
        System.out.println("Check setter c1");
        try {
            Method x = Triangle.class.getDeclaredMethod("setC1", Segment.class);
            assertTrue("Revoir setC1", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Triangle.class);
            for (int i = 0; i < 100; ++i) {
                Segment v = (Segment) ReflectUtilities.randomValue(Segment.class);
                ReflectUtilities.getFromMethodTA(
                        Triangle.class,
                        o,
                        "setC1",
                        Segment.class,
                        v
                );
                assertTrue("Revoir setC1", ReflectUtilities.getAttribut(
                        Triangle.class,
                        o,
                        "c1"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setC1");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setC1");
        }
    }

    @Test
    public void p000036000_checkSetterTrianglec2() {
        System.out.println("Check setter c2");
        try {
            Method x = Triangle.class.getDeclaredMethod("setC2", Segment.class);
            assertTrue("Revoir setC2", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Triangle.class);
            for (int i = 0; i < 100; ++i) {
                Segment v = (Segment) ReflectUtilities.randomValue(Segment.class);
                ReflectUtilities.getFromMethodTA(
                        Triangle.class,
                        o,
                        "setC2",
                        Segment.class,
                        v
                );
                assertTrue("Revoir setC2", ReflectUtilities.getAttribut(
                        Triangle.class,
                        o,
                        "c2"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setC2");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setC2");
        }
    }

    @Test
    public void p000036000_checkSetterTrianglec3() {
        System.out.println("Check setter c3");
        try {
            Method x = Triangle.class.getDeclaredMethod("setC3", Segment.class);
            assertTrue("Revoir setC3", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Triangle.class);
            for (int i = 0; i < 100; ++i) {
                Segment v = (Segment) ReflectUtilities.randomValue(Segment.class);
                ReflectUtilities.getFromMethodTA(
                        Triangle.class,
                        o,
                        "setC3",
                        Segment.class,
                        v
                );
                assertTrue("Revoir setC3", ReflectUtilities.getAttribut(
                        Triangle.class,
                        o,
                        "c3"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setC3");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setC3");
        }
    }

    @Test
    public void p000035000_checkGetterTriangleC1() {
        System.out.println("Check getter c1");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Triangle.class);
                Object attr = ReflectUtilities.getAttribut(Triangle.class, o, "c1");
                Object getAttr = ReflectUtilities.getFromMethod(Triangle.class, o, "getC1");
                assertTrue("Revoir getC1", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getC1");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getC1");
        }
    }

    @Test
    public void p000035000_checkGetterTriangleC2() {
        System.out.println("Check getter c2");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Triangle.class);
                Object attr = ReflectUtilities.getAttribut(Triangle.class, o, "c2");
                Object getAttr = ReflectUtilities.getFromMethod(Triangle.class, o, "getC2");
                assertTrue("Revoir getC2", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getC2");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getC2");
        }
    }

    @Test
    public void p000035000_checkGetterTriangleC3() {
        System.out.println("Check getter c3");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Triangle.class);
                Object attr = ReflectUtilities.getAttribut(Triangle.class, o, "c3");
                Object getAttr = ReflectUtilities.getFromMethod(Triangle.class, o, "getC3");
                assertTrue("Revoir getC3", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getC3");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getC3");
        }
    }

    @Test
    public void p000037000_testMethodTrianglegetPerimetre() {
        System.out.println("Test Method Triangle.getPerimetre");
        Class<?> classRef = cf.Triangle.class;
        Class<?> classToTest = Triangle.class;
        for (int i = 0; i < 100; ++i) {
            try {
                StringBuilder msg = new StringBuilder("revoir Triangle.getPerimetre() --> ");
                boolean result = ReflectUtilities.sameResult(msg, classRef, classToTest, "getPerimetre");
                assertTrue(msg.toString(), result);
            } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException ex) {
                fail("revoir Triangle.getPerimetre()");
            }
        }
    }

    @Test
    public void p000037000_testMethodTrianglegetBaryCentre() {
        System.out.println("Test Method Triangle.getBaryCentre");
        Class<?> classRef = cf.Triangle.class;
        Class<?> classToTest = Triangle.class;
        for (int i = 0; i < 100; ++i) {
            try {
                StringBuilder msg = new StringBuilder("revoir Triangle.getBaryCentre() --> ");
                boolean result = ReflectUtilities.sameResult(msg, classRef, classToTest, "getBaryCentre");
                assertTrue(msg.toString(), result);
            } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException ex) {
                fail("revoir Triangle.getBaryCentre()");
            }
        }
    }

    @Test
    public void p000037000_testMethodTrianglegetSurface() {
        System.out.println("Test Method Triangle.getSurface");
        Class<?> classRef = cf.Triangle.class;
        Class<?> classToTest = Triangle.class;
        for (int i = 0; i < 100; ++i) {
            try {
                StringBuilder msg = new StringBuilder("revoir Triangle.getSurface() --> ");
                boolean result = ReflectUtilities.sameResult(msg, classRef, classToTest, "getSurface");
                assertTrue(msg.toString(), result);
            } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException ex) {
                fail("revoir Triangle.getSurface()");
            }
        }
    }


    /*
    @Test
    public void p0000_testMethodPointtoString() {
        System.out.println("Test Method Point.toString");
        Class<?> classRef = cf.Point.class;
        Class<?> classToTest = Point.class;
        for (int i = 0; i < 100; ++i) {
            try {
                StringBuilder msg = new StringBuilder("revoir Point.toString() --> ");
                boolean result = ReflectUtilities.sameResult(msg, classRef, classToTest, "toString");
                assertTrue(msg.toString(), result);
            } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException ex) {
                fail("revoir Point.toString()");
            }
        }
    }

    @Test
    public void p0000_testMethodSegmentgetLongueur() {
        System.out.println("Test Method Segment.getLongueur");
        Class<?> classRef = cf.Segment.class;
        Class<?> classToTest = Segment.class;
        for (int i = 0; i < 100; ++i) {
            try {
                StringBuilder msg = new StringBuilder("revoir Segment.getLongueur() --> ");
                boolean result = ReflectUtilities.sameResult(msg, classRef, classToTest, "getLongueur");
                assertTrue(msg.toString(), result);
            } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException ex) {
                fail("revoir Segment.getLongueur()" + ex);
            }
        }
    }

    @Test
    public void p2000_testMethodSegmenttoString() {
        System.out.println("Test Method Segment.toString");
        Class<?> classRef = cf.Segment.class;
        Class<?> classToTest = Segment.class;
        for (int i = 0; i < 100; ++i) {
            try {
                StringBuilder msg = new StringBuilder("revoir Segment.toString() --> ");
                boolean result = ReflectUtilities.sameResult(msg, classRef, classToTest, "toString");
                assertTrue(msg.toString(), result);
            } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException | InstantiationException ex) {
                fail("revoir Segment.toString()");
            }
        }
    }

    @Test
    public void p0100_checkFieldx() {
        System.out.println("Check attribut x");
        try {
            Field x = Point.class.getDeclaredField("x");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(double.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p0100_checkFieldy() {
        System.out.println("Check attribut y");
        try {
            Field x = Point.class.getDeclaredField("y");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(double.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p0200_checkConstructorPointdoubledouble() {
        System.out.println("Présence Constructeur de cf.Point");
        try {
            Constructor x = Point.class.getDeclaredConstructor(
                    double.class,
                    double.class
            );
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Constructeur de cf.Point");
        }
    }

    @Test
    public void p0300_checkMethodgetY() {
        System.out.println("getY");
        try {
            Method x = Point.class.getDeclaredMethod("getY"
            );
            assertTrue("Revoir getY (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getY (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getY (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getY (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getY (Retour)", x.getReturnType().equals(double.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getY");
        }
    }

    @Test
    public void p0400_checkMethodsetYdouble() {
        System.out.println("setY");
        try {
            Method x = Point.class.getDeclaredMethod("setY",
                    double.class);
            assertTrue("Revoir setY (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setY (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setY (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setY (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setY (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setY");
        }
    }

    @Test
    public void p0300_checkMethodgetX() {
        System.out.println("getX");
        try {
            Method x = Point.class.getDeclaredMethod("getX"
            );
            assertTrue("Revoir getX (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getX (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getX (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getX (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getX (Retour)", x.getReturnType().equals(double.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getX");
        }
    }

    @Test
    public void p0400_checkMethodsetXdouble() {
        System.out.println("setX");
        try {
            Method x = Point.class.getDeclaredMethod("setX",
                    double.class);
            assertTrue("Revoir setX (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setX (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setX (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setX (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setX (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setX");
        }
    }

    @Test
    public void p0500_checkMethodtoString() {
        System.out.println("toString");
        try {
            Method x = Point.class.getDeclaredMethod("toString"
            );
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir toString (Retour)", x.getReturnType().equals(String.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir toString");
        }
    }

    @Test
    public void p00000_checkSetterx() {
        System.out.println("Check setter x");
        try {
            Method x = Point.class.getDeclaredMethod("setX", double.class);
            assertTrue("Revoir setX", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Point.class);
            for (int i = 0; i < 100; ++i) {
                double v = (double) ReflectUtilities.randomValue(double.class);
                ReflectUtilities.getFromMethodTA(
                        Point.class,
                        o,
                        "setX",
                        double.class,
                        v
                );
                assertTrue("Revoir setX", ReflectUtilities.getAttribut(
                        Point.class,
                        o,
                        "x"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setX");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setX");
        }
    }

    @Test
    public void p00000_checkSettery() {
        System.out.println("Check setter y");
        try {
            Method x = Point.class.getDeclaredMethod("setY", double.class);
            assertTrue("Revoir setY", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Point.class);
            for (int i = 0; i < 100; ++i) {
                double v = (double) ReflectUtilities.randomValue(double.class);
                ReflectUtilities.getFromMethodTA(
                        Point.class,
                        o,
                        "setY",
                        double.class,
                        v
                );
                assertTrue("Revoir setY", ReflectUtilities.getAttribut(
                        Point.class,
                        o,
                        "y"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setY");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setY");
        }
    }

    @Test
    public void p00000_checkGetterX() {
        System.out.println("Check getter x");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Point.class);
                Object attr = ReflectUtilities.getAttribut(Point.class, o, "x");
                Object getAttr = ReflectUtilities.getFromMethod(Point.class, o, "getX");
                assertTrue("Revoir getX", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getX");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getX");
        }
    }

    @Test
    public void p00000_checkGetterY() {
        System.out.println("Check getter y");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Point.class);
                Object attr = ReflectUtilities.getAttribut(Point.class, o, "y");
                Object getAttr = ReflectUtilities.getFromMethod(Point.class, o, "getY");
                assertTrue("Revoir getY", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getY");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getY");
        }
    }

// ------------ Segment -------------
    @Test
    public void p0600_checkFieldp1() {
        System.out.println("Check attribut p1");
        try {
            Field x = Segment.class.getDeclaredField("p1");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(Point.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p0600_checkFieldp2() {
        System.out.println("Check attribut p2");
        try {
            Field x = Segment.class.getDeclaredField("p2");
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(2) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(2) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(2) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(2) == Modifier.isStatic(x.getModifiers()));
            assertTrue("", x.getType().equals(Point.class));
        } catch (NoSuchFieldException | SecurityException ex) {
            fail("");
        }
    }

    @Test
    public void p0700_checkConstructorSegmentPointPoint() {
        System.out.println("Présence Constructeur de cf.Segment");
        try {
            Constructor x = Segment.class.getDeclaredConstructor(
                    Point.class,
                    Point.class
            );
            assertTrue("Revoir  (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir  (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Constructeur de cf.Segment");
        }
    }

    @Test
    public void p0900_checkMethodgetP1() {
        System.out.println("getP1");
        try {
            Method x = Segment.class.getDeclaredMethod("getP1"
            );
            assertTrue("Revoir getP1 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getP1 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getP1 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getP1 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getP1 (Retour)", x.getReturnType().equals(Point.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getP1");
        }
    }

    @Test
    public void p0800_checkMethodsetP1Point() {
        System.out.println("setP1");
        try {
            Method x = Segment.class.getDeclaredMethod("setP1",
                    Point.class);
            assertTrue("Revoir setP1 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setP1 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setP1 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setP1 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setP1 (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setP1");
        }
    }

    @Test
    public void p0800_checkMethodgetP2() {
        System.out.println("getP2");
        try {
            Method x = Segment.class.getDeclaredMethod("getP2"
            );
            assertTrue("Revoir getP2 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getP2 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getP2 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getP2 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getP2 (Retour)", x.getReturnType().equals(Point.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getP2");
        }
    }

    @Test
    public void p0900_checkMethodsetP2Point() {
        System.out.println("setP2");
        try {
            Method x = Segment.class.getDeclaredMethod("setP2",
                    Point.class);
            assertTrue("Revoir setP2 (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir setP2 (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir setP2 (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir setP2 (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir setP2 (Retour)", x.getReturnType().equals(void.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir setP2");
        }
    }

    @Test
    public void p0800_checkMethodgetLongueur() {
        System.out.println("getLongueur");
        try {
            Method x = Segment.class.getDeclaredMethod("getLongueur"
            );
            assertTrue("Revoir getLongueur (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir getLongueur (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir getLongueur (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir getLongueur (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir getLongueur (Retour)", x.getReturnType().equals(double.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir getLongueur");
        }
    }

    @Test
    public void p1000_checkMethodtoString() {
        System.out.println("toString");
        try {
            Method x = Segment.class.getDeclaredMethod("toString"
            );
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPrivate(1) == Modifier.isPrivate(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isProtected(1) == Modifier.isProtected(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isPublic(1) == Modifier.isPublic(x.getModifiers()));
            assertTrue("Revoir toString (Modificateurs)", Modifier.isStatic(1) == Modifier.isStatic(x.getModifiers()));
            assertTrue("Revoir toString (Retour)", x.getReturnType().equals(String.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            fail("Revoir toString");
        }
    }

    @Test
    public void p01200_checkSetterp1() {
        System.out.println("Check setter p1");
        try {
            Method x = Segment.class.getDeclaredMethod("setP1", Point.class);
            assertTrue("Revoir setP1", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Segment.class);
            for (int i = 0; i < 100; ++i) {
                Point v = (Point) ReflectUtilities.randomValue(Point.class);
                ReflectUtilities.getFromMethodTA(
                        Segment.class,
                        o,
                        "setP1",
                        Point.class,
                        v
                );
                assertTrue("Revoir setP1", ReflectUtilities.getAttribut(
                        Segment.class,
                        o,
                        "p1"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setP1");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setP1");
        }
    }

    @Test
    public void p01200_checkSetterp2() {
        System.out.println("Check setter p2");
        try {
            Method x = Segment.class.getDeclaredMethod("setP2", Point.class);
            assertTrue("Revoir setP2", x.getReturnType().equals(void.class));
            Object o = ReflectUtilities.randomValue(Segment.class);
            for (int i = 0; i < 100; ++i) {
                Point v = (Point) ReflectUtilities.randomValue(Point.class);
                ReflectUtilities.getFromMethodTA(
                        Segment.class,
                        o,
                        "setP2",
                        Point.class,
                        v
                );
                assertTrue("Revoir setP2", ReflectUtilities.getAttribut(
                        Segment.class,
                        o,
                        "p2"
                ).equals(v));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir setP2");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester setP2");
        }
    }

    @Test
    public void p01100_checkGetterP1() {
        System.out.println("Check getter p1");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Segment.class);
                Object attr = ReflectUtilities.getAttribut(Segment.class, o, "p1");
                Object getAttr = ReflectUtilities.getFromMethod(Segment.class, o, "getP1");
                assertTrue("Revoir getP1", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getP1");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getP1");
        }
    }

    @Test
    public void p01100_checkGetterP2() {
        System.out.println("Check getter p2");
        try {
            for (int i = 0; i < 100; ++i) {
                Object o = ReflectUtilities.randomValue(Segment.class);
                Object attr = ReflectUtilities.getAttribut(Segment.class, o, "p2");
                Object getAttr = ReflectUtilities.getFromMethod(Segment.class, o, "getP2");
                assertTrue("Revoir getP2", ReflectUtilities.equals(attr, getAttr));
            }
        } catch (SecurityException | NoSuchMethodException | NoSuchFieldException ex) {
            fail("Revoir getP2");
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Il manque un constructeur pour tester getP2");
        }
    }
     */
}
