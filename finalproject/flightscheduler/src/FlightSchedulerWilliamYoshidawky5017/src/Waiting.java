/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 6
 * Waiting class
 */

import java.sql.Timestamp;
import java.sql.Date;

public class Waiting {
    private String flight;
    private Date day;
    private String customer;
    private Timestamp currentTimestamp;
    
    public Waiting(){
        
    }

    public Waiting(String flight, Date day, String customer, java.sql.Timestamp currentTimestamp) { 
        setFlight(flight);
        setDay(day);
        setCustomer(customer);
        setTimestamp(currentTimestamp);
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
    
    public void setTimestamp(java.sql.Timestamp currentTimestamp){
        this.currentTimestamp = currentTimestamp;
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
    
    public Timestamp getTimestamp(){
        return currentTimestamp;
    }
    
}