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
package edu.uha.miage.mag.models;

import edu.uha.miage.mag.service.Article;
import edu.uha.miage.mag.service.Articles;
import java.io.Serializable;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class PanierModel implements Serializable {


    
    private Article article;
    private boolean articleOk;
    private Article chosenArticle;

    public PanierModel(Article article, boolean articleOk) {
        super();
        this.article = article;
        this.articleOk = articleOk;
    }

    public PanierModel() {
        this(new Article("", 0), true);
        chosenArticle = null;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public boolean isArticleOk() {
        return articleOk;
    }

    public void setArticleOk(boolean articleOk) {
        this.articleOk = articleOk;
    }

    public Article getChosenArticle() {
        return chosenArticle;
    }

    public static Object handle(HttpServletRequest request) {
        String name = request.getParameter("name");

        PanierModel model;

        if (request.getSession().getAttribute("model") == null) {
            model = new PanierModel();
            System.out.println("NULL");
        } else {
            model = (PanierModel) request.getSession().getAttribute("model");
            System.out.println("PAS NULL");
        }

        if (Articles.isAvailable(name)) {
            model.chosenArticle = Articles.getArticle(name);
            model.articleOk = true;
        } else {
            model.articleOk = false;
            model.article = new Article(name, Double.NaN);

        }
        return model;
    }
    
    public Set<String> getNames() {
        return Articles.getNames();
    }
}
