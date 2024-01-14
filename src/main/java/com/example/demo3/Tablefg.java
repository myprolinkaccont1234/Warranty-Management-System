package com.example.demo3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Tablefg extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a TableView
        TableView<Person> tableView = new TableView<>();

        // Create columns for the table
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");

        // Set cell value factories for the columns
       // firstNameCol.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
       // lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
       // ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());

        // Add columns to the table
        tableView.getColumns().addAll(firstNameCol, lastNameCol, ageCol);

        // Add sample data to the table
        tableView.getItems().addAll(
                new Person("John", "Doe", 30),
                new Person("Jane", "Smith", 25),
                new Person("Bob", "Johnson", 40)
        );

        // Create a VBox to hold the table
        VBox vbox = new VBox(tableView);

        // Create a scene and set it on the stage
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);

        // Set the stage title
        primaryStage.setTitle("Beautiful Table Example");

        // Show the stage
        primaryStage.show();
    }
  class Person {
        private final String firstName;
        private final String lastName;
        private final int age;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}