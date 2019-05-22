package terrain;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Mur implements Cellule {
    private Image image;

    public Mur() {
        try {
            image = ImageIO.read(new File("mur.bmp"));
        } catch (IOException ex) {
            Logger.getLogger(Mur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Image getImage() {
        return image;
    }
}
