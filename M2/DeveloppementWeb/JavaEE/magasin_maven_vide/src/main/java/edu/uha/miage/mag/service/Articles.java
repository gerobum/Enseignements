
package edu.uha.miage.mag.service;

import java.util.HashMap;
import java.util.Map;


public class Articles {
    private static final Map<String, Article> articles = new HashMap<>();
    
    static {
        articles.put("Coffee", new Article("Coffee", 1.0));
        articles.put("Tea", new Article("Tea", 0.85));
        articles.put("Choco", new Article("Choco", 1.25));
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
}
