/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package volee;

abstract class A {

    public abstract void m();
}

public class Main {

    public static void main(String[] args) {
        A a = new A() {
            @Override
            public void m() {
                System.out.println("à la volée");
                
            }
        };
    }
}
