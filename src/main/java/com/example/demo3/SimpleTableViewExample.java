package com.example.demo3;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleTableViewExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a TableView
        TableView<Person> table = new TableView<>();

        // Create columns for the table
        TableColumn<Person, String> nameColumn = new TableColumn<>("Name");
       // nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Person, Integer> ageColumn = new TableColumn<>("Age");
        //ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());

        // Add columns to the table
        table.getColumns().addAll(nameColumn, ageColumn);

        // Add data to the table
        table.getItems().addAll(
                new Person("John", 30),
                new Person("Alice", 25),
                new Person("Bob", 35)
        );

        // Create a layout and add the table to it
        VBox vbox = new VBox(table);

        // Create a scene and set it in the stage
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);

        // Set the title of the stage
        primaryStage.setTitle("Simple Table View Example");

        // Show the stage
        primaryStage.show();
    }

    public static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}