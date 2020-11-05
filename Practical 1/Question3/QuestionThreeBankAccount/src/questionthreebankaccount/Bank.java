/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionthreebankaccount;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rob
 */
public class Bank 
{
    Scanner scanner = new Scanner(System.in);
    private Account[] users = new Account[3];
    private Account currentUser;
     
    public void populateUsers()
    {
        users[0] = new Account(1000.00, "Rob"); 
        users[1] = new Account(11111.00, "Tom"); 
        users[2] = new Account(2222222.00, "Max"); 
        
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        
        for (int i = 0; i < users.length; i++)
        {
             
            try {
                String str = "INSERT INTO customer values(name, balance, created, last_accessed)";
                Jdbc jdbc = new Jdbc(str);
                jdbc.connect();
                //jdbc.insertCustomer(users[i].name, users[i].balance, ts, ts);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void initialMenu(Bank bank)
    {  
       
       
       System.out.println("1: Get Account");
       System.out.println("2: Add Interest");   
       System.out.println("3: Quit");

       char result = scanner.next().charAt(0);
       
        switch(result) 
        {
            case '1':
                userMenu(bank); 
                break;
            case '2':
                bank.addInterest();
                break;
            case '3':
                System.exit(0);
                break;
            default:
                System.out.println("Input Error");
        }
    }
    
    public void userMenu(Bank bank)
    {
        
        System.out.println("Please enter the name of the user: ");
        
        String typedUser = scanner.next();
        
        Account fetchedUser = getUser(typedUser);
        
        if(fetchedUser == null)
        {   
            System.out.println("Account not found try again...");
            userMenu(bank);
        }
        else
        {
            currentUser = fetchedUser; 
            
            System.out.println("1: deposit money");
            System.out.println("2: withdraw money");
            System.out.println("3: Get Balance");
            
            int result = scanner.nextInt();
            
            switch(result) 
            {
                case 1:
                    currentUser.depositMoney();
                    System.out.println(currentUser.balance);
                    break;
                case 2:
                    currentUser.withdrawMoney();
                    break;
                case 3:
                    currentUser.getBalance();
                    break;
                default:
                    System.out.println("Input Error");
            }
            
        }       
    }

    private Account getUser(String name)
    {
        try {
            String str = "SELECT * FROM customer WHERE name=? LIMIT 1";
            Jdbc jdbc = new Jdbc(str);
            jdbc.connect();
            jdbc.retrieveCustomer(name);
            
            for (int i = 0; i < users.length; i++)
            {
                if(name.equals(users[i].name))
               {
                    return users[i];
                }

            }     
               
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        return null;
    }
    
    public void addInterest()
    {
        for (int i = 0; i < users.length; i++)
        {
            users[i].balance = users[i].balance * 1.03;
            // Needs to function in Account.java to ensure DB entry only happens in one place.
            // Same function as depositMoney
        }
    }
    
}
