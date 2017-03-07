/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitaires;

import exceptions.RacineCarr�eDunNombreN�gatif;
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

    // Mauvaise id�e de faire �a   
    for (int i = 0; i < 1000; i++) {
      int v = random.nextInt();
      int valabsv = v < 0 ? -v : v;
      assertEquals(valabsv, Mathos.valeurAbsolue(v));
    }

    // Une meilleure id�e est d'essayer d'exprimer l'essence m?me
    // de la valeur absolue : |v| = |-v|
    for (int i = 0; i < 1000; i++) {
      int v = random.nextInt();
      assertEquals(Mathos.valeurAbsolue(v), Mathos.valeurAbsolue(-v));
    }
  }

  @Test
  public void testRacineCarr�e() {
    System.out.println("racineCarr�e");
    assertEquals(0, Mathos.racineCarr�e(0), Mathos.epsilon);
    assertEquals(1, Mathos.racineCarr�e(1), Mathos.epsilon);
    assertEquals(2, Mathos.racineCarr�e(4), Mathos.epsilon);
    assertEquals(1.41421, Mathos.racineCarr�e(2), Mathos.epsilon);

    for (int i = 0; i < 1000; i++) {
      double v = random.nextDouble() * random.nextInt(10000);
      double rv = Mathos.racineCarr�e(v);
      assertEquals(v, rv * rv, Mathos.epsilon * Mathos.epsilon);
    }
    for (int i = 0; i < 1000; i++) {
      double v = random.nextDouble() * (10000 - random.nextInt(20000));
      assertEquals(Mathos.valeurAbsolue(v), Mathos.racineCarr�e(v * v), Mathos.epsilon);
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
  public void testRacineCarr�eProt�g�e() {
    System.out.println("racineCarr�eProt�g�e");
    try {
      assertEquals(0, Mathos.racineCarr�eProt�g�e(0), Mathos.epsilon);
      assertEquals(1, Mathos.racineCarr�eProt�g�e(1), Mathos.epsilon);
      assertEquals(2, Mathos.racineCarr�eProt�g�e(4), Mathos.epsilon);
      assertEquals(1.41421, Mathos.racineCarr�eProt�g�e(2), Mathos.epsilon);
      try {
        assertEquals(1.41421, Mathos.racineCarr�eProt�g�e(-2), Mathos.epsilon);
        fail();
      } catch (RacineCarr�eDunNombreN�gatif rc) { }
      for (int i = 0; i < 1000; i++) {
        double v = random.nextDouble() * (10000 - random.nextInt(20000));
        try {
          double rv = Mathos.racineCarr�eProt�g�e(v);
          assertEquals(v, rv * rv, Mathos.epsilon * Mathos.epsilon);
          assertFalse(v + "", v < 0);
        } catch (RacineCarr�eDunNombreN�gatif rc) {
          assertTrue(v + "", v < 0);
        }
      }
      for (int i = 0; i < 1000; i++) {
        double v = random.nextDouble() * (10000 - random.nextInt(20000));
        assertEquals(Mathos.valeurAbsolue(v), Mathos.racineCarr�eProt�g�e(v * v), Mathos.epsilon);
      }
    } catch (RacineCarr�eDunNombreN�gatif ex) {
      fail();
    }
  }
}