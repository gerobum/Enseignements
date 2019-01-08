package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jeu.GrilleP4;

/**
 *
 * @author yvan
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene root = new Scene(new GrilleP4());
        primaryStage.setScene(root);
        primaryStage.setTitle("Puissance 4");
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
