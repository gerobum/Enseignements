package fenetrefx;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author yvan
 */
public class ApplicationFX1 extends Application {
    @Override
    public void start(Stage primaryStage) {     
        primaryStage.setTitle("Une fenêtre qui fait de l'effet");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
} 
