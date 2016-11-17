package fenetrefx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

/**
 *
 * @author maillot
 */
public class AFXPointsCardinaux extends Application {

    private Button top, bottom, left, right;
    private Label  center;

    private final Background vert = new Background(new BackgroundFill(Color.rgb(200, 255, 200), null, null));

    
    EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Button b = (Button) event.getSource();
            center.setText(b.getText());
        }
    };
    
    

    @Override
    public void start(Stage primaryStage) {
        
        
        
        top = new Button(("Top"));
        
        //top.setBackground(rouge);

        center = new Label("Cliquez !");
        center.setBackground(vert);
        center.setPrefSize(300, 300);
        center.setAlignment(Pos.CENTER);

        bottom = new Button("Bottom");
        //bottom.setBackground(bleu);

        left = new Button("Left");
        //left.setBackground(jaune);

        right = new Button("Right");
        //right.setBackground(cyan);
        
        top.setOnAction(action);
        bottom.setOnAction(action);
        left.setOnAction(action);
        right.setOnAction(action);
        
        
        BorderPane pane = new BorderPane();
        pane.setTop(new StackPane(top));
        pane.setCenter(center);
        pane.setBottom(new StackPane(bottom));
        pane.setLeft(new StackPane(left));
        pane.setRight(new StackPane(right));

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cliquez sur un bouton");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
