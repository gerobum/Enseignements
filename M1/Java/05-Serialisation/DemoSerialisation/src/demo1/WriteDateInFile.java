/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import metier.Personne;

/**
 *
 * @author yvan
 */
public class WriteDateInFile {
    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchFieldException {
        Personne luke = new Personne("Luke", "Lucky",9, 3, 1965, true);
        /*Field field = Personne.class.getField("serialPersistentFields");
        field.
        System.out.println(luke);*/
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tmp"))) {
            out.writeObject("Aujourd'hui");
            out.writeObject(new Date());
            out.writeObject(luke);
        }
    }
}
