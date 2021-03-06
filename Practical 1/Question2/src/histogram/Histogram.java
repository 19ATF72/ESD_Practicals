/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package histogram;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.Scanner; // Import the Scanner class to read text files
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author James
 */
public class Histogram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
        try {
            File inputFile = new File("grades.txt");
            Scanner reader = new Scanner(inputFile);  // Create a Scanner object
           
            int[] gradeCount = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int intInput;
            int count = 0;
            int category = -1;
            String str = "SELECT * FROM studentgrades";
            
            //String update = "UPDATE 'studentgrades' SET 'grades' = 'test' ";
            
            Jdbc jdbc = new Jdbc(str);
            
            while (reader.hasNextLine()) {
                intInput = Integer.parseInt(reader.nextLine());
                
                if (intInput >= 0 && intInput < 11){
                    gradeCount[0] += 1;
                    category = 0;
                } else if (intInput >= 11 && intInput < 21){
                    gradeCount[1] += 1;
                    category = 1;
                } else if (intInput >= 21 && intInput < 31){
                    gradeCount[2] += 1;
                    category = 2;
                } else if (intInput >= 31 && intInput < 41){
                    gradeCount[3] += 1;
                    category = 3;
                } else if (intInput >= 41 && intInput < 51){
                    gradeCount[4] += 1;
                    category = 4;
                } else if (intInput >= 51 && intInput < 61){
                    gradeCount[5] += 1;
                    category = 5;
                } else if (intInput >= 61 && intInput < 71){
                    gradeCount[6] += 1;
                    category = 6;
                } else if (intInput >= 71 && intInput < 81){
                    gradeCount[7] += 1;
                    category = 7;
                } else if (intInput >= 81 && intInput < 91){
                    gradeCount[8] += 1;
                    category = 8;
                } else if (intInput >= 91 && intInput < 101){
                    gradeCount[9] += 1;
                    category = 9;
                } 
                
                try {
                jdbc.connect();
                //jdbc.retrieve();
                
                //jdbc.insert(count, intInput, category);
                //jdbc.retrieve();
                
                //jdbc.update(update);
                //jdbc.retrieve();
                
                //jdbc.closeAll();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Histogram.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                count += 1;
                
            }
                
            for (int i = 0; i < 10; i++){
                String histogramLength = "";
                for (int j = 0; j < gradeCount[i]; j++) {
                    histogramLength += "*";
                }
                
                try {
                jdbc.connect();
                //jdbc.retrieve();
                
                //jdbc.insert(count, intInput, category);
                count = jdbc.retrieve_count(i);

                //jdbc.update(update);
                //jdbc.retrieve();
                
                //jdbc.closeAll();
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(Histogram.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println((i*10 + 1) + " - " + (i*10 + 10) + " | " + histogramLength + " | " + count);
            }
          
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        
    }
    
}
