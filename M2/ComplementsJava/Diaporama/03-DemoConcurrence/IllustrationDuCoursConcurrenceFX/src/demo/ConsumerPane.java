
package demo;

import java.util.Random;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

/*
 *
 * @author maillot
 */
public class ConsumerPane extends GridPane {
    private ProgressBar progressBar;
    public ConsumerPane(Consumer consumer) {
        init(consumer);
    }
    
    private void init(Consumer consumer) {
        progressBar = new ProgressBar();
        progressBar.progressProperty().bind(consumer.progressProperty());
        progressBar.setPrefWidth(500);
        progressBar.setStyle("-fx-background-color: red;");
        
        addRow(0, new Label(consumer.getConsumerName()), progressBar);
        new Thread(consumer).start();
    }
}
