// #### V3.0 A model in order to manage a list according to the session
package edu.uha.miage.models;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class SessionHandler {

    /*public static void addPathToList(HttpSession session, String path) {
        List<String> list = (List<String>) session.getAttribute("list");
        if (list == null) {
            list = new LinkedList<>();
        }
        list.add(path);
        session.setAttribute("list", list);

    }*/
    public static void addToStringList(List<String> list, HttpServletRequest request) {
        StringBuilder path = new StringBuilder(request.getRequestURI());
        if (request.getQueryString() != null && !request.getQueryString().
                isEmpty()) {
            path.append('?').append(request.getQueryString());
        }
        list.add(path.toString());
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

    private static void append(String queryString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
