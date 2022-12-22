package com.learnofdatabase.com;

import com.learnofdatabase.com.Helper.DBHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    static private DBHandler dbHandler;
    static private Connection connection;
    static private PreparedStatement preparedStatement;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        dbHandler = new DBHandler();
        connection = dbHandler.getDbConnection();

        String insert = "INSERT INTO usersofapp(firstname,lastname,username,address,yearofborn)"
                + "VALUES(?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(insert);

        preparedStatement.setString(1, "Michael");
        preparedStatement.setString(2, "French");
        preparedStatement.setString(3, "michaelf");
        preparedStatement.setString(4, "64276 Ewa Beach");
        preparedStatement.setInt(5, 1996);

    }
}