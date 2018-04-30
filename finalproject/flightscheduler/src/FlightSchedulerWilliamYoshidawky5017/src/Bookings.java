/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 6
 * Bookings class
 */

import java.sql.Date;

public class Bookings {
    private String flight;
    private Date day;
    private String customer;
    
    public Bookings(){
        
    }
    
    public Bookings(String flight, Date day, String customer) { 
        setFlight(flight);
        setDay(day);
        setCustomer(customer);
    }   
    
    public void setFlight(String flight){
        this.flight = flight;
    }
    
    public void setDay(Date day){
        this.day = day;
    }

    public void setCustomer(String customer){
        this.customer = customer;
    }    
    
    public String getFlight(){
        return flight;
    }
    
    public Date getDay(){
        return day;
    }
    
    public String getCustomer(){
        return customer;
    }

}