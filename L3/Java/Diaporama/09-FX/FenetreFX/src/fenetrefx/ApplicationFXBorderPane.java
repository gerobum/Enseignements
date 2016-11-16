package fenetrefx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author yvan
 */
public class ApplicationFXBorderPane extends Application {
    @Override
    public void start(Stage primaryStage) {     
        // Placement d'un texte dans un panneau
        Label label = new Label("Je vous fais de l'FX ?");
        // Définition de sa couleur
        label.setTextFill(Color.YELLOW);
        label.setFont(new Font(30.0));
        
        label.setBackground(new Background(new BackgroundFill(Color.CRIMSON, null, null)));
        StackPane pane = new StackPane(label);
        // Création et dimensionnement d'une scène
        Scene scene = new Scene(pane, 150, 80);
        // Attribution de la scène au stage initial.
        primaryStage.setScene(scene);
        // Le reste est inchangé.
        primaryStage.setTitle("Une fenêtre qui fait de l'effet");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }   
}
