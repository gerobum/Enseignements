/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author yvan
 */
public class TryFinally {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        {
            FileReader in = null;
            FileWriter out = null;
            try {
                in = new FileReader("orig");
                out = new FileWriter("dest");
                int c;
                String s = "";
                while ((c = in.read()) != -1) {
                    s += (char) c;
                }
                out.write(s);
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
        }
        {
            try (FileReader in = new FileReader("orig");
                    FileWriter out = new FileWriter("dest")) {
                int c;
                String s = "";
                while ((c = in.read()) != -1) {
                    s += (char) c;
                }
                out.write(s);
            }
        }
    }
}
