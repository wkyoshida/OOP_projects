/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 6
 * WaitingQueries class
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.sql.Timestamp;

public class WaitingQueries {
    private static final String URL = "jdbc:derby://localhost:1527/FlightSchedulerDBWilliamYoshidawky5017";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";

    private static Connection connection;
    private static PreparedStatement customersWaiting;
    private static PreparedStatement insertNewWait;
    private static PreparedStatement customerStatus;
    private static PreparedStatement deleteWaitFlight;
    private static PreparedStatement deleteWaitCustomer;
    private static PreparedStatement retrieveWait;
    private static PreparedStatement retrieveWaitGetCustomer;

    public WaitingQueries(){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            customersWaiting = connection.prepareStatement(
                "SELECT CUSTOMER FROM WAITING WHERE DATE = ? "
            );

            insertNewWait = connection.prepareStatement(
                "INSERT INTO WAITING (CUSTOMER, FLIGHT, DATE, TIMESTAMP) values (?, ?, ?, ?)");
            
            customerStatus = connection.prepareStatement(
                "SELECT * FROM WAITING WHERE CUSTOMER = ? "
            );
            
            deleteWaitFlight = connection.prepareStatement(
                "DELETE FROM WAITING WHERE FLIGHT = ?");      
            
            deleteWaitCustomer = connection.prepareStatement(
                "DELETE FROM WAITING WHERE CUSTOMER = ? AND DATE = ?");    
            
            retrieveWait = connection.prepareStatement(
                "SELECT * FROM WAITING WHERE FLIGHT = ? AND DATE = ?");
            
            retrieveWaitGetCustomer = connection.prepareStatement(
                "SELECT * FROM WAITING WHERE TIMESTAMP = ? AND FLIGHT = ? AND DATE = ?");
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    
    public static String getFirstWait(String flight, Date day){
        String customer = "";
        ResultSet resultSet = null;
        ResultSet resultSetCustomer = null;
        List< java.sql.Timestamp > results = null;
        java.sql.Timestamp earliest = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp checkTimestamp = earliest;

        try {
            retrieveWait.setString(1, flight);
            retrieveWait.setDate(2, day);

            resultSet = retrieveWait.executeQuery();
            results = new ArrayList< java.sql.Timestamp >();
            while (resultSet.next()){
                results.add(
                    resultSet.getTimestamp("TIMESTAMP"));
            }
            
            for ( java.sql.Timestamp s : results)
            {
                if (s.before(earliest)) {
                    earliest = s;
                }
            }
            
            if (earliest == checkTimestamp) {
            }
            else {
                retrieveWaitGetCustomer.setTimestamp(1, earliest);
                retrieveWaitGetCustomer.setString(2, flight);
                retrieveWaitGetCustomer.setDate(3, day);

                resultSetCustomer = retrieveWaitGetCustomer.executeQuery();
                while (resultSetCustomer.next()){
                    customer = resultSetCustomer.getString("CUSTOMER");
                }                
                
            }
            
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return customer;
    }
    
    
    public static int dropWaitFlight(String flight){
        int result = 0;

        try {
            deleteWaitFlight.setString(1, flight);

            result = deleteWaitFlight.executeUpdate();           
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }
    
    public static int dropWaitCustomer(String customer, Date day){
        int result = 0;

        try {
            deleteWaitCustomer.setString(1, customer);
            deleteWaitCustomer.setDate(2, day);

            result = deleteWaitCustomer.executeUpdate();           
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }

    public static List< String > statusWaitDay(Date day) {
        
        List< String > results = null;
        ResultSet resultSet = null;

        try {
            customersWaiting.setDate(1, day);
            resultSet = customersWaiting.executeQuery();
            results = new ArrayList< String >();
            while (resultSet.next()){
                results.add(new String(
                    resultSet.getString("CUSTOMER")));
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return results;
    }   
    
    public static List< String > statusCustomerWait(String customer) {
        
        List< String > results = null;
        ResultSet resultSet = null;

        try {
            customerStatus.setString(1, customer);
            resultSet = customerStatus.executeQuery();
            results = new ArrayList< String >();
            while (resultSet.next()){
                results.add(resultSet.getString("FLIGHT"));
                results.add(" | ");
                results.add(String.valueOf(resultSet.getDate("DATE")));
                results.add(" | ");
                results.add("Waitlist");
                results.add("\n");
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return results;
    }   

    public static int addWaitList(String name, String flight, Date day){
        int result = 0;
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        try {
            insertNewWait.setString(1, name);
            insertNewWait.setString(2, flight);
            insertNewWait.setDate(3, day);
            insertNewWait.setTimestamp(4, currentTimestamp);

            result = insertNewWait.executeUpdate();           
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