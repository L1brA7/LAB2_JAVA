import geometry2d.*;
import javafx.application.Application;

import java.lang.Math;
import javafx.event.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import java.util.Collections;

import javafx.stage.Stage;

import java.util.ArrayList;

    
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button RectButton = new Button("Rectangle");
        RectButton.setLayoutX(60);
        RectButton.setLayoutY(10);
        Button CircButton = new Button("Circle");
        CircButton.setLayoutX(10);
        CircButton.setLayoutY(10);
        
        Canvas canvas = new Canvas(512, 512);
        var gc = canvas.getGraphicsContext2D();
        ArrayList<Figure> F_ARR = new ArrayList<Figure>();
        
        RectButton.setOnAction(value ->  {
            addRect(gc, F_ARR);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            DRAW_MAIN(gc, F_ARR);
        });
        CircButton.setOnAction(value ->  {
            addCirc(gc, F_ARR);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            DRAW_MAIN(gc, F_ARR);
        });
        
        var root = new Pane();
        root.setStyle("-fx-background-color: cornsilk;");
        root.getChildren().add(canvas);
        root.getChildren().add(CircButton);
        root.getChildren().add(RectButton);
        
        Scene scene = new Scene(root, 512, 512);

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            int X; int Y;
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.PRIMARY) {
                    X = (int)event.getX(); Y = (int)event.getY();
                    for(int i = F_ARR.size() - 1; i >= 0; i--) {
                        if (F_ARR.get(i).isin(X, Y)) {
                            F_ARR.get(i).setRGB(RND(255), RND(255), RND(255));
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            DRAW_MAIN(gc, F_ARR);
                            break;
                        }
                    }
                }
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            
            int X; int Y;
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    X = (int)event.getX(); Y = (int)event.getY();
                    for(int i = F_ARR.size() - 1; i >= 0; i--) {
                        if (F_ARR.get(i).isin(X, Y)) {
                            if (F_ARR.get(i) instanceof Rect) {F_ARR.get(i).setCoords(X - F_ARR.get(i).getData()[0] / 2, Y - F_ARR.get(i).getData()[1] / 2);}
                            if (F_ARR.get(i) instanceof Circ) {F_ARR.get(i).setCoords(X - F_ARR.get(i).getData()[0], Y - F_ARR.get(i).getData()[0]);}
                            for(int j = F_ARR.size() - 1; j >= i; j--) {
                                Collections.swap(F_ARR, i, j);
                            }
                            break;
                        }
                    }
                }
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                DRAW_MAIN(gc, F_ARR);
            }
        });
        System.out.println("yes");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    

    private void addCirc(GraphicsContext gc, ArrayList<Figure> F_ARR) {
        int CircX = RND(512); int CircY = RND(512);
        Figure myCirc = new Circ(RND(50) + 20, CircX, CircY);
        myCirc.setRGB(RND(255), RND(255), RND(255));
        F_ARR.add(myCirc);
    }

    private void addRect(GraphicsContext gc, ArrayList<Figure> F_ARR) {
        int RectX = RND(512); int RectY = RND(512);
        Figure myRect = new Rect(RND(100) + 20, RND(100) + 20, RectX, RectY);
        myRect.setRGB(RND(255), RND(255), RND(255));
        F_ARR.add(myRect);
    }

    private void DRAW_MAIN(GraphicsContext gc, ArrayList<Figure> F_ARR) {
        int X; int Y; int R;
        for(int i = 0; i < F_ARR.size(); i++) {
            X = F_ARR.get(i).getCoords()[0];
            Y = F_ARR.get(i).getCoords()[1];
            Color c = Color.rgb(F_ARR.get(i).getRGB()[0], F_ARR.get(i).getRGB()[1], F_ARR.get(i).getRGB()[2]);
            gc.setFill(c);
            if (F_ARR.get(i) instanceof Rect) {
                gc.fillRect(X, Y, F_ARR.get(i).getData()[0], F_ARR.get(i).getData()[1]);
                gc.strokeRect(X, Y, F_ARR.get(i).getData()[0], F_ARR.get(i).getData()[1]);
                gc.fillText("" + i, (double)X, (double)Y);
            }
            if (F_ARR.get(i) instanceof Circ) {
                R = F_ARR.get(i).getData()[0] * 2;
                gc.fillOval(X, Y, R, R);
                gc.strokeOval(X, Y, R, R);
                gc.fillText("" + i, (double)X, (double)Y);
            }
        }
    }

    private int RND(int i) {
        return (int)(Math.random() * i);
    }
}
