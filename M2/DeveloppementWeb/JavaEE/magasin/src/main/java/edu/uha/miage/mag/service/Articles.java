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
package edu.uha.miage.mag.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author yvan
 */
public class Articles {
    private static final Map<String, Article> articles = new HashMap<>();
    
    static {
        articles.put("Coffee", new Article("Coffee", 1.0));
        articles.put("Tea", new Article("Tea", 0.85));
        articles.put("Chocolat", new Article("Chocolat", 1.25));
        articles.put("Tisane", new Article("Tisane", 0.55));
    }
    
    /**
     * Retourne si l'article dont le nom est donné en paramètre est disponible.
     * @param articleName nom de l'article
     * @return l'article nommé articleName est disponible
     */
    public static boolean isAvailable(String articleName) {
        return articles.keySet().contains(articleName);
    }
    
    public static Article getArticle(String articleName) {
        return articles.get(articleName);
    }
    
    public static void add(String articleName, double price) {
        articles.put(articleName, new Article(articleName, price));
    }
    
    public static void remove(String articleName) {
        articles.remove(articleName);
    }
    
    public static Set<String> getNames() {
        return articles.keySet();
    }
}
