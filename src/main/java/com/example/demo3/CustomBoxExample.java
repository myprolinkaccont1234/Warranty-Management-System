package com.example.demo3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CustomBoxExample extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Custom Box Example");

        // Create a StackPane as the root layout
        StackPane stackPane = new StackPane();

        // Create a VBox to hold the content
        VBox content = new VBox(10);
        content.setPadding(new Insets(20));

        // Labels, TextFields, and Buttons
        Label label1 = new Label("Label 1:");
        TextField textField1 = new TextField();
        Label label2 = new Label("Label 2:");
        TextField textField2 = new TextField();
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");

        // Set maximum and minimum sizes for the text fields
        textField1.setMinSize(200, Control.USE_PREF_SIZE);
        textField1.setMaxSize(200, Control.USE_PREF_SIZE);
        textField2.setMinSize(200, Control.USE_PREF_SIZE);
        textField2.setMaxSize(200, Control.USE_PREF_SIZE);

        // Add elements to the content VBox
        content.getChildren().addAll(label1, textField1, label2, textField2, button1, button2);

        // Create a custom shape for the box with a wider top border
        Region customShape = new Region();
        customShape.setStyle("-fx-background-color: white; -fx-border-color: black;");
        customShape.setMinSize(400, 250);
        customShape.setMaxSize(400, 250);
        customShape.setShape(new BoxWithWideTopBorder(400, 250, 120));

        // Add the custom shape and content to the StackPane
        stackPane.getChildren().addAll(customShape, content);

        Scene scene = new Scene(stackPane, 400, 250);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class BoxWithWideTopBorder extends Rectangle {
    public BoxWithWideTopBorder(double width, double height, double topBorderWidth) {
        super(width, height);
        setStroke(Color.BLACK);
        setStrokeWidth(topBorderWidth);
    }
}