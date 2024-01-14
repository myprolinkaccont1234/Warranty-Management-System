package com.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Fog_pass extends Application {

    @FXML
    private Label fog_enp;

    @FXML
    private Label fog_erp;

    @FXML
    private PasswordField fog_np;

    @FXML
    private PasswordField fog_rp;

    @FXML
    private TextField fog_user;

    Connection con = new DBConet().DBConet();

    @FXML
    void fog_clear() {
        fog_user.setText("");
        fog_np.clear();
        fog_rp.clear();
        fog_erp.setText("");
        fog_enp.setText("");
    }

    @FXML
    void fog_update() {
        try {
            PreparedStatement ps=con.prepareStatement("update users set password=? where username =?");
            ps.setString(1,"");
            ps.setString(2,"");
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxm = new FXMLLoader(Fog_pass.class.getResource("fog_pass.fxml"));
        Scene scene = new Scene(fxm.load(), 600, 303);
        stage.setTitle("Password Change");
        stage.initStyle(StageStyle.UTILITY);
        Button close=new Button("X");
        close.setOnAction(event ->stage.close());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
