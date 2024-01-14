package com.example.demo3;

import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ActiP  extends Application {
    @FXML
    private Text Akey;

    @FXML
    private AnchorPane act;

    @FXML
    private Text days;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(Activation.class.getResource("Acti_p.fxml"));
        Scene scene = new Scene(fxml.load(), 457, 150);
        stage.initStyle(StageStyle.UTILITY);
        Button close=new Button("X");
        close.setOnAction(event ->stage.close());
        stage.setMaximized(false);
        stage.setTitle("Activation");
        stage.setScene(scene);
        stage.show();


    }

@FXML
    public void active_r() {
            Connection con = new DBConet().DBConet();
            PreparedStatement psd = null;
            try {
                psd = con.prepareStatement("select * from users where username=?");
                psd.setString(1, Bn.user_name);
                ResultSet rt = psd.executeQuery();
                while (rt.next()) {
                    String key = rt.getString("A_key");
                    Akey.setText(key);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public static void main(String[] args) {
        launch();
    }

}
