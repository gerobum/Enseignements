package exo5;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/*
Écrire dans un paquetage exo5 une classe qui implémente l’interface Set du 
framework Collections.
*/
public class MySet<E> implements Set<E>, Cloneable {

    private class Element<E> implements Cloneable {

        private final E e;
        private Element<E> suivant;

        public Element(E e, Element<E> suivant) {
            this.e = e;
            this.suivant = suivant;
        }

        @Override
        public Element<E> clone() {
            try {
                return (Element<E>) super.clone();
            } catch (CloneNotSupportedException ex) {
                throw new InternalError();
            }
        }
    }

    private Element<E> tete = null;

    // Redéfinitions de Object
    @Override
    // equals doit tenir compte de l'absence d'ordre dans un set
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MySet<?> os = (MySet<?>) obj;
        if (size() != os.size()) {
            return false;
        }
        return this.containsAll(os);
    }

    @Override
    // hashCode doit tenir compte de l'absence d'ordre dans un set
    public int hashCode() {
        int hash = 17;
        Element<E> crt = tete;
        while (crt != null) {
            hash = hash ^ crt.e.hashCode();
            crt = crt.suivant;
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (!isEmpty()) {
            sb.append(tete.e);
            Element<E> crt = tete.suivant;
            while (crt != null) {
                sb.append(", ").append(crt.e);
                crt = crt.suivant;
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public MySet<E> clone() {
        try {
            MySet<E> clone = (MySet<E>) super.clone();

            if (tete != null) {
                clone.tete = tete.clone();

                Element<E> crtclone = clone.tete;
                Element<E> crt = tete;
                while (crt.suivant != null) {
                    crtclone.suivant = crt.suivant.clone();
                    crtclone = crtclone.suivant;
                    crt = crt.suivant;
                }
            }
            return clone;
        } catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }

    // Implémentation des méthodes
    @Override
    public int size() {
        Element<E> crt = tete;
        int size = 0;
        while (crt != null) {
            ++size;
            crt = crt.suivant;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return tete == null;
    }

    @Override
    public boolean contains(Object o) {
        Element<E> crt = tete;
        while (crt != null && !(crt.e == null && o == null) && !(crt.e != null && crt.e.equals(o))) {
            crt = crt.suivant;
        }
        return crt != null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Element<E> crt = tete;

            @Override
            public boolean hasNext() {
                return crt != null;
            }

            @Override
            public E next() {
                E e = crt.e;
                crt = crt.suivant;
                return e;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] to = new Object[size()];
        Iterator<E> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            to[i++] = it.next();
        }
        return to;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int size = size();
        T[] r;
        if (a.length >= size) {
            r = a;
        } else {
            r = (T[]) Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            r[i++] = (T) it.next();
        }
        for (; i < r.length; ++i) {
            r[i] = null;
        }
        return r;
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        } else {
            tete = new Element<>(e, tete);
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        Element<E> crt = tete;
        if (tete != null) {
            if ((o == null && tete.e == null) || tete.e.equals(o)) {
                tete = tete.suivant;
                return true;
            } else {
                while (crt.suivant != null && !(crt.suivant.e == null && o == null) && !crt.suivant.e.equals(o)) {
                    crt = crt.suivant;
                }
                if (crt.suivant != null) {
                    crt.suivant = crt.suivant.suivant;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changement = false;
        for (Object e : c) {
            changement = add((E) e) || changement;
        }
        return changement;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changement = false;
        while (tete != null && !c.contains(tete.e)) {
            tete = tete.suivant;
            changement = true;
        }

        if (tete != null) {
            Element<E> crt = tete;
            while (crt.suivant != null) {
                if (!c.contains(crt.suivant.e)) {
                    crt.suivant = crt.suivant.suivant;
                    changement = true;
                } else {
                    crt = crt.suivant;
                }
            }
        }

        return changement;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changement = false;
        while (tete != null && c.contains(tete.e)) {
            tete = tete.suivant;
            changement = true;
        }

        if (tete != null) {
            Element<E> crt = tete;
            while (crt.suivant != null) {
                if (c.contains(crt.suivant.e)) {
                    crt.suivant = crt.suivant.suivant;
                    changement = true;
                } else {
                    crt = crt.suivant;
                }
            }
        }

        return changement;
    }

    @Override
    public void clear() {
        tete = null;
    }
}
