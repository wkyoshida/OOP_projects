/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 6
 * Day class
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;

public class Day {
    private static final String URL = "jdbc:derby://localhost:1527/FlightSchedulerDBWilliamYoshidawky5017";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";

    private static Connection connection;
    private static PreparedStatement selectAllDays;
    private static PreparedStatement insertNewDay;

    public Day(){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            selectAllDays = connection.prepareStatement(
                "SELECT * FROM DAY"
            );
            
            insertNewDay = connection.prepareStatement(
                "INSERT INTO DAY " + 
                "(DATE) " + 
                "VALUES (?)");

        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    
    public static int addDay(Date day){
        int result = 0;

        try {
            insertNewDay.setDate(1, day);

            result = insertNewDay.executeUpdate();           
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }

    public static List< Date > getAllDays() {
        
        List< Date > results = null;
        ResultSet resultSet = null;

        try {
            resultSet = selectAllDays.executeQuery();
            results = new ArrayList< Date >();
            while (resultSet.next()){
                results.add(resultSet.getDate("DATE"));
            }
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