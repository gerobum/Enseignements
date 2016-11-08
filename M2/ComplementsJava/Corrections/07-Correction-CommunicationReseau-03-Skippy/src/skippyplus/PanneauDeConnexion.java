
package skippyplus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import skippyplus.serveur.InfoDeCom;
import skippyplus.serveur.ServeurDeSkippy;

/**
 *
 * @author yvan
 */
public class PanneauDeConnexion extends JPanel {

    private final JTextField pseudo = new JTextField(20);
    private final JButton connexion = new JButton("Se connecter");
    private final JList<String> liste = new JList<>(new DefaultListModel<String>());
    private Map<String, InfoDeCom> pseudos = new HashMap<>();
    private final int portDeDiscussion;
    private final InetAddress adresseDuServeur;
    private final int portConnexion;
    private final int portDeconnexion;
    private final int portMAJConnectes;

    private final ActionListener action;

    public PanneauDeConnexion(final InetAddress adresseDuServeur, final int portConnexion, final int portDeconnexion, final int portDeDiscussion, final DatagramSocket in) {
        this.adresseDuServeur = adresseDuServeur;
        this.portConnexion = portConnexion;
        this.portDeconnexion = portDeconnexion;
        this.portDeDiscussion = portDeDiscussion;
        this.portMAJConnectes = majListeConnectes();
        liste.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        liste.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    try {
                        InfoDeCom ifc = pseudos.get(liste.getSelectedValue());
                        byte[] tampon;
                        tampon = "Bonjour".getBytes();

                        DatagramPacket packet = new DatagramPacket(tampon, tampon.length, ifc.adresse, ifc.portDeDiscussion);
                        in.send(packet);
                    } catch (IOException ex) {
                        Logger.getLogger(PanneauDeConnexion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });

        setLayout(new BorderLayout());
        JPanel sud = new JPanel();
        sud.add(pseudo);
        FontMetrics fm = getFontMetrics(connexion.getFont());
        sud.add(connexion);
        sud.setPreferredSize(new Dimension(400, sud.getPreferredSize().height));

        add(sud, "South");
        liste.setPreferredSize(new Dimension(400, 200));
        add(liste, "Center");

        connexion.setEnabled(false);

        pseudo.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent ce) {
                if (pseudo.getText().trim().length() > 0) {
                    connexion.setEnabled(true);
                } else {
                    connexion.setEnabled(false);
                }
            }
        });

        action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if ("Se connecter".equals(connexion.getText())) {
                        Socket s = new Socket(PanneauDeConnexion.this.adresseDuServeur, PanneauDeConnexion.this.portConnexion);
                        ObjectInputStream sin = new ObjectInputStream(s.getInputStream());
                        PrintStream sout = new PrintStream(s.getOutputStream());
                        ServeurDeSkippy.Message mes;
                        sout.println(pseudo.getText().trim());
                        switch (mes = (ServeurDeSkippy.Message) sin.readObject()) {
                            case PSEUDO_EXISTANT:
                                JOptionPane.showMessageDialog(null, mes, "Message du serveur", JOptionPane.ERROR_MESSAGE);
                                break;
                            case CONNECTE:
                                JOptionPane.showMessageDialog(null, mes, "Message du serveur", JOptionPane.PLAIN_MESSAGE);
                                connexion.setText("Se déconnecter");
                                pseudo.setEnabled(false);
                                sout.println(PanneauDeConnexion.this.portDeDiscussion);
                                sout.println(PanneauDeConnexion.this.portMAJConnectes);
                                // Le serveur envoie la liste des pseudos connectés
                                pseudos = (Map<String, InfoDeCom>) sin.readObject();
                                DefaultListModel<String> dlm = (DefaultListModel<String>) liste.getModel();
                                dlm.removeAllElements();
                                for (String pseudo : pseudos.keySet()) {
                                    dlm.addElement(pseudo);
                                }
                                break;
                            default:
                                throw new AssertionError(((ServeurDeSkippy.Message) sin.readObject()).name());

                        }
                    } else {
                        Socket s = new Socket(PanneauDeConnexion.this.adresseDuServeur, PanneauDeConnexion.this.portDeconnexion);
                        ObjectInputStream sin = new ObjectInputStream(s.getInputStream());
                        PrintStream sout = new PrintStream(s.getOutputStream());
                        ServeurDeSkippy.Message mes;
                        sout.println(pseudo.getText().trim());
                        switch (mes = (ServeurDeSkippy.Message) sin.readObject()) {
                            case PSEUDO_INEXISTANT:
                                JOptionPane.showMessageDialog(null, mes, "Message du serveur", JOptionPane.ERROR_MESSAGE);
                                break;
                            case DECONNECTE:
                                JOptionPane.showMessageDialog(null, mes, "Message du serveur", JOptionPane.PLAIN_MESSAGE);
                                connexion.setText("Se connecter");
                                pseudo.setEnabled(true);
                                break;
                            default:
                                throw new AssertionError(((ServeurDeSkippy.Message) sin.readObject()).name());

                        }
                    }
                } catch (UnknownHostException ex) {

                    JOptionPane.showMessageDialog(null, "Le serveur n'est pas actif " + ex, "Message de Skippy", JOptionPane.PLAIN_MESSAGE);
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Le serveur n'est pas actif " + ex, "Message de Skippy", JOptionPane.PLAIN_MESSAGE);
                }
            }
        };

        connexion.addActionListener(action);
        pseudo.addActionListener(action);

    }

    private int majListeConnectes() {
        try {
            final ServerSocket serveur = new ServerSocket(0);
            new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        try (Socket s = serveur.accept()) {
                            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                            ServeurDeSkippy.Message msg = (ServeurDeSkippy.Message) ois.readObject();
                            InfoDeCom ifc = (InfoDeCom) ois.readObject();
                            switch (msg) {
                                case AJOUT:
                                    pseudos.put(ifc.pseudo, ifc);
                                    ((DefaultListModel<String>) liste.getModel()).addElement(ifc.pseudo);
                                    break;
                                case RETRAIT:
                                    pseudos.remove(ifc.pseudo);
                                    ((DefaultListModel<String>) liste.getModel()).removeElement(ifc.pseudo);
                                    break;
                            }
                        } catch (IOException | ClassNotFoundException ex) {
                            Logger.getLogger(PanneauDeConnexion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }).start();
            return serveur.getLocalPort();
        } catch (IOException ex) {
            Logger.getLogger(PanneauDeConnexion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
}
