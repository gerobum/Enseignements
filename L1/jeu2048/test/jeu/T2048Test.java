/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

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
public class T2048Test {

    private final T2048 t1, t2, t3, t4;
    private final T2048 tp00, tp01, tp02, tp11;

    public T2048Test() {
        int[][] jt1 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        t1 = new T2048();
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                t1.set(l, c, jt1[l][c]);
            }
        }
        int[][] jt2 = {
            {13, 9, 5, 1},
            {14, 10, 6, 2},
            {15, 11, 7, 3},
            {16, 12, 8, 4}};
        t2 = new T2048();
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                t2.set(l, c, jt2[l][c]);
            }
        }
        int[][] jt3 = {
            {16, 15, 14, 13},
            {12, 11, 10, 9},
            {8, 7, 6, 5},
            {4, 3, 2, 1}};
        t3 = new T2048();
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                t3.set(l, c, jt3[l][c]);
            }
        }
        int[][] jt4 = {
            {4, 8, 12, 16},
            {3, 7, 11, 15},
            {2, 6, 10, 14},
            {1, 5, 9, 13}};
        t4 = new T2048();
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                t4.set(l, c, jt4[l][c]);
            }
        }

        int[][] jt00 = {
            {13, 2, 3, 1},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {16, 14, 15, 4}};
        tp00 = new T2048();
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                tp00.set(l, c, jt00[l][c]);
            }
        }
        int[][] jt01 = {
            {13, 9, 3, 1},
            {5, 6, 7, 2},
            {15, 10, 11, 12},
            {16, 14, 8, 4}};
        tp01 = new T2048();
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                tp01.set(l, c, jt01[l][c]);
            }
        }
        int[][] jt02 = {
            {13, 9, 5, 1},
            {14, 6, 7, 2},
            {15, 10, 11, 3},
            {16, 12, 8, 4}};
        tp02 = new T2048();
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                tp02.set(l, c, jt02[l][c]);
            }
        }
        int[][] jt11 = {
            {13, 9, 5, 1},
            {14, 10, 6, 2},
            {15, 11, 7, 3},
            {16, 12, 8, 4}};
        tp11 = new T2048();
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                tp11.set(l, c, jt11[l][c]);
            }
        }
        assertNotEquals(t1, t2);
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

    /**
     * Test of permutLeft method, of class T2048.
     */
    @Test
    public void testPermutLeft() {
        System.out.println("permutLeft");
    }

    /**
     * Test of rotateLeft method, of class T2048.
     */
    @Test
    public void testRotateRight() {
        System.out.println("rotateRight");
        assertNotEquals(t1, t2);
        T2048 t = t1.clone();
        assertEquals(t, t1);
        t.rotateRight();
        assertEquals(t, t2);
        t.rotateRight();
        assertEquals(t, t3);
        t.rotateRight();
        assertEquals(t, t4);
        t.rotateRight();
        assertEquals(t, t1);
    }

    /**
     * Test of rotateLeft method, of class T2048.
     */
    @Test
    public void testRotateLeft() {
        System.out.println("rotateLeft");
        assertNotEquals(t1, t2);
        T2048 t = t1.clone();
        assertEquals(t, t1);
        t.rotateLeft();
        assertEquals(t, t4);
        t.rotateLeft();
        assertEquals(t, t3);
        t.rotateLeft();
        assertEquals(t, t2);
        t.rotateLeft();
        assertEquals(t, t1);
    }

    /**
     * Test of permutLeft method, of class T2048.
     */
    @Test
    public void testPermutRight() {
        System.out.println("permutRight");
        T2048 t = t1.clone();
        t.permutRight(new Position(0, 0));
        assertEquals(t, tp00);
        t.permutRight(new Position(0, 1));
        assertEquals(t, tp01);
        t.permutRight(new Position(0, 2));
        assertEquals(t, tp02);
        t.permutRight(new Position(1, 1));
        assertEquals(t, tp11);
    }

    /**
     * Test of affiche method, of class T2048.
     */
    @Test
    public void testAffiche() {
    }

    /**
     * Test of set method, of class T2048.
     */
    @Test
    public void testSet() {
    }

    /**
     * Test of get method, of class T2048.
     */
    @Test
    public void testGet() {
    }

    /**
     * Test of hashCode method, of class T2048.
     */
    @Test
    public void testHashCode() {
    }

    /**
     * Test of equals method, of class T2048.
     */
    @Test
    public void testEquals() {
    }

    /**
     * Test of clone method, of class T2048.
     */
    @Test
    public void testClone() {
    }

    /**
     * Test of set method, of class T2048.
     */
    @Test
    public void testSet_Position_Integer() {
    }

    /**
     * Test of set method, of class T2048.
     */
    @Test
    public void testSet_3args() {
    }

    /**
     * Test of get method, of class T2048.
     */
    @Test
    public void testGet_int_int() {
    }

    /**
     * Test of get method, of class T2048.
     */
    @Test
    public void testGet_Position() {
    }

    /**
     * Test of toString method, of class T2048.
     */
    @Test
    public void testToString() {
    }

    // #### Down
    /**
     * Test of down method, of class T2048.
     */
    public void testDown_0000() {
        T2048 tf = new T2048(
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of down method, of class T2048.
     */
    public void testDown_0002() {
        T2048 tf = new T2048(
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                2, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of down method, of class T2048.
     */
    public void testDown_0024() {
        T2048 tf = new T2048(
                0, 0, 0, 0,
                0, 0, 0, 0,
                2, 0, 0, 0,
                4, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    4, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    4, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    4, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    4, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    4, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    4, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of down method, of class T2048.
     */
    public void testDown_0248() {
        T2048 tf = new T2048(
                0, 0, 0, 0,
                2, 0, 0, 0,
                4, 0, 0, 0,
                8, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    4, 0, 0, 0,
                    8, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }

        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    4, 0, 0, 0,
                    8, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    4, 0, 0, 0,
                    0, 0, 0, 0,
                    8, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    4, 0, 0, 0,
                    8, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of down method, of class T2048.
     */
    public void testDown_24816() {
        T2048 tf = new T2048(
                2, 0, 0, 0,
                4, 0, 0, 0,
                8, 0, 0, 0,
                16, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    4, 0, 0, 0,
                    8, 0, 0, 0,
                    16, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of down method, of class T2048.
     */
    public void testDown_0004() {
        T2048 tf = new T2048(
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                4, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of down method, of class T2048.
     */
    public void testDown_0044() {
        T2048 tf = new T2048(
                0, 0, 0, 0,
                0, 0, 0, 0,
                4, 0, 0, 0,
                4, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    2, 0, 0, 0,
                    2, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of down method, of class T2048.
     */
    public void testDown_0048() {
        T2048 tf = new T2048(
                0, 0, 0, 0,
                0, 0, 0, 0,
                4, 0, 0, 0,
                8, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    2, 0, 0, 0,
                    4, 0, 0, 0,
                    4, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    4, 0, 0, 0,
                    4, 0, 0, 0,
                    4, 0, 0, 0
            );
            t.down();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of down method, of class T2048.
     */
    @Test
    public void testDown() {
        System.out.println("down");
        testDown_0000();
        testDown_0002();
        testDown_0024();
        testDown_0248();
        testDown_24816();
        testDown_0004();
        testDown_0044();
        testDown_0048();/**/
    }

// #### Up
    /**
     * Test of up method, of class T2048.
     */
    public void testUp_0000() {
        T2048 tf = new T2048(
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of up method, of class T2048.
     */
    public void testUp_0002() {
        T2048 tf = new T2048(
                2, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of up method, of class T2048.
     */
    public void testUp_0024() {
        T2048 tf = new T2048(
                4, 0, 0, 0,
                2, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    4, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    4, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    4, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    4, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    4, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    4, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of up method, of class T2048.
     */
    public void testUp_0248() {
        T2048 tf = new T2048(
                8, 0, 0, 0,
                4, 0, 0, 0,
                2, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    8, 0, 0, 0,
                    4, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }

        {
            T2048 t = new T2048(
                    8, 0, 0, 0,
                    0, 0, 0, 0,
                    4, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    8, 0, 0, 0,
                    4, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    8, 0, 0, 0,
                    4, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of up method, of class T2048.
     */
    public void testUp_24816() {
        T2048 tf = new T2048(
                2, 0, 0, 0,
                4, 0, 0, 0,
                8, 0, 0, 0,
                16, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    4, 0, 0, 0,
                    8, 0, 0, 0,
                    16, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of up method, of class T2048.
     */
    public void testUp_0004() {
        T2048 tf = new T2048(
                4, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    2, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of up method, of class T2048.
     */
    public void testUp_0044() {
        T2048 tf = new T2048(
                4, 0, 0, 0,
                4, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    2, 0, 0, 0,
                    2, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of up method, of class T2048.
     */
    public void testUp_0048() {
        T2048 tf = new T2048(
                8, 0, 0, 0,
                4, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    4, 0, 0, 0,
                    4, 0, 0, 0,
                    2, 0, 0, 0,
                    2, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    4, 0, 0, 0,
                    4, 0, 0, 0,
                    4, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    4, 0, 0, 0,
                    4, 0, 0, 0,
                    4, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.up();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of up method, of class T2048.
     */
    @Test
    public void testUp() {
        System.out.println("up");
        testUp_0000();
        testUp_0002();
        testUp_0024();
        testUp_0248();
        testUp_24816();
        testUp_0004();
        testUp_0044();
        testUp_0048();/**/
    }

// #### Right
    /**
     * Test of right method, of class T2048.
     */
    public void testRight_0000() {
        T2048 tf = new T2048(
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of right method, of class T2048.
     */
    public void testRight_0002() {
        T2048 tf = new T2048(
                0, 0, 0, 2,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 2, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 2, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 0, 2,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of right method, of class T2048.
     */
    public void testRight_0024() {
        T2048 tf = new T2048(
                0, 0, 2, 4,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 4, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 4, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 4,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 2, 4, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 2, 0, 4,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 0, 2, 4,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of right method, of class T2048.
     */
    public void testRight_0248() {
        T2048 tf = new T2048(
                0, 8, 4, 2,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    0, 8, 4, 2,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }

        {
            T2048 t = new T2048(
                    8, 0, 4, 2,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    8, 4, 0, 2,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    8, 4, 2, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of right method, of class T2048.
     */
    public void testRight_24816() {
        T2048 tf = new T2048(
                2, 4, 8, 16,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 4, 8, 16,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of right method, of class T2048.
     */
    public void testRight_0004() {
        T2048 tf = new T2048(
                0, 0, 0, 4,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 2, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 2, 0, 2,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 0, 2,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 0, 2, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 2, 2, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    2, 2, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of right method, of class T2048.
     */
    public void testRight_0044() {
        T2048 tf = new T2048(
                0, 0, 4, 4,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 2, 2, 2,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of right method, of class T2048.
     */
    public void testRight_0048() {
        T2048 tf = new T2048(
                0, 0, 4, 8,
                0, 0, 0, 0,
                0, 0, 0, 0,
                0, 0, 0, 0
        );
        {
            T2048 t = new T2048(
                    2, 2, 4, 4,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    0, 4, 4, 4,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
        {
            T2048 t = new T2048(
                    4, 4, 4, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0,
                    0, 0, 0, 0
            );
            t.right();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of right method, of class T2048.
     */
    @Test
    public void testRight() {
        System.out.println("right");
        testRight_0000();
        testRight_0002();
        testRight_0024();
        testRight_0248();
        testRight_24816();
        testRight_0004();
        testRight_0044();
        testRight_0048();/**/
    }

    // left
    /**
     * Test of right method, of class T2048.
     */
    public void testLeft_1() {
        T2048 tf = new T2048(
                0, 0, 0, 0,
                4, 0, 0, 0,
                2, 4, 2, 0,
                2, 4, 8, 32
        );
        {
            T2048 t = new T2048(
                    0, 0, 0, 0,
                    0, 0, 0, 4,
                    0, 2, 4, 2,
                    2, 4, 8, 32
            );
            t.left();
            assertEquals(tf, t);
        }
    }

    /**
     * Test of right method, of class T2048.
     */
    @Test
    public void testLeft() {
        System.out.println("left");
        testLeft_1();
    }

    /*
0	0	0	0	
0	0	0	4	
0	2	4	2	
2	4	8	32
     */
}