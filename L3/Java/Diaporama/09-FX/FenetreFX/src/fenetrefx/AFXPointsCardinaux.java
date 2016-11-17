package fenetrefx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author maillot
 */
public class AFXPointsCardinaux extends Application {

    private Button top, bottom, left, right;
    private Label center;

    private final EventHandler<ActionEvent> action = e -> center.setText(((Button) e.getSource()).getText());

    private final Background vert = new Background(new BackgroundFill(Color.rgb(200, 255, 200), null, null));

    @Override
    public void start(Stage primaryStage) {
        top = new Button("Top");

        center = new Label("Center");
        center.setPrefSize(200, 200);
        center.setBackground(vert);
        center.setAlignment(Pos.CENTER);

        bottom = new Button("Bottom");

        left = new Button("Left");

        right = new Button("Right");

        top.setOnAction(action);
        bottom.setOnAction(action);
        left.setOnAction(action);
        right.setOnAction(action);

        BorderPane pane = new BorderPane();
        pane.setTop(new StackPane(top));
        pane.setCenter(new StackPane(center));
        pane.setBottom(new StackPane(bottom));
        pane.setLeft(new StackPane(left));
        pane.setRight(new StackPane(right));

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cliquez moi");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
