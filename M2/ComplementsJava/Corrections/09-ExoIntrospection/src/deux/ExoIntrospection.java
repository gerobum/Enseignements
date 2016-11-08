/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deux;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 *
 * @author yvan
 */
public class ExoIntrospection {

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
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        boolean encore = true;
      
        
        String nom = null;
        do {
            try {
                Scanner sin = new Scanner(System.in);
                System.out.println("Donnez le nom pleinement qualifié d'une classe");
                nom = sin.nextLine();
                Class c = Class.forName(nom);
                choisirMethode(c);
                
            } catch (ClassNotFoundException ex) {
                if ("fin".equals(nom)) {
                    encore = false;
                } else {
                    System.err.println("Erreur dans le nom");
                }
            } catch (NoSuchMethodException ex) {
               
            }
        } while (encore);
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
    
    private static Class<?> forName(String nom) throws ClassNotFoundException {
        try {
            return Class.forName(nom);
        } catch (ClassNotFoundException ex) {
            switch(nom) {
                case "int" :
                    return int.class;
                case "long" :
                    return long.class;
                case "short" :
                    return short.class;
                case "byte" :
                    return byte.class;
                case "boolean" :
                    return boolean.class;
                case "float" :
                    return float.class;
                case "double" :
                    return double.class;
                case "String" :
                    return String.class;
                default:
                    throw ex;
            }
        }
    }

    private static void choisirMethode(Class c) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Scanner sin = new Scanner(System.in);
        for (Method m : c.getDeclaredMethods()) {
            if (estStatiqueAvecParamTypeSimple(m)) {
                System.out.println(m);
            }
        }
        System.out.println("Entrez le nom d'une méthode");
        String nom = sin.next();
        System.out.print("Combien de paramètres ? ");
        int n = sin.nextInt();
        Class[] tc = new Class[n];
        for(int i = 0; i < tc.length; i++) {
            try {
                System.out.println("Entrez le type du paramètre #"+i+" : ");
                tc[i] = forName(sin.next());
            } catch (ClassNotFoundException ex) {
                i--;
            }
        }
        Method m = c.getDeclaredMethod(nom, tc);
        executer(m);
        
    }

    private static void executer(Method m) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {        
        System.out.println("Méthode choisie : " + m);
        Object[] tc = new Object[m.getParameterTypes().length];
        int i = 0;
        for(Class<?> c : m.getParameterTypes()) {
            System.out.print("Entrez une valeur de type " + c + " : ");
            tc[i++] = saisir(c);
        }
        m.setAccessible(true);
        System.out.println("Résultat : " + m.invoke(null, tc));
    }

    private static Object saisir(Class<?> c) {
        Scanner sin = new Scanner(System.in);
        if (c == int.class) {
            return sin.nextInt();
        } else if (c == long.class) {
            return sin.nextLong();
        } else if (c == byte.class) {
            return sin.nextByte();
        } else if (c == short.class) {
            return sin.nextShort();
        } else if (c == double.class) {
            return sin.nextDouble();
        } else if (c == float.class) {
            return sin.nextFloat();
        } else if (c == boolean.class) {
            return sin.nextBoolean();
        } else {
            return sin.next();
        }
    }

}
