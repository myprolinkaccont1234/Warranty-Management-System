package com.example.demo3;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Stackpane extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        StackPane stackPane = new StackPane();
        Text t=new Text("This");
        TextField tx=new TextField();
        stackPane.setMargin(tx, new Insets(50, 150, 200, 50));
        stackPane.setMargin(t, new Insets(100, 150, 50, 20));
        //tx.set
        tx.setMaxSize(100,50);
        ObservableList list = stackPane.getChildren();
        list.addAll(t,tx);
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }

}
