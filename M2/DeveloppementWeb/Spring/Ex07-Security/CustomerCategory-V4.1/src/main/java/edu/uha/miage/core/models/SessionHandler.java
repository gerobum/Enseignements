
package edu.uha.miage.core.models;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class SessionHandler {


    public static void clearList(HttpSession session) {
        List<String> list = (List<String>) session.getAttribute("stringList");
        if (list == null) {
            list = new LinkedList<>();
        } else {
            list.clear();
        }
        session.setAttribute("stringList", list);

    }
}
