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
public class Driver {

    
    Scanner scanner = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
 
        Bank bank = new Bank();
        
        bank.populateUsers();
        
        
        while(true)
        {
            bank.initialMenu(bank);
        }
    
    }
    
       
     
    
   
    
}
