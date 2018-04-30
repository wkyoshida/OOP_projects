/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 6
 * Flight class
 */

public class Flight {
    private String flightName;
    private int seats;

    public Flight(){
        
    }
    
    public Flight(String flightName, int seats) { 
        setFlightName(flightName);
        setSeats(seats);
    }   
    
    public void setFlightName(String flightName){
        this.flightName = flightName;
    }
    
    public void setSeats(int seats){
        this.seats = seats;
    }
    
    public String getFlightName(){
        return flightName;
    }
    
    public int getFlightSeats(){
        return seats;
    }
            
}
