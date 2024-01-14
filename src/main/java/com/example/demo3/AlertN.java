package com.example.demo3;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AlertN extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button button=new Button("click");
        Group g=new Group(button);
        Scene scene=new Scene(g,400,500);
        primaryStage.setScene(scene);
        primaryStage.show();
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Alert al = new Alert(Alert.AlertType.CONFIRMATION);
                al.setTitle("Massage");
                al.setHeaderText(null);
                al.setContentText("Insert A Key");
                al.showAndWait();
                String  a=al.toString();
                int alt=al.hashCode();
                String alr= String.valueOf(al.hashCode());
                System.out.println(a);
                System.out.println(alt);
                System.out.println(alr);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
