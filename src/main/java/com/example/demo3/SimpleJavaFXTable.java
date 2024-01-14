package com.example.demo3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SimpleJavaFXTable extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a TableView
        TableView<String> table = new TableView<>();

        // Create a single TableColumn
        TableColumn<String, String> column = new TableColumn<>("Data");

        // Add the TableColumn to the TableView
        table.getColumns().add(column);

        // Add data to the TableColumn
        table.getItems().addAll("Row 1", "Row 2", "Row 3");

        // Create a layout and add the table to it
        StackPane root = new StackPane();
        root.getChildren().add(table);

        // Create a scene and set it in the stage
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);

        // Set the title of the stage
        primaryStage.setTitle("Simple JavaFX Table Example");

        // Show the stage
        primaryStage.show();
    }
}