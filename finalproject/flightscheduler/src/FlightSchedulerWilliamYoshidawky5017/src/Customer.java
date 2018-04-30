/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 6
 * Customer class
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class Customer {
    private static final String URL = "jdbc:derby://localhost:1527/FlightSchedulerDBWilliamYoshidawky5017";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";

    private static Connection connection;
    private static PreparedStatement selectAllCustomers;
    private static PreparedStatement insertNewCustomer;

    public Customer(){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            selectAllCustomers = connection.prepareStatement(
                "SELECT * FROM CUSTOMER"
            );

            insertNewCustomer = connection.prepareStatement(
                "INSERT INTO CUSTOMER " + 
                "(NAME) " + 
                "VALUES (?)");
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    public static List< String > getAllCustomers() {
        
        List< String > results = null;
        ResultSet resultSet = null;

        try {
            resultSet = selectAllCustomers.executeQuery();
            results = new ArrayList< String >();
            while (resultSet.next()){
                results.add(new String(
                    resultSet.getString("NAME")));
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return results;
    }   

    public static int addCustomer(String name){
        int result = 0;

        try {
            insertNewCustomer.setString(1, name);

            result = insertNewCustomer.executeUpdate();           
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }

    public void close(){
        try {
            connection.close();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}