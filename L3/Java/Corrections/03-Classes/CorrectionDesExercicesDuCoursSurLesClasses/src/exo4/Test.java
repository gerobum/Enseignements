/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exo4;

/**
 *
 * @author yvan
 */
public class Test {
    public static void main(String[] args) {
        Afficheur.printfSimplifie("% tiens vaut mieux que % tu l'auras\n", 1, 2);        
        Afficheur.printfSimplifie(" % tiens vaut mieux que % tu l'auras\n", 1, 2);
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                Afficheur.printfSimplifie("% x % = %", i, j, i*j);
                System.out.println();
            }
        }
    }
}
