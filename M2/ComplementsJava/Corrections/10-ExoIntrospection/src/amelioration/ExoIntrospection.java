/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amelioration;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ExoIntrospection {

    private static Scanner IN = new Scanner(System.in);

    private static Object newInstance(Class<?> c) {

        int i = 0;
        ArrayList<Constructor> ac = getConstructors(c);
        for (Constructor k : ac) {
            System.out.println(i++ + ". " + k.toString());
        }

        System.out.print("Choisir un constructeur (ou -1) ");

        try {
            Constructor k = ac.get(IN.nextInt());
            System.out.println(k);
            List<Object> params = new LinkedList<>();
            for (Class<?> x : k.getParameterTypes()) {
                params.add(getValue(x));
            }
            return k.newInstance(params.toArray());
        } catch (ArrayIndexOutOfBoundsException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return null;
        }
    }

    /*
     Écrire un programme qui
     — lit au clavier le nom d’une classe
     — affiche sur la sortie standard toutes ses méthodes statiques déclarées directement dans
     cette classe et dont les paramètres et le type de retour sont tous des types simples (ou
     primitifs) ou String.
     — propose à l’utilisateur de choisir une méthode parmi celles affichées en saisisant au clavier
     son nom et le type de ses paramètres (sa signature)
     — demande au clavier des valeurs pour les paramètres
     — exécute la méthode et affiche son résultat.
     */
    public static void main(String[] args) throws ClassNotFoundException {

        String nom;

        System.out.println("Donnez le nom pleinement qualifié d'une classe");
        nom = IN.next();
        Class c = Class.forName(nom);

        Object objet = newInstance(c);
        System.out.println("L'objet instancié : " + objet);
        System.out.println();

        int i = 0;
        ArrayList<Method> am = getMethods(c, p -> true);
        for (Method m : am) {
            System.out.println(i++ + ". " + m.toString());
        }
        System.out.print("Choisir une méthode : ");
        int choix = IN.nextInt();
        execute(objet, am.get(choix));
    }

    private static ArrayList<Constructor> getConstructors(Class<?> c) {
        ArrayList<Constructor> a = new ArrayList<>();
        Stream.of(c.getDeclaredConstructors()).forEach(m -> {
            a.add(m);
        });
        return a;
    }

    private static ArrayList<Method> getMethods(Class<?> c, Predicate<Method> p) {
        ArrayList<Method> a = new ArrayList<>();
        Stream.of(c.getDeclaredMethods()).filter(m -> p.test(m)).forEach(m -> {
            a.add(m);
        });
        return a;
    }

    public static boolean estStatiqueAvecParamTypeSimple(Method m) {
        for (Class c : m.getParameterTypes()) {
            if (!c.isPrimitive() && c != String.class) {
                return false;
            }
        }
        Class c = m.getReturnType();
        if (!c.isPrimitive() && c != String.class) {
            return false;
        }
        return Modifier.isStatic(m.getModifiers());
    }

    private static void execute(Object o, Method m) {
        List<Object> params = new LinkedList<>();
  
        for (Class<?> c : m.getParameterTypes()) {
            params.add(getValue(c));
        }
        try {
            Object r = m.invoke(o, params.toArray());
            if (r != null) {
                System.out.println(r);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        }
    }

    private static Object getValue(Class<?> c) {
        if (c.isArray()) {
            System.out.println("Entrez un tableau de " + c.getComponentType());
            System.out.print("Combien d'éléments dans le tableau : ");
            int ne = IN.nextInt();
            Object to = Array.newInstance(c.getComponentType(), ne);
            for (int i = 0; i < ne; ++i) {
                Array.set(to, i, getValue(c.getComponentType()));
            }
            return to;
        } else {
            System.out.print("Entrez un " + c.getName() + " : ");
            if (c == int.class) {
                return IN.nextInt();
            } else if (c == boolean.class) {
                return IN.nextBoolean();
            } else if (c == byte.class) {
                return IN.nextByte();
            } else if (c == double.class) {
                return IN.nextDouble();
            } else if (c == float.class) {
                return IN.nextFloat();
            } else if (c == long.class) {
                return IN.nextLong();
            } else if (c == short.class) {
                return IN.nextShort();
            } else if (c == char.class) {
                return IN.next().charAt(0);
            } else if (c == String.class) {
                return IN.next();
            } else {
                return newInstance(c);
            }
        }
    }
}

