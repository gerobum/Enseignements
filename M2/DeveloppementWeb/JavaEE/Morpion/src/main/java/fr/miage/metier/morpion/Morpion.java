/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package fr.miage.metier.morpion;

import fr.miage.metier.morpion.exceptions.FinishedException;
import fr.miage.metier.morpion.exceptions.AlreadyChosenException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * Pour jouer au morpion contre l'ordinateur
 *
 * Celui qui commence a toujours les O L'autre joue avec les X
 *
 *
 */
public class Morpion implements Cloneable {

    private static enum Result {
        WON, DRAW, LOST
    }

    private static Random R = new Random();

    private Pion[][] morpion = {
        {Pion.EMPTY, Pion.EMPTY, Pion.EMPTY},
        {Pion.EMPTY, Pion.EMPTY, Pion.EMPTY},
        {Pion.EMPTY, Pion.EMPTY, Pion.EMPTY},};
    private LinkedList<Integer> remaining = new LinkedList<>();

    private Pion whoIsPlaying = Pion.ROND;

    /**
     * Pour lancer une partie de morpion.
     *
     * @param computerFirst vrai => l'ordinateur commence
     */
    public Morpion(boolean computerFirst) {
        for (int i = 1; i <= 9; ++i) {
            remaining.add(i);
        }
        if (computerFirst) {
            computerIsPlaying();
        }
    }

    /*private Result evaluate() {
        
            if (whoWin() == Pion.EMPTY.value) {
                return Result.DRAW;
            } else if (whoIsPlaying.value == whoWin()) {
                return Result.LOST;
            } else {
                return Result.WON;
            }
        
    }*/
    private static class Move {

        public final Pion pion;
        public final int p;
        public final Result result;

        public Move(Pion pion, int p, Result result) {
            this.pion = pion;
            this.p = p;
            this.result = result;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 29 * hash + Objects.hashCode(this.pion);
            hash = 29 * hash + this.p;
            hash = 29 * hash + Objects.hashCode(this.result);
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
            final Move other = (Move) obj;
            if (this.p != other.p) {
                return false;
            }
            if (this.pion != other.pion) {
                return false;
            }
            return this.result == other.result;
        }

    }

    private static Move bestMove(Morpion morpion, int niveau, boolean max) {
        if (morpion.isFinished()) {
            throw new IllegalStateException("Already finished");
        }
        try {
            List<Integer> nulMoves = new LinkedList<>();
            Collections.shuffle(nulMoves);

            for (int p : morpion.remaining) {
                Morpion cp = morpion.clone();
                cp.set(p, cp.whoIsPlaying);
                if (cp.whoWin() != Pion.EMPTY.value) {
                    if (max) {
                        return new Move(cp.whoIsPlaying, p, Result.WON);
                    } else {
                        return new Move(cp.whoIsPlaying, p, Result.LOST);
                    }
                } else {
                    if (cp.isFinished()) {
                        return new Move(cp.whoIsPlaying, p, Result.DRAW);
                    }
                    nulMoves.add(p);
                }
            }

            Set<Move> movesSet = new HashSet<>();
            for (int p : nulMoves) {

                Morpion cp = morpion.clone();
                cp.set(p, cp.whoIsPlaying);
                cp.togglePlayer();
                movesSet.add(bestMove(cp, niveau - 1, !max));
            }
            
            List<Move> moves = new LinkedList<>(movesSet);

            if (max) {
                Collections.sort(moves, (t1, t2) -> t1.result.compareTo(t2.result));
            } else {
                Collections.sort(moves, (t1, t2) -> t2.result.compareTo(t1.result));
            }
            return moves.get(0);

        } catch (AlreadyChosenException | FinishedException ex) {
            throw new IllegalStateException();
        }
    }

    private static Move bestMove(Morpion morpion) {
        return bestMove(morpion, 10, true);
    }

    private int computerMove() {
        Iterator<Integer> iterator = remaining.iterator();
        int n = R.nextInt(remaining.size());
        int p = iterator.next();
        for (int i = 0; i < n - 1; ++i) {
            p = iterator.next();
        }
        return p;
        /*int p0 = 0;
        for (int p : remaining) {
            Result result = tryMove(this.clone(), p, 10);
            switch(result) {
                case WON:
                    return p;
                case DRAW:
                    p0 = p;
            }
        }
        return p0;*/
    }

    private void computerIsPlaying() {
        if (whoWin() == Pion.EMPTY.value) {
            try {
                set(bestMove(this).p, whoIsPlaying);
                togglePlayer();
            } catch (AlreadyChosenException | FinishedException ex) {
                // this should not happend
            }
        }
    }

