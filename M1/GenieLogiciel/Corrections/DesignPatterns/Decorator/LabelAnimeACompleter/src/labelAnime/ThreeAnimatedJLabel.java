package labelAnime;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ThreeAnimatedJLabel extends JFrame {

    private JLabel turn, blink, turnAndBlink;

    public ThreeAnimatedJLabel() {
        super("Labels à animer");
        setUI();
        animation();
    }

    private void setUI() {

        turn = new JLabel("Tourne ", JLabel.CENTER);
        blink = new JLabel("Clignote", JLabel.CENTER);
        turnAndBlink = new JLabel("Tourne et Clignote ", JLabel.CENTER);

        Font font = new Font("Courrier", Font.BOLD, 25);
        turn.setFont(font);
        blink.setFont(font);
        turnAndBlink.setFont(font);

        getContentPane().setLayout(new GridLayout(0, 1));

        getContentPane().add(turn);
        getContentPane().add(blink);
        getContentPane().add(turnAndBlink);

        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void animation() {
        // TODO Wrap the turn, blink and turnAndBlink JLabel with your
        //      own decorator in order to make them turn and blink and so on. 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ThreeAnimatedJLabel();
            }
        });

    }
}
