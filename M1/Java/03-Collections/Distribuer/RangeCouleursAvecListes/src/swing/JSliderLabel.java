/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author maillot
 */
public class JSliderLabel extends JPanel {
    private JSlider jslider;

    public JSlider getJslider() {
        return jslider;
    }

    public JTextField getJtextfield() {
        return jtextfield;
    }
    private JTextField jtextfield;
    //private JLabel label;
    
    public JSliderLabel(int min, int max) {
        this(new JSlider(min, max), new JTextField());
    }

    public JSliderLabel(JSlider glisseur, JTextField tf) {
        this.jslider = glisseur;
        this.jtextfield = tf;
        tf.setColumns((int) Math.log10(glisseur.getMaximum())+1);
        tf.setText(glisseur.getValue()+"");
        setLayout(new BorderLayout());
        add(tf, "West");
        add(glisseur, "Center");
        
        glisseur.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                tf.setText(glisseur.getValue()+"");
            }
        });
    }
    
    public void addChangeListener(ChangeListener change) {
        jslider.addChangeListener(change);
    }
    
    public int getValue() {
        return jslider.getValue();
    }
    
    public void setValue(int v) {
        jslider.setValue(v);
    }
  
}
