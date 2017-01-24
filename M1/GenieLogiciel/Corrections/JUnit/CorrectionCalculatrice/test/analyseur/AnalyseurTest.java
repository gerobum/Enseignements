/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseur;

import java.util.Random;
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
public class AnalyseurTest {

  public static Random random = new Random();

  public AnalyseurTest() {
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
  public void testEstUnChiffre() {
    System.out.println("estUnChiffre");
    assertTrue(Analyseur.estUnChiffre('0', 2));
    assertTrue(Analyseur.estUnChiffre('1', 2));
    assertFalse(Analyseur.estUnChiffre('2', 2));
    assertFalse(Analyseur.estUnChiffre(' ', 2));

    for (char c = Character.MIN_VALUE; c < Character.MAX_VALUE; c++) {
      for (int base = 2; base <= 10; base++) {
        if (c >= '0' && c < '0' + base) {
          assertTrue((char) c + " devrait être "
                  + "un chiffre en base "
                  + base, Analyseur.estUnChiffre((char) c, base));
        } else {
          assertFalse((char) c + " ne devrait pas être "
                  + "un chiffre en "
                  + "base " + base,
                  Analyseur.estUnChiffre((char) c, base));
        }
      }
      for (int base = 11; base <= 36; base++) {
        if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'a'
                + (base - 11))
                || (c >= 'A' && c <= 'A' + (base - 11))) {
          assertTrue((char) c + " devrait être un chiffre en "
                  + "base "
                  + base, Analyseur.estUnChiffre((char) c, base));
        } else {
          assertFalse((char) c + " ne devrait pas etre "
                  + "un chiffre en " + "base " + base,
                  Analyseur.estUnChiffre((char) c, base));
        }
      }

    }
  }

  private static String genererUnRelatifValide(int b) {
    StringBuilder sb = new StringBuilder();
    int x = random.nextInt(3);
    if (x == 0) {
      sb.append('-');
    } else if (x == 1) {
      sb.append('+');
    }
    return sb.append(genererUnNaturelValide(b)).toString();
  }

  private static String genererUnNaturelValide(int b) {
    StringBuilder sb = new StringBuilder();
    int n = random.nextInt(100) + 1;
    for (int i = 0; i < n; i++) {
      char c;
      int ic = random.nextInt(b);
      if (ic < 10) {
        c = (char) ('0' + ic);
      } else {
        if (random.nextBoolean()) {
          c = (char) ('A' + ic - 10);
        } else {
          c = (char) ('a' + ic - 10);
        }
      }
      sb.append(c);
    }
    return sb.toString();
  }

  private static String[] genererDesRelatifsInvalides(int b) {
    StringBuilder sb = new StringBuilder();
    String[] resultat = new String[5];
    resultat[0] = "";

    resultat[1] = "$,";

    sb.append(genererUnRelatifValide(b));
    sb.append('*');
    sb.append(genererUnNaturelValide(b));
    resultat[2] = sb.toString();

    sb.append(genererUnRelatifValide(b));
    sb.append('+');

    resultat[3] = sb.toString();

    sb = new StringBuilder();
    sb.append(genererUnRelatifValide(b));
    if (b < 10) {
      sb.append((char) (b));
    } else if (b < 36) {
      sb.append((char) ('A' + b - 10));
    } else {
      sb.append('*');
    }
    sb.append(genererUnNaturelValide(b));
    resultat[4] = sb.toString();
    return resultat;
  }

  private static String[] genererDesNaturelsInvalides(int b) {
    StringBuilder sb = new StringBuilder();
    String[] resultat = new String[5];
    resultat[0] = "";
    resultat[1] = "$";
    resultat[2] = "$11";
    sb.append(genererUnNaturelValide(b));
    sb.append('*');
    sb.append(genererUnNaturelValide(b));
    resultat[3] = sb.toString();

    sb = new StringBuilder();
    sb.append(genererUnNaturelValide(b));
    if (b < 10) {
      sb.append((char) (b));
    } else if (b < 36) {
      sb.append((char) ('A' + b - 10));
    } else {
      sb.append('+');
    }
    sb.append(genererUnNaturelValide(b));
    resultat[4] = sb.toString();
    return resultat;
  }

  @Test
  public void testEstUnNaturel() {
    System.out.println("estUnNaturel");
    int b = 2;
    // Base 2
    assertTrue(Analyseur.estUnNaturel("0", b));
    assertTrue(Analyseur.estUnNaturel("1", b));
    assertFalse(Analyseur.estUnNaturel("", b));
    assertFalse(Analyseur.estUnNaturel("2", b));
    assertTrue(Analyseur.estUnNaturel("1011010101100010101101010111010100", b));
    assertFalse(Analyseur.estUnNaturel("1101010010102010101100110110101010", b));
    assertTrue(Analyseur.estUnNaturel(genererUnNaturelValide(b), b));
    String[] tmi = genererDesNaturelsInvalides(b);
    for (String mi : tmi) {
      assertFalse(Analyseur.estUnNaturel(mi, b));
    }
    // Base 3
    b = 3;
    assertTrue(Analyseur.estUnNaturel("0", b));
    assertTrue(Analyseur.estUnNaturel("1", b));
    assertTrue(Analyseur.estUnNaturel("2", b));
    assertFalse(Analyseur.estUnNaturel("", b));
    assertFalse(Analyseur.estUnNaturel("3", b));
    assertTrue(Analyseur.estUnNaturel("10211012010110220010101120120101110102100", b));
    assertFalse(Analyseur.estUnNaturel("11010100101032010101100110110101010", b));
    assertTrue(Analyseur.estUnNaturel(genererUnNaturelValide(b), b));
    tmi = genererDesNaturelsInvalides(b);
    for (String mi : tmi) {
      assertFalse(Analyseur.estUnNaturel(mi, b));
    }
    // Base 5
    b = 5;
    assertTrue(Analyseur.estUnNaturel("0", b));
    assertTrue(Analyseur.estUnNaturel("1", b));
    assertTrue(Analyseur.estUnNaturel("2", b));
    assertTrue(Analyseur.estUnNaturel("3", b));
    assertTrue(Analyseur.estUnNaturel("4", b));
    assertFalse(Analyseur.estUnNaturel("", b));
    assertFalse(Analyseur.estUnNaturel("5", b));
    assertTrue(Analyseur.estUnNaturel("14233214122141211444121221413332414121441233214", b));
    assertFalse(Analyseur.estUnNaturel("142332141221412114441212214133324141214412332145", b));
    assertTrue(Analyseur.estUnNaturel(genererUnNaturelValide(b), b));
    tmi = genererDesNaturelsInvalides(b);
    for (String mi : tmi) {
      assertFalse(Analyseur.estUnNaturel(mi, b));
    }
    // Base 10
    b = 10;
    assertTrue(Analyseur.estUnNaturel("0", b));
    assertTrue(Analyseur.estUnNaturel("1", b));
    assertTrue(Analyseur.estUnNaturel("2", b));
    assertTrue(Analyseur.estUnNaturel("3", b));
    assertTrue(Analyseur.estUnNaturel("4", b));
    assertTrue(Analyseur.estUnNaturel("5", b));
    assertTrue(Analyseur.estUnNaturel("6", b));
    assertTrue(Analyseur.estUnNaturel("7", b));
    assertTrue(Analyseur.estUnNaturel("8", b));
    assertTrue(Analyseur.estUnNaturel("9", b));
    assertFalse(Analyseur.estUnNaturel("", b));
    assertFalse(Analyseur.estUnNaturel("A", b));
    assertFalse(Analyseur.estUnNaturel("a", b));
    assertTrue(Analyseur.estUnNaturel("124789541023450154561", b));
    assertFalse(Analyseur.estUnNaturel("11010100101032010a101100110110101010", b));
    assertTrue(Analyseur.estUnNaturel(genererUnNaturelValide(b), b));
    tmi = genererDesNaturelsInvalides(b);
    for (String mi : tmi) {
      assertFalse(Analyseur.estUnNaturel(mi, b));
    }
    // Base 16
    b = 16;
    assertTrue(Analyseur.estUnNaturel("0", b));
    assertTrue(Analyseur.estUnNaturel("1", b));
    assertTrue(Analyseur.estUnNaturel("2", b));
    assertTrue(Analyseur.estUnNaturel("3", b));
    assertTrue(Analyseur.estUnNaturel("4", b));
    assertTrue(Analyseur.estUnNaturel("5", b));
    assertTrue(Analyseur.estUnNaturel("6", b));
    assertTrue(Analyseur.estUnNaturel("7", b));
    assertTrue(Analyseur.estUnNaturel("8", b));
    assertTrue(Analyseur.estUnNaturel("9", b));
    assertTrue(Analyseur.estUnNaturel("A", b));
    assertTrue(Analyseur.estUnNaturel("B", b));
    assertTrue(Analyseur.estUnNaturel("C", b));
    assertTrue(Analyseur.estUnNaturel("D", b));
    assertTrue(Analyseur.estUnNaturel("E", b));
    assertTrue(Analyseur.estUnNaturel("F", b));
    assertTrue(Analyseur.estUnNaturel("a", b));
    assertTrue(Analyseur.estUnNaturel("b", b));
    assertTrue(Analyseur.estUnNaturel("c", b));
    assertTrue(Analyseur.estUnNaturel("d", b));
    assertTrue(Analyseur.estUnNaturel("e", b));
    assertTrue(Analyseur.estUnNaturel("f", b));
    assertFalse(Analyseur.estUnNaturel("", b));
    assertFalse(Analyseur.estUnNaturel("$", b));
    assertFalse(Analyseur.estUnNaturel("-", b));
    assertTrue(Analyseur.estUnNaturel("1247a68b9A4fFDE4a", b));
    assertFalse(Analyseur.estUnNaturel("1247a68b9AG4fFDE4a", b));
    assertTrue(Analyseur.estUnNaturel(genererUnNaturelValide(b), b));
    tmi = genererDesNaturelsInvalides(b);
    for (String mi : tmi) {
      assertFalse(Analyseur.estUnNaturel(mi, b));
    }
    // Base 36

    assertTrue(Analyseur.estUnNaturel(genererUnNaturelValide(b), b));
    tmi = genererDesNaturelsInvalides(b);
    for (String mi : tmi) {
      assertFalse(Analyseur.estUnNaturel(mi, b));
    }
    // Base 37
    assertFalse(Analyseur.estUnNaturel("0", 37));
    // Base -1
    assertFalse(Analyseur.estUnNaturel("0", -1));
  }

  @Test
  public void testEstUnRelatif() {
    System.out.println("estUnRelatif");
    for (int b = 2; b <= 36; b++) {
      assertTrue(Analyseur.estUnRelatif("-0", b));
      assertTrue(Analyseur.estUnRelatif("+0", b));
      assertTrue(Analyseur.estUnRelatif("0", b));
      assertTrue(Analyseur.estUnRelatif("1", b));
      assertTrue(Analyseur.estUnRelatif("-1", b));
      assertTrue(Analyseur.estUnRelatif("+1", b));
      assertFalse(Analyseur.estUnRelatif("", b));
      assertFalse(Analyseur.estUnRelatif(" ", b));
      assertFalse(Analyseur.estUnRelatif("+", b));
      assertFalse(Analyseur.estUnRelatif("-", b));
      for (int i = 0; i < 1000; i++) {
        String r = genererUnRelatifValide(b);
        assertTrue(r, Analyseur.estUnRelatif(r, b));
        String[] tri = genererDesRelatifsInvalides(b);
        for (String ri : tri) {
          assertFalse(ri, Analyseur.estUnRelatif(ri, b));
        }
      }
    }
  }

  @Test
  public void testEstUnNombreAVirgule() {
    System.out.println("estUnNombreAVirgule");
    for (int b = 2; b <= 36; b++) {
      assertTrue(Analyseur.estUnNombreAVirgule(",", b));
      assertTrue(Analyseur.estUnNombreAVirgule(",0", b));
      assertTrue(Analyseur.estUnNombreAVirgule("0,", b));
      assertTrue(Analyseur.estUnNombreAVirgule("1,", b));
      assertTrue(Analyseur.estUnNombreAVirgule("-1,", b));
      assertTrue(Analyseur.estUnNombreAVirgule("+0,1", b));
      assertTrue(Analyseur.estUnNombreAVirgule("10,00", b));
      assertFalse(Analyseur.estUnNombreAVirgule("", b));
      assertFalse(Analyseur.estUnNombreAVirgule(" ", b));
      assertFalse(Analyseur.estUnNombreAVirgule("+", b));
      assertFalse(Analyseur.estUnNombreAVirgule("-", b));
      for (int i = 0; i < 1000; i++) {
        String r = genererUnNombreAVirguleValide(b);
        assertTrue(r, Analyseur.estUnNombreAVirgule(r, b));
        String[] tri = genererDesNombresAVirguleInvalides(b);
        for (String ri : tri) {
          assertFalse(ri, Analyseur.estUnNombreAVirgule(ri, b));
        }
      }
    }
  }

  private String genererUnNombreAVirguleValide(int b) {
    StringBuilder sb = new StringBuilder();
    if (random.nextBoolean()) {
      sb.append(genererUnRelatifValide(b));
    }
    if (random.nextBoolean()) {
      sb.append(',');
    }
    if (random.nextBoolean()) {
      sb.append(genererUnRelatifValide(b));
    }
    return sb.toString();
  }

  private String[] genererDesNombresAVirguleInvalides(int b) {

    String[] resultat = new String[3];
    resultat[0] = "";

    resultat[1] = "$,";
    StringBuilder sb = new StringBuilder();
    if (random.nextBoolean()) {
      sb.append(genererUnRelatifValide(b));
    }
    sb.append('.');
    if (random.nextBoolean()) {
      sb.append(genererUnRelatifValide(b));
    }
    resultat[2] = sb.toString();

    sb = new StringBuilder();
    

    return resultat;
  }
}