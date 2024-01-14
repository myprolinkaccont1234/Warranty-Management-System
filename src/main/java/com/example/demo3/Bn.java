package com.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Bn extends Application{
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    static String user_name;

    static int id;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxm = new FXMLLoader(Bn.class.getResource("Bn.fxml"));
        Scene scene = new Scene(fxm.load(), 600, 372);
        stage.setTitle("Login");
        stage.initStyle(StageStyle.UTILITY);
        Button close=new Button("X");
        close.setOnAction(event ->stage.close());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    protected void login() {
        Connection con = new DBConet().DBConet();
        Stage loginStage = (Stage) username.getScene().getWindow();

        try {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                System.out.println("empty");
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Invalid message");
                al.setHeaderText(null);
                al.setContentText("Username or Password Cannot be Empty");
                al.showAndWait();
            } else {
                PreparedStatement ps = con.prepareStatement("select * from users where username=?");
                ps.setString(1, username.getText());
                ResultSet re = ps.executeQuery();

                if (re.next()) {
                    String user = re.getString("username");
                    String pass = re.getString("password");
                    String ed_date = re.getString("End_date");

                    if (password.getText().equals(pass)) {
                        user_name = username.getText();
                        String state = re.getString("state");
                        id=re.getInt("us_id");

                        if (user_name.equals("Admin") || user_name.equals("admin")) {
                            Userm un = new Userm();
                            un.start(new Stage());
                        } else if (state.equals("trail")) {
                            LocalDate ed = LocalDate.parse(ed_date);
                            long rem = ChronoUnit.DAYS.between(LocalDate.now(), ed);

                          boolean u_confirm = show_con("Information","You Are Using Trial version And Remaining " + rem + " Days \n\n Do You Want to Buy Now");

                            if (u_confirm) {
                                    Activation A = new Activation();
                                    A.start(new Stage());
                                    loginStage.close();
                            } else {
                                Cal c = new Cal();
                                c.start(new Stage());
                                loginStage.close();

                            }
                        } else if (state.equals("expired")) {
                            boolean confirm = show_con("Information", "Your Trial period has ended \n\n Do You Want to Buy Now");

                            if (confirm) {
                                Activation A = new Activation();
                                A.start(new Stage());
                            } else {
                                Alert al = new Alert(Alert.AlertType.INFORMATION);
                                al.setTitle("Information");
                                al.setHeaderText(null);
                                al.setContentText("You have to buy to Continue using the Application \n\n Thank You.");
                                al.showAndWait();
                            }
                        } else if (state.equals("active") && (!user_name.equals("Admin") || !user_name.equals("admin"))) {
                            Cal c = new Cal();
                            c.start(new Stage());
                        }
                    } else {
                        // Password is incorrect
                        Alert alt = new Alert(Alert.AlertType.ERROR);
                        alt.setTitle("Invalid message");
                        alt.setHeaderText(null);
                        alt.setContentText("Username or Password is Invalid. Please try again.");
                        alt.showAndWait();
                    }
                } else {
                    // Username not found in the database
                    Alert alt = new Alert(Alert.AlertType.ERROR);
                    alt.setTitle("Invalid message");
                    alt.setHeaderText(null);
                    alt.setContentText("Username not found. Please check your username.");
                    alt.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error");
            al.setHeaderText(null);
            al.setContentText("An error occurred: " + e.getMessage());
            al.showAndWait();
        }
    }
    @FXML
    protected void clear() {
        username.setText("");
        password.setText("");
    }
    public  boolean show_con(String title,String content){
        Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setTitle(title);
        al.setHeaderText(null);
        al.setContentText(content);
        al.showAndWait();
        ButtonType yes=new ButtonType("Ok");
        ButtonType no=new ButtonType("Cancel");
        al.getButtonTypes().setAll(yes,no);
        Optional<ButtonType> re=al.showAndWait();

        if (re.isPresent() && re.get()==yes){
            return true;
        }
        else {
            return false;
        }
    }


    public static void main(String[] args) {
        launch();
    }

    public void fog_password() throws Exception {
        Fog_pass fg=new Fog_pass();
        fg.start(new Stage());
    }
}
