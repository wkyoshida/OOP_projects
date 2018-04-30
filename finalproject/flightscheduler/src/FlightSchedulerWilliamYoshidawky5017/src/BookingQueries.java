/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 6
 * BookingQueries class
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingQueries {
    private static final String URL = "jdbc:derby://localhost:1527/FlightSchedulerDBWilliamYoshidawky5017";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";

    private static Connection connection;
    private static PreparedStatement customersBooked;
    private static PreparedStatement getFlightSeats;
    private static PreparedStatement insertNewBooking;
    private static PreparedStatement customerStatus;
    private static PreparedStatement deleteBookingFlight;
    private static PreparedStatement deleteBookingCustomer;
    private static PreparedStatement deleteBookingCustomerGetFlight;
    private static PreparedStatement dropFlightCustomers;

    public BookingQueries(){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            customersBooked = connection.prepareStatement(
                "SELECT CUTOMER FROM BOOKING WHERE FLIGHT = ? AND DATE = ?"
            );
            
            getFlightSeats = connection.prepareStatement("SELECT COUNT(FLIGHT) FROM BOOKING WHERE FLIGHT = ? AND DATE = ?");

            insertNewBooking = connection.prepareStatement(
                "INSERT INTO BOOKING " + 
                "(CUTOMER, FLIGHT, DATE) " + 
                "VALUES (?, ?, ?)");
            
            customerStatus = connection.prepareStatement(
                "SELECT * FROM BOOKING WHERE CUTOMER = ? ");
            
            dropFlightCustomers = connection.prepareStatement(
                "SELECT * FROM BOOKING WHERE FLIGHT = ? ");            
            
            deleteBookingFlight = connection.prepareStatement(
                "DELETE FROM BOOKING WHERE FLIGHT = ?"); 
            
            deleteBookingCustomer = connection.prepareStatement(
                "DELETE FROM BOOKING WHERE CUTOMER = ? AND DATE = ?"); 
            
            deleteBookingCustomerGetFlight = connection.prepareStatement(
                "SELECT * FROM BOOKING WHERE CUTOMER = ? AND DATE = ?"); 
            
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
    
    public static int dropBookingFlight(String flight){
        int result = 0;

        try {
            deleteBookingFlight.setString(1, flight);

            result = deleteBookingFlight.executeUpdate();           
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }
    
    public static String dropBookingCustomer(String customer, Date day){
        String flight = "";
        ResultSet resultSet = null;
        int result;

        try {
            deleteBookingCustomerGetFlight.setString(1, customer);
            deleteBookingCustomerGetFlight.setDate(2, day);
            
            resultSet = deleteBookingCustomerGetFlight.executeQuery();
            while (resultSet.next()){
                flight = resultSet.getString("FLIGHT");
            }
            
            deleteBookingCustomer.setString(1, customer);
            deleteBookingCustomer.setDate(2, day);

            result = deleteBookingCustomer.executeUpdate();           
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return flight;
    }
    
    public static int seatsBooked(String flight, Date day){
        int result = 0;
        ResultSet resultSet = null;

        try {
            getFlightSeats.setString(1, flight); 
            getFlightSeats.setDate(2, day);
            resultSet = getFlightSeats.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1);         
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return result;
    }

    public static List< Bookings > droppedCustomers (String flight) {
        
        List< Bookings > results = null;
        ResultSet resultSet = null;

        try {
            dropFlightCustomers.setString(1, flight); 
            resultSet = dropFlightCustomers.executeQuery();
            results = new ArrayList< Bookings >();
            while (resultSet.next()){
                results.add(new Bookings(
                    flight,
                    resultSet.getDate("DATE"),
                    resultSet.getString("CUTOMER")));
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return results;
    }       
    
    public static List< String > statusCustomerBooking(String customer) {
        
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
                results.add("Booking");
                results.add("\n");
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return results;
    }       
    
    public static List< String > statusDayFlight(String flight, Date day) {
        
        List< String > results = null;
        ResultSet resultSet = null;

        try {
            customersBooked.setString(1, flight); 
            customersBooked.setDate(2, day);
            resultSet = customersBooked.executeQuery();
            results = new ArrayList< String >();
            while (resultSet.next()){
                results.add(new String(
                    resultSet.getString("CUTOMER")));
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        
        return results;
    }   

    public static int addBooking(String name, String flight, Date day){
        int result = 0;

        try {
            insertNewBooking.setString(1, name);
            insertNewBooking.setString(2, flight);
            insertNewBooking.setDate(3, day);

            result = insertNewBooking.executeUpdate();           
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