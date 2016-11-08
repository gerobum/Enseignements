package fichiers;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Stream;
import javax.swing.JFileChooser;

/*
 * 7.3 Sujet
 * Écrire une classe utilitaire 2 dont les méthodes permettront d’afficher, 
 * compter, sélectionner, trier, voire davantage, les fichiers d’une 
 * arborescence de fichiers ou leurs propriétés.
 */
public class Fichiers {

    public final static Random random = new Random();

    /*
     * 1. Écrire une méthode pour afficher le chemin absolu de tous les fichiers 
     * et dossiers accessibles depuis la racine.
     */
    public static void afficherTousLesFichiersEtDossier(Path racine) throws IOException {
        Files.walk(racine)
                .forEach(System.out::println);
    }

    /*
     * 2. Écrire une méthode pour afficher le chemin absolu de tous les dossiers 
     * accessibles depuis la racine.
     */
    public static void afficherTousLesDossiers(Path racine) throws IOException {
        Files.walk(racine)
                .filter(p -> p.toFile().isDirectory())
                .forEach(System.out::println);
    }

    /*
     * 3. Écrire une méthode pour afficher le chemin absolu de tous les fichiers et 
     * dossiers cachés accessibles depuis la racine.
     */
    public static void afficherTousLesDossiersEtFichiersCaches(Path racine) throws IOException {

        Files.walk(racine)
                .filter(p -> p.toFile().isHidden())
                .forEach(System.out::println);
    }

    /*
     * 4. Écrire une méthode pour afficher le chemin absolu de tous les fichiers 
     * cachés accessibles depuis la racine.
     */
    public static void afficherTousLesFichiersCaches(Path racine) throws IOException {
        Files.walk(racine)
                .filter(p -> p.toFile().isHidden())
                .filter(p -> p.toFile().isFile())
                .forEach(System.out::println);
    }

    /*
     * 5. Écrire une méthode pour afficher le chemin absolu de tous les fichiers 
     * de taille nulle accessibles depuis la racine.
     */
    public static void afficherTousLesFichiersDeTailleNulle(Path racine) throws IOException {

        Files.walk(racine)
                .filter(p -> p.toFile().length() == 0)
                .forEach(System.out::println);

    }

    /*
     * 5+. Pas demandé.
     */
    public static void afficherTousLesFichiersCachesDeTailleNulle(Path racine) throws IOException {

        Files.walk(racine)
                .filter(p -> p.toFile().isHidden())
                .filter(p -> p.toFile().length() == 0)
                .forEach(System.out::println);

    }

    /*
     * 6. Écrire une méthode qui retourne le nombre d’octets qu’occupe toute 
     * l’arborescence issue de racine.
     */
    public static long nombreDOctets(Path racine) throws IOException {
        return Files.walk(racine)
                .mapToLong(p -> p.toFile().length())
                .sum();
    }

    /*
     * 7. Écrire une méthode qui retourne le nombre d’octets qu’occupent les 
     * fichiers cachés accessibles depuis la racine.
     */
    public static long nombreDOctetsDesFichiersCaches(Path racine) throws IOException {
        return Files.walk(racine)
                .filter(p -> p.toFile().isHidden())
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
    }

    /*
     * 8. Écrire une méthode pour afficher tous les fichiers accessibles depuis 
     * la racine différents de celui passé en paramètre mais de même taille et 
     * de même nom.
     */
    public static void afficherTousLesFichiersDifferentsDefMaisDeMemeTailleEtDeMemeNom(Path racine, File f) throws IOException {
        Files.walk(racine)
                .filter(p -> p.toFile().isFile())
                .filter(p -> p.toFile().length() == f.length())
                .filter(p -> p.toFile().getName().equals(f.getName()))
                .filter(p -> !p.toFile().equals(f))
                .forEach(System.out::println);
    }

