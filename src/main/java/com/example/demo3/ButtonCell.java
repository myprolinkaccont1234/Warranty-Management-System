package com.example.demo3;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class ButtonCell extends javafx.scene.control.TableCell<javafx.scene.control.TableColumn<Chkbal, Void>, javafx.scene.control.TableCell<Chkbal, Void>> {
    private final Button editButton = new Button("Edit");

    ButtonCell() {
        editButton.setOnAction(event -> {
           TableColumn<Chkbal, Void> item = getTableView().getItems().get(getIndex());

        });
}
}
