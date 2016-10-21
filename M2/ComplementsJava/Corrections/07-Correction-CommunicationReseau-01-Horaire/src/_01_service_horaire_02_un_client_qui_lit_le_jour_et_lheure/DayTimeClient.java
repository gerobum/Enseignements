package _01_service_horaire_02_un_client_qui_lit_le_jour_et_lheure;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * JLabel affichant l'heure en se connectant chaque seconde au serveur
 * DayTimeService
 *
 * @author maillot
 */
public class DayTimeClient extends JLabel {

    private String adresse;
    private int port;
    private Socket socket;

    public DayTimeClient(String adresse, int port) {
        super("               C'est bientôt l'heure !!!                  ", JLabel.CENTER);

        this.adresse = adresse;
        this.port = port;
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
                // Ca fonctionne tant que le service est ouvert.
                while (enService) {
                    try {
                        Thread.sleep(3000);
                        // Création d'un socket connecté à adresse:port
                        socket = new Socket(DayTimeClient.this.adresse, DayTimeClient.this.port);
                        // Enrobage du flux issue du socket dans un Scanner
                        // pour faciliter la lecture d'une chaîne.
                        Scanner sc = new Scanner(socket.getInputStream());
                        // Une requête est faite au serveur et le texte
                        setText(sc.nextLine()); // du JLabel est mis à jour.

                    } catch (IOException ioe) {
                        setText("Le service est fermé");
                        enService = false;
                    } catch (InterruptedException ex) {
                    }
                }
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
