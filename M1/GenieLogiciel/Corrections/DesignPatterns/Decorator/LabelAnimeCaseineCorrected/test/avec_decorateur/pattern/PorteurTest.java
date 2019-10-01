/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avec_decorateur.pattern;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import static junit.framework.TestCase.*;
import org.junit.Test;

/**
 *
 * @author yvan
 */
public class PorteurTest {
    
    private Object m = new Object();
    private boolean fini = false;
    private boolean isSpinningAndBlinking = false;
    private boolean isBlinking = false;
    private boolean isSpinning = false;

    @Test
    public void checkAnimateur() {
        System.out.println("Vérification Animateur");
        assertTrue("La classe Animateur doit rester abstraite",
                Modifier.isAbstract(Animateur.class.getModifiers()));
    }

    @Test
    public void checkPorteur() {
        System.out.println("Vérification Porteur");
        assertTrue("Attention à respecter la structure de pattern Decorator. "
                + "Essayer de voir à quelle classe de la structure du pattern "
                + "correspond la classe Porteur",
                Porteur.class.getSuperclass().equals(Animateur.class)
        );
    }

    @Test
    public void checkDecorateur() {
        System.out.println("Vérification Decorateur");
        assertTrue("Attention à respecter la structure de pattern Decorator. "
                + "Essayer de voir à quelle classe de la structure du pattern "
                + "correspond la classe Decorateur",
                Decorateur.class.getSuperclass().equals(Animateur.class)
        );
        assertTrue("Avez-vous un attribut correct dans la classe Decorateur ",
                Arrays.stream(Decorateur.class.getDeclaredFields()).anyMatch(p
                        -> p.getType().equals(Animateur.class)
                && Modifier.isPrivate(p.getModifiers())
                && !Modifier.isStatic(p.getModifiers())
                ));
    }

    @Test
    public void checkTourneur() {
        System.out.println("Vérification Tourneur");
        assertTrue("Attention à respecter la structure de pattern Decorator. "
                + "Essayer de voir à quelle classe de la structure du pattern "
                + "correspond la classe Tourneur",
                Tourneur.class.getSuperclass().equals(Decorateur.class)
        );
    }

    @Test
    public void checkClignoteur() {
        System.out.println("Vérification Clignoteur");
        assertTrue("Attention à respecter la structure de pattern Decorator. "
                + "Essayer de voir à quelle classe de la structure du pattern "
                + "correspond la classe Clignoteur",
                Clignoteur.class.getSuperclass().equals(Decorateur.class)
        );
    }

    @Test
    public void testAnimer() {
        System.out.println("test animer");
        JLabel label = new JLabel("Test");
        Constructor cporteur;
        Porteur porteur = null;
        try {
            cporteur = Porteur.class.getDeclaredConstructor(JLabel.class);
            porteur = (Porteur) cporteur.newInstance(label);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Revoir le constructeur de Porteur");
        }
        Constructor ctourneur;
        Tourneur tourneur = null;
        try {
            ctourneur = Tourneur.class.getDeclaredConstructor(Animateur.class);
            tourneur = (Tourneur) ctourneur.newInstance(porteur);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Revoir le constructeur de Tourneur " + ex);
        }
        Constructor cclignoteur;
        Clignoteur clignotourneur = null;
        try {
            cclignoteur = Clignoteur.class.getDeclaredConstructor(Animateur.class);
            clignotourneur = (Clignoteur) cclignoteur.newInstance(tourneur);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            fail("Revoir le constructeur de Clignoteur" + ex);
        }

        clignotourneur.animer();
        Color couleur = label.getForeground();
        String text = label.getText();
        long first = System.currentTimeMillis();
        boolean blink = false;
        boolean turn = false;
        int i = 0;
        int[] np = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        while (i < np.length && (!blink || !turn)) {
            try {
                Thread.sleep(i * 10);
                blink = blink || !couleur.equals(label.getForeground());
                turn = turn || !text.equals(label.getText());
            } catch (InterruptedException ex) {
            }
            i++;
        }
        i = 0;
        while (i < np.length && (!blink || !turn)) {
            try {
                Thread.sleep(i * 50);
                blink = blink || !couleur.equals(label.getForeground());
                turn = turn || !text.equals(label.getText());
            } catch (InterruptedException ex) {
            }
            i++;
        }
        long duree = (System.currentTimeMillis() - first) / 1000;
        assertTrue("Le texte n'a pas clignoté en " + duree + " secondes", blink);
        assertTrue("Le texte n'a pas tourné en " + duree + " secondes", turn);
    }

