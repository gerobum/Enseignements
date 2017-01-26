
package calculatrice;

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

    private static final Random RANDOM = new Random();

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private static String genererUneExpressionValide(int pmax, int nmax, int nemax) {
        int n = RANDOM.nextInt(pmax);
        StringBuilder sb = new StringBuilder();
        sb.append(genererUneExpressionSansParentheseValide(nmax, nemax));

        for (int i = 0; i < n; i++) {

            sb.append(genererDesEspaces(1));
            sb.append(genererUnOperateur());
            sb.append(genererDesEspaces(1));

            sb.append(genererUneExpressionValide(pmax, nmax, nemax));
        }

        StringBuilder sx = new StringBuilder();
        if (RANDOM.nextBoolean()) {
            sx.append('(').append(genererDesEspaces(1));
            sx.append(sb);
            sx.append(genererDesEspaces(1)).append(')');
        } else {
            sx.append(sb);
        }

        return sx.toString();
    }

    private static String genererUnOperateur() {
        StringBuilder sb = new StringBuilder();
        if (RANDOM.nextBoolean()) {
            if (RANDOM.nextBoolean()) {
                sb.append('+');
            } else {
                sb.append('-');
            }
        } else {

            if (RANDOM.nextBoolean()) {
                sb.append('*');
            } else {
                sb.append('/');
            }
        }
        return sb.toString();
    }

    private static String genererUneParenthese() {
        if (RANDOM.nextBoolean()) {
            return "(";
        } else {
            return ")";
        }
    }

    private static String genererUneExpressionInvalide() {
        StringBuilder sb = new StringBuilder(genererUneExpressionValide(2, 4, 0));

        int x = RANDOM.nextInt(sb.length() + 1);

        return sb.insert(x, genererUneParenthese()).toString();
    }

    private static String genererDesEspaces(int nmax) {
        if (nmax <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int n = RANDOM.nextInt(nmax);
        for (int j = 0; j < n; j++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    private static String genererUneExpressionSansParentheseValide(int nmax, int nemax) {
        int n = RANDOM.nextInt(5);
        StringBuilder sb = new StringBuilder();
        sb.append(genererUnNombreAVirguleValide(nmax));

        for (int i = 0; i < n; i++) {
            sb.append(genererDesEspaces(nemax));
            sb.append(genererUnOperateur());
            sb.append(genererDesEspaces(nemax));
            sb.append(genererUnNombreAVirguleValide(nmax));
        }
        return sb.toString();
    }

    private static String genererUneExpressionSansParentheseInvalide(int nmax, int nemax) {
        return genererUneOperationInvalide(nmax, nemax);
    }

    private static String genererUneOperationInvalide(int nmax, int nemax) {
        int n;
        StringBuilder sb = new StringBuilder();
        sb.append(genererUnNombreAVirguleInvalide(nmax));
        sb.append(genererDesEspaces(nemax));
        sb.append(genererUnOperateur());
        sb.append(genererDesEspaces(nemax));
        sb.append(genererUnNombreAVirguleInvalide(nmax));
        return sb.toString();
    }

    private static String genererUneOperationValide(int nmax, int nemax) {
        StringBuilder sb = new StringBuilder();
        sb.append(genererUnNaturelValide(nmax));
        sb.append(genererDesEspaces(nemax));
        sb.append(genererUnOperateur());
        sb.append(genererDesEspaces(nemax));
        sb.append(genererUnNaturelValide(nmax));
        return sb.toString();
    }

    private static String genererUnNombreAVirguleInvalide(int nmax) {
        StringBuilder sb = new StringBuilder();
        do {
            if (RANDOM.nextBoolean()) {
                int x = RANDOM.nextInt(3);
                if (x == 0) {
                    sb.append('+');
                } else if (x == 1) {
                    sb.append('-');
                }
                sb.append(genererUnNaturelInvalide(nmax / 2));
            }

            char c;
            do {
                c = (char) RANDOM.nextInt();
            } while (c == ',' || c == '0' || c == '1' || c == '2' || c == '3'
                    || c == '4' || c == '5' || c == '6' || c == '7'
                    || c == '8' || c == '9');
            sb.append(c);
            if (RANDOM.nextBoolean()) {
                sb.append(genererUnNaturelInvalide(nmax / 2));
            }
        } while ("".equals(sb.toString()));
        return sb.toString();
    }

    private static String genererUnNombreAVirguleValide(int nmax) {
        StringBuilder sb = new StringBuilder();
        do {
            if (RANDOM.nextBoolean()) {
                int x = RANDOM.nextInt(3);
                if (x == 0) {
                    sb.append('+');
                } else if (x == 1) {
                    sb.append('-');
                }
                sb.append(genererUnNaturelValide(nmax / 2));
            }
            if (RANDOM.nextBoolean()) {
                sb.append(',');
            }
            if (RANDOM.nextBoolean()) {
                sb.append(genererUnNaturelValide(nmax / 2));
            }
        } while ("".equals(sb.toString()));
        return sb.toString();
    }

    private static String genererUnNaturelValide(int nmax) {
        int n = RANDOM.nextInt(nmax) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append((char) (RANDOM.nextInt(10) + '0'));
        }
        return sb.toString();
    }

    private static String genererUnNaturelInvalide(int nmax) {
        int n1 = RANDOM.nextInt(nmax) / 2 + 1;
        int n2 = RANDOM.nextInt(nmax) / 2 + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n1; i++) {
            sb.append((char) (RANDOM.nextInt(10) + '0'));
        }
        char c;
        do {
            c = (char) RANDOM.nextInt();
        } while (c == '0' || c == '1' || c == '2' || c == '3' || c == '4'
                || c == '5' || c == '6' || c == '7' || c == '8' || c == '9');
        sb.append(c);
        for (int i = 0; i < n2; i++) {
            sb.append((char) (RANDOM.nextInt(10) + '0'));
        }
        return sb.toString();
    }

    @Test
    public void testEstUnChiffre() {
        System.out.println("estUnChiffre");
        for (char c = Character.MIN_VALUE; c < '0'; c++) {
            assertFalse(c + " ne devrait pas être un chiffre", Analyseur.estUnChiffre(c));
        }
        for (char c = '0'; c <= '9'; c++) {
            assertTrue(c + " devrait être un chiffre", Analyseur.estUnChiffre(c));
        }
        for (char c = '9' + 1; c < Character.MAX_VALUE; c++) {
            assertFalse(c + " ne devrait pas être un chiffre", Analyseur.estUnChiffre(c));
        }
        assertFalse(Character.MAX_VALUE + " ne devrait pas être un chiffre", Analyseur.estUnChiffre(Character.MAX_VALUE));
    }

    @Test
    public void testEstUnNaturel() {
        System.out.println("estUnNaturel");
        assertFalse(Analyseur.estUnNaturel(null));
        assertFalse(Analyseur.estUnNaturel(""));
        assertFalse(Analyseur.estUnNaturel("+"));
        assertFalse(Analyseur.estUnNaturel("-"));
        assertFalse(Analyseur.estUnNaturel(","));
        for (int i = 0; i < 10; i++) {
            assertTrue("" + ((char) ('0' + i)), Analyseur.estUnNaturel("" + ((char) ('0' + i))));
        }
        for (int i = 0; i < 1000; i++) {
            String s = genererUnNaturelValide(100);
            assertTrue(i + ": " + s, Analyseur.estUnNaturel(s));
            s = genererUnNaturelInvalide(100);
            assertFalse(i + ": " + s, Analyseur.estUnNaturel(s));
        }
    }

    @Test
    public void testEstUnRelatif() {
        System.out.println("estUnRelatif");
        assertFalse(Analyseur.estUnRelatif(null));
        assertFalse(Analyseur.estUnRelatif(""));
        assertFalse(Analyseur.estUnRelatif("+"));
        assertFalse(Analyseur.estUnRelatif("-"));
        assertFalse(Analyseur.estUnRelatif(","));
        for (int i = 0; i < 10; i++) {
            assertTrue("" + ((char) ('0' + i)), Analyseur.estUnRelatif("" + ((char) ('0' + i))));
            assertTrue("+" + ((char) ('0' + i)), Analyseur.estUnRelatif("" + ((char) ('0' + i))));
            assertTrue("-" + ((char) ('0' + i)), Analyseur.estUnRelatif("" + ((char) ('0' + i))));
        }
        for (int i = 0; i < 1000; i++) {
            String s = genererUnNaturelValide(100);
            assertTrue(i + ": " + s, Analyseur.estUnRelatif(s));
            assertTrue(i + ": +" + s, Analyseur.estUnRelatif("+" + s));
            assertTrue(i + ": -" + s, Analyseur.estUnRelatif("-" + s));
            s = genererUnNaturelInvalide(100);
            assertFalse(i + ": " + s, Analyseur.estUnRelatif(s));
            assertFalse(i + ": +" + s, Analyseur.estUnRelatif("+" + s));
            assertFalse(i + ": -" + s, Analyseur.estUnRelatif("-" + s));
        }
    }

    @Test
    public void testEstUnNombreAVirgule() {
        System.out.println("estUnNombreAVirgule");
        assertFalse(Analyseur.estUnNombreAVirgule(null));
        assertFalse(Analyseur.estUnNombreAVirgule(""));
        assertFalse(Analyseur.estUnNombreAVirgule("+"));
        assertFalse(Analyseur.estUnNombreAVirgule("-"));
        assertFalse(Analyseur.estUnNombreAVirgule("+,"));
        assertFalse(Analyseur.estUnNombreAVirgule("-,"));
        assertTrue(Analyseur.estUnNombreAVirgule(","));

        for (int i = 0; i < 10; i++) {
            assertTrue("" + ((char) ('0' + i)), Analyseur.estUnNombreAVirgule("" + ((char) ('0' + i))));
            assertTrue("+" + ((char) ('0' + i)), Analyseur.estUnNombreAVirgule("" + ((char) ('0' + i))));
            assertTrue("-" + ((char) ('0' + i)), Analyseur.estUnNombreAVirgule("" + ((char) ('0' + i))));
        }
        for (int i = 0; i < 1000; i++) {
            String s = genererUnNombreAVirguleValide(100);
            assertTrue(i + ": " + s, Analyseur.estUnNombreAVirgule(s));
            s = genererUnNombreAVirguleInvalide(100);
            assertFalse(i + ": " + s, Analyseur.estUnNombreAVirgule(s));
        }
    }

    @Test
    public void testEstUneOperation() {
        System.out.println("estUneOperation");
        String ops[] = {
            "0",
            "1+2",
            "1-2",
            "1*2",
            "1/2",
            "-1+2",
            "-1-2",
            "-1*2",
            "-1/2",
            "1+-2",
            "1--2",
            "1*-2",
            "1/-2",
            "-1+-2",
            "-1--2",
            "-1*-2",
            "-1/-2",
            "1 + 2",
            "1 - 2",
            "1 * 2",
            "1 / 2",
            "-1 + 2",
            "-1 - 2",
            "-1 * 2",
            "-1/ 2",
            "1 +-2",
            "1- -2",
            " 1 *-2",
            "1/ -2",
            "-1 +-2",
            "-1 --2",
            "-1 *-2",
            "-1 /-2",
            "1,5+214577,64789",
            "11457,4578 -  -245487",
            "-45741,78781 * 78477,78782",
            "-78475547,788471 / -25457854,78854",
            "-78475547,788471 - -25457854,78854",
            "+78475547,788471 + +25457854,78854",
            "1,5+214577,64789",
            "11457,4578 -  +245487",
            "+45741,78781 * 78477,78782",
            "+78475547,788471 / +25457854,78854",
            "++89",
            "+-89748,784"
        };

        String noops[] = {
            "*0",
            "*1+2",
            "1-2-",
            "1*2+",
            "1//2",
            "-1**+2",
            "-1-*-*2",
            "++++89",
            "+++-89748,784"
        };
        for (String op : ops) {
            assertTrue(op + " devrait être une opération", Analyseur.estUneOperation(op));
        }

        for (int i = 0; i < 1000; i++) {
            String op = genererUneOperationValide(50, 5);
            assertTrue(op + " devrait être une opération", Analyseur.estUneOperation(op));
        }

        for (String noop : noops) {
            assertFalse(noop + " ne devrait pas être une opération", Analyseur.estUneOperation(noop));
        }

        for (int i = 0; i < 1000; i++) {
            String noop = genererUneOperationInvalide(50, 5);
            assertFalse(noop + " ne devrait pas être une opération", Analyseur.estUneOperation(noop));
        }
    }

    @Test
    public void testEstUneExpressionSansParenthese() {
        System.out.println("estUneExpressionSansParenthese");
        String ops[] = {
            "0",
            "1+2",
            "1-2",
            "1*2",
            "1/2",
            "-1+2",
            "-1-2",
            "-1*2",
            "-1/2",
            "1+-2",
            "1--2",
            "1*-2",
            "1/-2",
            "-1+-2",
            "-1--2",
            "-1*-2",
            "-1/-2",
            "1 + 2",
            "1 - 2",
            "1 * 2",
            "1 / 2",
            "-1 + 2",
            "-1 - 2",
            "-1 * 2",
            "-1/ 2",
            "1 +-2",
            "1- -2",
            " 1 *-2",
            "1/ -2",
            "-1 +-2",
            "-1 --2",
            "-1 *-2",
            "-1 /-2",
            "1,5+214577,64789",
            "11457,4578 -  -245487",
            "-45741,78781 * 78477,78782",
            "-78475547,788471 / -25457854,78854",
            "-78475547,788471 - -25457854,78854",
            "+78475547,788471 + +25457854,78854",
            "1,5+214577,64789",
            "11457,4578 -  +245487",
            "+45741,78781 * 78477,78782",
            "+78475547,788471 / +25457854,78854",
            "++89",
            "+-89748,784",
            "1+2-3",
            "1-2+3",
            "1*2*3",
            "1/2/3",
            "-1+2*-7",
            "-1-2--7",
            "-1*2*+8",
            "-1/2*2/-1",
            "1+-2+8",
            "1--2--1",
            "1*-2*-1+9*78/4784,5",
            "1/-2+4785",
            "-1+-2+8*-4",
            "1,5+214577,64789*4785,8799/78,9854",
            "11457,4578 -  -245487 + 7847552,4578",
            "-45741,78781 * 78477,78782 / -478545",
            "- - - 11457,4578 -  -245487 + 7847552,4578",
            "-45741,78781 * - - - - 78477,78782 / + + + + -478545",
            "++++89",
            "+++-89748,784",
            "- - + + - 457478554,785454"
        };

        String noops[] = {
            "*0",
            "*1+2",
            "1-2-",
            "1*2+",
            "1//2",
            "-1**+2",
            "-1-*-*2"
        };
        for (String op : ops) {
            assertTrue(op + " devrait être une expression sans parenthèse", Analyseur.estUneExpressionSansParenthese(op));
        }

        for (int i = 0; i < 1000; i++) {
            String op = genererUneExpressionSansParentheseValide(10, 5);
            assertTrue(op + " devrait être une expression sans parenthèse", Analyseur.estUneExpressionSansParenthese(op));
        }

        for (String noop : noops) {
            assertFalse(noop + " ne devrait pas être une expression sans parenthèse", Analyseur.estUneExpressionSansParenthese(noop));
        }

        for (int i = 0; i < 1000; i++) {
            String noop = genererUneExpressionSansParentheseInvalide(10, 5);
            assertFalse(noop + " ne devrait pas être une expression sans parenthèse", Analyseur.estUneExpressionSansParenthese(noop));
        }
    }

    @Test
    public void testEstUneExpression() {
        System.out.println("estUneExpression");
        String ops[] = {
            "0",
            "1+2",
            "1-2",
            "1*2",
            "1/2",
            "-1+2",
            "-1-2",
            "-1*2",
            "-1/2",
            "1+-2",
            "1--2",
            "1*-2",
            "1/-2",
            "-1+-2",
            "-1--2",
            "-1*-2",
            "-1/-2",
            "1 + 2",
            "1 - 2",
            "1 * 2",
            "1 / 2",
            "-1 + 2",
            "-1 - 2",
            "-1 * 2",
            "-1/ 2",
            "1 +-2",
            "1- -2",
            " 1 *-2",
            "1/ -2",
            "-1 +-2",
            "-1 --2",
            "-1 *-2",
            "-1 /-2",
            "1,5+214577,64789",
            "11457,4578 -  -245487",
            "-45741,78781 * 78477,78782",
            "-78475547,788471 / -25457854,78854",
            "-78475547,788471 - -25457854,78854",
            "+78475547,788471 + +25457854,78854",
            "1,5+214577,64789",
            "11457,4578 -  +245487",
            "+45741,78781 * 78477,78782",
            "+78475547,788471 / +25457854,78854",
            "++89",
            "+-89748,784",
            "1+2-3",
            "1-2+3",
            "1*2*3",
            "1/2/3",
            "-1+2*-7",
            "-1-2--7",
            "-1*2*+8",
            "-1/2*2/-1",
            "1+-2+8",
            "1--2--1",
            "1*-2*-1+9*78/4784,5",
            "1/-2+4785",
            "-1+-2+8*-4",
            "1,5+214577,64789*4785,8799/78,9854",
            "11457,4578 -  -245487 + 7847552,4578",
            "-45741,78781 * 78477,78782 / -478545",
            "- - - 11457,4578 -  -245487 + 7847552,4578",
            "-45741,78781 * - - - - 78477,78782 / + + + + -478545",
            "++++89",
            "+++-89748,784",
            "- - + + - 457478554,785454",
            "(1*3+(4/(4-8))) - (12*(4778,78 / 98))",
            "145 - -(4785454 + (47844545 / (15457 * 474))) + (458 / (47854 * 98744) + -7847) + 45787"
        };

        String noops[] = {
            "*0",
            "*1+2",
            "1-2-",
            "1*2+",
            "1//2",
            "-1**+2",
            "-1-*-*2",
            "(1*3+(4/(4-8)) - (12*(4778,78 / 98))",
            "145 - -(4785454 + (47844545 / (15457 * 474))( + (458 / (47854 * 98744) + -7847) + 45787"
        };
        for (String op : ops) {
            assertTrue(op + " devrait être une expression", Analyseur.estUneExpression(op));
        }

        for (int i = 0; i < 1000; i++) {
            String op = genererUneExpressionValide(2, 4, 4);
            assertTrue(op + " devrait être une expression", Analyseur.estUneExpression(op));
        }

        for (String noop : noops) {
            assertFalse(noop + " ne devrait pas être une expression", Analyseur.estUneExpression(noop));
        }

        for (int i = 0; i < 1000; i++) {
            String noop = genererUneExpressionInvalide();
            assertFalse(noop + " ne devrait pas être une expression", Analyseur.estUneExpression(noop));
        }
    }

    @Test
    public void testAdditionDeNaturels() {
        System.out.println("Test addition de naturels");
        int a;
        int b;
        int c;
        for (int i = 0; i < 1000000; ++i) {

            a = RANDOM.nextInt(Integer.MAX_VALUE) / 2;
            b = RANDOM.nextInt(Integer.MAX_VALUE) / 2;
            c = a + b;
            
  
            
            assertEquals(a+"+"+b+" devrait être égal à "+c, c+"", Analyseur.additionDeNaturels(a+"", b+""));
            
        }
        assertEquals("1000000000000000000000000000000000000000000000000000000000000000000000000000000000", Analyseur.additionDeNaturels("999999999999999999999999999999999999999999999999999999999999999999999999999999999", "1"));

    }
    
    @Test
    public void testSoustraction() {
        System.out.println("Test soustraction");
        
        int a = 365;
        int b = 65;
        int c = a - b;
        assertEquals(a+"+"+b+" devrait être égal à "+c, c+"", Analyseur.soustractionDeNaturels(a+"", b+""));
    }

    @Test
    public void testAddition() {
        System.out.println("Test addition");
        int a;
        int b;
        int c;
        for (int i = 0; i < 1000000; ++i) {

            a = RANDOM.nextInt()/2;
            b = RANDOM.nextInt()/2;
            c = a + b;
            
  
            
            assertEquals(a+"+"+b+" devrait être égal à "+c, c+"", Analyseur.additionDeRelatifs(a+"", b+""));
            
        }
        assertEquals("1000000000000000000000000000000000000000000000000000000000000000000000000000000000", Analyseur.additionDeRelatifs("999999999999999999999999999999999999999999999999999999999999999999999999999999999", "1"));

        assertEquals("999999999999999999999999999999999999999999999999999999999999999999999999999999999", Analyseur.additionDeRelatifs("1000000000000000000000000000000000000000000000000000000000000000000000000000000000", "-1"));
        
        assertEquals("-1000000000000000000000000000000000000000000000000000000000000000000000000000000000", Analyseur.additionDeRelatifs("-999999999999999999999999999999999999999999999999999999999999999999999999999999999", "-1"));
        
        assertEquals("0", Analyseur.additionDeRelatifs("-457812477748000214474520121447855474558745547889996658002102047840004178000154878400547874002545487", "457812477748000214474520121447855474558745547889996658002102047840004178000154878400547874002545487"));
        
        assertEquals("0", Analyseur.additionDeRelatifs("457812477748000214474520121447855474558745547889996658002102047840004178000154878400547874002545487", "-457812477748000214474520121447855474558745547889996658002102047840004178000154878400547874002545487"));
        

    }
    
    

    @Test
    public void testMultiplicationDeNaturelPasUnScalaire() {
        System.out.println("Test MultiplicationDeNaturelParUnScalaire");
     
        for (int i = 0; i < 1000000; ++i) {

            char a = (char)('0'+RANDOM.nextInt(10));
            int b = RANDOM.nextInt(Integer.MAX_VALUE)/10;
            int c = (a-'0') * b;
            
            
            assertEquals(a+"*"+b+" devrait être égal à "+c, c+"", Analyseur.multiplicationDUnScalaireParUnNaturel(a, b+""));
            
        }   
        
        for(int i = 0; i < 100; ++i) {
            char a = (char)('0'+RANDOM.nextInt(10));
            String b = genererUnNaturelValide(100);
            String c = "0";
            for(int j = 0; j < (a-'0'); ++j) {
                c = Analyseur.additionDeRelatifs(c, b);
            }
            assertEquals(a+"*"+b+" devrait être égal à "+c, c+"", Analyseur.multiplicationDUnScalaireParUnNaturel(a, b));
        }
    }   

    @Test
    public void testMultiplicationDeNaturels() {
        System.out.println("Test MultiplicationDeNaturels");
        
        int rc = (int) Math.sqrt(Integer.MAX_VALUE);
     
        for (int i = 0; i < 1000000; ++i) {

            int a = RANDOM.nextInt(rc);
            int b = RANDOM.nextInt(rc);
            int c = a * b;
            
            
            assertEquals(a+"*"+b+" devrait être égal à "+c, c+"", Analyseur.multiplicationDeNaturels(a+"", b+""));
            
        }   
        
        for(int i = 0; i < 1; ++i) {
            String a = genererUnNaturelValide(12);
            String b = genererUnNaturelValide(12);
            String c = "0";
            if (Analyseur.compare(a, b)<0) {
                String x = a;
                a = b;
                b = x;
            }
            String cb = b;
            while(!"0".equals(cb)) {
                c = Analyseur.additionDeRelatifs(c, a);
                cb = Analyseur.soustractionDeNaturels(cb, "1");
            }
            assertEquals(a+"*"+b+" devrait être égal à "+c, c+"", Analyseur.multiplicationDeNaturels(a, b));
        }
    }
}
