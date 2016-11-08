package partie1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * Threads et Runnables.
 *
 * Exercice 1.
 *
 *
 * Objectif : Implémentation de l'interface Runnable, révisions sur les
 * composants graphiques.
 *
 * Sujet : Par extension de la classe javax.swing.Jlabel et par implémentation
 * de l'interface java.lang.Runnable développer un composant graphique de type
 * montre calendrier. Ce composant devra avoir un aspect tel que celui montré
 * ci-dessous. * dimanche 4 octobre 2009 14:12:15
 *
 *
 *
 * Ecrire un client autonome, basé sur un javax.swing.Jframe qui permettra
 * d'afficher votre composant.
 *
 *
 * Aide: Pour consruire une date voir la Javadoc des classes java.util.Date et
 * java.text.DateFormat. Pour la construction du composant graphique voir la
 * javadoc des classes javax.swing.JLabel et java.awt.Font
 *
 *
 *
 *
 * Une instance de LocalizedCalendar local.PNG
 *
 * @author maillot
 */
public class JLabelCalendar extends JLabel /* L'utilisation de Timer et de TimerTask évite implements Runnable */ {

    private static DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
    private final Timer TIMER;
    private final TimerTask TIMER_TASK;

    public JLabelCalendar() {
        super(dateFormat.format(new Date()), JLabel.CENTER);

        // -----------------------------------------
        // L'utilisation de Timer et de TimerTask permet d'éviter Thread.sleep(1000) dans une boucle.
        TIMER = new Timer();
        TIMER_TASK = new TimerTask() {
            @Override
            public void run() {
                setText(dateFormat.format(new Date()));
            }
        };
        TIMER.schedule(TIMER_TASK, 0, 1000);
        // ---
        init();
    }

    private void init() {
        // Pour la couleur de fond ...
        setOpaque(true);
        setBackground(Color.cyan);
        // Pour une fonte plus visible...
        setFont(getFont().deriveFont(60F));

        FontMetrics fm = getFontMetrics(getFont());
        setPreferredSize(new Dimension((int) (fm.stringWidth(getText()) * 1.5), (int) (fm.getHeight() * 1.5)));

        // Pour avoir une bordure intérieure
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Pour mettre à jour l'affichage...
        // --- new Thread(this).start();
    }

    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        dateFormat = SimpleDateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, locale);
        setText(dateFormat.format(new Date()));
    }

    /* -----------------------------------------
    -- L'utilisation de Timer et de TimerTask permet d'éviter Thread.sleep(1000) dans une boucle.
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                setText(dateFormat.format(new Date()));
            } catch (InterruptedException ex) {
                Logger.getLogger(JLabelCalendar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    --- */
}
