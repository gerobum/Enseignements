package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import sodoku.GrandCarr�;
import sudoku.Case;
import sudoku.Grille;
import sudoku.Zone9;

class Bouton extends JButton {
    
    private final Font FONT_FOR_1_CAR = new Font("Bradley Hand ITC", Font.BOLD, 50);
    private final Font FONT_FOR_9_CAR = new Font("Bradley Hand ITC", Font.BOLD, 16);;
    
    
    private final Case aCase;
    public final int l, c;
    public Bouton(Case aCase, int l, int c) {
        this.l = l;
        this.c = c;
        this.aCase = aCase;
        construireNomBouton();
    }
    /*public Bouton(String nom, Case aCase, int l, int c) {
        super(nom);
        this.aCase = aCase;
        this.l = l;
        this.c = c;

    }*/    
    public Case getCarr�() {
        return aCase;
    }
    
    
    public final void construireNomBouton() {
        if (aCase.numberOfPossibily() == 1) {
            setText(aCase.getPossibilities()[0]+"");
            setFont(FONT_FOR_1_CAR);
        } else {
            setFont(FONT_FOR_9_CAR);
            StringBuilder sb = new StringBuilder();
            sb.append("<html>");
            Integer[] p = aCase.getPossibilities();
            int x=1;
            for(int k : p) {
                sb.append(k);
                if (x % 3 == 0)
                    sb.append("<br>");
                ++x;
            }
            
            sb.append("</html>");
            setText(sb.toString());  
        }
    }
}

public class FrameSudoku extends JFrame implements ActionListener, KeyListener, MouseListener, FocusListener {
    Grille grille;
    LinkedList<Zone9> liste;
    Bouton[][] bouton;
    Bouton boutonQuiaLeFocus;
    JMenuItem action_nouvelle_partie, action_r�cure, action_pr�c�dent, action_r�soudre, action_init, action_v�rif;
    
