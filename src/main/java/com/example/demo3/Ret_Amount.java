package com.example.demo3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Ret_Amount extends Application {
    @FXML
    private TextField add_cus;

    @FXML
    private DatePicker add_date;

    @FXML
    private Label add_e_cus;

    @FXML
    private Label add_e_phone;

    @FXML
    private Label add_e_settle;

    @FXML
    private TextField add_phone;

    @FXML
    private TextField add_settle;

    @FXML
    private TextField chk_cus_name;

    @FXML
    private Label chk_lab_name;

    @FXML
    private Label chk_lab_phone;

    @FXML
    private TextField chk_phone;

    @FXML
    private TextField man_cus_name;

    @FXML
    private TextField man_phone;


    @FXML
    private TextArea man_desc;

    @FXML
    private DatePicker man_order;


    @FXML
    private TextField man_settle;

    @FXML
    private TextField man_tot;

    @FXML
    private TableView<Chkbal> tv_tab;

    @FXML
    private TableColumn<Chkbal, Integer> ca;

    @FXML
    private TableColumn<Chkbal, Integer> cb;

    @FXML
    private TableColumn<Chkbal, String> cc;

    @FXML
    private TableColumn<Chkbal, String> cd;

    @FXML
    private TableColumn<Chkbal, Integer> ce;
    @FXML
    private TableColumn<Chkbal, Integer> cf;

static  int id;

    Connection con = new DBConet().DBConet();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
      //  FXMLLoader fxmls = new FXMLLoader(Ret_Amount.class.getResource("Chk_Balance.fxml"));
       // Scene scene = new Scene(1300, 500);
      //  primaryStage.setResizable(false);
       // primaryStage.setTitle("Dashboard");
      //  primaryStage.setScene(scene);
        primaryStage.show();
    }
    @FXML
    void add_bal() {
        String a= add_cus.getText();
        int b= Integer.parseInt(add_phone.getText());
        String c= String.valueOf(add_date.getValue());
        int d= Integer.parseInt(add_settle.getText());
        try {
            int total;
            PreparedStatement pas= con.prepareStatement("Select ord_id from orderdetail where mob_num=?");
            pas.setInt(1,b);
            ResultSet rs= pas.executeQuery();
            total=rs.getInt("total");
            id=rs.getInt("ord_id");
            PreparedStatement ps= con.prepareStatement("insert into settle(ord_id,mob_num,customer,settle_date,settle_Amount) values(?,?,?,?,?)");
            ps.setInt(1,id);
            ps.setInt(2,b);
            ps.setString(3,a);
            ps.setString(4,c);
            ps.setDouble(5,d);
            ps.executeUpdate();
            PreparedStatement pm=con.prepareStatement("select sum(settle_Amount) from settle where ord_id=?");
            pm.setInt(1,id);
            ResultSet rd=pm.executeQuery();
            int sum=rd.getInt(1);
            int rem_tot=total-sum;
            PreparedStatement pd=con.prepareStatement("update remain set rem_tot=? where ord_id=?");
            pd.setInt(1,rem_tot);
            pd.setInt(2,id);
            pd.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void add_clear() {
        add_cus.setText("");
        add_phone.setText("");
        add_e_cus.setText("");
        add_e_phone.setText("");
        add_e_settle.setText("");

    }

    @FXML
    void chk_chk() {
        Alert er;
        int len=chk_phone.getText().length();
        if(chk_phone.getText().isEmpty()){
            if ((chk_cus_name.getText().isEmpty())){
                er = new Alert(Alert.AlertType.ERROR);
                er.setTitle("Massage");
                er.setHeaderText(null);
                er.setContentText("Fill At least One Field");
                er.showAndWait();
            }
        }
        if (!chk_phone.getText().isBlank()) {
            if (!chk_phone.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
              add_e_phone.setText("Numbers only");
            }
        }
        if (!chk_cus_name.getText().isEmpty()) {
            if (chk_cus_name.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
              add_e_cus.setText("Alphabetic characters only");
            }
        }
        else if(!chk_phone.getText().isEmpty() || !chk_cus_name.getText().isEmpty()){
            Connection con = new DBConet().DBConet();
            String baseQuery = "select * from settle where";
            List<String> conditions = new ArrayList<>();
            List<Object> parameters = new ArrayList<>();
            if (!chk_phone.getText().isEmpty()) {
                conditions.add("mob_num like ?%");
                parameters.add(chk_phone.getText());
                // System.out.println("id " + t12.getText());
            }

// Check if the user filled the warranty_start_date field
            if (!chk_cus_name.getText().isEmpty()) {
                conditions.add("customer like ?%");
                parameters.add(chk_cus_name.getText());

            }

            if (!conditions.isEmpty()) {
                String finalQuery = baseQuery + " " + String.join(" OR ", conditions);

                try {
                    PreparedStatement preparedStatement = con.prepareStatement(finalQuery);
                    for (int i = 0; i < parameters.size(); i++) {
                        preparedStatement.setObject(i + 1, parameters.get(i));
                    }
                    ResultSet re = preparedStatement.executeQuery();
                    ObservableList<Chkbal> list2 = FXCollections.observableArrayList();
                    while (re.next()) {
                        chk_lab_name.setText(re.getString("customer"));
                        chk_lab_phone.setText(re.getString("mob_num"));
                        int ord_id=re.getInt("ord_id");
                        ca.setCellValueFactory(new PropertyValueFactory<Chkbal, Integer>("ca"));
                        cb.setCellValueFactory(new PropertyValueFactory<Chkbal, Integer>("cb"));
                        cc.setCellValueFactory(new PropertyValueFactory<Chkbal, String>("cc"));
                        cd.setCellValueFactory(new PropertyValueFactory<Chkbal, String>("cd"));
                        ce.setCellValueFactory(new PropertyValueFactory<Chkbal, Integer>("ce"));
                        cf.setCellValueFactory(new PropertyValueFactory<Chkbal, Integer>("cf"));
                        PreparedStatement pn=con.prepareStatement("select * from settle where ord_id=?");
                        pn.setInt(1,ord_id);
                        ResultSet rt=pn.executeQuery();
                        while (rt.next()){
                            PreparedStatement pc= con.prepareStatement("select rem_tot from remain where ord_id=?");
                            pc.setInt(1,ord_id);
                            ResultSet rn=pc.executeQuery();
                            int rem=rn.getInt("rem_tot");

                           // list2.add(new Chkbal(re.getInt("ord_id"),re.getInt("total"),re.getString("ord_detail"),rt.getString("settle_date"),rt.getInt("settle_Amount"),rem));
                            tv_tab.setItems(list2);
                        }
                    }

                } catch (SQLException e) {
                    Alert al = new Alert(Alert.AlertType.ERROR);
                    al.setTitle("Warranty Management System");
                    al.setHeaderText(null);
                    al.setContentText(String.valueOf(e));
                    al.showAndWait();
                }
            } else {
                Alert al = new Alert(Alert.AlertType.ERROR);
                al.setTitle("Warranty Management System");
                al.setHeaderText(null);
                al.setContentText("Try again Later");
                al.showAndWait();
            }
        }


    }

    @FXML
    void chk_clear() {
            chk_cus_name.setText("");
            chk_phone.setText("");
    }

    @FXML
    void man_add() {
        try {
            String a=man_cus_name.getText();
            int b= Integer.parseInt(man_phone.getText());
            String c= String.valueOf(man_order.getValue());
            int d= Integer.parseInt(man_settle.getText());
            int e= Integer.parseInt(man_tot.getText());
            String f=man_desc.getText();
            int rem=e-d;
            PreparedStatement pa=con.prepareStatement("insert into orderdetail (customer,mob_num,order_date,ord_detail,total,settle) values (?,?,?,?,?,?)");
            pa.setString(1,a);
            pa.setInt(2,b);
            pa.setString(3,c);
            pa.setString(4,f);
            pa.setInt(5,e);
            pa.setInt(6,d);
            pa.executeUpdate();
            PreparedStatement pd= con.prepareStatement("insert into settle(ord_id,mob_num,customer,settle_date,settle_Amount) values (?,?,?,?,?)");
            pd.setInt(1,id);
            pd.setInt(2,b);
            pd.setString(3,a);
            pd.setString(4,c);
            pd.setDouble(5,d);
            pd.executeUpdate();
            PreparedStatement pf=con.prepareStatement("insert into remain(ord_id,mob_num,customer,rem_tot) values (?,?,?,?)");
            pf.setInt(1,id);
            pf.setInt(2,b);
            pf.setString(3,a);
            pf.setInt(4,rem);
            pf.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void man_clear() {
man_tot.setText("");
        man_settle.setText("");
        man_order.setValue(LocalDate.parse(""));
        man_cus_name.setText("");
        man_phone.setText("");
        man_desc.setText("");
    }
}
