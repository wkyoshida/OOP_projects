/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 3
 * BasePlusCommissionEmployee Subclass / CommissionEmployee Superclass
 */
package employee;

public class BasePlusCommissionEmployee extends CommissionEmployee {
    private double baseSalary; // base salary per week

    public BasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber, 
            double grossSales, double commisionRate, double baseSalary) { // constructor with superclass variables and subclass variables
        
        super(firstName, lastName, socialSecurityNumber, grossSales, commisionRate);
        
        // if baseSalary is invalid throw exception
        if (baseSalary < 0.0)
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        this.baseSalary = baseSalary;
    }   
    
    public void setBaseSalary(double baseSalary){
        if (baseSalary < 0.0)
            throw new IllegalArgumentException("Base salary must be >= 0.0");
        this.baseSalary = baseSalary;
    }
    
    public double getBaseSalary(){
        return baseSalary;
    }
    
    @Override
    public double earnings(){
        return getBaseSalary() + super.earnings(); // use earnings method from superclass CommissionEmployee
    }
    
    @Override
    public void raise(double percent){ // commission rate is increased as well in parent class CommissionEmployee's raise method
        super.raise(percent);
        this.baseSalary = baseSalary + (percent * baseSalary);
    }   
    
    @Override
    public String toString(){
        return String.format("%s: %s %s %s: %s%n\t%s: %.2f%n\t%s: %.4f%n\t%s%.2f%n\t%s%.2f%n", 
                "Base Salary Plus Commissioned Employee", super.getFirstName(), super.getLastName(), // use superclass' superclass methods
                "with ssn", super.getSocialSecurityNumber(),                                         // 
                "Gross Sales", super.getGrossSales(),                                                // use superclass methods
                "Commission Rate", super.getCommissionRate(),                                        //
                "with Base Salary of: $", getBaseSalary(),
                "Earnings: $", earnings());
    }
}