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
        //updateDataBase("Jack", "Pen", "jackp", "23901 South Coast", 1980, 1);
        updateDataBase("Dominic", "Eardrum", "dominice", "93228 San Francisco", 1994, 1);
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
            System.out.println(resultSet.getInt("ID") + " Names: " + resultSet.getString("firstname") + " "
                    + resultSet.getString("lastname") + "     "
                    + " Username: " + resultSet.getString("username") + "     "
                    + " Home address: " + resultSet.getString("address") + "     "
                    + " Year of born: " + resultSet.getInt("yearofborn"));
        }

    }

    public static void updateDataBase(String firstname, String lastname, String username,
                                      String address, int yearofborn, int ID) {

        String query = "UPDATE usersofapp SET firstname = ? , lastname = ? , username = ? , address = ? , yearofborn = ? "
                + "where ID = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, address);
            preparedStatement.setInt(5, yearofborn);
            preparedStatement.setInt(6, ID);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}