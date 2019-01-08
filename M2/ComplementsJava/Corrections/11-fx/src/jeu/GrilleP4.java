package jeu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author yvan
 */
public class GrilleP4 extends GridPane {

    private final LabelP4[][] LABEL;
    private final int[] pos = {5, 5, 5, 5, 5, 5, 5};

    public GrilleP4() {
        setVgap(7);
        setHgap(7);
        setPadding(new Insets(20));
        LABEL = new LabelP4[6][7];
        setBackground(new Background(new BackgroundFill(Color.NAVY, CornerRadii.EMPTY, Insets.EMPTY)));

        EventHandler<? super MouseEvent> action = p -> {
            LabelP4 l = (LabelP4) p.getSource();
            //putRedOnCol(l.COL);
        };

        for (int row = 0; row < LABEL.length; ++row) {
            for (int col = 0; col < LABEL[row].length; ++col) {
                LABEL[row][col] = new LabelP4(row, col);
                add(LABEL[row][col], col, row);
                LABEL[row][col].setOnMouseClicked(action);
            }
        }
        new Jeu().start();
    }

    public boolean isColFree(int col) {
        return col >= 0 && col <= 6 && pos[col] >= 0;
    }

    public void putRedOnCol(int col) {
        if (isColFree(col)) {
            LABEL[pos[col]][col].setRed();
            pos[col]--;
        }
    }

    public void putYellowOnCol(int col) {
        if (isColFree(col)) {
            LABEL[pos[col]][col].setYellow();
            pos[col]--;
        }
    }

    public void putEmptyOnCol(int col) {
        if (isColFree(col)) {
            LABEL[pos[col]][col].setEmpty();
            pos[col]--;
        }
    }

    public void send(DatagramSocket ds, String msg) throws UnknownHostException, IOException {
        DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(), Inet4Address.getLocalHost(), 1414);
        ds.send(dp);
    }

    public String receive(DatagramSocket ds) throws IOException {
        byte[] msg = new byte[256];
        DatagramPacket dp = new DatagramPacket(msg, 256);
        ds.receive(dp);
        return new String(msg);
    }
    
    

    private class Jeu extends Thread {

        private byte[] message = new byte[256];
        private InetAddress serverAdress;
        private int serverPort;

        @Override
        public void run() {
            try (DatagramSocket ds = new DatagramSocket()) {
                send(ds, "X");
                System.out.println("Attente de go");
                String msg;
                do {
                    msg = receive(ds);
                } while (!"go".equals(msg));
                System.out.println("go reçu");
            } catch (SocketException ex) {
            } catch (IOException ex) {
            }
        }
    }
}
