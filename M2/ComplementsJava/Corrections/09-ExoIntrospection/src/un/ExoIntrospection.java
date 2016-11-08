package un;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

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
    public static void main(String[] args) throws ClassNotFoundException {

        String nom;

        Scanner sin = new Scanner(System.in);
        System.out.println("Donnez le nom pleinement qualifié d'une classe");
        nom = sin.nextLine();
        Class c = Class.forName(nom);

        for (Method m : c.getDeclaredMethods()) {
            if (estStatiqueAvecParamTypeSimple(m)) {
                System.out.println(m);
            }
        }
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

}
