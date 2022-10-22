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
package fr.miage.modele;

import fr.miage.metier.morpion.Morpion;
import fr.miage.metier.morpion.Pion;
import fr.miage.metier.morpion.exceptions.FinishedException;
import fr.miage.metier.morpion.exceptions.AlreadyChosenException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yvan
 */
public class ModelMorpion {

    private Morpion morpion = new Morpion(true);
    private String message;
    private boolean finished;
    private boolean firstPlayerIsChosen = false;
    private char playerToken;

    public char get(int l, int c) {
        return morpion.get(l, c);
    }

    public static ModelMorpion handle(HttpServletRequest request) {
        
        if (request.getParameter("rejouer") != null)
            return new ModelMorpion();
        
        
        ModelMorpion modelMorpion = (ModelMorpion) request.getSession().getAttribute("modele");
        if (modelMorpion == null)
            modelMorpion = new ModelMorpion();
        
        if (request.getParameter("firstPlayer") != null) {
            if ("moi".equals(request.getParameter("firstPlayer"))) {
                modelMorpion.morpion = new Morpion(false);
                modelMorpion.playerToken = 'O';
            } else {
                modelMorpion.playerToken = 'X';
            }
            modelMorpion.firstPlayerIsChosen = true;
        }
        
        int whichL = -1, whichC = -1;
        boolean stop = false;
        for (int l = 0; l < 3 && !stop; ++l) {
            for (int c = 0; c < 3 && !stop; ++c) {
                if (request.getParameter("b" + l + c) != null) {
                    whichL = l;
                    whichC = c;
                    stop = true;
                }
            }
        }
        if (whichL >= 0) {

            try {
                modelMorpion.morpion.play(whichL, whichC);
                modelMorpion.finished = false;
                modelMorpion.message = "Joue encore";
            } catch (AlreadyChosenException | FinishedException ex) {
                modelMorpion.finished = true;
                if (modelMorpion.morpion.whoWin() == Pion.EMPTY.value) {
                    modelMorpion.message = "Match null";
                } else {
                    modelMorpion.message = "Les " + modelMorpion.morpion.whoWin() + " gagnent";
                }
            }
        }
        return modelMorpion;
    }

    public String getMessage() {
        return message;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isFirstPlayerIsChosen() {
        return firstPlayerIsChosen;
    }

    public char getPlayerToken() {
        return playerToken;
    }
    
}
