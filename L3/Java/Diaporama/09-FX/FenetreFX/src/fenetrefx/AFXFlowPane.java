package fenetrefx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author maillot
 */
public class AFXFlowPane extends Application {
    private Label top, bottom, left, right, center;
    private final Border borderAll = new Border(new BorderStroke(null, BorderStrokeStyle.DASHED, null, new BorderWidths(1.0)));
    private final Border borderLR = new Border(new BorderStroke(null, null, null, null,
            BorderStrokeStyle.NONE, BorderStrokeStyle.DOTTED, BorderStrokeStyle.NONE, BorderStrokeStyle.DOTTED,
            CornerRadii.EMPTY, new BorderWidths(1.0), Insets.EMPTY));
    private final Background rouge = new Background(new BackgroundFill(Color.rgb(255, 200, 200), null, null));
    private final Background vert = new Background(new BackgroundFill(Color.rgb(200, 255, 200), null, null));
    private final Background bleu = new Background(new BackgroundFill(Color.rgb(200, 200, 255), null, null));
    private final Background jaune = new Background(new BackgroundFill(Color.rgb(255, 255, 200), null, null));
    private final Background cyan = new Background(new BackgroundFill(Color.rgb(200, 255, 255), null, null));
    

    @Override
    public void start(Stage primaryStage) {     
        top = new Label("Top (0x50");
        top.setPrefSize(50, 50);
        top.setBorder(borderAll);
        top.setBackground(rouge);

        center = new Label("Center (200x200)");
        center.setTextAlignment(TextAlignment.CENTER);
        center.setContentDisplay(ContentDisplay.CENTER);
        center.setPrefSize(200, 200);
        center.setBorder(borderAll);
        center.setBackground(vert);
        center.setAlignment(Pos.CENTER);

        bottom = new Label("Bottom (30x30)");
        bottom.setPrefSize(30, 30);
        bottom.setBorder(borderAll);
        bottom.setBackground(bleu);

        left = new Label(" Left ");
        left.setBorder(borderAll);
        left.setBackground(jaune);

        right = new Label(" Right ");
        right.setBorder(borderAll);
        right.setBackground(cyan);

        FlowPane pane = new FlowPane();
        pane.setRowValignment(VPos.CENTER);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().add(top);
        pane.getChildren().add(right);
        pane.getChildren().add(bottom);
        pane.getChildren().add(left);
        pane.getChildren().add(center);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Utilisation d'un FlowPane");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
