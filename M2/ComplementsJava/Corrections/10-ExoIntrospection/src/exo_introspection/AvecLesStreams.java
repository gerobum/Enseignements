package exo_introspection;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maillot
 */
public class AvecLesStreams {

    public static Class<?> forName(String name) throws ClassNotFoundException {
        switch (name) {
            case "int":
                return int.class;
            case "double":
                return double.class;
            case "char":
                return char.class;
            case "byte":
                return byte.class;
            case "float":
                return byte.class;
            case "long":
                return byte.class;
            case "boolean":
                return boolean.class;
            default:
                return Class.forName(name);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Scanner in = new Scanner(System.in);
            System.out.print("Entrez le nom d'une classe : ");
            Class<?> aClass = Class.forName(in.next());
            boolean atleastone = false;
            System.out.println("Les méthodes statiques qui respectent les conditions :");
            Arrays.stream(aClass.getDeclaredMethods())
                    .filter(m -> Modifier.isStatic(m.getModifiers()))
                    .filter(m -> m.getReturnType().isPrimitive()
                            || m.getReturnType() == String.class)
                    .filter(m -> {
                        for (Class<?> t : m.getParameterTypes()) {
                            if (!t.isPrimitive() && t != String.class) {
                                return false;
                            }

                        }
                        return true;
                    })
                    .forEach(System.out::println);
            System.out.println("------------------------------");
            System.out.println("Choisir une méthode parmi celles proposées");
            Method m = getMethodForName(aClass);
            exec(m);

        } catch (ClassNotFoundException ex) {
            System.out.println("Attention il faut donner le nom pleinement qualifié d'une classe.");
        } catch (NoSuchMethodException ex) {
            System.out.println("La méthode saisie n'est pas reconnue : " + ex.getMessage());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.println("Problème à l'invocation de la méthode choisie");
        }
    }

    private static Method getMethodForName(Class<?> classe) throws NoSuchMethodException, ClassNotFoundException {
        Scanner in = new Scanner(System.in);
        String nom, nometparams;
        System.out.print("Nom de la méthode et le type de ses paramètres : ");
        nometparams = in.nextLine();
        Scanner sin = new Scanner(nometparams);
        nom = sin.next();
        List<Class<?>> lparams = new ArrayList<>();
        while (sin.hasNext()) {
            lparams.add(forName(sin.next()));
        }

        return classe.getMethod(nom, lparams.toArray(new Class<?>[]{}));
    }

    private static void exec(Method m) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("Exécution de " + m.getName());
        List<Object> lp = new ArrayList<>();
        for (Class<?> p : m.getParameterTypes()) {
            lp.add(getValue(p));
        }
        if (m.getReturnType() != void.class) {
            System.out.println(m.invoke(null, lp.toArray()));
        } else {
            m.invoke(null, lp.toArray());
        }
    }

    private static Object getValue(Class<?> c) {
        Scanner in = new Scanner(System.in);
        System.out.print("Entrez un " + c.getName() + " : ");
        if (c == int.class) {
            return in.nextInt();
        } else if (c == boolean.class) {
            return in.nextBoolean();
        } else if (c == byte.class) {
            return in.nextByte();
        } else if (c == double.class) {
            return in.nextDouble();
        } else if (c == float.class) {
            return in.nextFloat();
        } else if (c == long.class) {
            return in.nextLong();
        } else if (c == short.class) {
            return in.nextShort();
        } else if (c == char.class) {
            return in.next().charAt(0);
        } else if (c == String.class) {
            return in.next();
        } else {
            return null;
        }
    }
}
