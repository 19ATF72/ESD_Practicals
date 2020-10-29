/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionthreebankaccount;
import java.util.Scanner;

/**
 *
 * @author rob
 */
public class Bank 
{
    private Account[] users = new Account[3];
    private Account currentUser;
    
    Scanner scanner = new Scanner(System.in);
    
    
    public void populateUsers()
    {
        users[0] = new Account(1000.00, "Rob"); 
        users[1] = new Account(11111.00, "Tom"); 
        users[2] = new Account(2222222.00, "Max"); 
        
        for (int i = 0; i < users.length; i++)
        {
             System.out.println(users[i].name);
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
        for (int i = 0; i < users.length; i++)
        {
            if(name.equals(users[i].name))
            {
                return users[i];
            }
            
        }     
        return null;
    }
    
   
 
    public void addInterest()
    {
        for (int i = 0; i < users.length; i++)
        {
            users[i].balance = users[i].balance * 1.03;
        }
    }
        
    
    
}
