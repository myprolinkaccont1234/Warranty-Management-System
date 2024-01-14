package com.example.demo3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConet {
    Connection DBConet(){
    Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/warranty","root","1234");
            //System.out.println("ok");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return c;

    }
}
