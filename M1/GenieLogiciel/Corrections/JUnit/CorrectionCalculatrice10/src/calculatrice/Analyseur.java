/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatrice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author yvan
 */
public class Analyseur {

    public static boolean estUnChiffre(char c) {
        return c >= '0' && c <= '9';
    }

    public static boolean estUnNaturel(String mot) {
        if (mot == null || mot.length() == 0) {
            return false;
        }
        for (char c : mot.toCharArray()) {
            if (!estUnChiffre(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean estUnRelatif(String mot) {
        if (mot == null || mot.length() == 0) {
            return false;
        }
        return estUnNaturel(mot)
                || ((mot.charAt(0) == '+' || mot.charAt(0) == '-') && estUnNaturel(mot.substring(1)));
    }

    public static boolean estUnNombreAVirgule(String mot) {
        if (mot == null || mot.length() == 0 || "+".equals(mot)
                || "-".equals(mot) || "+,".equals(mot)
                || "-,".equals(mot) || "+,".equals(mot)) {
            return false;
        }
        //Pattern p = Pattern.compile("[+-]?[0-9]*,?[0-9]*");
        Pattern p = Pattern.compile("(.*),(.*)");
        //return p.matcher(mot).matches();
        Matcher matcher = p.matcher(mot);
        if (matcher.matches()) {
            return estUnRelatif(matcher.group(1).trim()) && estUnNaturel(matcher.group(2).trim());
        }
        return false;
    }

    public static boolean estUnNombre(String mot) {
        return estUnNombreAVirgule(mot) || estUnRelatif(mot) || estUnNaturel(mot);
    }

    public static boolean estUneOperation(String mot) {
        /*Pattern p1 = Pattern.compile("[+-]?(.*)");
        Matcher m = p1.matcher(mot);
        if (m.matches() && estUnNombreAVirgule(m.group(1).trim())) {
            return true;
        } else */{
            //Pattern p1 = Pattern.compile("^[+-]?([^+/*-]*)[+/*-](.*)$");
            Pattern p1 = Pattern.compile("[+-]?([^+*/-]*)[+*/-](.*)");
            Matcher m = p1.matcher(mot);
            if (m.matches()) {
                return estUnNombre(m.group(1).trim()) && estUnNombre(m.group(2).trim());
            } else {
                return false;
            }
        }
    }

    public static boolean estUneExpressionSansParenthese(String mot) {
        if (estUneOperation(mot) || estUnNombre(mot)) {
            return true;
        }

        Pattern p = Pattern.compile("[+-]\\s*(.*)");
        Matcher m = p.matcher(mot);
        if (m.matches()) {
            return estUneExpressionSansParenthese(m.group(1).trim());
        }

        p = Pattern.compile("^[+-]?([^+/*-]+)\\s*[+/*-]\\s*(.*)");
        m = p.matcher(mot);
        if (m.matches()) {
            return estUneExpressionSansParenthese(m.group(1)) && estUneExpressionSansParenthese(m.group(2));
        } else {
            return false;
        }
    }

    public static boolean estUneExpression(String mot) {
        if (Analyseur.estUneExpressionSansParenthese(mot)) {
            return true;
        } else {
            Pattern p = Pattern.compile(".*\\(([^()]+)\\).*");
            Matcher m = p.matcher(mot);
            if (m.matches()) {
                if (estUneExpressionSansParenthese(m.group(1))) {

                    return estUneExpression(mot.substring(0, m.start(1) - 1) + "0" + mot.substring(m.end(1) + 1));
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static int compare(String a, String b) {
        if (a.length() > b.length()) {
            return 1;
        } else if (a.length() < b.length()) {
            return -1;
        } else {
            for (int i = 0; i < a.length(); ++i) {
                if (a.charAt(i) > b.charAt(i)) {
                    return 1;
                } else if (a.charAt(i) < b.charAt(i)) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public static String soustractionDeNaturels(String a, String b) {
        StringBuilder resultat = new StringBuilder();
        int cmp = compare(a, b);
        String c;
        if (cmp == 0) {
            return "0";
        } else if (cmp < 0) {
            c = a;
            a = b;
            b = c;
        }

        int ia = a.length() - 1;
        int ib = b.length() - 1;

        int ir = 0;

        while (ib >= 0) {
            int va = a.charAt(ia) - '0';
            int vb = b.charAt(ib) - '0' + ir;
            if (va < vb) {
                va += 10;
                ir = 1;
            } else {
                ir = 0;
            }
            resultat.append((char) ('0' + (va - vb)));
            --ia;
            --ib;
        }

        while (ia >= 0) {

            int va = a.charAt(ia) - '0';
            int vb = ir;
            if (va < vb) {
                va += 10;
                ir = 1;
            } else {
                ir = 0;
            }
            resultat.append((char) ('0' + (va - vb)));
            --ia;
        }

        resultat.reverse();

        while (resultat.charAt(0) == '0' && resultat.length() > 1) {
            resultat.deleteCharAt(0);
        }

        if (cmp < 0) {
            resultat.insert(0, "-");
        }
        return resultat.toString();
    }

    public static String additionDeRelatifs(String a, String b) {
        String somme;
        boolean aneg = a.charAt(0) == '-';
        boolean bneg = b.charAt(0) == '-';

        if (aneg && bneg) {
            somme = additionDeNaturels(a.substring(1), b.substring(1));
            somme = "-" + somme;
        } else if (aneg && !bneg) {
            somme = soustractionDeNaturels(b, a.substring(1));
        } else if (!aneg && bneg) {
            somme = soustractionDeNaturels(a, b.substring(1));
        } else {
            somme = additionDeNaturels(a, b);
        }
        return somme;
    }

    public static String additionDeNaturels(String a, String b) {

        int ia = a.length() - 1;
        int ib = b.length() - 1;
        StringBuilder somme = new StringBuilder();
        int ir = 0;
        while (ib >= 0 && ia >= 0) {
            int is = a.charAt(ia) - '0' + b.charAt(ib) - '0' + ir;
            ir = is / 10;
            somme.append((char) ('0' + is % 10));
            --ia;
            --ib;
        }
        if (ia >= 0) {
            while (ia >= 0) {
                int is = a.charAt(ia) - '0' + ir;
                ir = is / 10;
                somme.append((char) ('0' + is % 10));
                --ia;
                --ib;
            }
        } else {
            while (ib >= 0) {
                int is = b.charAt(ib) - '0' + ir;
                ir = is / 10;
                somme.append((char) ('0' + is % 10));
                --ia;
                --ib;
            }
        }
        if (ir > 0) {
            somme.append((char) ('0' + ir));
        }
        return somme.reverse().toString();
    }

    public static String multiplicationDUnScalaireParUnNaturel(char s, String n) {
        if (s == '0') {
            return "0";
        }
        if (s == '1') {
            return n;
        }
        StringBuilder resultat = new StringBuilder();
        int is = s - '0';
        int ir = 0;
        int i = n.length() - 1;
        while (i >= 0) {
            int v = (n.charAt(i) - '0') * is + ir;
            resultat.append((char) (v % 10 + '0'));
            ir = v / 10;
            --i;
        }
        if (ir > 0) {
            resultat.append((char) (ir + '0'));
        }
        return resultat.reverse().toString();
    }

    public static String multiplicationDeNaturels(String a, String b) {

        if (compare(a, b) > 0) {
            String x = a;
            a = b;
            b = x;
        }

        String c = "0";
        String zeros = "";

        for (int i = b.length() - 1; i >= 0; --i) {
            String produitIntermediaire = multiplicationDUnScalaireParUnNaturel(b.charAt(i), a);
            produitIntermediaire += zeros;
            zeros += "0";
            c = additionDeNaturels(c, produitIntermediaire);
        }

        while (c.charAt(0) == '0' && c.length() > 1) {
            c = c.substring(1);
        }

        return c;
    }
}
