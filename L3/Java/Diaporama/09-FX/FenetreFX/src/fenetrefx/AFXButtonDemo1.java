package fenetrefx;

import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author maillot
 */
public class AFXButtonDemo1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button bouton = new Button("0 fois");

        bouton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Button b = (Button) e.getSource();
                Scanner sc = new Scanner(b.getText());
                int n = sc.nextInt();
                b.setText((n + 1) + " fois");
            }
        });


        StackPane pane = new StackPane();
        pane.setPrefSize(200, 200);
        pane.getChildren().add(bouton);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cliquez moi");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
