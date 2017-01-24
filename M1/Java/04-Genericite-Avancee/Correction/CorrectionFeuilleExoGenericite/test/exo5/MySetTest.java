/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo5;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
public class MySetTest {

    public MySetTest() {
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
     * Test of equals method, of class MySet.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        MySet<Integer> s1 = new MySet<>();
        MySet<Integer> s2 = new MySet<>();
        List<Integer> l1 = new LinkedList<>();
        List<Integer> l2 = new LinkedList<>();
        for (int i = 1; i < 10; ++i) {
            l1.add(i);
            l2.add(10 - i);
            s1.add(i);
            s2.add(10 - i);
        }
        assertNotEquals(l2, l1);
        assertEquals(s1, s2);
    }

    /**
     * Test of hashCode method, of class MySet.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        MySet<Integer> s1 = new MySet<>();
        MySet<Integer> s2 = new MySet<>();
        List<Integer> l1 = new LinkedList<>();
        List<Integer> l2 = new LinkedList<>();
        for (int i = 1; i < 10; ++i) {
            l1.add(i);
            l2.add(10 - i);
            s1.add(i);
            s2.add(10 - i);
        }
        assertNotEquals(l2.hashCode(), l1.hashCode());
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    /**
     * Test of toString method, of class MySet.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MySet<Integer> s1 = new MySet<>();
        for (int i = 1; i < 10; ++i) {
            s1.add(i);
        }
        assertEquals("{9, 8, 7, 6, 5, 4, 3, 2, 1}", s1.toString());
    }

    /**
     * Test of clone method, of class MySet.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        MySet<Integer> s1 = new MySet<>();
        for (int i = 1; i < 10; ++i) {
            s1.add(i);
        }
        MySet<Integer> s2 = s1.clone();
        assertEquals(s1, s2);
        s1.add(10);
        assertNotEquals(s1, s2);
    }

    /**
     * Test of size method, of class MySet.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        MySet<Integer> ms = new MySet<>();
        assertEquals(ms.size(), 0);
        for (int i = 1; i < 10; ++i) {
            ms.add(i);
            assertEquals(ms.size(), i);
        }
        assertNotEquals(ms.size(), 0);
    }

    /**
     * Test of isEmpty method, of class MySet.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        MySet<Integer> ms = new MySet<>();
        assertTrue(ms.isEmpty());
        for (int i = 1; i < 10; ++i) {
            ms.add(i);
            assertFalse(ms.isEmpty());
        }
    }

    /**
     * Test of contains method, of class MySet.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        MySet<Integer> ms = new MySet<>();
        assertFalse(ms.contains(0));
        assertFalse(ms.contains(null));
        for (int i = 1; i < 10; ++i) {
            ms.add(i);
            assertFalse(ms.contains(0));
            assertFalse(ms.contains(null));
            for (int j = i; j > 0; j--) {
                assertTrue(ms.contains(j));
            }
        }
    }

    /**
     * Test of iterator method, of class MySet.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        MySet<Integer> ms = new MySet<>();
        Iterator<Integer> I = ms.iterator();
        assertFalse(I.hasNext());
        for (int i = 1; i < 10; ++i) {
            ms.add(i);
        }
        I = ms.iterator();
        assertTrue(I.hasNext());
        int i = 9;
        while (I.hasNext()) {
            assertEquals(new Integer(i--), I.next());
        }
    }

    /**
     * Test of toArray method, of class MySet.
     */
    @Test
    public void testToArray_0args() {
        System.out.println("toArray");
        MySet<Integer> ms = new MySet<>();
        Integer[] tint = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] ta = {};
        for (int i = 1; i < 10; ++i) {
            ms.add(i);
        }
        assertArrayEquals(tint, ms.toArray());
    }

    /**
     * Test of toArray method, of class MySet.
     */
    @Test
    public void testToArray_GenericType() {
        System.out.println("toArray");
        MySet<Integer> ms = new MySet<>();
        Integer[] tint = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] ta = {};
        for (int i = 1; i < 10; ++i) {
            ms.add(i);
        }
        assertArrayEquals(tint, ms.toArray(ta));
    }

    /**
     * Test of add method, of class MySet.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        MySet<Integer> ms = new MySet<>();
        for (int i = 1; i < 10; ++i) {
            ms.add(i);
            assertTrue(ms.contains(i));
        }
        assertFalse(ms.contains(0));
    }

    /**
     * Test of remove method, of class MySet.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        MySet<Integer> ms = new MySet<>();
        for (int i = 1; i < 10; ++i) {
            ms.add(i);
        }
        for (int i = 1; i < 10; ++i) {
            assertTrue(ms.contains(i));
            ms.remove(i);
            assertFalse(ms.contains(i));
        }
    }

    /**
     * Test of containsAll method, of class MySet.
     */
    @Test
    public void testContainsAll() {
        System.out.println("containsAll");
        MySet<Integer> ms = new MySet<>();
        for (int i = 1; i < 10; ++i) {
            ms.add(i);
        }
        List<Integer> l = new LinkedList<>();
        l.add(5);
        l.add(4);
        l.add(7);
        assertTrue(ms.containsAll(l));
        l.add(null);
        assertFalse(ms.containsAll(l));
    }

    /**
     * Test of addAll method, of class MySet.
     */
    @Test
    public void testAddAll() {
        System.out.println("addAll");
        MySet<Integer> s1 = new MySet<>();
        for (int i = 1; i < 7; ++i) {
            s1.add(i);
        }
        MySet<Integer> s2 = new MySet<>();
        for (int i = 4; i < 10; ++i) {
            s2.add(i);
        }
        MySet<Integer> s3 = new MySet<>();
        for (int i = 1; i < 10; ++i) {
            s3.add(i);
        }
        s1.addAll(s2);
        assertEquals(9, s1.size());
        assertEquals(s3, s1);
    }

    /**
     * Test of retainAll method, of class MySet.
     */
    @Test
    public void testRetainAll() {
        System.out.println("retainAll");
        MySet<Integer> s1 = new MySet<>();
        for (int i = 1; i < 7; ++i) {
            s1.add(i);
        }
        MySet<Integer> s2 = new MySet<>();
        for (int i = 4; i < 10; ++i) {
            s2.add(i);
        }
        MySet<Integer> s3 = new MySet<>();
        for (int i = 4; i < 7; ++i) {
            s3.add(i);
        }
        s1.retainAll(s2);
        assertEquals(s3, s1);
    }

    /**
     * Test of removeAll method, of class MySet.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        MySet<Integer> s1 = new MySet<>();
        for (int i = 1; i < 7; ++i) {
            s1.add(i);
        }
        MySet<Integer> s2 = new MySet<>();
        for (int i = 4; i < 10; ++i) {
            s2.add(i);
        }
        MySet<Integer> s3 = new MySet<>();
        for (int i = 1; i < 4; ++i) {
            s3.add(i);
        }
        s1.removeAll(s2);
        assertEquals(s3, s1);
    }

    /**
     * Test of clear method, of class MySet.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        MySet<Integer> s1 = new MySet<>();
        for (int i = 1; i < 7; ++i) {
            s1.add(i);
        }
        assertNotEquals(0, s1.size());
        s1.clear();
        assertEquals(0, s1.size());
    }

}
