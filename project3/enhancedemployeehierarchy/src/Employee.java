/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 3
 * Abstract Employee Superclass
 */
package employee;

public abstract class Employee { // abstract class
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;

    public Employee(String firstName, String lastName, String socialSecurityNumber) { 
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }   
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getSocialSecurityNumber(){
        return socialSecurityNumber;
    }
    
    @Override
    public String toString(){
        return String.format("%s: %s %s %s: %s%n", 
                "Employee", getFirstName(), getLastName(), 
                "with ssn", getSocialSecurityNumber()); 
    }
    
    public abstract double earnings();
    
    public abstract void raise(double percent);

    public static void main(String []args) { // test objects
        CommissionEmployee employee1 = new CommissionEmployee("Fred", "Jones", "111-11-1111", 2000.0, .05);
        BasePlusCommissionEmployee employee2 = new BasePlusCommissionEmployee("Sue", "Smith", "222-22-2222", 3000.0, .05, 300);
        SalariedEmployee employee3 = new SalariedEmployee("Sha", "Yang", "333-33-3333", 1150.0);
        HourlyEmployee employee4 = new HourlyEmployee("Ian", "Tanning", "444-44-4444", 15.0, 50);
        HourlyEmployee employee5 = new HourlyEmployee("Angela", "Domchek", "555-55-5555", 20.0, 40);
        System.out.printf("%s%n%s%s%s%s%s", "Employee information.", employee1, employee2, employee3, employee4, employee5);
        
        // after raises
        Employee[] employeeNumber = {employee1,employee2,employee3,employee4,employee5};
        
        for(Employee x : employeeNumber ) { // iterate through employees and give them raises
            if (x instanceof SalariedEmployee){
                x.raise(.04);
            }
            else {
                x.raise(.02);
            }
        }
        
        System.out.printf("%n%s%n%s%s%s%s%s", "Employee information after raises.", employee1, employee2, employee3, employee4, employee5);
    }            
            
}