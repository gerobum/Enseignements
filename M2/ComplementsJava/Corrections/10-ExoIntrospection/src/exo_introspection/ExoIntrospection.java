package exo_introspection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExoIntrospection {

    private static final Scanner IN = new Scanner(System.in);

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

        System.out.print("Donnez le nom pleinement qualifié d'une classe : ");
        nom = IN.next();
        Class c = Class.forName(nom);        

        int i = 0;
        ArrayList<Method> am = getMethods(c, exo_introspection.ExoIntrospection::estStatiqueAvecParamTypeSimple);
        for (Method m : am) {
            System.out.println(i++ + ". " + m.toString());
        }
        System.out.println("--------------------------------");
        System.out.print(String.format("Choisir une méthode de la classe %s : ", c.getName()));
        int choix = IN.nextInt();
        System.out.println("--------------------------------");
        execute(am.get(choix));
        System.out.println("--------------------------------");
    }

    private static ArrayList<Constructor> getConstructors(Class<?> c) {
        ArrayList<Constructor> a = new ArrayList<>();
        Stream.of(c.getDeclaredConstructors()).forEach(m -> {
            a.add(m);
        });
        return a;
    }

    private static ArrayList<Method> getMethods(Class<?> c, Predicate<Method> p) {
        return (ArrayList<Method>) Stream.of(c.getDeclaredMethods()).filter(m -> p.test(m)).collect(Collectors.toList());
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
    
    private static String toString(Method m, Object[] params, Object result) {
        StringBuilder sb = new StringBuilder(m.getName());
        
        sb.append(('('));
        if (params != null && params.length > 0) {
            sb.append(params[0]);
            for(int i = 1; i < params.length; ++i) {
                sb.append(", ").append(params[i]);
            }            
        }
        sb.append(") = ").append(result);
        
        return sb.toString();
    }

    private static void execute(Method m) {
        Object[] params = new Object[m.getParameterCount()];
        int i = 0;
        for (Class<?> c : m.getParameterTypes()) {
            params[i] = getValue(c);
        }
        try {
            Object r = m.invoke(null, params);
            System.out.println(toString(m, params, r));
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.err.println(ex);
        }
    }

    private static Object getValue(Class<?> c) {
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
            return null;
        }
    }

}
