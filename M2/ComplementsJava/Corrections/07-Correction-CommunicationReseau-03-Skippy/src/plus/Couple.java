package plus;

import java.util.Objects;

public class Couple<T, U> {

    public final T un;
    public final U deux;

    public Couple(T un, U deux) {
        this.un = un;
        this.deux = deux;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.un);
        hash = 73 * hash + Objects.hashCode(this.deux);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Couple<?, ?> other = (Couple<?, ?>) obj;
        if (!Objects.equals(this.un, other.un)) {
            return false;
        }
        return Objects.equals(this.deux, other.deux);
    }

};
