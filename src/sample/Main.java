package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    final Delta dragDelta = new Delta();
    final BooleanProperty inDrag = new SimpleBooleanProperty(false);
    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragDelta.x = primaryStage.getX() - event.getScreenX();
                dragDelta.y = primaryStage.getY() - event.getScreenY();
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + dragDelta.x);
                primaryStage.setY(event.getScreenY() + dragDelta.y);
                primaryStage.getWidth();
                primaryStage.getHeight();
                primaryStage.getX();
                primaryStage.getY();
                inDrag.set(true);

            }
        });
        primaryStage.setTitle("Shutown me");
        Platform.setImplicitExit(false);
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);

        });
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 556, 500));
        primaryStage.show();
    }

    private static class Delta {
        double x, y;
    }

}
