/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import geom.Point;

/**
 *
 * @author yvan
 */
public class Main {
    public static void main(String[] args) {
        Point p00 = new Point();
        Point p11 = new Point(1, 1);
        
        Point pg = new Point(0.2, 0.5);
        Point pd = new Point(0.8, 0.5);
        
        System.out.println(pg.aGauche(p00, p11));
        System.out.println(pd.aGauche(p00, p11));
    }
}