    /**
     * Pour savoir quel pion est joué en ligne l et colonne c
     *
     * @param l la ligne dont on veut le pion
     * @param c la colonne dont on veut le pion
     * @return le caractère en en ligne l et colonne c ('X', 'O' ou ' ')
     * @throws ArrayIndexOutOfBoundsException si l ou c ne sont pas dans [0,3[
     */
    public char get(int l, int c) {
        return morpion[l][c].value;
    }

    /**
     * Pour savoir si la partie est finie
     *
     * @return vrai si la partie est finie
     */
    public boolean isFinished() {
        return remaining.isEmpty() || whoWin() != Pion.EMPTY.value;
    }

    /**
     * Pour jouer son pion en ligne l, colonne c. Le pion est choisi
     * automatiquement. L'ordinateur joue automatiquement après.
     *
     * @param l
     * @param c
     * @throws AlreadyChosenException si la case(l, c) a déjà été jouée
     * @throws FinishedException si la partie est finie
     */
    public void play(int l, int c) throws AlreadyChosenException, FinishedException {
        if (isFinished()) {
            throw new FinishedException();
        }
        if (get(l, c) != Pion.EMPTY.value) {
            throw new AlreadyChosenException();
        }
        set(l, c, whoIsPlaying);
        togglePlayer();
        computerIsPlaying();
    }

    private void togglePlayer() {
        if (whoIsPlaying == Pion.ROND) {
            whoIsPlaying = Pion.CROIX;
        } else {
            whoIsPlaying = Pion.ROND;
        }
    }

    private void set(int l, int c, Pion v) throws AlreadyChosenException {
        if (!isFinished()) {

            if (get(l, c) != Pion.EMPTY.value) {
                throw new AlreadyChosenException();
            }
            if (v != Pion.CROIX && v != Pion.ROND) {
                throw new IllegalStateException();
            }
            morpion[l][c] = v;
            remaining.remove((Object) (l * 3 + c + 1));
        }

    }

    private void set(int p, Pion v) throws AlreadyChosenException, FinishedException {
        p--;
        set(p / 3, p % 3, v);
    }

    /**
     * Pour connaître le vainqueur
     *
     * @return 'X' si les X ont gagné, 'O' si les O ont gagné et ' ' sinon.
     */
    public char whoWin() {
        for (int i = 0; i < 3; ++i) {
            if (get(i, 0) == get(i, 1) && get(i, 1) == get(i, 2) && get(i, 0) != Pion.EMPTY.value) {
                return get(i, 0);
            }
        }
        for (int i = 0; i < 3; ++i) {
            if (get(0, i) == get(1, i) && get(1, i) == get(2, i) && get(0, i) != Pion.EMPTY.value) {
                return get(0, i);
            }
        }
        if (get(0, 0) == get(1, 1) && get(1, 1) == get(2, 2) && get(0, 0) != Pion.EMPTY.value) {
            return get(0, 0);
        }
        if (get(0, 2) == get(1, 1) && get(1, 1) == get(2, 0) && get(0, 2) != Pion.EMPTY.value) {
            return get(0, 2);
        }
        return Pion.EMPTY.value;
    }

    /*public Pion getWhoIsPlaying() {
        return whoIsPlaying;
    }*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        for (int l = 0; l < 2; ++l) {
            sb.append(get(l, 0)).append('|').append(get(l, 1)).append('|').append(get(l, 2)).append('\n');
            sb.append("-+-+-\n");
        }
        int l = 2;
        sb.append(get(l, 0)).append('|').append(get(l, 1)).append('|').append(get(l, 2)).append('\n');
        return sb.toString();
    }

    @Override
    public Morpion clone() {
        try {
            Morpion copy = (Morpion) super.clone();
            copy.morpion = morpion.clone();
            for (int i = 0; i < copy.morpion.length; ++i) {
                copy.morpion[i] = morpion[i].clone();
            }
            copy.remaining = (LinkedList<Integer>) remaining.clone();
            return copy;
        } catch (CloneNotSupportedException ex) {
            throw new IllegalStateException();
        }
    }

    public static void main(String[] args) {
        Morpion morpion = new Morpion(true);
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("[^0-9]");
        System.out.println(morpion);

        while (!morpion.isFinished()) {
            System.out.print("> ");
            try {
                morpion.play(scanner.nextInt(), scanner.nextInt());

            } catch (AlreadyChosenException | FinishedException ex) {
                System.out.println("Déjà choisie");
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Hors cadre " + Arrays.toString(ex.getStackTrace()));
            } catch (InputMismatchException ex) {
                System.out.println("Essaye encore");
            }
            System.out.println(morpion);
        }

        System.out.println("The winner is " + morpion.whoWin());

    }

}
