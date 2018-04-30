/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 6
 * FlightQueries class
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class FlightQueries {
    private static final String URL = "jdbc:derby://localhost:1527/FlightSchedulerDBWilliamYoshidawky5017";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";

    private static Connection connection;
    private static PreparedStatement selectAllFlights;
    private static PreparedStatement numberSeats;
    private static PreparedStatement insertNewFlight;
    private static PreparedStatement deleteFlight;

    public FlightQueries(){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            selectAllFlights = connection.prepareStatement("SELECT * FROM FLIGHT");
            
            numberSeats = connection.prepareStatement("SELECT SEATS FROM FLIGHT WHERE NAME = ?");
            
            insertNewFlight = connection.prepareStatement(
                "INSERT INTO FLIGHT " + 
                "(NAME, SEATS) " + 
                "VALUES (?, ?)");
            
            deleteFlight = connection.prepareStatement(
                "DELETE FROM FLIGHT WHERE NAME = ?");   
            
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    
    public static int dropFlight(String name){
        int result = 0;

        try {
            deleteFlight.setString(1, name);

            result = deleteFlight.executeUpdate();           
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }
    
    public static int addFlight(String name, int seats){
        int result = 0;

        try {
            insertNewFlight.setString(1, name);
            insertNewFlight.setInt(2, seats);

            result = insertNewFlight.executeUpdate();           
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }

    public static List< String > getAllFlights() {
        
        List< String > results = null;
        ResultSet resultSet = null;
       
        try {
            resultSet = selectAllFlights.executeQuery();
            results = new ArrayList< String >();
            while (resultSet.next()){
                results.add(resultSet.getString("NAME"));
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return results;
    }   
    
    public static int getFlightSeats(String name) {
        
        int results = 0;
        ResultSet resultSet = null;
       
        try {
            numberSeats.setString(1, name);
            resultSet = numberSeats.executeQuery();
            resultSet.next();
            results = resultSet.getInt(1); 
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return results;
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