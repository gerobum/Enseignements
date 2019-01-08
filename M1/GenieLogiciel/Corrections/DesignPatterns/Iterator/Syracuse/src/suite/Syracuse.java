
package suite;

import java.util.Iterator;

public class Syracuse implements Iterator<Integer> {
    private int u_n;

    public Syracuse(int u_n) {
        this.u_n = u_n;
    }

    @Override
    public boolean hasNext() {
        return u_n > 1;
    }

    @Override
    public Integer next() {
        if (u_n % 2 == 0)
            u_n = u_n/2;
        else
            u_n = 3*u_n+1;
        return u_n;
    }  
}
