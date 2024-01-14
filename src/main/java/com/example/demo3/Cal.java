package com.example.demo3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cal extends Application {
    @FXML
    BorderPane bp;
    @FXML
    AnchorPane ap;

    @FXML
    TextField t6, t7, t9, t12, name;

    @FXML
    DatePicker t8, t10, td2;

    static String b_key, state;
    @FXML
    Label lb1, lb2, lb4, lb6, lb7;
    Connection con = new DBConet().DBConet();
@FXML
Menu Active;
    @FXML
    private TableColumn<Detail, Integer> fc;

    @FXML
    private TableColumn<Detail, Integer> fic;

    @FXML
    private TableColumn<Detail, String> foc;


    @FXML
    private TableColumn<Detail, String> sc;

    @FXML
    private TableColumn<Detail, String> sec;

    @FXML
    private TableColumn<Detail, String> sic;

    @FXML
    private TableColumn<Detail, String> tc;


    @FXML
    private TableView<Detail> tv;

    @FXML
    TextArea t11;
    static String endate,A_key;
    static String s;

static String user=Bn.user_name;
static int id=Bn.id;



    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmls = new FXMLLoader(Cal.class.getResource("Cal.fxml"));
        Scene scene = new Scene(fxmls.load(), 1300, 500);
        stage.setTitle("Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void fg() {
        t8.setValue(LocalDate.now());
    }

    @FXML
    void fh() {
        t8.setValue(LocalDate.now());
    }

    @FXML
    void gg() {
        caldate();
    }

    @FXML
    private void buttoni() {
        bp.setCenter(ap);

    }

    @FXML
    void caldate() {
        LocalDate start = LocalDate.now();
        try {
            int months = Integer.parseInt(t9.getText());

            LocalDate endDate = start.plusMonths(months);

            endate = String.valueOf(endDate);
            System.out.println(endate);
            t10.setValue(endDate);
        } catch (NumberFormatException e) {
            t10.setValue(null);
        }

    }

    @FXML
    private void buttonii() {
        loadpage("page_i");

    }

    @FXML
    private void buttoniii() {

        loadpage("Chk_Balance");
    }

    @FXML
    private void buttonis() {
        loadpage("pageii");
    }

    private void loadpage(String s) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(s + ".fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bp.setCenter(root);
        Axiver();

    }

    @FXML
    private void add_war() {
        String s1 = t6.getText();
        String s2 = t7.getText();
        String s3 = String.valueOf(t8.getValue());
        String s6 = t11.getText();
        String s4 = t9.getText();
        if (s1.isBlank() && s2.isBlank() && s6.isBlank() && s4.isBlank() && t8.getValue() == null) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Massage");
            al.setHeaderText(null);
            al.setContentText("Fill At least One Field to Complete Operation");
            al.showAndWait();
        }
        if (t6.getText().isEmpty()) {
            lb1.setText("Can't be empty");
        } else if (!t6.getText().matches("^[A-Za-z.]+$")) {
            lb1.setText("Name Must contain Characters Only");
        } else if (s1.matches("^[A-Za-z.]+$")) {
            lb1.setText("");
            s1 = t6.getText();
        }

        if (s2.isBlank()) {
            lb2.setText("Can't be empty");
        } else if (!s2.matches("^[A-Za-z.]+$")) {
            lb2.setText("Name Must contain Characters Only");
        } else {
            lb2.setText("");
            s1 = t7.getText();
        }

        if (t9.getText().isEmpty()) {
            lb4.setText("Month Cannot be Empty");
        } else if (!s4.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
            lb4.setText("Numbers Only");
        } else if (0 > Integer.parseInt(s4)) {
            lb4.setText("Month Cannot be Minus Values");
        } else if (!t9.getText().isEmpty()) {
            lb4.setText("");
        }
        if (t6.getText().isEmpty()) {
            lb1.setText("Can't be empty");
        } else if (t7.getText().isEmpty()) {
            lb2.setText("Can't be empty");
        } else {
            System.out.println("final");
            int s5 = Integer.parseInt(t9.getText());
            store(s1, s2, s3, s6, s5);
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Massage");
            al.setHeaderText(null);
            al.setContentText("Warranty was Added");
            al.showAndWait();
            clears();
        }
    }

    void clears() {
        lb1.setText("");
        lb2.setText("");
        lb4.setText("");
        t6.setText("");
        t7.setText("");
        t9.setText("");
        t10.setValue(null);
        t11.setText("");
    }

    @FXML
    private void clear_i() {
        clears();

    }

    void store(String s1, String s2, String s3, String s6, int s4) {

        try {
            PreparedStatement ps = con.prepareStatement("insert into warranty(customer_name,product,Warranty_st_date,Warranty_period,Warranty_ed_date,discription) values(?,?,?,?,?,?)");
            ps.setString(1, s1);
            ps.setString(2, s2);
            ps.setString(3, s3);
            ps.setInt(4, s4);
            ps.setString(5, String.valueOf(t10.getValue()));
            ps.setString(6, s6);
            ps.executeUpdate();
        } catch (SQLException e) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Massage");
            al.setHeaderText(null);
            al.setContentText(String.valueOf(e));
            al.showAndWait();
        }
    }

    @FXML
    public void chk_war() {
        if (!name.getText().isEmpty()) {
            s = name.getText();
        }
        System.out.println("first: " + s);

        if (t12.getText().isEmpty() && name.getText().isEmpty() && td2.getValue() == null) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Massage");
            al.setHeaderText(null);
            al.setContentText("Fill At least One Field to Complete Operation");
            al.showAndWait();
        }
        if (!t12.getText().isBlank()) {
            if (!t12.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                lb6.setText("Numbers only");
            }
            if (t12.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                lb6.setText("");
            }
        }
        if (!name.getText().isEmpty()) {
            if (name.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                lb7.setText("Alphabetic characters only");
            }
            if (!name.getText().matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                lb7.setText("");
            }
        }
        if (!name.getText().isEmpty() || !t12.getText().isBlank() || td2.getValue() != null) {
            System.out.println("in function");
            tv.setVisible(true);
            Connection con = new DBConet().DBConet();
            String baseQuery = "select * from warranty where";
            List<String> conditions = new ArrayList<>();
            List<Object> parameters = new ArrayList<>();
            if (!t12.getText().isEmpty()) {
                conditions.add("product_id = ?");
                parameters.add(t12.getText());
                System.out.println("id " + t12.getText());
            }

// Check if the user filled the warranty_start_date field
            if (!name.getText().isEmpty()) {
                conditions.add("customer_name = ?");
                parameters.add(name.getText());

            }
            System.out.println("second : " + s);

// Check if the user filled the customer_name field
            if (td2.getValue() != null) {
                conditions.add("Warranty_st_date = ?");
                parameters.add(td2.getValue());
            }

// Combine the conditions with 'OR' and construct the final query
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
                        fc.setCellValueFactory(new PropertyValueFactory<Detail, Integer>("fc"));
                        sc.setCellValueFactory(new PropertyValueFactory<Detail, String>("sc"));
                        tc.setCellValueFactory(new PropertyValueFactory<Detail, String>("tc"));
                        foc.setCellValueFactory(new PropertyValueFactory<Detail, String>("foc"));
                        fic.setCellValueFactory(new PropertyValueFactory<Detail, Integer>("fic"));
                        sic.setCellValueFactory(new PropertyValueFactory<Detail, String>("sic"));
                        sec.setCellValueFactory(new PropertyValueFactory<Detail, String>("sec"));
                        String da = re.getString("Warranty_ed_date");
                        LocalDate givenDate = LocalDate.parse(da);
                        LocalDate cur = LocalDate.now();
                        Period period = Period.between(cur, givenDate);
                        int remainingMonths = period.getMonths();
                        int remainingDays = period.getDays();
                        String res;
                        if (remainingMonths > 0 || remainingDays > 0) {
                            res = remainingMonths + " months and " + remainingDays + " days";
                        } else if (remainingMonths < 0 || remainingDays < 0) {
                            res = "Warranty period end";
                        } else {
                            res = "Warranty end Today";
                        }
                        list.add(new Detail(re.getInt("product_id"), re.getInt("Warranty_period"), re.getString("customer_name"), re.getString("Warranty_st_date"), re.getString("Warranty_ed_date"), re.getString("discription"), res));
                    }
                    tv.setItems(list);
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
        System.out.println("sec : " + s);
    }

    @FXML
    private void clear_ii() {
        lb6.setText("");
        lb7.setText("");
        t12.setText("");
        name.setText("");
        td2.setValue(null);
    }



    public void activekey() {
        Connection con = new DBConet().DBConet();
        try {
            PreparedStatement ps = con.prepareStatement("select * from users where username=?");
            ps.setString(1, Bn.user_name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                state = rs.getString("state");
                b_key = rs.getString("A_key");
            }
            if (state.equals("trail")) {
                Activation ac = new Activation();
                ac.start(new Stage());
            } else if (state.equals("active")) {
                ActiP ap = new ActiP();
                ap.start(new Stage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    public void profile() throws Exception {
        Profile pf=new Profile();
        pf.start(new Stage());
    }


    public void Axiver() {
        try {
            PreparedStatement ps= con.prepareStatement("select * from users where us_id =? and username =?");
            ps.setInt(1,id);
            ps.setString(2,user);
            ResultSet re=ps.executeQuery();
            while (re.next()){
                String sta=re.getString("state");
                A_key=re.getString("A_key");
                if (sta.equals("trail")){
                    Active.setStyle("-fx-background-color:red");
                }
                if (sta.equals("active")) {
                    Active.setStyle("-fx-background-color:white");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
@FXML
    public void profile_show(ActionEvent actionEvent) throws Exception {
        Profile pf1=new Profile(user,A_key);
        Profile pf=new Profile();
        pf.start(new Stage());

    }
}

