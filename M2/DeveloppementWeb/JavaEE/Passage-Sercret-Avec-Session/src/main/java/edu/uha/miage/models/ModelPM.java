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
package edu.uha.miage.models;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class ModelPM implements Serializable {

    private boolean connected = false;
    private String name = "";
    private static final String PASSWORD = "Vive Java !";
    private List<String> msgs = new LinkedList<>();

    public ModelPM() {

    }

    private ModelPM(String name, boolean connected) {
        this.name = name;
        this.connected = connected;
    }

    public List<String> getMsgs() {
        return msgs;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addMsg(String msg) {
        msgs.add(msg);
    }

    public static void handle(HttpServletRequest request, HttpServletResponse response) {
        boolean connected;
        String name;
        String password;
        String msg;

        name = request.getParameter("name");
        password = request.getParameter("password");
        if (name != null && name.trim().isEmpty()) {
            connected = false;
            msg = "Tu as bien un nom ?";
        } else if (!PASSWORD.equals(password)) {
            msg = "Ce n'est pas la phrase magique " + name + ". Reformule !";
            connected = false;
        } else {
            connected = true;
            msg = "Bienvenue dans le monde merveilleux de Java, " + name;
        }
        ModelPM pm = new ModelPM(name, connected);
        
        pm.addMsg(msg);
        if (connected) {
            pm.addMsg("");
            pm.addMsg("Maintenant tu restes connecté même si tu navigues ailleurs.");
            pm.addMsg("");
            pm.addMsg("Pour te déconnecter : /logout.");
            pm.addMsg("");
            pm.addMsg("En revanche, si tu fermes le navigateur la connexion est perdue");
            pm.addMsg("Pour l'éviter, il faut utiliser les cookies");
            pm.addMsg("");
            pm.addMsg("Voir HttpServletRequest.getCookies()");
            pm.addMsg("");
            pm.addMsg("À ton clavier");
        }
        // #### getSession()
        request.getSession().setAttribute("pm", pm);
    }
}
