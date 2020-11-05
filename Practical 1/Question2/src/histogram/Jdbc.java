/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package histogram;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

/**
 *
 * @author me-aydin
 */
public class Jdbc {
    
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    String query = null;
    
    
    public Jdbc(String query){
        this.query = query;
    }
    
    public void connect() throws ClassNotFoundException, SQLException{
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/histogram","micah", "OqpWJsbw0X9164b38noF");                                        
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    public void retrieve(){
        try {
           
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            
            ResultSetMetaData metaData = rs.getMetaData();

            int numberOfColumns = metaData.getColumnCount();
        
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.print(metaData.getColumnName(i)+" ");     
            System.out.println("\n----------------------");
            
            while (rs.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.print(rs.getObject(i)+" ");
                System.out.println();
            }                                        
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    public int retrieve_count(int category){
        
        int count = 0;
        
        try {
            //statement = connection.createStatement();
            //int val = statement.executeUpdate(sql);
            PreparedStatement stmt=connection.prepareStatement("SELECT COUNT(category) AS count FROM studentgrades WHERE category=?");
            stmt.setInt(1,category);
                                   
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
              count = rs.getInt(1);
            }
            
            //System.out.println("Number of records: " + count);
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
             
    }
    
    public void insert(int val1, int val2, int val3){
        try {
            //statement = connection.createStatement();
            //int val = statement.executeUpdate(sql);
            PreparedStatement stmt=connection.prepareStatement("INSERT INTO studentgrades (studentid, grades, category) VALUES (?, ?, ?)");
            stmt.setInt(1,val1);
            stmt.setInt(2,val2);
            stmt.setInt(3,val3);
            
            int i = stmt.executeUpdate();
            System.out.println(i+" records inserted");
           
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    public void update(String sql) {
        try {
            statement = connection.createStatement();
            int val = statement.executeUpdate(sql);
            System.out.println(val+" rows updated.");
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void closeAll(){
        try {
            rs.close();
            statement.close(); 		
            connection.close();                                         
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }
            
}