/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 *
 * @author yvan
 */
public class T2048 implements Cloneable {

    private Integer[][] t = new Integer[4][4];
    private final Random R = new Random();

    public T2048(int... x) {
        if (x.length != 0) {
            int i = 0;
            for (int l = 0; l < 4; ++l) {
                for (int c = 0; c < 4; ++c) {
                    t[l][c] = x[i] == 0 ? null : x[i];
                    ++i;
                }
            }
        }
        //putRandomNumber();
    }

    public void set(Position p, Integer d) {
        set(p.l, p.c, d);
    }

    public void set(int l, int c, Integer d) {
        t[l][c] = d;
    }

    public Integer get(int l, int c) {
        return t[l][c];
    }

    public Integer get(Position p) {
        return get(p.l, p.c);
    }

    public void permutRight(Position p) {
        Integer i = get(p);
        Position n = p.previous();
        while (!n.equals(p)) {
            set(n.next(), get(n));
            n = n.previous();
        }
        set(n.next(), i);
    }

    public void permutLeft(Position p) {
        Integer i = get(p);
        Position n = p.next();
        while (!n.equals(p)) {
            set(n.previous(), get(n));
            n = n.next();
        }
        set(n.previous(), i);
    }

    public void rotateLeft() {
        permutLeft(new Position(0, 0));
        permutLeft(new Position(0, 1));
        permutLeft(new Position(0, 2));
        permutLeft(new Position(1, 1));
    }

    public void rotateRight() {
        permutRight(new Position(0, 0));
        permutRight(new Position(0, 1));
        permutRight(new Position(0, 2));
        permutRight(new Position(1, 1));
    }

    private boolean down(int c) {
        boolean change = false;
        int nl = 3;

        while (nl >= 0) {
            int l = nl - 1;
            while (l >= 0 && !Objects.equals(t[l][c], t[nl][c])) {
                --l;
            }
            if (l >= 0 && t[nl][c] != null) {
                t[nl][c] += t[l][c];
                t[l][c] = null;
                change = true;
            }
            --nl;
        }

        nl = 3;
        while (nl >= 0) {
            int l = nl;

            while (l >= 0 && t[l][c] == null) {
                --l;
            }

            if (l >= 0 && l < nl) {
                t[nl][c] = t[l][c];
                t[l][c] = null;
                change = true;
            }

            nl--;
        }
        return change;
    }

    public void left() {
        rotateLeft();
        down();
        rotateRight();
    }

    public void right() {
        rotateRight();
        down();
        rotateLeft();
    }

    public void up() {
        rotateLeft();
        rotateLeft();
        down();
        rotateLeft();
        rotateLeft();
    }

    private int nbNull() {
        int nbNull = 0;
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                if (t[l][c] == null) {
                    ++nbNull;
                }
            }
        }
        return nbNull;
    }

    private void putRandomNumber() {
        int v = 2;
        if (R.nextInt(5) == 0) {
            v = 4;
        }

        int n = R.nextInt(nbNull());
        int l = 0;
        int c = 0;
        for (int i = 0; i < n; ++i) {
            ++c;
            if (c == 4) {
                c = 0;
                ++l;
                if (l == 4) {
                    l = 0;
                }
            }
            while (t[l][c] != null) {
                ++c;
                if (c == 4) {
                    c = 0;
                    ++l;
                    if (l == 4) {
                        l = 0;
                    }
                }
            }
        }
        t[l][c] = v;
    }

    public void down() {
        boolean b0, b1, b2, b3;
        b0 = down(0);
        b1 = down(1);
        b2 = down(2);
        b3 = down(3);
        
        /*if (b0 || b1 || b2 || b3) {
            putRandomNumber();
        }*/
    }

    public void affiche() {
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                System.out.print((t[l][c] == null ? 0 : t[l][c]) + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Arrays.deepHashCode(this.t);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final T2048 other = (T2048) obj;
        return Arrays.deepEquals(this.t, other.t);
    }

    @Override
    public T2048 clone() {
        try {
            T2048 r = (T2048) super.clone();
            r.t = t.clone();
            return r;

        } catch (CloneNotSupportedException ex) {
            throw new IllegalStateException();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                sb.append(t[l][c]).append(" ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
