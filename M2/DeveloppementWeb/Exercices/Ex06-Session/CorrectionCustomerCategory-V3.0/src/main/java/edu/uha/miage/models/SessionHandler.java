// #### V3.0 A model in order to manage a list according to the session
package edu.uha.miage.models;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class SessionHandler {
    public static void addPathToList(HttpSession session, String path) {
        List<String> list = (List<String>) session.getAttribute("list");
        if (list == null) {
            list = new LinkedList<>();    
        }
        list.add(path);
        session.setAttribute("list", list);

    }

    public static void clearList(HttpSession session) {
        List<String> list = (List<String>) session.getAttribute("list");
        if (list == null) {
            list = new LinkedList<>();    
        } else {
            list.clear();
        }
        session.setAttribute("list", list);

    }
}
