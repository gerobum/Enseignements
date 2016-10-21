/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author yvan
 */
public class ServeurDeSkippy {

    private final Map<String, InfoDeCom> pseudos = new HashMap<>();

    public ServeurDeSkippy() {
        new TraitementDemandesConnexion().start();
        new TraitementDemandesDeconnexion().start();
    }

    public static enum Message {

        PSEUDO_EXISTANT("Ce pseudo existe déjà"),
        PSEUDO_INEXISTANT("Ce pseudo n'existe pas"),
        CONNECTE("Vous êtes connecté"),
        DECONNECTE("Vous êtes déconnecté"),
        AJOUT,
        RETRAIT,;

        private final String msg;

        private Message() {
            this.msg = this.name();
        }

        private Message(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return msg;
        }
    }

    public Message messages;

    private class TraitementDemandesConnexion extends Thread {

        @Override
        public void run() {

            try {
                ServerSocket connexion = new ServerSocket(1515);
                while (true) {
                    try (Socket s = connexion.accept()) {
                        Scanner sin = new Scanner(s.getInputStream());
                        ObjectOutputStream sout = new ObjectOutputStream(s.getOutputStream());
                        String newpseudo = sin.nextLine();
                        if (pseudos.containsKey(newpseudo)) {
                            sout.writeObject(Message.PSEUDO_EXISTANT);
                        } else {
                            // Ajout d'un nouveau pseudo connecté (newpseudo)
                            sout.writeObject(Message.CONNECTE);
                            // Création des infos de com pour ce pseudo
                            // L'adresse du client est récupérée
                            // et il doit envoyer le port de discussion 
                            // suivi du port de mise à jour de la liste.
                            InfoDeCom newifc = new InfoDeCom(newpseudo, s.getInetAddress(), sin.nextInt(), sin.nextInt());
                            // Il faut avertir tout le monde de l'ajout d'un pseudo et ses infos,
                            // Avant même d'ajouter le nouveau pseudo afin que celui-ci
                            // ne se rajoute pas lui-même.
                            Message msg = Message.AJOUT;
                            for (String pseudo : pseudos.keySet()) {
                                InfoDeCom ifc = pseudos.get(pseudo);
                                try (Socket skt = new Socket(ifc.adresse, ifc.portDeConnectes)) {
                                    ObjectOutputStream oout = new ObjectOutputStream(skt.getOutputStream());
                                    oout.writeObject(msg);
                                    oout.writeObject(newifc);
                                }
                            }
                            // Il faut envoyer à ce nouveau connecté la liste des autres connectés
                            sout.writeObject(pseudos);
                            // Le pseudo et ses infos de connection sont ajoutés.
                            pseudos.put(newpseudo, newifc);// Doit être après la boucle.                            
                        }
                    }
                }
            } catch (IOException ex) {
            }
        }
    }

    private class TraitementDemandesDeconnexion extends Thread {

        @Override
        public void run() {

            try {
                ServerSocket connexion = new ServerSocket(1516);
                while (true) {
                    System.out.println("En attente de connexion");
                    try (Socket s = connexion.accept()) {
                        Scanner sin = new Scanner(s.getInputStream());
                        ObjectOutputStream sout = new ObjectOutputStream(s.getOutputStream());
                        String oldpseudo = sin.nextLine();
                        if (pseudos.containsKey(oldpseudo)) {
                            sout.writeObject(Message.DECONNECTE);
                            InfoDeCom oldifc = pseudos.remove(oldpseudo);
                            Message msg = Message.RETRAIT;
                            // Tout le monde est averti.
                            for (String pseudo : pseudos.keySet()) {
                                InfoDeCom ifc = pseudos.get(pseudo);
                                try (Socket skt = new Socket(ifc.adresse, ifc.portDeConnectes)) {
                                    ObjectOutputStream oout = new ObjectOutputStream(skt.getOutputStream());
                                    oout.writeObject(msg);
                                    oout.writeObject(oldifc);
                                }
                            }
                        } else {
                            sout.writeObject(Message.PSEUDO_INEXISTANT);
                        }
                    }
                }
            } catch (IOException ex) {
            }
        }
    }

    public static void main(String[] args) {
        new ServeurDeSkippy();
    }
}
