package fenetrefx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author maillot
 */
public class AFXBorderGridPane extends Application {

    private StackPane top, bottom, left, right, center;
    private final Border borderAll = new Border(new BorderStroke(null, BorderStrokeStyle.DASHED, null, new BorderWidths(1.0)));
    private final Border borderLR = new Border(new BorderStroke(null, null, null, null,
            BorderStrokeStyle.NONE, BorderStrokeStyle.DOTTED, BorderStrokeStyle.NONE, BorderStrokeStyle.DOTTED,
            CornerRadii.EMPTY, new BorderWidths(1.0), Insets.EMPTY));
    private final Background rouge = new Background(new BackgroundFill(Color.rgb(255, 200, 200), null, null));
    private final Background vert = new Background(new BackgroundFill(Color.rgb(200, 255, 200), null, null));
    private final Background bleu = new Background(new BackgroundFill(Color.rgb(200, 200, 255), null, null));
    private final Background jaune = new Background(new BackgroundFill(Color.rgb(255, 255, 200), null, null));
    private final Background cyan = new Background(new BackgroundFill(Color.rgb(200, 255, 255), null, null));

    public GridPane getGridPane() {
        Label top, bottom, left, right, center;
        GridPane pane = new GridPane();

        top = new Label("(2,3)");
        top.setPrefSize(50, 50);
        top.setBorder(borderAll);
        top.setBackground(rouge);
        top.setAlignment(Pos.CENTER);

        center = new Label("(2,5)");
        center.setTextAlignment(TextAlignment.CENTER);
        center.setContentDisplay(ContentDisplay.CENTER);
        center.setPrefSize(200, 200);
        center.setBorder(borderAll);
        center.setBackground(vert);
        center.setAlignment(Pos.CENTER);

        bottom = new Label("(3,2)");
        bottom.setPrefSize(40, 30);
        bottom.setBorder(borderAll);
        bottom.setBackground(bleu);
        bottom.setAlignment(Pos.CENTER);

        left = new Label("(4,3)");
        left.setBorder(borderAll);
        left.setBackground(jaune);
        left.setAlignment(Pos.CENTER);

        right = new Label("(1,0)");
        right.setBorder(borderAll);
        right.setBackground(cyan);
        right.setAlignment(Pos.CENTER);

        pane.add(top, 2, 3);
        pane.add(right, 1, 0);
        pane.add(bottom, 3, 2);
        pane.add(left, 4, 3);
        pane.add(center, 2, 5);

        return pane;
    }

    @Override
    public void start(Stage primaryStage) {
        top = new StackPane(new Label("Top (0x50)"));
        top.setPrefSize(50, 50);
        top.setBorder(borderAll);
        top.setBackground(rouge);

        //center = new StackPane(getGridPane());
        //center.setPrefSize(200, 200);
        //center.setBackground(vert);

        bottom = new StackPane(new Label("Bottom (30x30)"));
        bottom.setPrefSize(30, 30);
        bottom.setBorder(borderAll);
        bottom.setBackground(bleu);

        left = new StackPane(new Label(" Left "));
        left.setBorder(borderLR);
        left.setBackground(jaune);

        right = new StackPane(new Label(" Right "));
        right.setBorder(borderLR);
        right.setBackground(cyan);

        BorderPane pane = new BorderPane();
        pane.setTop(top);
        pane.setCenter(getGridPane());
        pane.setBottom(bottom);
        pane.setLeft(left);
        pane.setRight(right);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Un GridPane au centre d'un BorderPane");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
