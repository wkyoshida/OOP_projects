/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 3
 * CommissionEmployee Subclass / Employee Superclass
 */
package employee;

public class CommissionEmployee extends Employee {
    private double grossSales; // gross weekly sales
    private double commissionRate; // commission percentage
    
    public CommissionEmployee(String firstName, String lastName, 
            String socialSecurityNumber, double grossSales, double commissionRate) { // constructor with superclass variables and subclass variables
        super(firstName, lastName, socialSecurityNumber);
        
        // if grossSales is invalid throw exception        
        if (grossSales < 0.0)
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        this.grossSales = grossSales;
        
        // if commissionRate is invalid throw exception
        if (commissionRate <= 0.0 || commissionRate >= 1.0)
            throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
        this.commissionRate = commissionRate;        
    }   
    
    public void setGrossSales(double grossSales){     
        if (grossSales < 0.0)
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        this.grossSales = grossSales;
    }
    
    public double getGrossSales(){
        return grossSales;
    }
    
    public void setCommissionRate(double commissionRate){
        if (commissionRate <= 0.0 || commissionRate >= 1.0)
            throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
        this.commissionRate = commissionRate;
    }
    
    public double getCommissionRate(){
        return commissionRate;
    }
    
    @Override
    public double earnings(){
        return getCommissionRate() * getGrossSales();
    }
    
    @Override
    public void raise(double percent){
        this.commissionRate = commissionRate + (percent * commissionRate);
    }   
    
    @Override
    public String toString(){
        return String.format("%s: %s %s %s: %s%n\t%s: %.2f%n\t%s: %.4f%n\t%s%.2f%n", 
                "Commissioned Employee", super.getFirstName(), super.getLastName(), // use superclass methods
                "with ssn", super.getSocialSecurityNumber(), 
                "Gross Sales", getGrossSales(), 
                "Commission Rate", getCommissionRate(), 
                "Earnings: $", earnings());
    }
            
}