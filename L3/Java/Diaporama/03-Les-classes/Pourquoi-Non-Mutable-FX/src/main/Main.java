package main;

import geometrie.non_mutable.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private Triangle toit;
    private Carre facade;
    private final Button translateToit = new Button("\u2923");
    private final Button translateFacade = new Button("\u2925");
    private Canvas canvas;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Intérêt des classes non-mutables");
        home();
        BorderPane root = new BorderPane();
        canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes();
        translateFacade.setFont(Font.font(50.0));
        translateToit.setFont(Font.font(50.0));
        root.setTop(translateToit);
        root.setBottom(translateFacade);
        root.setCenter(canvas);
        stage.setScene(new Scene(root));
        
        setListener();

        stage.show();
    }

    private void home() {

        Point p20 = new Point(300, 400);
        Point p22 = new Point(400, 400);
        Point p13 = new Point(350, 330);
        Point p00 = new Point(300, 500);
        Point p02 = new Point(400, 500);

        toit = new Triangle(p20, p13, p22);
        facade = new Carre(p00, p20, p22, p02);
    }

    public void drawTriangle(GraphicsContext gc, Triangle triangle) {
        double[] x = new double[3];
        double[] y = new double[3];
        for (int i = 0; i < 3; ++i) {

            x[i] = triangle.getPoint(i).getX();
            y[i] = triangle.getPoint(i).getY();
        }
        gc.strokePolygon(x, y, 3);
    }

    public void drawCarre(GraphicsContext gc, Carre carre) {
        double[] x = new double[4];
        double[] y = new double[4];
        for (int i = 0; i < 4; ++i) {
            x[i] = carre.getPoint(i).getX();
            y[i] = carre.getPoint(i).getY();
        }
        gc.strokePolygon(x, y, 4);
    }

    private void drawShapes() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 800, 800);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(6);
        gc.setLineJoin(StrokeLineJoin.BEVEL);
        gc.setLineCap(StrokeLineCap.ROUND);
        drawCarre(gc, facade);
        drawTriangle(gc, toit);
        /*
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);*/
    }

    private void setListener() {
        translateFacade.setOnAction(p -> {
            facade.translate(10, 10);            
            drawShapes();
        });
        translateToit.setOnAction(p -> {
            toit.translate(-4, -5);
            drawShapes();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
