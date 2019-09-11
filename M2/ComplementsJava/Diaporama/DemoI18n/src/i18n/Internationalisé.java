package i18n;

/**
 *
 * @author yvan
 */
public class Internationalisé {
    static public void main(String[] args) {
        System.out.println(java.util.ResourceBundle.getBundle("i18e/mots").getString("BONJOUR"));
        System.out.println(java.util.ResourceBundle.getBundle("i18e/mots").getString("COMMENT ALLEZ VOUS ?"));
        System.out.println(java.util.ResourceBundle.getBundle("i18e/mots").getString("AU REVOIR"));
    }  
}
