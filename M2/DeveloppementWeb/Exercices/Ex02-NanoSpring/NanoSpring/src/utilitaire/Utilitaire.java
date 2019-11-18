package utilitaire;

import com.sun.net.httpserver.HttpServer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import annotations.Get;
import annotations.Param;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilitaire {

    public static void mappingUrl(HttpServer serveur, Class<?> c) {
        for (Method m : c.getDeclaredMethods()) {
            Get get = m.getAnnotation(Get.class);
            if (get != null) {
                System.out.println(get);
                System.out.println(m);
                serveur.createContext(get.value(), new MyHandler(c, m));
            }
        }
    }

    static class MyHandler implements HttpHandler {

        private final Class<?> c;
        private final Method m;

        private MyHandler(Class<?> c, Method m) {
            this.c = c;
            this.m = m;
        }

        @Override
        public void handle(HttpExchange t) throws IOException {
            try {
                Object o = c.newInstance();

                Parameter[] params = m.getParameters();
                Object[] p = new Object[params.length];
                if (p.length > 0) {
                    Param param = params[0].getAnnotation(Param.class);
                    if (param != null) {
                        p[0] = lookFor(t.getRequestURI().getQuery(), param.value());
                    }
                }

                String response = (String) m.invoke(o, p);
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Utilitaire.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String lookFor(String query, String param) {
        Scanner in = new Scanner(query);
        in.useDelimiter("&");
        while (in.hasNext()) {
            String line = in.next();
            String[] cv = line.split("=");
            if (cv[0].equals(param)) {
                return cv[1];
            }
        }
        return "";
    }
}
