package com.example.demo3;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class R_table extends Application {






    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


         TableView<Chkbal> tv_tab=new TableView<Chkbal>();

         TableColumn<Chkbal, Integer> ca=new TableColumn<>("Col_A");

         TableColumn<Chkbal, Integer> cb=new TableColumn<>("Col_b");

        TableColumn<Chkbal, String> cc=new TableColumn<>("Col_c");

         TableColumn<Chkbal, String> cd=new TableColumn<>("Col_d");

         TableColumn<Chkbal, Integer> ce=new TableColumn<>("Col_e");

        TableColumn<Chkbal, Integer> cf=new TableColumn<>("Col_f");

         TableColumn<Chkbal, Chkbal> cg=new TableColumn<>("Action");

        Connection con = new DBConet().DBConet();
        try {
            int d=778;
            PreparedStatement pre = con.prepareStatement("select * from orderdetail where  mob_num=?");
            pre.setInt(1,d);
            ResultSet re = pre.executeQuery();

                    while (re.next()) {

                        ca.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCa()).asObject());
                        cb.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCb()).asObject());
                        cc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCc()));
                        cd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCd()));
                        ce.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCe()).asObject());
                        cf.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getCf()).asObject());
                        cg.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue()));
                        cg.setCellFactory(new Callback<>() {
                            @Override
                            public TableCell<Chkbal, Chkbal> call(TableColumn<Chkbal, Chkbal> chkbalChkbalTableColumn) {
                                return new TableCell<>() {
                                    final Button btn = new Button("modify");

                                    {
                                        btn.setOnAction(event -> {
                                            Chkbal chkbal = getTableView().getItems().get(getIndex());
                                            System.out.println(chkbal.toString());
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
                        tv_tab.getColumns().addAll(ca,cb,cc,cd,cf,ce,cg);
                        ObservableList<Chkbal> list3 = null;list3= FXCollections.observableArrayList(
                               // new Chkbal(re.getInt("ord_id"),re.getInt("total"),re.getString("ord_detail"),"",2,200)//rt.getString("settle_date"),rt.getInt("settle_Amount"),rem);
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

        Scene scene=new Scene(tv_tab,1000,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
