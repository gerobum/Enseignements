package fenetrefx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
import javafx.stage.StageStyle;

/**
 *
 * @author maillot
 */
public class AFXBorderPane extends Application {
        private Label top, bottom, left, right, center;
    private StackPane stop, sbottom, sleft, sright, scenter;
    private final Border borderAll = new Border(new BorderStroke(null, BorderStrokeStyle.DASHED, null, new BorderWidths(1.0)));
    private final Border borderLR = new Border(new BorderStroke(null, null, null, null, 
            BorderStrokeStyle.NONE, BorderStrokeStyle.DOTTED, BorderStrokeStyle.NONE, BorderStrokeStyle.DOTTED, 
            CornerRadii.EMPTY, new BorderWidths(1.0), Insets.EMPTY));
    
  
    private void définirLesLabels() {
        top = new Label("Top (0x50)");
        //top.setBorder(border);
        stop = new StackPane(top);
        stop.setPrefSize(50, 50);
        stop.setBorder(borderAll);
        
        center = new Label("Center (200x200)");
        //center.setBorder(border);
        scenter = new StackPane(center);
        scenter.setPrefSize(200, 200);
        //scenter.setBorder(border);
        
        //scenter.setBackground(new Background(new BackgroundFill(null, null, null)));
        
        bottom = new Label("Bottom (30x30)");
        sbottom = new StackPane(bottom);
        //bottom.setBorder(borderAll);
        sbottom.setPrefSize(30, 30);
        sbottom.setBorder(borderAll);
        
        
        left = new Label(" Left ");
        //left.setBorder(border);
        sleft = new StackPane(left);
        sleft.setBorder(borderLR);
        
        right = new Label(" Right ");
        //right.setBorder(border);
        sright = new StackPane(right);
        sright.setBorder(borderLR);
    }
    @Override
    public void start(Stage primaryStage) {     
        définirLesLabels();
        BorderPane pane = new BorderPane();
        pane.setTop(stop);
        pane.setCenter(scenter);
        pane.setBottom(sbottom);
        pane.setLeft(sleft);
        pane.setRight(sright);
        
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Utilisation d'un BorderPane");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }  
}
