package _02_service_echo._05_client_graphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

/**
 * Une classe implémentant un client graphique pour le service echo
 *
 * @author guichet
 */
public class GraphicEchoClient extends JFrame implements ActionListener {

  private JTextArea echoZone;
  private JTextArea writeZone;
  private JButton sendButton;
  private JButton clearButton;
  private JButton closeButton;
  String host;
  int port;

  public GraphicEchoClient(String host, int port) {
    super("GraphicEchoClient");
    this.port = port;
    this.host = host;
    getContentPane().setLayout(new BorderLayout(3, 3));

    getContentPane().setBackground(Color.YELLOW);
    makeIHM();
    // Rajouter ici le nécessaire pour communiquer

  }

  /**
   * Méthode d'aide privée utilisée pour configurer le composant graphique
   */
  private void makeIHM() {
    this.echoZone = new JTextArea(5, 32);
    this.echoZone.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
    this.echoZone.setBackground(Color.ORANGE);

    this.echoZone.setEditable(false);
    this.getContentPane().add(echoZone, BorderLayout.NORTH);
    this.writeZone = new JTextArea(5, 32);
    this.writeZone.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    this.writeZone.setBackground(Color.CYAN);
    this.getContentPane().add(writeZone, BorderLayout.CENTER);
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
    this.getContentPane().add(pane, BorderLayout.SOUTH);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    pack();
    setVisible(true);
  }

  /**
   * Soumission d'une requète au serveur écho
   */
  private void sendRequest() {
  }

  /*
   * Réaction aux clics sur un bouton
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == sendButton) {
      sendRequest();
    } else if (e.getSource() == closeButton) {
      if (closeButton.getText().equals("CLOSE CONNECTION")) {

      } else {

      }
    } else {
    }
  }

  public static void main(String[] args) {
    new GraphicEchoClient("localhost", 1414);
  }

}
