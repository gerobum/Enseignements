package redefinition;

class T0 {
}

class T1 extends T0 {
}

class C0 {

    public void f1() {
        System.out.println("Dans C0");
    }

    protected T0 f2() {
        return null;
    }

    protected T0 f3() {
        return null;
    }
}

public class C1 extends C0 {

    public void f1() {
        System.out.println("Dans C1");
    }

    protected T0 f2() {
        return null;
    }

    public T1 f3() {
        return null;
    }

}