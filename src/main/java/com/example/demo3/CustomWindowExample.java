package com.example.demo3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CustomWindowExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        // Create a rectangle with a top border of 50 pixels
        Rectangle borderBox = new Rectangle(400, 200); // Set your desired width and height
        borderBox.setFill(Color.TRANSPARENT); // Transparent fill
        borderBox.setStroke(Color.BLUEVIOLET); // Border color
        borderBox.setStrokeWidth(10); // Border width on top

        root.getChildren().add(borderBox);

        Scene scene = new Scene(root, 400, 400); // Set your desired scene dimensions
        primaryStage.setTitle("Border Box Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}