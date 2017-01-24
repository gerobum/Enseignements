/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

import exceptions.RacineCarréeDunNombreNégatif;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yvan
 */
public class MathosTest {

  private static Random random = new Random();

  public MathosTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  @Test
  public void testValeurAbsolue() {
    System.out.println("valeurAbsolue");
    assertEquals(0, Mathos.valeurAbsolue(0));
    assertEquals(10, Mathos.valeurAbsolue(10));
    assertEquals(10, Mathos.valeurAbsolue(-10));

    // Mauvaise idée de faire ça   
    for (int i = 0; i < 1000; i++) {
      int v = random.nextInt();
      int valabsv = v < 0 ? -v : v;
      assertEquals(valabsv, Mathos.valeurAbsolue(v));
    }

    // Une meilleure idée est d'essayer d'exprimer l'essence m?me
    // de la valeur absolue : |v| = |-v|
    for (int i = 0; i < 1000; i++) {
      int v = random.nextInt();
      assertEquals(Mathos.valeurAbsolue(v), Mathos.valeurAbsolue(-v));
    }
  }

  @Test
  public void testRacineCarrée() {
    System.out.println("racineCarrée");
    assertEquals(0, Mathos.racineCarrée(0), Mathos.epsilon);
    assertEquals(1, Mathos.racineCarrée(1), Mathos.epsilon);
    assertEquals(2, Mathos.racineCarrée(4), Mathos.epsilon);
    assertEquals(1.41421, Mathos.racineCarrée(2), Mathos.epsilon);

    for (int i = 0; i < 1000; i++) {
      double v = random.nextDouble() * random.nextInt(10000);
      double rv = Mathos.racineCarrée(v);
      assertEquals(v, rv * rv, Mathos.epsilon * Mathos.epsilon);
    }
    for (int i = 0; i < 1000; i++) {
      double v = random.nextDouble() * (10000 - random.nextInt(20000));
      assertEquals(Mathos.valeurAbsolue(v), Mathos.racineCarrée(v * v), Mathos.epsilon);
    }
  }

  @Test
  public void testValeurAbsolue_int() {
    System.out.println("valeurAbsolue");
  }

  @Test
  public void testValeurAbsolue_double() {
    System.out.println("valeurAbsolue");
  }

  @Test
  public void testRacineCarréeProtégée() {
    System.out.println("racineCarréeProtégée");
    try {
      assertEquals(0, Mathos.racineCarréeProtégée(0), Mathos.epsilon);
      assertEquals(1, Mathos.racineCarréeProtégée(1), Mathos.epsilon);
      assertEquals(2, Mathos.racineCarréeProtégée(4), Mathos.epsilon);
      assertEquals(1.41421, Mathos.racineCarréeProtégée(2), Mathos.epsilon);
      try {
        assertEquals(1.41421, Mathos.racineCarréeProtégée(-2), Mathos.epsilon);
        fail();
      } catch (RacineCarréeDunNombreNégatif rc) { }
      for (int i = 0; i < 1000; i++) {
        double v = random.nextDouble() * (10000 - random.nextInt(20000));
        try {
          double rv = Mathos.racineCarréeProtégée(v);
          assertEquals(v, rv * rv, Mathos.epsilon * Mathos.epsilon);
          assertFalse(v + "", v < 0);
        } catch (RacineCarréeDunNombreNégatif rc) {
          assertTrue(v + "", v < 0);
        }
      }
      for (int i = 0; i < 1000; i++) {
        double v = random.nextDouble() * (10000 - random.nextInt(20000));
        assertEquals(Mathos.valeurAbsolue(v), Mathos.racineCarréeProtégée(v * v), Mathos.epsilon);
      }
    } catch (RacineCarréeDunNombreNégatif ex) {
      fail();
    }
  }
}