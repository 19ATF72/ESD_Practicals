/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionthreebankaccount;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public String name;
    public double balance;
    
    Scanner scanner = new Scanner(System.in);

    public Account(double _balance, String _name)
    {
        name = _name;
        balance = _balance; 
    }
    
    public void withdrawMoney()
    {

        System.out.println("Enter an amount you would like to withdraw: ");

        String withdrawAmount = scanner.next();

        Boolean isStringNumeric = isNumeric(withdrawAmount);

        if(isStringNumeric == true)
        {
            balance = balance - Double.parseDouble(withdrawAmount);
            Timestamp ts = new Timestamp(System.currentTimeMillis());

            System.out.println("Withdrawing $" + withdrawAmount);
            System.out.println("Your new account balance is: " + balance);
            
            try {
                String str = "UPDATE customer SET balance = ?, last_accessed = ? WHERE name = ?";
                Jdbc jdbc = new Jdbc(str);
                jdbc.connect();
                jdbc.updateBalance(name, balance, ts);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            System.out.println("Your withdraw amount is invalid.");
            System.out.println("Please try again...");
        }
    }
    
    public void depositMoney()
    {
        System.out.println("Enter an amount you would like to deposit: ");

        String depositAmount = scanner.next();

        Boolean isStringNumeric = isNumeric(depositAmount);

        if(isStringNumeric == true)
        {
            balance = balance + Double.parseDouble(depositAmount);
            Timestamp ts = new Timestamp(System.currentTimeMillis());

            System.out.println("Deposting $" + depositAmount);
            System.out.println("Your new account balance is: " + balance);
            try {
                String str = "UPDATE customer SET balance = ?, last_accessed = ? WHERE name = ?";
                Jdbc jdbc = new Jdbc(str);
                jdbc.connect();
                jdbc.updateBalance(name, balance, ts);
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            System.out.println("Your deposiit amount is invalid.");
            System.out.println("Please try again...");
        }
    }
    
    public void getBalance()
    {
        try {
            String str = "SELECT balance FROM customer WHERE name=?";
            Jdbc jdbc = new Jdbc(str);
            jdbc.connect();
            int bal = jdbc.retrieveBalance(name);

            //System.out.println("The account balance is " + balance);  
            System.out.println("The account balance on the DB is " + bal);  
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }
     
    private static boolean isNumeric(String str) 
    { 
        try 
        {  
            Double.parseDouble(str);  
            return true;
        } 
        catch(NumberFormatException e)
        {  
            return false;  
        }  
    }
    
}
