/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author yvan
 */
public class ReadDateFromFile {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("tmp"))) {
            System.out.println(in.readObject());
            System.out.println(in.readObject());
            System.out.println(in.readObject());
        }
    }
}
