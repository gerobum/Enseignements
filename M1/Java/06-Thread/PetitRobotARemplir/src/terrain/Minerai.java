package terrain;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Minerai implements Cellule {
    private Image image;
    public Minerai() {
        try {
            image = ImageIO.read(new File("minerai.bmp"));
        } catch (IOException ex) {
            Logger.getLogger(Minerai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public Image getImage() {
        return image;
    }
}
