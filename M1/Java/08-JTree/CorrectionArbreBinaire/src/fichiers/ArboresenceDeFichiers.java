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
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author maillot
 */
public class ArboresenceDeFichiers {

  public static DefaultMutableTreeNode parcoursEnProfondeur(Path dir) throws IOException {
    DefaultMutableTreeNode node = new DefaultMutableTreeNode(dir);
    //System.out.println(indent + dir.getFileName());
    List<Path> liste = new LinkedList<>();
    if (Files.isDirectory(dir)) {
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
        for (Path entry : stream) {
          liste.add(entry);
        }
      }
    }

    for (Path p : liste) {
      node.add(parcoursEnProfondeur(p));
    }
    return node;
  }

  public static void main(String[] args) throws IOException {

    DefaultMutableTreeNode racine = null;

    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int returnVal = jfc.showOpenDialog(null);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = jfc.getSelectedFile();

      Path dir = file.toPath();

      racine = parcoursEnProfondeur(dir);
    }



    JTree arbre = new JTree(new DefaultTreeModel(racine));

    JFrame f = new JFrame("Un arbre simple");
    f.getContentPane().add(arbre);
    f.setVisible(true);
    f.pack();
  }
}
