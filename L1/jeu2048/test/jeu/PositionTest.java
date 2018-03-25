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
public class PositionTest {
    
    public PositionTest() {
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
     * Test of next method, of class Position.
     */
    @Test
    public void testNext() {
        System.out.println("next");
        Position[][] p = new Position[4][4];
        for(int l = 0; l < 4; ++l)
            for(int c = 0; c < 4; ++c)
                p[l][c] = new Position(l, c);
        assertEquals(p[0][3], p[0][0].next());
        assertEquals(p[1][3], p[0][1].next());
        assertEquals(p[2][3], p[0][2].next());
        assertEquals(p[3][3], p[0][3].next());
        
        assertEquals(p[0][2], p[1][0].next());
        assertEquals(p[1][2], p[1][1].next());
        assertEquals(p[2][2], p[1][2].next());
        assertEquals(p[3][2], p[1][3].next());
        
        assertEquals(p[0][1], p[2][0].next());
        assertEquals(p[1][1], p[2][1].next());
        assertEquals(p[2][1], p[2][2].next());
        assertEquals(p[3][1], p[2][3].next());
        
        assertEquals(p[0][0], p[3][0].next());
        assertEquals(p[1][0], p[3][1].next());
        assertEquals(p[2][0], p[3][2].next());
        assertEquals(p[3][0], p[3][3].next());
    }

    /**
     * Test of previous method, of class Position.
     */
    @Test
    public void testPrevious() {
        System.out.println("previous");
        for(int l = 0; l < 4; ++l)
            for(int c = 0; c < 4; ++c) {
                Position p = new Position(l,c);
                assertEquals(p.next().previous(), p);
                assertEquals(p.previous().next(), p);
            }
    }
    
}
