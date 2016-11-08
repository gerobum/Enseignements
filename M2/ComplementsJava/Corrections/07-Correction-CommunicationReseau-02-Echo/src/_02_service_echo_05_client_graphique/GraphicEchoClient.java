package _02_service_echo_05_client_graphique;

/**
 *
 * @author maillot
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

/**
 * Une classe implémentant un client graphique pour le service echo
 *
 * @author guichet
 */
public class GraphicEchoClient extends JPanel implements ActionListener {

    private Socket client;
    private PrintStream sout;
    private Scanner sin;
    private JTextArea echoZone;
    private JTextArea writeZone;
    private JButton sendButton;
    private JButton clearButton;
    private JButton closeButton;
    String host;
    int port;

    public GraphicEchoClient(String host, int port) {
        super(new BorderLayout(3, 3));
        this.port = port;
        this.host = host;
        try {
            this.setBackground(Color.YELLOW);
            this.setOpaque(true);
            makeIHM();
            client = new Socket(host, port);
            sin = new Scanner(client.getInputStream());
            sout = new PrintStream(client.getOutputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(GraphicEchoClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectException ex) {
            sendButton.setEnabled(false);
            clearButton.setEnabled(false);
            closeButton.setText("OPEN CONNECTION");
            writeZone.append("Le serveur n'est pas lancé");
        } catch (IOException ex) {
            Logger.getLogger(GraphicEchoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Méthode d'aide privée utilisée pour configurer le composant graphique
     */
    private void makeIHM() {
        this.echoZone = new JTextArea(5, 32);
        this.echoZone.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        this.echoZone.setBackground(Color.ORANGE);

        this.echoZone.setEditable(false);
        this.add(echoZone, BorderLayout.NORTH);
        this.writeZone = new JTextArea(5, 32);
        this.writeZone.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        this.writeZone.setBackground(Color.CYAN);
        this.add(writeZone, BorderLayout.CENTER);
        JPanel pane = new JPanel(new GridLayout(1, 3, 3, 3));
        this.sendButton = new JButton("SEND");
        this.sendButton.addActionListener(this);
        this.clearButton = new JButton("CLEAR");
        this.clearButton.addActionListener(this);
        this.closeButton = new JButton("CLOSE CONNECTION");
        this.closeButton.addActionListener(this);
        pane.add(sendButton);
        pane.add(clearButton);
        pane.add(closeButton);
        this.add(pane, BorderLayout.SOUTH);
    }

    /*
     * Soumission d'une requète au serveur écho
     */
    protected void sendRequest() {
        // Comme le texte du TextArea peut contenir des sauts de lignes
        // Et comme un saut ligne met fin à la lecture du flux du côté serveur,
        // On envoie les lignes une à une en relisant l'écho correspondant à 
        // chaque fois.
        Scanner text = new Scanner(writeZone.getText());
        while (text.hasNext()) {
            sout.println(text.nextLine());
            echoZone.append(sin.nextLine());
            echoZone.append("\n");
        }
    }

    /*
     * Réaction aux clics sur le bouton
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendButton) {
            // Si l'évènement est émis par le bouton send
            if ("CLOSE CONNECTION".equals(closeButton.getText())) {
                sendRequest();
            }
        } else if (e.getSource() == closeButton) {
            // Si l'évènement est émis par le bouton close
            if ("CLOSE CONNECTION".equals(closeButton.getText())) {
                this.echoZone.setText("Déconnection de " + client.getInetAddress().getHostAddress());
                try {
                    // Pour mettre fin à l'échange en cours avec le serveur.
                    sout.println("fin");
                    // fermer les flots et le socket client
                    sout.close();
                    sin.close();
                    client.close();
                } catch (IOException ex) {
                    // Si erreur éditer la trace de la pile d'exécution dans le panneau echo
                    StackTraceElement[] st = ex.getStackTrace();
                    StringBuilder sb = new StringBuilder();
                    for (StackTraceElement ste : st) {
                        sb.append(ste.toString());
                    }
                    this.echoZone.setText(sb.toString());
                }
            } else {
                // Nouvelle tentative de lancement (il faut que le serveur tourne)
                writeZone.setText("");
                sendButton.setEnabled(true);
                clearButton.setEnabled(true);
                closeButton.setText("CLOSE CONNECTION");

                try {
                    port = Integer.parseInt(JOptionPane.showInputDialog("Entrez le numéro du port de ECHO"));
                    client = new Socket(host, port);
                    sin = new Scanner(client.getInputStream());
                    sout = new PrintStream(client.getOutputStream());
                } catch (UnknownHostException ex) {
                    Logger.getLogger(GraphicEchoClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectException ex) {
                    sendButton.setEnabled(false);
                    clearButton.setEnabled(false);
                    closeButton.setText("OPEN CONNECTION");
                    writeZone.append("Le serveur n'est pas lancé");
                } catch (IOException ex) {
                    Logger.getLogger(GraphicEchoClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            // remettre à "blanc" les zones d'affichage
            this.writeZone.setText("");
            this.echoZone.setText("");
            this.echoZone.setBackground(Color.YELLOW);
        }
    }

    /*
     * Lancement du client
     */
    public static void main(String[] args) throws IOException {

        GraphicEchoClient gcl = new GraphicEchoClient("localhost", 1414);
        JFrame fr = new JFrame("GraphicEchoClient");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.getContentPane().add(gcl);
        fr.pack();
        fr.setVisible(true);

    }
}
