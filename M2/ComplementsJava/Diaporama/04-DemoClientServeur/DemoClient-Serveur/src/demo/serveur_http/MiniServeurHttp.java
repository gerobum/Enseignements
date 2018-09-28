/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.serveur_http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author yvan
 */
public class MiniServeurHttp {

    private static HttpServer serveur;

    public static void main(String[] args) throws MalformedURLException, IOException, XMLStreamException {
        // Création d'un serveur Http à l'écoute sur le port 8000
        serveur = HttpServer.create(new InetSocketAddress(8000), 0);
        // Mapping d'url
        serveur.createContext("/hello", new HelloHandler());
        serveur.createContext("/compte", new CompteHandler());
        serveur.createContext("/stop", new StopHandler());
        serveur.start();
    }

    // Traitement de l'url /hello
    static class HelloHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Hello";
            t.sendResponseHeaders(200, response.length());
            try (OutputStream os = t.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    // Traitement de l'url /compte
    static class CompteHandler implements HttpHandler {

        private static int nb = 0;

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "<h1>Ca fait la " + (++nb) + " ieme fois</h1>";
            t.sendResponseHeaders(200, response.length());

            try (OutputStream os = t.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    // Traitement de l'url /stop
    static class StopHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            switch (t.getRequestMethod().toUpperCase()) {
                case "POST":
                    serveur.stop(3);
                    break; 
                default:
                    String response = "<h1>Stopper le serveur !!!</h1>";
                    response += getForm();
                    
                    t.sendResponseHeaders(200, response.length());

                    try (OutputStream os = t.getResponseBody()) {
                        os.write(response.getBytes());
                    }
            }
        }
        
        private String getForm() {
            String form = "<form action=\"/stop\" method=\"post\">";
            form += "<div class=\"button\"><button type=\"submit\">STOP (et le serveur tombe dans 3 secondes)</button></div>";
            form += "</form>";
            return form;
        }
    }
}
