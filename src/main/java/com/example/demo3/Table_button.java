package com.example.demo3;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Table_button extends Application {
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
    private TextArea man_desc;

    @FXML
    private DatePicker man_order;

    @FXML
    private TextField man_phone;

    @FXML
    private TextField man_settle;

    @FXML
    private TextField man_tot;

    @FXML
    private TextField rem_cus_name;

    @FXML
    private TextField rem_cus_phone;

    @FXML
    private TextField up_cus_cur_phone;

    @FXML
    private TextField up_cus_new_phone;

    @FXML
    private Label cus_e_n;

    @FXML
    private Label cus_ep;

    @FXML
    TableView<Chkbal> tv_tab;//=new TableView<Chkbal>();

    @FXML
    TableColumn<Chkbal, Integer> ca;//=new TableColumn<>("Col_A");
    @FXML
    TableColumn<Chkbal, Integer> cb;//=new TableColumn<>("Col_b");
    @FXML
    TableColumn<Chkbal, String> cc;//=new TableColumn<>("Col_c");
    @FXML
    TableColumn<Chkbal, String> cd;//=new TableColumn<>("Col_d");
    @FXML
    TableColumn<Chkbal, Integer> ce;//=new TableColumn<>("Col_e");
    @FXML
    TableColumn<Chkbal, Integer> cf;//=new TableColumn<>("Col_f");
    @FXML
    TableColumn<Chkbal, Chkbal> cg;//=new TableColumn<>("Action");

    @FXML
    private TableColumn<Chkbal, String> ch;


    static  int id;

    Connection con = new DBConet().DBConet();



    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmls = new FXMLLoader(Table_button.class.getResource("Chk_Balance.fxml"));
        Scene scene = new Scene(fxmls.load(), 1300, 500);
        primaryStage.setResizable(false);
        // primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    @FXML
   void remove_cus(){

    }

    @FXML
    void update_cus(){

    }

    @FXML
    void rem_clear(){

    }
    @FXML
    void update_clear(){

    }
    @FXML
    void add_bal() {

        try {
            int total = 0;
            PreparedStatement pas= con.prepareStatement("Select ord_id from orderdetail where mob_num=?");
            pas.setInt(1, Integer.parseInt(chk_phone.getText()));
            ResultSet rs= pas.executeQuery();
            while (rs.next()){
                total=rs.getInt("total");

            }
            id=rs.getInt("ord_id");
            PreparedStatement ps= con.prepareStatement("insert into settle(ord_id,mob_num,customer,settle_date,settle_Amount) values(?,?,?,?,?)");
            ps.setInt(1,id);
          //  ps.setInt(2,b);
          //  ps.setString(3,a);
          //  ps.setString(4,c);
          //  ps.setDouble(5,d);
          //  ps.executeUpdate();
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
     /*   add_cus.setText("");
        add_phone.setText("");
        add_e_cus.setText("");
        add_e_phone.setText("");
        add_e_settle.setText("");*/

    }

    @FXML
    void chk_chk() throws SQLException {
        tv_tab.setVisible(false);
        if (chk_cus_name.getText().isEmpty() && chk_phone.getText().isEmpty()) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Massage");
            al.setHeaderText(null);
            al.setContentText("Fill At least One Field to Complete Operation");
            al.showAndWait();
            tv_tab.setVisible(false);
        }
        if (!chk_phone.getText().isBlank()) {
            if (!chk_phone.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                cus_ep.setText("Numbers only");
                tv_tab.setVisible(false);
            }
            if (chk_phone.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                cus_ep.setText("");
                tv_tab.setVisible(false);
            }
        }
        if (!chk_cus_name.getText().isEmpty()) {
            if (chk_cus_name.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                cus_e_n.setText("Alphabetic characters only");
                tv_tab.setVisible(false);
            }
            if (!chk_cus_name.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                cus_e_n.setText("");
                tv_tab.setVisible(false);
            }
        }
        if (!chk_cus_name.getText().isEmpty() || !chk_phone.getText().isBlank()) {
           // System.out.println("in function");
            tv_tab.setVisible(true);
            Connection con = new DBConet().DBConet();
            String baseQuery = "select distinct o.ord_id,o.customer,o.mob_num,o.order_date,o.ord_detail,o.total,rem_tot,settle_date,settle_Amount from settle s left join orderdetail o on o.ord_id=s.ord_id left join remain r on s.ord_id=r.ord_id where ";
            List<String> conditions = new ArrayList<>();
            List<Object> parameters = new ArrayList<>();
            if (!chk_phone.getText().isEmpty()) {
                conditions.add("o.mob_num = ?");
                parameters.add(chk_phone.getText());
            }

// Check if the user filled the warranty_start_date field
            if (!chk_cus_name.getText().isEmpty()) {
                conditions.add("o.customer = ?");
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
                    ObservableList<Detail> list = FXCollections.observableArrayList();

                        while (re.next()) {
                            ca.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCa()).asObject());
                            ch.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCh()));
                            cb.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCb()).asObject());
                            cc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCc()));
                            cd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCd()));
                            ce.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCe()).asObject());
                            cf.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCf()).asObject());
                            cg.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue()));
                            new TableRow<>();
                            cg.setCellFactory(new Callback<>() {
                                @Override
                                public TableCell<Chkbal, Chkbal> call(TableColumn<Chkbal, Chkbal> chkbalChkbalTableColumn) {
                                    return new TableCell<>() {
                                        final Button btn = new Button("modify");

                                        {
                                            btn.setStyle("-fx-background-color: green; -fx-background-radius: 15px;-fx-alignment:center");
                                            btn.setOnAction(event -> {
                                                Chkbal chkbal = getTableView().getItems().get(getIndex());
                                                chkbal.toString();
                                                try {
                                                    chkbal.loader();
                                                } catch (Exception e) {
                                                    throw new RuntimeException(e);
                                                }
                                            });
                                        }

                                        @Override
                                        protected void updateItem(Chkbal item, boolean empty) {
                                            super.updateItem(item, empty);
                                            if (item == null || empty) {

                                                setGraphic(null);
                                            } else {
                                                setGraphic(btn);
                                            }

                                        }
                                    };
                                }
                            });
                            // tv_tab.getColumns().addAll(ca,cb,cc,cd,cf,ce,cg);
                            int settle_A = 0;
                            settle_A = re.getInt("settle_Amount") + settle_A;
                            int rem = re.getInt("total") - settle_A;
                            ObservableList<Chkbal> list3 = null;
                            list3 = FXCollections.observableArrayList(
                                    new Chkbal(re.getInt("ord_id"), re.getString("order_date"), re.getInt("total"), re.getString("ord_detail"), re.getString("settle_date"), re.getInt("settle_Amount"), rem)//rt.getString("settle_date"),rt.getInt("settle_Amount"),rem);
                            );
                            tv_tab.setItems(list3);
                        }


                    } catch (SQLException e) {
                        Alert al = new Alert(Alert.AlertType.ERROR);
                        al.setTitle("Warranty Management System");
                        al.setHeaderText(null);
                        al.setContentText(String.valueOf(e));
                        al.showAndWait();
                    }

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
            PreparedStatement pa=con.prepareStatement("insert into orderdetail(customer,mob_num,order_date,ord_detail,total,settle) values (?,?,?,?,?,?)");
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
    public static void main(String[] args) {

        launch(args);
    }
}
