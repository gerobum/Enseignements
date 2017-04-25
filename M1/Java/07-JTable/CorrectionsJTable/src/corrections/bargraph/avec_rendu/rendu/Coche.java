/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corrections.bargraph.avec_rendu.rendu;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;

/**
 *
 * @author yvan
 */
public class Coche extends JTextField {
    public Coche() {
 
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setOpaque(true);
        setBackground(Color.red);
        //setBorderPaintedFlat(true);
        setForeground(Color.blue);
    }
}
