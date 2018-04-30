/*
 * WILLIAM YOSHIDA (wky5017)
 * CMPSC 221 - Al Verbanec - Fall 2017
 * Programming Assignment 1
 */
package savingsaccount;

public class SavingsAccount {
    public SavingsAccount(double initialBalance) { // constructor sets initial balance value
        savingsBalance = initialBalance;
    }   
    
    private static double annualInterestRate; 
            
    private double savingsBalance;
    
    public static double calculateMonthlyInterest(double balance, double interestRate) { // Monthly Interest calculated with current balance and current rate
        return (balance * interestRate) / 12;
    }
  
    public static void setInterestRate(double newRate) { 
        annualInterestRate = newRate;
    }
    
    public static void main(String []args) {
        SavingsAccount saver1 = new SavingsAccount(2000); // open new account with initial balance of 2000
        SavingsAccount saver2 = new SavingsAccount(3000); // open new account with initial balance of 3000
        System.out.println("Savings Account Balances");
        System.out.println("Month       Saver1    Saver2");
       
        for(int month = 1; month <= 13; month = month + 1) { // include 13th month account balance
            if (month < 13){
                setInterestRate(0.04);
            }
            else {
                setInterestRate(0.05); // at 13th month, increase interest rate
            }
            saver1.savingsBalance += saver1.calculateMonthlyInterest(saver1.savingsBalance, annualInterestRate); // add interest
            saver2.savingsBalance += saver2.calculateMonthlyInterest(saver2.savingsBalance, annualInterestRate); // to current balance
            if (month < 10) {
                System.out.println(month + "          " + String.format("%.2f", saver1.savingsBalance) + "   " + String.format("%.2f", saver2.savingsBalance)); // format ouput
            }
            else {
                System.out.println(month + "         " + String.format("%.2f", saver1.savingsBalance) + "   " + String.format("%.2f", saver2.savingsBalance)); // format output to align data together
            }
        }

    }            
            
}