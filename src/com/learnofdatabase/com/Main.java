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
        //updateDataBase("Jon Snow", 47, "XL", 11, 4);
        deleteUserFromDataBase(7);
        readFromDataBase();

    }

    public static void writeToDataBase() throws SQLException {

        String insert = "INSERT INTO newusers(fullname,sizeofshoes,sizeoftshirts,numberoforders)"
                + "VALUES(?,?,?,?)";

        preparedStatement = connection.prepareStatement(insert);

        preparedStatement.setString(1, "Anne Fox");
        preparedStatement.setInt(2, 34);
        preparedStatement.setString(3, "S");
        preparedStatement.setInt(4, 4);
        preparedStatement.executeUpdate();

    }

    public static void readFromDataBase() throws SQLException {

        String query = "SELECT * from newusers";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(
                    resultSet.getInt("idnewusers")
                    + " Fullname: " + resultSet.getString("fullname") + "     "
                    + " Size of shoes: " + resultSet.getInt("sizeofshoes") + "     "
                    + " Size of t-shirts: " + resultSet.getString("sizeoftshirts") + "     "
                    + " Number of orders: " + resultSet.getInt("numberoforders"));
        }

    }

    public static void updateDataBase(String fullname, int sizeofshoes, String sizeoftshirts,
                                      int numberoforders, int idnewusers) {

        String query = "UPDATE newusers SET fullname = ? , sizeofshoes = ? , sizeoftshirts = ? , numberoforders = ? "
                + "where idnewusers = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fullname);
            preparedStatement.setInt(2, sizeofshoes);
            preparedStatement.setString(3, sizeoftshirts);
            preparedStatement.setInt(4, numberoforders);
            preparedStatement.setInt(5, idnewusers);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteUserFromDataBase(int idnewusers) {

        String query = "DELETE from newusers where idnewusers = ? ";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idnewusers);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}