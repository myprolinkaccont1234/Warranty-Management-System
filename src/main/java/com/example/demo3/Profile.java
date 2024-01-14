package com.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Profile extends Application {
    @FXML
    private Label cus_name;

    @FXML
    private Label licence_no;

    @FXML
    private Label mobile_num;

    @FXML
    private Label rem_date;

    @FXML
    private Label stst;

    @FXML
    private Label trail;

    public Profile() {

    }

    public Profile(String user, String aKey) {
        cus_name.setText(user);
        licence_no.setText(aKey);
    }


    @FXML
    void change_password(MouseEvent event) {

    }

    @FXML
    void remove_account(MouseEvent event) {

    }

    @FXML
    void update_cus_detail(MouseEvent event) {

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxm = new FXMLLoader(Profile.class.getResource("Profile.fxml"));
        Scene scene = new Scene(fxm.load(), 600, 372);
        stage.setTitle("Profile");
        stage.initStyle(StageStyle.UTILITY);
        Button close=new Button("X");
        close.setOnAction(event ->stage.close());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}
