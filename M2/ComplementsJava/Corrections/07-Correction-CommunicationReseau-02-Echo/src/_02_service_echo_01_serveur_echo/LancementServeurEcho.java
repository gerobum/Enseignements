package _02_service_echo_01_serveur_echo;

/*
 *
 * @author maillot
 */
public class LancementServeurEcho {
    public static void main(String[] args) {
        new EchoService(1414).start();
    }
}
