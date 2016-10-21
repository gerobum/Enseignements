package client;

/**
 *
 * @author maillot
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class Skippy extends JFrame implements Runnable {

    private JTextArea echanges;
    private JTextField envoi;
    private final DatagramSocket in;
    // ???? private final Set<Couple<InetAddress, Integer>> adressesPorts = new HashSet<>();
    private JScrollPane scrollZone;
    private final Pattern addressPort = Pattern.compile("@([^\\s:]+):(\\d{4,5})(.*)");
    private final InetAddress adresseDuServeur;
    private final String pseudo;
    private final int portConnexion;
    private final int portDeconnexion;
    private String pseudoDistant;
    private InetAddress adresseDistante;
    private int portDistant;

    private final ActionListener send = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                sendRequest();
            } catch (IOException ex) {
                Logger.getLogger(Skippy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public Skippy(InetAddress adresseDuServeur, int portConnexion) throws IOException {
        this.pseudo = "";
        this.adresseDuServeur = adresseDuServeur;
        this.portConnexion = portConnexion;
        this.portDeconnexion = portConnexion + 1;
        getContentPane().setBackground(Color.YELLOW);
        in = new DatagramSocket();
        makeIHM();
    }

    /**
     * Méthode d'aide privée utilisée pour configurer le composant graphique
     */
    private void makeIHM() {
        echanges = new JTextArea(10, 50);
        echanges.setText("Entrez @<adresse distante>:<port distant><Un éventuel message>\n"
                + "pour vous connecter avec quelqu'un qui a ouvert Skippy."
                + "\nPar exemple : @127.0.0.1:54789 Bonjour\n"
                + "\nUne fois la communication établie vous pouvez communiquer normalement.\n\n");

        echanges.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        echanges.setBackground(Color.ORANGE);
        echanges.setLineWrap(true);
        echanges.setEditable(false);

        scrollZone = new JScrollPane(echanges);

        JPanel centre = new JPanel(new BorderLayout());

        centre.add(scrollZone, BorderLayout.CENTER);
        envoi = new JTextField(32);
        envoi.addActionListener(send);
        envoi.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        envoi.setBackground(Color.CYAN);
        centre.add(envoi, BorderLayout.SOUTH);

        getContentPane().add(centre, "Center");
        getContentPane().add(new PanneauDeConnexion(adresseDuServeur, portConnexion, portDeconnexion, in.getLocalPort(), in), "East");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        setTitle(pseudo + " (" + in.getLocalPort() + ")");
    }

    /**
     * Soumission d'une requête.
     */
    private void sendRequest() throws UnknownHostException, IOException {
        // Cette méthode 
        //    1. récupère le texte saisi dans le champs de texte envoi,
        //    2. vérifie s'il s'agit d'une demande de connection,
        //    3. le cas échéant récupère l'adresse et le port demandé,
        //    3. extrait le message pour l'ajouter dans la zone d'échange,
        //    4. construit et envoie le paquet avec le DatagramSocket in.
        String ligne = envoi.getText();
        Matcher matcher = addressPort.matcher(ligne);
        String message;
        if (matcher.matches()) {
            adresseDistante = InetAddress.getByName(matcher.group(1));
            portDistant = Integer.parseInt(matcher.group(2));
            message = matcher.group(3).trim();
        } else {
            message = ligne.trim();
        }
        //--------

        echanges.append("Me : " + message + "\n");
        echanges.setCaretPosition(echanges.getText().length());
        // on construit le paquet a envoyer
        byte[] tampon;
        tampon = message.getBytes();

        DatagramPacket packet = new DatagramPacket(tampon, tampon.length, adresseDistante, portDistant);
        in.send(packet);

        envoi.setText("");
    }

    /**
     * Cette méthode permet de recevoir un paquet envoyé dans DatagramSocket in.
     * et la retourne.
     *
     * Elle met également à jour l'adresse et le port distants.
     *
     * @return @throws IOException
     */
    private String receive() throws IOException {
        byte[] tampon = new byte[256];
        DatagramPacket message = new DatagramPacket(tampon, tampon.length);

        in.receive(message);
        portDistant = message.getPort();
        adresseDistante = message.getAddress();

        return new String(tampon).trim();
    }

    /*
     Infiniment, les messages reçus sont affichés dans la zone d'échanges. 
     */
    @Override
    public void run() {
        while (true) {
            try {
                String pm = receive();
                echanges.append("#" + pm + "\n");
                echanges.setCaretPosition(echanges.getCaretPosition());
                scrollZone.getViewport().setViewPosition(new Point(0, echanges.getSize().height));
            } catch (IOException ex) {
                Logger.getLogger(Skippy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Lancement du client
     */
    public static void main(String[] args) throws IOException {
         new Thread(new Skippy(InetAddress.getLocalHost(), 1515)).start();
    }
}
