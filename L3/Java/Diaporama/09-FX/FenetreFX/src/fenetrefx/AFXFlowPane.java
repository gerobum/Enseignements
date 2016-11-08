package fenetrefx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author maillot
 */
public class AFXFlowPane extends Application {
    private StackPane top, bottom, left, right, center;
    private final Border borderAll = new Border(new BorderStroke(null, BorderStrokeStyle.DASHED, null, new BorderWidths(1.0)));
    private final Border borderLR = new Border(new BorderStroke(null, null, null, null,
            BorderStrokeStyle.NONE, BorderStrokeStyle.DOTTED, BorderStrokeStyle.NONE, BorderStrokeStyle.DOTTED,
            CornerRadii.EMPTY, new BorderWidths(1.0), Insets.EMPTY));

    @Override
    public void start(Stage primaryStage) {
        top = new StackPane(new Label("Top (0x50)"));
        top.setPrefSize(50, 50);
        top.setBorder(borderAll);

        center = new StackPane(new Label("Center (200x200)"));
        center.setPrefSize(200, 200);
        center.setBorder(borderAll);

        bottom = new StackPane(new Label("Bottom (30x30)"));
        bottom.setPrefSize(30, 30);
        bottom.setBorder(borderAll);

        left = new StackPane(new Label(" Left "));
        left.setBorder(borderAll);

        right = new StackPane(new Label(" Right "));
        right.setBorder(borderAll);

        FlowPane pane = new FlowPane();
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
