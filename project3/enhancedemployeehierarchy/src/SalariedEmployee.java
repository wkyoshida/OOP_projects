/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 3
 * SalariedEmployee Subclass / Employee Superclass
 */
package employee;

public class SalariedEmployee extends Employee {
    private double salary;

    public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, 
            double salary) { // constructor with superclass variables and subclass variables
        
        super(firstName, lastName, socialSecurityNumber);
        
        // if salary is invalid throw exception
        if (salary <= 0.0)
            throw new IllegalArgumentException("Salary must be > 0.0");
        this.salary = salary;
    }               
  
    public void setSalary(double salary) { 
        if (salary <= 0.0)
            throw new IllegalArgumentException("Salary must be > 0.0");
        this.salary = salary;
    }
    
    public double getSalary(){
        return salary;
    }
    
    @Override
    public double earnings(){ // fixed earnings of salary amount
        return salary;
    }
    
    @Override
    public void raise(double percent){
        this.salary = salary + (percent * salary);
    }    

    @Override
    public String toString(){
        return String.format("%s: %s %s %s: %s%n\t%s: %.2f%n\t%s%.2f%n", 
                "Salaried Employee", super.getFirstName(), super.getLastName(), // use superclass methods
                "with ssn", super.getSocialSecurityNumber(),
                "Salary", getSalary(),
                "Earnings: $", earnings());
    }
}