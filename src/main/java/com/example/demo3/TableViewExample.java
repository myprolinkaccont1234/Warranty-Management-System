package com.example.demo3;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewExample extends Application {

    public static class Item {
        private final SimpleStringProperty name;
        private final SimpleStringProperty description;

        public Item(String name, String description) {
            this.name = new SimpleStringProperty(name);
            this.description = new SimpleStringProperty(description);
        }

        public String getName() {
            return name.get();
        }

        public String getDescription() {
            return description.get();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX TableView Example");

        TableView<Item> tableView = new TableView<>();
        ObservableList<Item> data = FXCollections.observableArrayList(
                new Item("Item 1", "Description 1"),
                new Item("Item 2", "Description 2"),
                new Item("Item 3", "Description 3")
        );

        TableColumn<Item, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().name);

        TableColumn<Item, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(cellData -> cellData.getValue().description);

        TableColumn<Item, Void> editCol = new TableColumn<>("Edit");
        editCol.setCellFactory(param -> new ButtonCell());

        tableView.getColumns().addAll(nameCol, descriptionCol, editCol);
        tableView.setItems(data);

        VBox vbox = new VBox(tableView);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class ButtonCell extends TableCell<Item, Void> {
        private final Button editButton = new Button("Edit");

        ButtonCell() {
            editButton.setOnAction(event -> {
                Item item = getTableView().getItems().get(getIndex());
                // Handle the edit action here
                System.out.println();
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setGraphic(editButton);
            } else {
                setGraphic(null);
            }
        }
    }
}