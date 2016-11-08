/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import implementation.UneImplémentation;
import interfaces.UneInterface;

/**
 *
 * @author yvan
 */
public class Main {

    public static void main(String[] args) {
        UneImplémentation a = new UneImplémentation();
        a.m1();
        System.out.println(a.m2(5, 2.5));
        System.out.println(UneImplémentation.c1);
        // Depuis java 1.8
        System.out.println(a.md());
        System.out.println(UneInterface.ms());
    }
}
