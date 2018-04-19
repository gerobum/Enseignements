package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import jeu.T2048;

/**
 *
 * @author yvan
 */
public class Main {

    private static JButton north, south, east, west;
    private static JLabel[][] labels;
    private static T2048 t;
    private static final char UP = '8';
    private static final char DOWN = '2';
    private static final char LEFT = '4';
    private static final char RIGHT = '6';

    public static void main(String[] args) {
        JFrame f2048 = create2048();
        f2048.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2048.pack();
        f2048.setVisible(true);

        /*Scanner in = new Scanner(System.in);
        
        
        t.affiche();
        
        while(true) {
            System.out.print("u,d,l,r");
            String r = in.next();
            switch(r) {
                case "u":
                    t.up();
                    break;
                case "d":
                    t.down();
                    break;
                case "r":
                    t.right();
                    break;
                case "l":
                    t.left();
                    break;
            }
            t.affiche();
        }*/
    }

    private static JFrame create2048() {
        JFrame f2048 = new JFrame("2048");

        t = new T2048();

        north = new JButton("\u21e7");
        south = new JButton("\u21e9");
        east = new JButton("\u21e8");
        west = new JButton("\u21e6");
        Font font = north.getFont().deriveFont(40f);
        north.setFont(font);
        south.setFont(font);
        east.setFont(font);
        west.setFont(font);

        f2048.setFocusable(true);
        f2048.setFocusTraversalKeysEnabled(true);

        f2048.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                //System.out.println("keyReleased");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("keyPressed");
            }

            @Override
            public void keyTyped(KeyEvent e) {
                char m = e.getKeyChar();
                switch (m) {
                    case UP:
                        t.up();                        
                        t.affiche();
                        changerCenter(t);
                        break;
                    case DOWN:
                        t.down();                        
                        t.affiche();
                        changerCenter(t);
                        break;
                    case LEFT:
                        t.left();                        
                        t.affiche();
                        changerCenter(t);
                        break;
                    case RIGHT:
                        t.right();                        
                        t.affiche();
                        changerCenter(t);
                        break;
                }
            }

        });

        JPanel center = createCenter(t);

        f2048.getContentPane().add(center, "Center");

        f2048.getContentPane().add(east, "East");
        f2048.getContentPane().add(west, "West");
        f2048.getContentPane().add(south, "South");
        f2048.getContentPane().add(north, "North");

        south.addActionListener(e -> {
            t.down();
            System.out.println(((JButton) e.getSource()).getText());
            t.affiche();
            changerCenter(t);
        });
        north.addActionListener(e -> {
            t.up();
            System.out.println(((JButton) e.getSource()).getText());
            t.affiche();
            changerCenter(t);
        });
        east.addActionListener(e -> {
            t.right();
            System.out.println(((JButton) e.getSource()).getText());
            t.affiche();
            changerCenter(t);
        });
        west.addActionListener(e -> {
            t.left();
            System.out.println(((JButton) e.getSource()).getText());
            t.affiche();
            changerCenter(t);
        });

        return f2048;
    }
    private static JLabel createJLabel(Integer i) {
        JLabel label = new JLabel(getString(i), JLabel.CENTER);
        label.setBorder(new BevelBorder(BevelBorder.RAISED));
        label.setOpaque(true);
        label.setFont(label.getFont().deriveFont(40f));

        label.setBackground(getColor(i));

        return label;
    }

    private static String getString(Integer i) {
        if (i == null || i == 0) {
            return "";
        } else {
            return i + "";
        }
    }

    private static Color getColor(Integer i) {
        if (i == null || i == 0) {
            return Color.white;
        } else {
            switch(i) {
                case 2:
                    return Color.ORANGE;
                case 4:
                    return Color.CYAN;
                case 8:
                    return Color.GREEN;
                case 16:
                    return Color.RED;
                case 32:
                    return Color.PINK;
                case 64:
                    return Color.MAGENTA;
                default:
                    return Color.BLACK;
            }
            
        }
    }

    private static void changerCenter(T2048 t) {
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                labels[l][c].setText(getString(t.get(l, c)));
                labels[l][c].setBackground(getColor(t.get(l, c)));
            }
        }
    }

    private static JPanel createCenter(T2048 t) {
        JPanel center = new JPanel(new GridLayout(4, 4));
        center.setPreferredSize(new Dimension(500, 500));
        labels = new JLabel[4][4];
        for (int l = 0; l < 4; ++l) {
            for (int c = 0; c < 4; ++c) {
                labels[l][c] = createJLabel(t.get(l, c));
                center.add(labels[l][c]);
            }
        }
        return center;
    }

}
