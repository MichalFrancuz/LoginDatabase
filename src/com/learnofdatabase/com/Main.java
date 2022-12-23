package com.learnofdatabase.com;

import com.learnofdatabase.com.Helper.DBHandler;

import java.sql.*;

public class Main {
    static private DBHandler dbHandler;
    static private Connection connection;
    static private PreparedStatement preparedStatement;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        dbHandler = new DBHandler();
        connection = dbHandler.getDbConnection();

        //writeToDataBase();
        readFromDataBase();

    }

    public static void writeToDataBase() throws SQLException {

        String insert = "INSERT INTO usersofapp(ID,firstname,lastname,username,address,yearofborn)"
                + "VALUES(?,?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(insert);

        preparedStatement.setInt(1, 7);
        preparedStatement.setString(2, "Sawyer");
        preparedStatement.setString(3, "James");
        preparedStatement.setString(4, "sawyerj");
        preparedStatement.setString(5, "40165 West Coast");
        preparedStatement.setInt(6, 1987);
        preparedStatement.executeUpdate();

    }

    public static void readFromDataBase() throws SQLException {

        String query = "SELECT * from usersofapp";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println("Names: " + resultSet.getString("firstname") + " "
                    + resultSet.getString("lastname") + "     "
                    + " Username: " + resultSet.getString("username") + "     "
                    + " Home address: " + resultSet.getString("address") + "     "
                    + " Year of born: " + resultSet.getInt("yearofborn"));
        }

    }
}