    public FrameSudoku(Grille g) {
        liste = new LinkedList<>();
        grille = g;
        bouton = new Bouton[9][9];
        miseEnplaceDesComposants();
    }
    private void miseEnplaceDesComposants() {
        
        setJMenuBar(new JMenuBar());
        getJMenuBar().add(new JMenu("Jeu"));
        action_nouvelle_partie = new JMenuItem("Nouvelle partie");
        getJMenuBar().getMenu(0).add(action_nouvelle_partie);
        action_nouvelle_partie.addActionListener(this);
        action_r�cure = new JMenuItem("R�cure");
        getJMenuBar().getMenu(0).add(action_r�cure);
        action_r�cure.addActionListener(this);
        action_pr�c�dent = new JMenuItem("Pr�c�dent");
        getJMenuBar().getMenu(0).add(action_pr�c�dent);
        action_pr�c�dent.addActionListener(this);
        action_r�soudre = new JMenuItem("R�soudre");
        getJMenuBar().getMenu(0).add(action_r�soudre);
        action_r�soudre.addActionListener(this);
        action_init = new JMenuItem("Init");
        getJMenuBar().getMenu(0).add(action_init);
        action_init.addActionListener(this);
        action_v�rif = new JMenuItem("V�rification");
        getJMenuBar().getMenu(0).add(action_v�rif);
        action_v�rif.addActionListener(this);
        
        getContentPane().setLayout(new GridLayout(3, 4, 20, 20));
        getContentPane().addKeyListener(this);
        getContentPane().setBackground(Color.ORANGE);
        
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                getContentPane().add(cr�er_panneau_avec_9_boutons(i, j));
            }
        }
        boutonQuiaLeFocus = bouton[0][0];
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private JPanel cr�er_panneau_avec_9_boutons(int l, int c) {
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(3,3, 5,5));
        panneau.setBackground(Color.ORANGE);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++) {
                int L = l*3+i;
                int C = c*3+j;
                
                bouton[L][C] = new Bouton(grille.getCase(L, C), L, C);
                bouton[L][C].setPreferredSize(new Dimension(70, 70));
                bouton[L][C].setFont(new Font("Bradley Hand ITC", Font.BOLD, 16));
                //grille.getCarr�(L,C).setBouton(bouton[L][C]);
                //grille.getCarr�(L,C).construire_nom_bouton();
                bouton[L][C].addKeyListener(this);
                bouton[L][C].addMouseListener(this);
                bouton[L][C].addFocusListener(this);
                bouton[L][C].setBackground(Color.WHITE);
                panneau.add(bouton[L][C]);
            }
        panneau.setVisible(true);
        return panneau;
    }
    private void mettre_�_blanc() {
        for(int i = 0; i < 9; i++)
            for(int j = 0; j < 9; j++) {
                bouton[i][j].setBackground(Color.WHITE);
                //bouton[i][j].aCase = grille.getCarr�(i,j);

            }
    }
    private void tenter_petit_carr�(int l, int c) {
        /*GrandCarr� gc = grille.copie();
        PetitCarr� petit_carr� = gc.getCarr�(l, c);
        if (petit_carr�.valeurs_possible().size() > 1) {
            for(Integer i : petit_carr�.valeurs_possible()) {
                petit_carr�.setValeur(i);
                gc.r�cure();
                if (gc.erreur()) {
                    grille.getCarr�(l, c).enlever(i);
                }  
                gc = grille.copie();
            }
        }*/
    }
    private void r�soudre() {
         for(int i = 0; i < 9; i++)
             for(int j = 0; j < 9; j++)
                 tenter_petit_carr�(i, j);
         mettre_�_blanc();
    }
     /*
    private GrandCarr� r�soudre(GrandCarr� gc) {
        if (gc.fini()) {
            return gc;
        } else {
            GrandCarr� gc2 = gc.copie();
            for(int i = 0; i < 9; i++)
                for(int j = 0; j < 9;j++)
                    for(Integer I : gc2.getCarr�(i, j).valeurs_possible()) {
                        System.out.println(I);
                        gc2.getCarr�(i, j).setValeur(I);
                        if (gc2.erreur()) {
                            gc2 = gc.copie();
                        } else {
                            gc2 = r�soudre(gc2);
                            if (gc2 != null) {
                                return gc2;
                            } else {
                                gc2 = gc.copie();
                            }
                        }
                        return null;
                    }
                        
            
        }
        return null;
    }
      **/
    @Override
    public void actionPerformed(ActionEvent e) {
        /*if (e.getSource() == action_nouvelle_partie) {
            grille.nouvelle_partie();
            liste.clear();
        } else if (e.getSource() == action_r�cure) {
            grille.r�cure();
        } else if (e.getSource() == action_pr�c�dent) {
            if (!liste.isEmpty()) {
                grille = liste.removeFirst();
                for(int i = 0; i < 9; i++)
                    for(int j = 0; j < 9; j++) {
                        bouton[i][j].setBackground(Color.WHITE);
                        bouton[i][j].aCase = grille.getCarr�(i,j);
                    }
                grille.mise_�_jour_des_boutons();
            }
        } else if (e.getSource() == action_r�soudre) {
         
            r�soudre();
            pack();
        } else if (e.getSource() == action_init) {
            grille.getCarr�(0,0).setValeur(6);
            grille.getCarr�(0,3).setValeur(8);
            grille.getCarr�(0,6).setValeur(3);
            grille.getCarr�(1,1).setValeur(4);
            grille.getCarr�(1,3).setValeur(5);
            grille.getCarr�(1,7).setValeur(8);
            grille.getCarr�(2,2).setValeur(7);
            grille.getCarr�(2,4).setValeur(9);
            grille.getCarr�(2,6).setValeur(1);
            grille.getCarr�(2,8).setValeur(4);
            grille.getCarr�(3,0).setValeur(5);
            grille.getCarr�(3,6).setValeur(9);
            grille.getCarr�(3,7).setValeur(6);
            grille.getCarr�(4,4).setValeur(5);
            grille.getCarr�(5,1).setValeur(8);
            grille.getCarr�(5,2).setValeur(2);
            grille.getCarr�(5,8).setValeur(3);
            grille.getCarr�(6,0).setValeur(4);
            grille.getCarr�(6,2).setValeur(3);
            grille.getCarr�(6,4).setValeur(2);
            grille.getCarr�(6,6).setValeur(6);
            grille.getCarr�(7,1).setValeur(6);
            grille.getCarr�(7,5).setValeur(8);
            grille.getCarr�(7,7).setValeur(3);
            grille.getCarr�(8,2).setValeur(8);
            grille.getCarr�(8,5).setValeur(7);        
            grille.getCarr�(8,8).setValeur(2);        
        } else if (e.getSource() == action_v�rif) {
            //for(int i = 0)
        }*/
    }
    @Override
    public void keyTyped(KeyEvent e) {
        /*Bouton b;
        if (e.getKeyChar() >= '1' && e.getKeyChar() <= '9') {
            /*try {
                liste.addFirst(grille.clone());
                
            } catch (CloneNotSupportedException exp) {
                exp.printStackTrace();
            }*//*  
            liste.addFirst(grille.copie());
            b = (Bouton)e.getSource();
            

            b.getCarr�().setValeur(Integer.parseInt(e.getKeyChar()+""));
            grille.r�cure();
        }*/
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int l = boutonQuiaLeFocus.l;
        int c = boutonQuiaLeFocus.c;
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_UP:
                l--;
                if (l < 0) l = 8;
                break;
            case KeyEvent.VK_DOWN:
                l++;
                if (l > 8) l = 0;
                break;
            case KeyEvent.VK_LEFT:
                c--;
                if (c < 0) c = 8;
                break;
            case KeyEvent.VK_RIGHT:
                c++;
                if (c > 8) c = 0;
                break;
            case KeyEvent.VK_BACK_SPACE:
                action_pr�c�dent.doClick();
                break;
            default:
                break;
        }
        if (bouton[l][c].requestFocusInWindow())
            boutonQuiaLeFocus = bouton[l][c];  
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if (((Bouton) e.getSource()).requestFocusInWindow())
            boutonQuiaLeFocus = (Bouton) e.getSource();  
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void focusLost(FocusEvent e) {
        ((Bouton) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }
    @Override
    public void focusGained(FocusEvent e) {
        ((Bouton) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
    }
}
