/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package _02_service_echo._03_serveur_echo_multithread;
public class EchoServiceMultiple {

    public static void main(String[] args) {
        /*
         * Pour la différence entre EchoServiceMultiple et EchoService :
         * 1) Lancer un serveur EchoService et deux EchoClient
         *    Vous verrez que seul l'un des deux fonctionne. Le premier qui
         *    a "la main" doit terminer son service (en tapant FIN) pour que
         *    le second puisse fonctionner.
         * 2) Mettre fin à EchoService, lancer EchoServiceMultiple et autant de
         *    de clients que voulu. Tous fonctionneront indépendemment les uns
         *    des autres.
         */
        new EchoServiceMultiple();
    }
}
