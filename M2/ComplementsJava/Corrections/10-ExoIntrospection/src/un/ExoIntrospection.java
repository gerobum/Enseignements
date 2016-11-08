package un;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExoIntrospection {

    private static Scanner sin = new Scanner(System.in);

    private static Object newInstance(Class<?> c) {

        int i = 0;
        ArrayList<Constructor> ac = getConstructors(c);
        for (Constructor k : ac) {
            System.out.println(i++ + ". " + k.toString());
        }

        System.out.print("Choisir un constructeur (ou -1) ");

        try {
            Constructor k = ac.get(sin.nextInt());
            System.out.println(k);
            //Object[] params = new Object[k.getParameterCount()];
            List<Object> params = new LinkedList<>();
            i = 0;
            for (Class<?> x : k.getParameterTypes()) {
                params.add(getValue(x));
            }
            return k.newInstance(params.toArray());
        } catch (ArrayIndexOutOfBoundsException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
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
        nom = sin.next();
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
        int choix = sin.nextInt();
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
        Object[] params = new Object[m.getParameterCount()];
        int i = 0;
        for (Class<?> c : m.getParameterTypes()) {
            params[i] = getValue(c);
        }
        try {
            Object r = m.invoke(o, params);
            if (r != null) {
                System.out.println(r);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.err.println(ex);
        }
    }

    private static Object getValue(Class<?> c) {
        System.out.print("Entrez un " + c.getName() + " : ");
        if (c == int.class) {
            return sin.nextInt();
        } else if (c == boolean.class) {
            return sin.nextBoolean();
        } else if (c == byte.class) {
            return sin.nextByte();
        } else if (c == double.class) {
            return sin.nextDouble();
        } else if (c == float.class) {
            return sin.nextFloat();
        } else if (c == long.class) {
            return sin.nextLong();
        } else if (c == short.class) {
            return sin.nextShort();
        } else if (c == char.class) {
            return sin.next().charAt(0);
        } else if (c == String.class) {
            return sin.next();
        } else {
            return newInstance(c);
        }
    }

}
