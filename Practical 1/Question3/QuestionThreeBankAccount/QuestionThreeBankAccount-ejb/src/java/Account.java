
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rob
 */
public class Account 
{
    
    private static String name;
    private double balance;
    
    Scanner scanner = new Scanner(System.in);
    
    public Account(double _balance, String _name)
    {
        name = _name;
        balance = _balance; 
    }
    
    public String getBalance()
    {
        return "The account balance is " + balance; 
    }
    
    public void withdrawMoney()
    {
        while(true)
        {
            System.out.println("Enter an amount you would like to withdraw: ");
            double withdrawAmount = scanner.nextDouble();
            
            if(withdrawAmount == 0)
            {   
                System.out.println("Your withdraw amount is 0 or invalid.");
                System.out.println("Please try again...");
            }
            else
            {
                balance = balance - withdrawAmount;
                
                System.out.println("Withdrawing $" + withdrawAmount);
                System.out.println("Your new account balance is: " + balance);
                break; 
            }
        }
    }
    
    public void depositMoney()
    {
        while(true)
        {
            System.out.println("Enter an amount you would like to deposit: ");
            double depositAmount = scanner.nextDouble();
            
            if(depositAmount == 0)
            {   
                System.out.println("Your deposit amount is 0 or invalid.");
                System.out.println("Please try again...");
            }
            else
            {
                balance = balance + depositAmount;
                
                System.out.println("Depositing $" + depositAmount);
                System.out.println("Your new account balance is: " + balance);
                break; 
            }
        }
    }
}
