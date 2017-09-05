/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointscardinaux;

import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class PointsCardinaux extends Application {

    private Label center;
    private Button top, bottom, right, left;

    @Override
    public void start(Stage primaryStage) {

        top = new Button("Top");
        bottom = new Button("Bottom");
        right = new Button("Right");
        left = new Button("Left");
        center = new Label();

        EventHandler<ActionEvent> action = event -> {
            Button b = (Button) event.getSource();
            center.setText(b.getText());
        };

        top.setOnAction(action);
        bottom.setOnAction(action);
        right.setOnAction(action);
        left.setOnAction(action);

        top.setOnAction(event -> {
            Button b = (Button) event.getSource();
            Scanner sc = new Scanner(b.getText());
            try {
                int n = sc.nextInt();
                b.setText((n + 1) + " fois");
            } catch (Exception a) {
                b.setText("0 fois");
            }
        });

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setBottom(bottom);
        root.setLeft(left);
        root.setRight(right);
        root.setCenter(center);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
