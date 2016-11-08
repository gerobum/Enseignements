package tri;

import interfaces.Compartimentable;
import interfaces.Compartimenteur;
import utilitaire.Stack;

/**
 *
 * @author yvan
 */
public class TrieurParCompartiment {

    private Object[] tableau;
    private Compartimenteur compartimenteur;

    public TrieurParCompartiment(Compartimentable[] tableau) {
        this(tableau, null);
    }

    public TrieurParCompartiment(Object[] tableau, Compartimenteur compartimenteur) {
        this.tableau = tableau;
        this.compartimenteur = compartimenteur;
    }

    public void trier() {
        if (compartimenteur == null) {

            Stack[] stacks = new Stack[((Compartimentable) tableau[0]).getNumberOfCompartiments()];
            for (int i = 0; i < stacks.length; ++i) {
                stacks[i] = new Stack();
            }
            for (int i = 0; i < tableau.length; ++i) {
                stacks[((Compartimentable) tableau[i]).getCompartiment()].push(tableau[i]);
            }
            int k = 0;
            for (Stack stack : stacks) {
                k = remplirTableauWithStack(stack, k);
            }
        } else {
            Stack[] stacks = new Stack[compartimenteur.getNumberOfCompartiments(tableau[0])];
            for (int i = 0; i < stacks.length; ++i) {
                stacks[i] = new Stack();
            }
            for (int i = 0; i < tableau.length; ++i) {
                stacks[compartimenteur.getCompartiment(tableau[i])].push(tableau[i]);
            }
            int k = 0;
            for (Stack stack : stacks) {
                k = remplirTableauWithStack(stack, k);
            }
        }
    }

    private int remplirTableauWithStack(Stack stack, int k) {
        while (!stack.isEmpty()) {
            tableau[k++] = (Compartimentable) stack.pop();
        }
        return k;
    }
}