    public static void afficherTousLesFichiersRangésParTaille(Path racine) throws IOException {
        
        Files.walk(racine)
                .filter(p -> p.toFile().isFile())
                .sorted((a, b) -> {
                    if (a.toFile().length() < b.toFile().length()) {
                        return -1;
                    } else if (a.toFile().length() > b.toFile().length()) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .forEach(p -> {
                    System.out.println(p.toFile().length() + " -> " + p.toFile().getName() + " : " + p);
                });

    }

    public static Stream<?> tousLesFichiersCachésTriés(Path racine) throws IOException {
        return Files.walk(racine)
                .map(p -> p.toFile())
                .filter(p -> p.isHidden())
                .sorted()
                .map(p -> p.length() + " -> " + p.getName())
                .distinct();
    }

    public static Stream<Path> tousLesDossiers(Path racine) throws IOException {

        return Files.walk(racine)
                .filter(p -> p.toFile().isDirectory());

    }

    public static void afficherTousLesDossiersDontLaTailleDesFichiersDirectementContenusEstSuperieureAl(Path racine, long l) throws IOException {
        Files.walk(racine)
                .filter(p -> {
                    File pf = p.toFile();
                    if (pf.isFile()) {
                        return false;
                    } else {
                        long n = 0;
                        for (File f : pf.listFiles()) {
                            if (f.isFile()) {
                                n += f.length();
                                if (n > l) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                })
                .forEach(System.out::println);
    }

    public static void afficherTousLesFichiersRangésParTailleEtParNom(Path racine) throws IOException {
        Files.walk(racine)
                .filter(p -> p.toFile().isFile())
                .sorted((a, b) -> {
                    if (a.toFile().length() < b.toFile().length()) {
                        return -1;
                    } else if (a.toFile().length() > b.toFile().length()) {
                        return 1;
                    } else {
                        return a.toFile().getName().compareTo(b.toFile().getName());
                    }
                })
                /*.sorted((a,b) -> {
                 return a.toFile().getName().compareTo(b.toFile().getName());
                 })*/
                .forEach(p -> {
                    System.out.println(p.toFile().length() + " -> " + p.toFile().getName() + " : " + p);
                });
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        Path racine;
        racine = new File("/home/yvan/AutresSauvegardes").toPath();
       
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            racine = jfc.getSelectedFile().toPath();
        } else {
            racine = new File(".").toPath();
        }
        
        
        long td, tf;
        
        td = System.currentTimeMillis();

        // afficherTousLesFichiersEtDossier(racine);
        // afficherTousLesDossiers(racine);
        // afficherTousLesDossiersEtFichiersCaches(racine);
        // afficherTousLesFichiersCaches(racine);
        // afficherTousLesFichiersDeTailleNulle(racine);
        // afficherTousLesFichiersCachesDeTailleNulle(racine);
        // System.out.println("Il y a " + nombreDOctets(racine) + " octets à partir de " + racine);
        // System.out.println("Les fichiers cachés occupent " + nombreDOctetsDesFichiersCaches(racine) + " octets à partir de " + racine);
        // afficherTousLesFichiersDifferentsDefMaisDeMemeTailleEtDeMemeNom(racine, new File("/home/yvan/AutresSauvegardes/Travail/Enseignement/2012-2013/M2/ComplementsJava/sujet1.html"));
        // afficherTousLesFichiersRangésParTaille(racine);
        // afficherTousLesFichiersRangésParTailleEtParNom(racine);
        //System.out.println("Il y a " + tousLesFichiersDeTailleNulle(racine).count() + " fichiers de taille nulle");
        // System.out.println("Il y a " + tousLesDossiersCaches(racine).count() + " dossiers cachés");
        // System.out.println("Il y a " + Files.walk(racine).count() + " fichiers");
        // System.out.println("Il y a " + Files.walk(racine).distinct().count() + " fichiers distincts");
        //tousLesFichiersCachésTriés(racine).forEach(System.out::println);
        //afficherTousLesDossiersDontLaTailleDesFichiersDirectementContenusEstSuperieureAl(racine, 1);
        // afficherTousLesFichiersRangésParTailleEtParNom(racine);
        
        tf= System.currentTimeMillis();
        
        System.out.println("Durée " + (tf-td) + " ms");
    }
}
