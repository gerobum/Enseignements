
package decoupage;

import java.util.Scanner;


public class Decoupage {
public static void main(String[] args) {
    Scanner in = new Scanner("Une phrase, ponctuée ou pas, est composée de mots.");
    in.useDelimiter("[, .]+");

    while(in.hasNext()) {
        System.out.println(in.next());
    }
}
}
