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

        //preparedStatement.setInt(1, 5);
        preparedStatement.setString(1, "Adalbert");
        preparedStatement.setString(2, "Brave");
        preparedStatement.setString(3, "adalbertb");
        preparedStatement.setString(4, "02941 Palma de Mallorca");
        preparedStatement.setInt(5, 1992);
        preparedStatement.executeUpdate();

    }
}