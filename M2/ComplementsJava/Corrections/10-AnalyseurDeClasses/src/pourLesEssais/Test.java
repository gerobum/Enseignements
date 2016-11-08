/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pourLesEssais;

import dialogue.DialogueCreationValeurQuelconque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author yvan
 */
public class Test extends JFrame {

  private final static Map<String, Class> primitive = new HashMap<>();

  static {
    primitive.put("int", int.class);
    primitive.put("byte", byte.class);
    primitive.put("short", short.class);
    primitive.put("long", long.class);
    primitive.put("char", char.class);
    primitive.put("boolean", boolean.class);
    primitive.put("float", float.class);
    primitive.put("double", double.class);

    primitive.put("int[]", new int[0].getClass());
    primitive.put("byte[]", new byte[0].getClass());
    primitive.put("short[]", new short[0].getClass());
    primitive.put("long[]", new long[0].getClass());
    primitive.put("char[]", new char[0].getClass());
    primitive.put("boolean[]", new boolean[0].getClass());
    primitive.put("float[]", new float[0].getClass());
    primitive.put("double[]", new double[0].getClass());
  }

  public Test() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

  }

  public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
    
    Scanner clavier = new Scanner(System.in);
    DialogueCreationValeurQuelconque dcvq;
    String nomType;
    while (true) {
      System.out.print("Un type : ");
      nomType = clavier.next();
      Class<?> c = primitive.get(nomType);
      if (c == null) {
        dcvq = new DialogueCreationValeurQuelconque(Class.forName(nomType));
      } else {
        dcvq = new DialogueCreationValeurQuelconque(c);
      }
      System.out.println(dcvq.getValeur());
    }
  }
}
