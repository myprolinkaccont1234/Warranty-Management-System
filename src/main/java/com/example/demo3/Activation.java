package com.example.demo3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;
import java.time.LocalDate;


public class Activation  extends Application {

    @FXML
    private TextField key;
    static String user = Bn.user_name;

    @FXML
    void active() throws Exception {
        Connection con = new DBConet().DBConet();
        if (key.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Message");
            al.setHeaderText(null);
            al.setContentText("Insert A Key");
            al.showAndWait();
        } else {
            String s = key.getText();
            boolean keyValid = false; // Add a flag to track key validity
            try {
                PreparedStatement psd = con.prepareStatement("select * from active_key where A_key=?");
                psd.setString(1, s);
                ResultSet re = psd.executeQuery();
                while (re.next()) {
                    String key = re.getString("A_key");
                    String st = re.getString("state");
                    if (!s.equals(key)) {
                        keyValid = false; // Key is not valid
                    } else if (st.equals("used")) {
                        keyValid = false; // Key is already used
                    } else {
                        LocalDate endDate = LocalDate.now().plusMonths(12);
                        String end_date = String.valueOf(endDate);

                        PreparedStatement pre = con.prepareStatement("update users set state=?,A_key =?,Act_date=?,End_date=? where username=?");
                        pre.setString(1, "active");
                        pre.setString(2, s);
                        pre.setString(3, String.valueOf(LocalDate.now()));
                        pre.setString(4, end_date);
                        pre.setString(5, user);
                        pre.executeUpdate();

                        PreparedStatement prt = con.prepareStatement("update active_key set state=? where A_key=? ");
                        prt.setString(1, "used");
                        prt.setString(2, s);
                        prt.executeUpdate();

                        keyValid = true; // Key is valid
                    }
                }
                if (!keyValid) { // Display error alert if key is not valid
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setTitle("Message");
                    al.setHeaderText(null);
                    al.setContentText("Key is Invalid or Already Used");
                    al.showAndWait();
                } else {
                    Alert al = new Alert(Alert.AlertType.INFORMATION);
                    al.setTitle("Message");
                    al.setHeaderText(null);
                    al.setContentText("Thank You for Purchasing\n You can close the Application And Open Again.");
                    al.showAndWait();
                    Stage loginStage = (Stage) key.getScene().getWindow();
                    Cal c = new Cal();
                    c.start(new Stage());
                    loginStage.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void clear() {
        key.setText("");
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxml = new FXMLLoader(Activation.class.getResource("Activation.fxml"));
        Scene scene = new Scene(fxml.load(), 466, 166);
        stage.initStyle(StageStyle.UTILITY);
        Button close = new Button("X");
        close.setOnAction(event -> stage.close());
        stage.setTitle("Activation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
