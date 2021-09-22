/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fichiers;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;

/**
 *
 * @author yvan
 */
public class Fichiers {
  public static void parcoursEnProfondeur(Path dir, String indent) throws IOException {
    System.out.println(indent + dir.getFileName());
    List<Path> liste = new LinkedList<>();
    if (Files.isDirectory(dir)) {
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
        for (Path entry : stream) {
          liste.add(entry);
        }
      }
    }

    for (Path p : liste) {
      parcoursEnProfondeur(p, indent + "  ");
    }
  }

  public static void main(String[] args) throws IOException {
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int returnVal = jfc.showOpenDialog(null);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = jfc.getSelectedFile();
      
      Path dir = file.toPath();
      
      parcoursEnProfondeur(dir, "");
    }
  }
}
