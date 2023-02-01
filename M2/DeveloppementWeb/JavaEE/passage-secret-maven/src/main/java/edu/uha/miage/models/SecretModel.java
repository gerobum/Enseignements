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

/**
 *
 * @author yvan
 */
public class SecretModel implements Serializable {

    private String title;
    private String name;
    private String magic;
    private String footer;
    private boolean nameOk;
    private boolean magicOk;
    private boolean connected;
    private static final String PASSWORD = "Vive Java !";
    private final List<String> finalMsgs = new LinkedList<>();

    public SecretModel(String title, String name, String magic, String footer, boolean nameOk, boolean magicOk, boolean connected) {
        this.title = title;
        this.name = name;
        this.magic = magic;
        this.footer = footer;
        this.nameOk = nameOk;
        this.magicOk = magicOk;
        this.connected = connected;
    }

    public SecretModel() {
        this(
                "Donne ton nom et dis la phrase magique pour entrer dans un monde merveilleux",
                "",
                "",
                "",
                true,
                true,
                false
        );
    }

    public List<String> getFinalMsgs() {
        return finalMsgs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMagic() {
        return magic;
    }

    public void setMagic(String magic) {
        this.magic = magic;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public boolean isNameOk() {
        return nameOk;
    }

    public void setNameOk(boolean nameOk) {
        this.nameOk = nameOk;
    }

    public boolean isMagicOk() {
        return magicOk;
    }

    public void setMagicOk(boolean magicOk) {
        this.magicOk = magicOk;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    private static void generateFinalMsg(SecretModel model) {
        model.finalMsgs.add("Mais attention !");
            model.finalMsgs.add("");
            model.finalMsgs.add("Mais si tu essayes à nouveau de lancer l'URL /passage-secret/magic");
            model.finalMsgs.add("tu vas t'apercevoir que tu n'es plus connecté.e.");
            model.finalMsgs.add("C'est normal. HTTP est sans état");
            model.finalMsgs.add("");
            model.finalMsgs.add("À toi de modifier cette application pour rester connecté.e,");
            model.finalMsgs.add("même si tu navigues ailleurs");
            model.finalMsgs.add("");
            model.finalMsgs.add("Jusqu'à ce tu décides finalement de quitter avec l'URL /passage-secret/logout");
            model.finalMsgs.add("");
            model.finalMsgs.add("Voir la méthode HttpServletRequest.getSession()");
            model.finalMsgs.add("Voir l'objet implicite sessionScope dans les pages JSP");
            model.finalMsgs.add("");
            model.finalMsgs.add("À ton clavier");
    }

    public static SecretModel handle(HttpServletRequest request) {
        String name = request.getParameter("name");
        String magic = request.getParameter("magic");

        SecretModel model = new SecretModel();

        if (name == null || name.isBlank()) {
            model.name = name;
            model.footer = "Tu as bien un nom ?";
            model.nameOk = false;
            model.connected = false;
        } else if (!PASSWORD.equals(magic)) {
            model.name = name;
            model.footer = "Ce n'est pas la phrase magique " + name + ". Reformule !";
            model.magicOk = false;
            model.connected = false;
        } else {
            model.name = name;
            model.connected = true;
            generateFinalMsg(model);
        }

        return model;
    }

}
