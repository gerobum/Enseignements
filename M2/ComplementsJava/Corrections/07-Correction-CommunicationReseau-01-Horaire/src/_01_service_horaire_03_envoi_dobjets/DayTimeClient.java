package _01_service_horaire_03_envoi_dobjets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/*
 * JLabel affichant l'heure en se connectant chaque seconde au serveur
 * DayTimeService
 *
 * @author maillot
 */
public class DayTimeClient extends JLabel {

    private String adresse;
    private int port;
    private DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);

    public DayTimeClient(String adresse_, int port_) {
        super("               C'est bientôt l'heure !!!                  ", JLabel.CENTER);

        adresse = adresse_;
        port = port_;
        // Pour la couleur de fond ...
        setOpaque(true);
        setBackground(Color.orange);
        // Pour une fonte plus visible...
        setFont(getFont().deriveFont(30F));

        FontMetrics fm = getFontMetrics(getFont());
        setPreferredSize(new Dimension((int) (fm.stringWidth(getText()) * 1.5), (int) (fm.getHeight() * 1.5)));

        // Pour avoir une bordure intérieure
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Pour mettre à jour l'affichage toutes les secondes
        new Thread(new Runnable() {
            boolean enService = true;

            @Override
            public void run() {
                // Dans une boucle infinie, toutes les secondes une requête
                // est faite au serveur dont l'addresse et le port sont des
                // attributs de la classe.
                while (enService) {
                    Object obj = "";
                    try {
                        Thread.sleep(1000);
                        // Création d'un socket connecté à adresse:port
                        // Aucune requête n'est faite.
                        Socket socket = new Socket(adresse, port);
                        // Enrobage du flux issue du socket dans un Scanner
                        // pour faciliter la lecture d'une chaîne.
                        ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());

                        // Une requête est faite au serveur et le texte
                        // du JLabel est mis à jour.
                        obj = oin.readObject();
                        Date d = (Date) obj;
                        setText(df.format(obj));
                    } catch (ClassCastException | IllegalArgumentException ii) {
                        // Signifie que le serveur n'a pas envoyé une date.
                        setText(obj.toString());
                        // Pour terminer le thread
                        enService = false;

                    } catch (ClassNotFoundException | InterruptedException | IOException ex) {
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
                setText("Le service est fermé !!!");

            }
        }).start();

    }

    public DayTimeClient(String adresse) {
        this(adresse, 1313);
    }

    public DayTimeClient() {
        this("localhost", 1313);
    }

    public DayTimeClient(int port) {
        this("localhost", port);
    }
}
