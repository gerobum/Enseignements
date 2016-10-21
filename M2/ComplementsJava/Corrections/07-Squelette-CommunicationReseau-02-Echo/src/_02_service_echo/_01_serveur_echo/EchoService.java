package _02_service_echo._01_serveur_echo;

/**
 * Le serveur "echo" attend sur le port 1414 un message d'un client. Et il lui
 * répond ce même message.<br> Tant qu'un client est connecté au serveur, il
 * peut envoyer des phrases pour recevoir leur echo. Il met fin à la connection
 * en envoyant le mot FIN.<br>
 * Remarquez que si un deuxième client demande un echo alors que le premier
 * n'a pas terminé, il doit attendre que celui-ci termine.<br>
 * Un client peut même mettre fin au service s'il connaît la phrase magique :
 * Sésame ! Ferme toi.
 */
public class EchoService {
  public static void main(String[] args) {
    
  }
}