    @Test
    public void checkFATA() {
        System.out.println("check FATA");
        try {
            // private JLabel tourne, clignote, tourneEtClignote;
            Field ft = avec_decorateur.swing.FenetreAvecTroisAnimations.class.getDeclaredField("tourne");
            Field fc = avec_decorateur.swing.FenetreAvecTroisAnimations.class.getDeclaredField("clignote");
            Field ftc = avec_decorateur.swing.FenetreAvecTroisAnimations.class.getDeclaredField("tourneEtClignote");
            //isSpinningAndBliling((JLabel) ftc.get(f));
        } catch (NoSuchFieldException | SecurityException ex) {
            System.err.println(ex);
            fail("FATA");
        }
    }
    @Test
    public void testFinal() {
        System.out.println("test final");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new sans_decorateur.swing.FenetreSansDecorateur("Démonstration");
                    avec_decorateur.swing.FenetreAvecTroisAnimations f = new avec_decorateur.swing.FenetreAvecTroisAnimations("À animer");
                    // private JLabel tourne, clignote, tourneEtClignote;
                    Field ft = avec_decorateur.swing.FenetreAvecTroisAnimations.class.getDeclaredField("tourne");
                    ft.setAccessible(true);
                    Field fc = avec_decorateur.swing.FenetreAvecTroisAnimations.class.getDeclaredField("clignote");
                    fc.setAccessible(true);
                    Field ftc = avec_decorateur.swing.FenetreAvecTroisAnimations.class.getDeclaredField("tourneEtClignote");
                    ftc.setAccessible(true);
                    isSpinningAndBlinking = isSpinningAndBlinking((JLabel) ftc.get(f));
                    isSpinning = isSpinning((JLabel) ft.get(f));
                    isBlinking = isBlinking((JLabel) fc.get(f));
                    

                } catch (SecurityException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException ex) {
                    //fail("Revoir les attributs de avec_decorateur.swing.FenetreAvecTroisAnimations");
                } finally {
                    synchronized(m) {
                        fini = true;
                        m.notifyAll();
                    }
                }
            }

            private boolean isBlinking(JLabel label) {

                Color couleur = label.getForeground();
                String text = label.getText();
                long first = System.currentTimeMillis();
                boolean blink = false;
                int i = 0;
                int[] np = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
                while (i < np.length && !blink) {
                    try {
                        Thread.sleep(i * 10);
                        blink = blink || !couleur.equals(label.getForeground());
                    } catch (InterruptedException ex) {
                    }
                    i++;
                }
                i = 0;
                while (i < np.length && !blink) {
                    try {
                        Thread.sleep(i * 50);
                        blink = blink || !couleur.equals(label.getForeground());
                    } catch (InterruptedException ex) {
                    }
                    i++;
                }
                long duree = (System.currentTimeMillis() - first) / 1000;
                return blink;
            }

            private boolean isSpinning(JLabel label) {

                Color couleur = label.getForeground();
                String text = label.getText();
                long first = System.currentTimeMillis();
                boolean turn = false;
                int i = 0;
                int[] np = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
                while (i < np.length && !turn) {
                    try {
                        Thread.sleep(i * 10);
                        turn = turn || !text.equals(label.getText());
                    } catch (InterruptedException ex) {
                    }
                    i++;
                }
                i = 0;
                while (i < np.length && !turn) {
                    try {
                        Thread.sleep(i * 50);
                        turn = turn || !text.equals(label.getText());
                    } catch (InterruptedException ex) {
                    }
                    i++;
                }
                long duree = (System.currentTimeMillis() - first) / 1000;
                return turn;
            }

            private boolean isSpinningAndBlinking(JLabel label) {

                Color couleur = label.getForeground();
                String text = label.getText();
                long first = System.currentTimeMillis();
                boolean blink = false;
                boolean turn = false;
                int i = 0;
                int[] np = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
                while (i < np.length && (!blink || !turn)) {
                    try {
                        Thread.sleep(i * 10);
                        blink = blink || !couleur.equals(label.getForeground());
                        turn = turn || !text.equals(label.getText());
                    } catch (InterruptedException ex) {
                    }
                    i++;
                }
                i = 0;
                while (i < np.length && (!blink || !turn)) {
                    try {
                        Thread.sleep(i * 50);
                        blink = blink || !couleur.equals(label.getForeground());
                        turn = turn || !text.equals(label.getText());
                    } catch (InterruptedException ex) {
                    }
                    i++;
                }
                long duree = (System.currentTimeMillis() - first) / 1000;
                return blink && turn;
            }
        });

        while(!fini) {
            synchronized(m) {
                try {
                    m.wait();
                } catch (InterruptedException ex) {
                }
            }
        }
        
        assertTrue("Vérifiez d'avoir bien animé tourneEtClignote dans FenetreAvecTroisAnimations.animation()", isSpinningAndBlinking);
        assertTrue("Vérifiez d'avoir bien animé clignote dans FenetreAvecTroisAnimations.animation()", isBlinking);
        assertTrue("Vérifiez d'avoir bien animé tourne dans FenetreAvecTroisAnimations.animation()", isSpinning);
    }
}
