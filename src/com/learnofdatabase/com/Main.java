package com.learnofdatabase.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        // calling this class is for older version of java
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginscheme",
                "root", "@M8!f1zki96");

        System.out.println("Connected to database " + connection.getCatalog());
    }
}