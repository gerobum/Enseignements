/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo5.mutable;

import static java.lang.Math.*;

/**
 *
 * @author yvan
 */
public class TestPoint {

    public static void main(String[] args) {
        Point p11 = new Point(1, 1);
        p11.afficher(true);
        p11.afficher(false);
        for (int i = 0; i < 8; i++) {
            p11.tourner(PI/4);
            p11.afficher(true);
            p11.afficher(false);
        }
    }
}
