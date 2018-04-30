/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 3
 * HourlyEmployee Subclass / Employee Superclass
 */
package employee;

public class HourlyEmployee extends Employee {
    private double hourlyWage;
    private double hoursWorked; 
    private static final double OVERTIME_RATE = 1.5;
    
    public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber, 
            double hourlyWage, double hoursWorked) { // constructor with superclass variables and subclass variables
        
        super(firstName, lastName, socialSecurityNumber);
        
        // if hourlyWage is invalid throw exception
        if (hourlyWage <= 0.0)
            throw new IllegalArgumentException("Hourly wage must be > 0.0");
        this.hourlyWage = hourlyWage;
                
        // if hoursWorked is invalid throw exception
        if (hoursWorked > 168.0 || hoursWorked < 1.0)
            throw new IllegalArgumentException("Hours worked must be between 1 and 168");
        this.hoursWorked = hoursWorked;
    }   
    
    public void setHourlyWage(double hourlyWage) { 
        if (hourlyWage <= 0.0)
            throw new IllegalArgumentException("Hourly wage must be > 0.0");
        this.hourlyWage = hourlyWage;
    }
    
    public double getHourlyWage(){
        return hourlyWage;
    }
    
    public void setHoursWorked(double hoursWorked) { 
        if (hoursWorked > 168.0 || hoursWorked < 1.0)
            throw new IllegalArgumentException("Hours worked must be between 1 and 168");
        this.hoursWorked = hoursWorked;
    }
    
    public double getHoursWorked(){
        return hoursWorked;
    }
    
    @Override
    public double earnings(){
        if (getHoursWorked() > 40) // include overtime pay to regular pay
            return calculateOvertimePay(getHourlyWage(), getHoursWorked(), OVERTIME_RATE); 
        else
            return getHoursWorked() * getHourlyWage();
    }
    
    @Override
    public void raise(double percent){
        this.hourlyWage = hourlyWage + (percent * hourlyWage);
    }   
    
    public double calculateOvertimePay(double hourlyWage, double hoursWorked, double OVERTIME_RATE) { 
        return hourlyWage * ((OVERTIME_RATE * (hoursWorked - 40)) + 40) ; // over-time rate applies to hours after 40 hours standard
    }
    
    @Override
    public String toString(){
        return String.format("%s: %s %s %s: %s%n\t%s: %.2f%n\t%s: %.2f%n\t%s%.2f%n", 
                "Hourly Employee", super.getFirstName(), super.getLastName(), // use superclass methods
                "with ssn", super.getSocialSecurityNumber(), 
                "Hourly Wage", getHourlyWage(), 
                "Hours Worked", getHoursWorked(), 
                "Earnings: $", earnings());
    }        
            
}