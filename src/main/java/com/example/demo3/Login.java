package com.example.demo3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws Exception {
       /* Connection con = new DBConet().DBConet();
        PreparedStatement ps=con.prepareStatement("select * from users where username=? and password=?");
        ps.setString(1,"admin");
        ps.setString(2,"adm1");
        Statement s=con.createStatement();
        ResultSet rs=ps.executeQuery();*/
        GridPane g=new GridPane();


        Label la1 = new Label("UserName :");
        la1.setStyle("-fx-font: normal bold 15px 'Calibri' ");

        TextField te1 = new TextField();
        Label la2 = new Label("password :");
        la2.setStyle("-fx-font: normal bold 15px 'Calibri' ");
        TextField te2 = new TextField();
        Button bu1 = new Button("Login");
        Button bu2 = new Button("Clear");
        Rectangle borderBox = new Rectangle(500, 150); // Set your desired width and height
        borderBox.setFill(Color.TRANSPARENT); // Transparent fill
        borderBox.setStroke(Color.BLUEVIOLET); // Border color
        borderBox.setStrokeWidth(200);
        g.add(borderBox,0,0,10,5);
        borderBox.setFill(Color.CADETBLUE);
        bu1.toFront();
        bu2.toFront();
        g.setMinSize(800, 200);
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setVgap(5);
        g.setHgap(5);
        g.add(la1,3,3);
        g.add(te1,4,3);
        g.add(la2,3,4);
        g.add(te2,4,4);
       g.add(bu2,3,5,0,4);
        g.add(bu1,4,5);
        Scene se=new Scene(g,600,400);
        stage.setScene(se);
        stage.show();
        stage.setMaxWidth(500);
        stage.setMaxHeight(200);
        stage.setTitle("Login form");


    }

    public static void main(String[] args) {
        launch();
    }
}
