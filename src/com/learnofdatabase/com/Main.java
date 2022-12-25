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
        //updateDataBase("jamiefoxx", "jamiefoxx@icloud.com", "987654321", Date.valueOf("2018-11-03"), 3);
        //deleteUserFromDataBase(5);
        readFromDataBase();

    }

    public static void writeToDataBase() throws SQLException {

        String insert = "INSERT INTO trainingtable(nickname, email, phonenumber, dateofjoin)"
                + "VALUES(?,?,?,?)";

        preparedStatement = connection.prepareStatement(insert);

        preparedStatement.setString(1, "spiderman");
        preparedStatement.setString(2, "spiderman@yahoo.com");
        preparedStatement.setString(3, "123456789");
        preparedStatement.setDate(4, java.sql.Date.valueOf("2016-04-17"));
        preparedStatement.executeUpdate();

    }

    public static void readFromDataBase() throws SQLException {

        String query = "SELECT * from trainingtable";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(
                    resultSet.getInt("id")
                    + " Nickname: " + resultSet.getString("nickname") + "     "
                    + " Address e-mail: " + resultSet.getString("email") + "     "
                    + " Phone number: " + resultSet.getString("phonenumber") + "     "
                    + " Date of join: " + resultSet.getDate("dateofjoin"));
        }

    }

    public static void updateDataBase(String nickname, String email, String phonenumber,
                                      Date dateofjoin, int id) {

        String query = "UPDATE trainingtable SET nickname = ? , email = ? , phonenumber = ? , dateofjoin = ? "
                + "where id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nickname);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phonenumber);
            preparedStatement.setDate(4, dateofjoin);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteUserFromDataBase(int id) {

        String query = "DELETE from trainingtable where id = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}