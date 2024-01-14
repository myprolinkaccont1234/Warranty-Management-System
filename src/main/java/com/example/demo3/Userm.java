package com.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Userm extends Application {

    @FXML
    BorderPane bp;
    @FXML
    AnchorPane ap;
    @FXML
    private Label erl;

    @FXML
    private TextField lic_text;

    @FXML
    private TextField name;

    @FXML
    private PasswordField pw;

    @FXML
    private PasswordField re_pw;

    @FXML
    private Label lbp;

    @FXML
    private Label lbpr;

    @FXML
    private Label lbu;

    @FXML
    private TextField R_lic;

    @FXML
    private TextField R_uer;

    static String passw;


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(Userm.class.getResource("User.fxml"));
        Scene scene = new Scene(fxml.load(), 1200, 500);
        stage.setResizable(false);
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    @FXML
    private void buttoni() {
        bp.setCenter(ap);

    }

    @FXML
    private void buttonii() {

        loadpage("page_iii");
    }

    private void loadpage(String s) {
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(s + ".fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bp.setCenter(root);
    }


    public void add_user() {
        if (name.getText().isEmpty() && pw.getText().isEmpty() && re_pw.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Massage");
            al.setHeaderText(null);
            al.setContentText("Fill At least One Field");
            al.showAndWait();
        }
        if (name.getText().isEmpty()) {
            lbu.setText("Can't be Empty");

        } else if (!name.getText().isEmpty()) {
            lbu.setText("");

        }
        if (pw.getText().isEmpty()) {
            lbp.setText("Password is Required");
        } else if (!pw.getText().isEmpty()) {
            lbp.setText("");

        }
        if (!pw.getText().isEmpty() && !re_pw.getText().isEmpty()) {
            if (!pw.getText().equals(re_pw.getText())) {
                lbpr.setText("password Doesn't Match");

            } else {
                passw = re_pw.getText();

            }
        }
        if (pw.getText().equals(passw) && !name.getText().isEmpty()) {

            Connection con = new DBConet().DBConet();
            String user_n = name.getText();
            String a_date = String.valueOf(LocalDate.now());
            LocalDate cur = LocalDate.now();
            LocalDate future_date = cur.plus(40, ChronoUnit.DAYS);
            String future = String.valueOf(future_date);
            try {
                PreparedStatement ps = con.prepareStatement("insert into users(username,password,state,A_key,Act_date,End_date) values (?,?,?,?,?,?)");
                ps.setString(1, user_n);
                ps.setString(2, passw);
                ps.setString(3, "trail");
                ps.setString(4, "");
                ps.setString(5, a_date);
                ps.setString(6, future);
                ps.executeUpdate();


                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Massage");
                al.setHeaderText(null);
                al.setContentText("Successfully Create User");
                al.showAndWait();
                clear();


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


    @FXML
    void clear() {
        lbpr.setText("");
        lbp.setText("");
        lbu.setText("");
        name.setText("");
        pw.setText("");
        re_pw.setText("");
    }

    public void remove() {
        Connection con = new DBConet().DBConet();
        if (R_uer.getText().isEmpty()) {
            if(R_lic.getText().isEmpty()) {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Information");
                al.setHeaderText(null);
                al.setContentText("Fill At least One field to Continue");
                al.showAndWait();
            }
        } else if(!R_uer.getText().isEmpty() || !R_lic.getText().isEmpty()) {
            String baseQuery = "delete from users where";
            List<String> conditions = new ArrayList<>();
            List<Object> parameters = new ArrayList<>();
            if (!R_uer.getText().isEmpty()) {
                conditions.add("username = ?");
                parameters.add(R_uer.getText());
            }

// Check if the user filled the warranty_start_date field
            if (!R_lic.getText().isEmpty()) {
                conditions.add("A_key = ?");
                parameters.add(R_lic.getText());

            }

// Check if the user filled the customer_name field

// Combine the conditions with 'OR' and construct the final query
            if (!conditions.isEmpty()) {
                String finalQuery = baseQuery + " " + String.join(" OR ", conditions);

                try {
                    PreparedStatement preparedStatement = con.prepareStatement(finalQuery);
                    for (int i = 0; i < parameters.size(); i++) {
                        preparedStatement.setObject(i + 1, parameters.get(i));
                    }
                    int a=preparedStatement.executeUpdate();
                    Alert al;
                    if (a==1){
                        al = new Alert(Alert.AlertType.INFORMATION);
                        al.setTitle("Information");
                        al.setHeaderText(null);
                        al.setContentText("User Removed Successfully");
                    }else {
                        al = new Alert(Alert.AlertType.ERROR);
                        al.setTitle("Information");
                        al.setHeaderText(null);
                        al.setContentText("User Not Found or Username Invalid");
                    }
                    al.showAndWait() ;
                    r_clear();

                } catch (SQLException e) {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setTitle("Information");
                    al.setHeaderText(null);
                    al.setContentText(String.valueOf(e));
                    al.showAndWait();
                }
            }

        }
    }
    @FXML
    void add_lic() {
        int len=lic_text.getText().length();
        if(lic_text.getText().isEmpty()){
            System.out.println("rty");
            erl.setText("Can't be Empty");
        } else if (len<21) {
            System.out.println("hhgjhj");
            erl.setText("Must Have At least 20 Characters");
        }
        else {
            Connection con = new DBConet().DBConet();
            String lic = lic_text.getText();
            try {
                PreparedStatement ps = con.prepareStatement("insert into active_key(A_key,state,Act_date,end_date) values (?,?,?,?)");
                ps.setString(1, lic);
                ps.setString(2, "");
                ps.setString(3, "");
                ps.setString(4, "");
                ps.executeUpdate();
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Massage");
                al.setHeaderText(null);
                al.setContentText("Successfully Add A New Licence Key");
                al.showAndWait();
                lic_text.setText("");
            } catch (SQLException e) {
                Alert al = new Alert(Alert.AlertType.INFORMATION);
                al.setTitle("Massage");
                al.setHeaderText(null);
                al.setContentText(String.valueOf(e));
                al.showAndWait();
            }
        }


    }

    public void r_clear() {
        R_lic.setText("");
        R_uer.setText("");
    }


}
