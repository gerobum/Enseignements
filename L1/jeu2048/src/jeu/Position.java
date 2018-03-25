/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

/**
 *
 * @author yvan
 */
public class Position {
    public final int l, c;

    public Position(int l, int c) {
        this.l = l;
        this.c = c;
    }
    
    public Position next() {
        return new Position(c, 3-l);
    }
    
    public Position previous() {
        return new Position(3-c, l);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.l;
        hash = 61 * hash + this.c;
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
        final Position other = (Position) obj;
        if (this.l != other.l) {
            return false;
        }
        return this.c == other.c;
    }
    
    
    @Override
    public String toString() {
        return "("+l+", "+c+")";
    }
}
