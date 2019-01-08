
package jeu;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author yvan
 */
public class LabelP4 extends Label {
    public final int ROW, COL;
    private final Node EMPTY = new Circle(50, Color.LIGHTGRAY);
    private final Node YELLOW = new Circle(50, Color.YELLOW);
    private final Node RED = new Circle(50, Color.RED);

    public LabelP4(int row, int col) {
        this.ROW = row;
        this.COL = col;
        setGraphic(EMPTY);
    }
    
    public boolean isRed() {
        return getGraphic().equals(RED);
    }
    
    public boolean isYellow() {
        return getGraphic().equals(YELLOW);
    }
    
    public boolean isEmpty() {
        return getGraphic().equals(EMPTY);
    }
    
    public void setRed() {
        setGraphic(RED);
    }
    
    public void setYellow() {
        setGraphic(YELLOW);
    }
    
    public void setEmpty() {
        setGraphic(EMPTY);
    }
}
