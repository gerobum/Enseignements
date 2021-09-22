
package main;

import application.Application;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import static utilitaire.Utilitaire.mappingUrl;

/**
 *
 * @author yvan
 */
public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer serveur = HttpServer.create(new InetSocketAddress(8000), 0);
        serveur.createContext("/", new MyHandler());
        mappingUrl(serveur, Application.class);
        serveur.setExecutor(null);
        serveur.start();
    }    
    
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "<h1>Bienvenue</h1>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
