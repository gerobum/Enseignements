package _03_skippy;

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
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
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

/**
 * Skippy permet de mettre en relation deux machines distantes. Une fois Skippy
 * lancé, et sachant qu'un autre Skippy est lancé sur une autre machine, pour
 * établir une connection il faut taper
 *
 * @<adresse ip>:<numéro de port> Un message quelconque Le Skippy branché sur
 * cette machine et ce port recevra le message. Une fois la connection établie,
 * les deux Skippy peuvent communiquer normalement.
 */
public class Skippy extends JFrame implements Runnable {

  // La zone de "chat" doit contenir plusieurs lignes.
  // un JTextArea est donc intéressant pour ça.
  private JTextArea echanges;
  // Les messages envoyés tiennent sur une ligne.
  private JTextField envoi;
  private final DatagramSocket in; // Pour communiquer
  private InetAddress distantAddress; // Pour mémorisez l'adresse du correspondant
  private int distantPort; // et son port.
  private JScrollPane scrollZone;
  // Ce pattern sera utilisé pour reconnaitre les demandes de connections, 
  // c'est-à-dire des chaînes comme "@127.0.0.1:45784 Bonjour"
  private final Pattern addressPort = Pattern.compile("@([^\\s:]+):(\\d{4,5})(.*)");

  private final ActionListener send = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        sendRequest();
      } catch (UnknownHostException ex) {
        Logger.getLogger(Skippy.class.getName()).log(Level.SEVERE, null, ex);
      } catch (SocketException ex) {
        Logger.getLogger(Skippy.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(Skippy.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  };

  /**
   * Pour établir une communication : envoyer un message dont le format est le
   * suivant :
   * <adresse distante>:<port distant> message.<br>
   * Par exemple : <br>
   *
   * @localhost:54789 Bonjour<br>
   * Une fois la communication établie, il suffit d'envoyer des messages
   * simples.
   * @throws IOException
   */
  public Skippy() throws IOException {

    getContentPane().setBackground(Color.YELLOW);
    in = new DatagramSocket();
    makeIHM();
  }


  /**
   * Méthode d'aide privée utilisée pour configurer le composant graphique
   */
  private void makeIHM() {
    echanges = new JTextArea(10, 20);
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

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);

    setTitle("Port #" + in.getLocalPort());
  }

  /**
   * Soumission d'une requête.
   */
  private void sendRequest() throws UnknownHostException, SocketException, IOException {
    // Cette méthode 
    //    1. récupère le texte saisi dans le champs de texte envoi,
    //    2. vérifie s'il s'agit d'une demande de connection,
    //    3. le cas échéant récupère l'adresse et le port demandé,
    //    3. extrait le message pour l'ajouter dans la zone d'échange,
    //    4. construit et envoie le paquet avec le DatagramSocket in.
    echanges.append("La méthode sendRequest reste à développer\n");
  }
  
  
  /**
   * Cette méthode permet de recevoir un paquet envoyé dans DatagramSocket in.
   * et la retourne.
   * 
   * Elle met également à jour l'adresse et le port distants.
   * @return @throws IOException
   */
  private String receive() throws IOException {
    echanges.append("La méthode receive reste à développer\n");
    try {
      Thread.sleep(10000);
    } catch (InterruptedException ex) {
      Logger.getLogger(Skippy.class.getName()).log(Level.SEVERE, null, ex);
    }
    return "La méthode receive reste à développer\n";
  }

  /*
  Infiniment, les messages reçus sont affichés dans la zone d'échanges. 
  */
  @Override
  public void run() {
    while (true) {
      try {
        String message = receive();
        echanges.append("#" + distantPort + " : " + message + "\n");
        echanges.setCaretPosition(echanges.getCaretPosition());
        scrollZone.getViewport().setViewPosition(new Point(0, echanges.getSize().height));
      } catch (IOException ex) {
        Logger.getLogger(Skippy.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }

  /*
   * Lancement du client
   */
  public static void main(String[] args) throws IOException {
      new Thread(new Skippy()).start();
  }
}
