package interfaces;

/**
 *
 * @author yvan
 */
public interface UneInterface {

    final int c1 = 2;

    void m1();

    int m2(int i, double d);
// Depuis java 1.8

    default String md() {
        return "default";
    }

    static String ms() {
        return "static";
    }

    class C {
    }

    interface I {
    }
}
