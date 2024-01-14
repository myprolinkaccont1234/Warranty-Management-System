package com.example.demo3;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class Modify_data extends Application {
    int ordid;
    @FXML
    private TextField settle_amount;

    @FXML
    private DatePicker settle_date;

    @FXML
    private Label settle_e_lab;

    Connection con = new DBConet().DBConet();

    public Modify_data() {
    }

    public Modify_data(int ordid) {
        this.ordid = ordid;
    }

    @FXML
    void delete_order() throws Exception {
        boolean confirm = show_con("Information", "Do You Want to Detete The Current Order");

        if (confirm) {
            try {
                PreparedStatement ps = con.prepareStatement("delete from settle where ord_id=?");
                ps.setInt(1, 2);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{

        }
    }

    @FXML
    void modify_clear() {
        settle_amount.setText("");
    }


    @FXML
    void getDate() {
        settle_date.setValue(LocalDate.now());

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


    @FXML
    void modify_update() {
        if (settle_amount.getText().isEmpty() && settle_date.getValue() == null) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Massage");
            al.setHeaderText(null);
            al.setContentText("Nothing Modified");
            al.showAndWait();
        }
        System.out.println("1");
        if (!settle_amount.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
            settle_e_lab.setText("Numbers Only");
        } else {
            System.out.println("2");
            try {
                int as = Integer.parseInt(settle_amount.getText());
                PreparedStatement pd = con.prepareStatement("select rem_tot from remain where ord_id=?");
                pd.setInt(1, ordid);
                ResultSet r = pd.executeQuery();
                int remr = 0;
                if (r.next()) {
                    remr = r.getInt("rem_tot");
                }
                System.out.println("3");
                PreparedStatement df = con.prepareStatement("select * from settle where ord_id=? ");
                df.setInt(1, ordid);
                ResultSet d = df.executeQuery();
                String s;
                int mobv;
                System.out.println("10");
                while (d.next()) {
                    System.out.println("9");
                    s = d.getString("customer");
                    mobv = d.getInt("mob_num");
                    System.out.println("4");
                    int er = remr - as;
                    if (er <= 0) {
                        System.out.println("6");
                        Alert al = new Alert(Alert.AlertType.INFORMATION);
                        al.setTitle("Massage");
                        al.setHeaderText(null);
                        al.setContentText("No Any Balance Remaining Check whether You are Settling Correct Order.\n Thnak You ");
                        al.showAndWait();
                    } else {
                        System.out.println("5");
                        PreparedStatement ps = con.prepareStatement("insert into settle(ord_id,mob_num,customer,settle_date,settle_Amount) values(?,?,?,?,?)");
                        ps.setInt(1, ordid);
                        ps.setString(2, s);
                        ps.setInt(3, mobv);
                        ps.setString(4, String.valueOf(settle_date.getValue()));
                        ps.setInt(5, Integer.parseInt(settle_amount.getText()));
                        ps.executeUpdate();
                        PreparedStatement sd = con.prepareStatement("update remain set rem_tot=? where ord_id=?");
                        sd.setInt(1, er);
                        sd.setInt(2, ordid);
                        sd.executeUpdate();
                        Alert al = new Alert(Alert.AlertType.INFORMATION);
                        al.setTitle("Massage");
                        al.setHeaderText(null);
                        al.setContentText("Amount of Rs."+settle_amount.getText()+".00 Settled Successfully");
                        al.showAndWait();
                    }
                }
                } catch(SQLException e){
                    throw new RuntimeException(e);
                }

            }
        }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxm = new FXMLLoader(Modify_data.class.getResource("Modify_data.fxml"));
        Scene scene = new Scene(fxm.load(), 577, 312);
        stage.initStyle(StageStyle.UTILITY);
        Button close = new Button("X");
        close.setOnAction(event -> stage.close());
        stage.setTitle("Modify Data");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}


