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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
        
        top.setPrefWidth(Double.MAX_VALUE);
        bottom.setPrefWidth(Double.MAX_VALUE);
        right.setPrefHeight(Double.MAX_VALUE);
        left.setPrefHeight(Double.MAX_VALUE);
        
        center.setBackground(new Background(
                new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)
        ));
        center.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        EventHandler<ActionEvent> action = event -> {
            Button b = (Button) event.getSource();
            center.setText(b.getText());
        };

        top.setOnAction(action);
        bottom.setOnAction(action);
        right.setOnAction(action);
        left.setOnAction(action);

        BorderPane border = new BorderPane();
        border.setTop(top);
        border.setBottom(bottom);
        border.setLeft(left);
        border.setRight(right);
        border.setCenter(center);
        Pane root = new StackPane(border);

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
