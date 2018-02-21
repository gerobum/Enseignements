/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.util.Random;
import java.util.stream.LongStream;

/**
 *
 * @author yvan
 */
public class DemoLambdaExpression {
    
    /*private static void test5() {
        System.out.println("Test 5");
        Random random = new Random();
        random.ints(2, 5);
        long first = System.currentTimeMillis();
        random.ints(2, 5)
                .limit(10000)
                .forEach(System.out::println);
        long last = System.currentTimeMillis();
        System.out.printf("calculé en %d ms\n", last - first);
        System.out.printf("--------------------------\n");
    }*/
    
    
    private static void test5() {
        System.out.println("Test 5");
        long first = System.currentTimeMillis();
        long d;
        d = LongStream.range(1, 1_000_000_000)
                .filter(p -> p % 2 == 0 && p % 999_999_999 == 0)
                .count();
        long last = System.currentTimeMillis();
        System.out.printf("%d - calculé en %d ms\n", d, last - first);
        System.out.printf("--------------------------\n");
    }
    

    private static void test4() {
        System.out.println("Test 4");
        long first = System.currentTimeMillis();
        long d;
        d = LongStream.range(1, 1_000_000_000)
                .filter(p -> p % 999_999_999 == 0)
                .filter(p -> p % 2 == 0)
                .count();
        long last = System.currentTimeMillis();
        System.out.printf("%d - calculé en %d ms\n", d, last - first);
        System.out.printf("--------------------------\n");
    }

    private static void test3() {
        System.out.println("Test 3");
        long first = System.currentTimeMillis();
        long d = LongStream.range(1, 1_000_000_000)
                .filter(p -> p % 2 == 0)
                .filter(p -> p % 999_999_999 == 0)
                .peek(System.out::println)
                .count();
        long last = System.currentTimeMillis();
        System.out.printf("%d - calculé en %d ms\n", d, last - first);
        System.out.printf("--------------------------\n");
    }

    private static void test2() {
        System.out.println("Test 2");
        long first = System.currentTimeMillis();
        long d = LongStream.range(1, 1_000_000_000)
                .filter(p -> p % 999_999_999 == 0)
                .count();
        long last = System.currentTimeMillis();
        System.out.printf("%d - calculé en %d ms\n", d, last - first);
        System.out.printf("--------------------------\n");
    }

    private static void test1() {
        System.out.println("Test 1");
        long first = System.currentTimeMillis();
        long d = LongStream.range(1, 1_000_000_000)
                .count();
        long last = System.currentTimeMillis();
        System.out.printf("%d - calculé en %d ms\n", d, last - first);
        System.out.printf("--------------------------\n");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

}
