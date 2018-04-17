package demo;

import java.util.concurrent.LinkedBlockingDeque;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
 *
 * @author maillot
 */
public class ConsumerApplication extends Application {

    private final GridPane CENTRE = new GridPane();
    private final Button AJOUTER = new Button("Ajouter");
    private static final LinkedBlockingDeque<Double> QUEUE;

    static {
        QUEUE = new LinkedBlockingDeque<>(3);
        Producer p = new Producer(QUEUE, "Producteur", 100, -1, false);
        p.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();

        root.setCenter(CENTRE);
        root.setBottom(AJOUTER);
        AJOUTER.setOnAction(e -> {
            Consumer consumer = new Consumer(QUEUE, "C" + CENTRE.getChildren().size(), 0);
            CENTRE.add(new ConsumerPane(consumer), 0, CENTRE.getChildren().size());

            primaryStage.sizeToScene();
        });

        Scene scene = new Scene(root);

        primaryStage.setTitle("Démo Producteur/Consommateur");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        ConsumerApplication.launch(args);
    }

}
