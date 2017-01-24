/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analyseur;

import java.util.regex.Pattern;

/**
 *
 * @author yvan
 */
public class Analyseur {

    public static boolean estUnChiffre(char c, int b) {
        return b >= 2 && b <= 10 && c >= '0' && c < '0' + b
                || b > 10 && b <= 36 && (c >= '0' && c <= '9'
                || c >= 'A' && c < 'A' + b - 10
                || c >= 'a' && c < 'a' + b - 10);
    }

    public static boolean estUnNaturel(String mot, int b) {
        if (b < 2 || b > 36 || mot.length() == 0) {
            return false;
        } else {

            for (char c : mot.toCharArray()) {
                if (!estUnChiffre(c, b)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean estUnRelatif(String mot, int b) {
        return mot != null && mot.length() > 0 && (estUnNaturel(mot, b)
                || ((mot.charAt(0) == '+' || mot.charAt(0) == '-')
                && estUnNaturel(mot.substring(1), b)));
    }

    public static boolean estUnNombreAVirgule(String mot, int b) {
        if (mot.length() == 0 || b < 2 || b > 36) {
            return false;
        }
        if (mot.length() == 1 && (mot.charAt(0) == '+' || mot.charAt(0) == '-')) {
            return false;
        }
        StringBuilder sb = new StringBuilder("[+-]?");
        if (b < 10) {
            sb.append("[0-").append((char) ('0' + b - 1)).append("]*,?[0-").append((char) ('0' + b - 1)).append("]*");
        } else {
            sb.append("[0-9").append("A-").append((char) ('A' + b - 10)).append("a-").append((char) ('a' + b - 10)).append("],?[0-9").append("A-").append((char) ('A' + b - 10)).append("a-").append((char) ('a' + b - 10)).append("]");
        }
        Pattern pattern = Pattern.compile(sb.toString());
        return pattern.matcher(mot).matches();
    }
}
