/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo3;

/**
 *
 * @author yvan
 */
public class Test {
    public static void main(String[] args) {
        for(short i = 0; i < Premier.nbPremiers(); i++) {
            System.out.print(Premier.premier(i)+" ");
        }
    }
}
