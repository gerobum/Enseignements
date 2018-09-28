/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author yvan
 */
public class LectureSite {

    public static void main(String[] args) throws MalformedURLException, IOException, XMLStreamException {
        URL url = new URL("http://www.uha.fr");
        URLConnection hc = url.openConnection();
        hc.connect();
        Scanner sc = new Scanner(hc.getInputStream());

        while (sc.hasNext()) {
            String ligne = sc.nextLine();
            System.out.println(ligne);
        }
    }
}
