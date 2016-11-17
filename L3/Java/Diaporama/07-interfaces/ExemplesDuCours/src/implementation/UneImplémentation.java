package implementation;

import interfaces.UneInterface;

/**
 *
 * @author yvan
 */
public class UneImplémentation implements UneInterface {

    @Override
    public void m1() {
        System.out.println("Il faut faire quelque chose");
    }

    @Override
    public int m2(int i, double d) {
        return (int) (i * d);
    }

    @Override
    public String md() {
        return UneInterface.super.md(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
