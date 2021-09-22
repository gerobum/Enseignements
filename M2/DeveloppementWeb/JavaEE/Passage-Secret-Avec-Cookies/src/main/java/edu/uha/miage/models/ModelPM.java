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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class ModelPM implements Serializable {

    private boolean connected;
    private String name;
    private static final String PASSWORD = "Vive Java !";
    // #### 
    private static final String COOKIE_KEY = "connectedOnPassageSecret";
    private final List<String> msgs;

    public ModelPM() {
        this("", false);
    }

    private ModelPM(String name, boolean connected) {
        this.msgs = new LinkedList<>();
        this.name = name;
        this.connected = connected;
        if (connected) {
            this.msgs.add("Heureux de te revoir " + name);
        }
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

    // #### 
    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie cookie = new Cookie(COOKIE_KEY, "NONAME");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

    }

    // #### 
    private static Cookie getConnected(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (COOKIE_KEY.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    // #### 
    public static void init(HttpServletRequest request) {
        if (request.getSession().getAttribute("pm") == null) {
            // #### 
            Cookie cookie = getConnected(request);
            if (cookie == null) {
                request.getSession().setAttribute("pm", new ModelPM());
            } else {
                request.getSession().setAttribute("pm", new ModelPM(cookie.getValue(), true));
            }
        }
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
            
            // #### 
            Cookie cookie = new Cookie(COOKIE_KEY, name);
            cookie.setMaxAge(5 * 60);
            // #### dans response
            response.addCookie(cookie);
            msg = "Bienvenue dans le monde merveilleux de Java, " + name;
        }
        ModelPM pm = new ModelPM(name, false);
        pm.connected = connected;
        pm.addMsg(msg);
        if (connected) {
            
            
            pm.addMsg("");
            pm.addMsg("Tu peux maintenant fermer le navigateur et revenir ici");
            pm.addMsg("");
            pm.addMsg("Pour partir complètement : /logout)");
        }
        request.getSession().setAttribute("pm", pm);
    }
}
