/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionthreebankaccount;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author micah
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
    
    public void retrieveUser(String name){
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
    
    public int retrieveCount(int category){
        
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
    
    public int retrieveBalance(String name){
        
        int balance = 0;
        
        try {
            PreparedStatement stmt=connection.prepareStatement("SELECT balance FROM customer WHERE name=?");
            stmt.setString(1,name);
                                   
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
              balance = rs.getInt(1);
            }
            
            //System.out.println("Number of records: " + count);
            
            
           
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return balance;
             
    }
    
    public void retrieveCustomer(String name){
        
        try {
            PreparedStatement stmt=connection.prepareStatement("SELECT * FROM customer WHERE name=?");
            stmt.setString(1,name);
                   
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){  
                System.out.println("Retrieved record: " + rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3) +" "+rs.getTimestamp(4) +" "+rs.getTimestamp(5));  
            }          
           
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    
    public void insertCustomer(String name, double balance, Timestamp lastAccessed, Timestamp created){
        try {
            //statement = connection.createStatement();
            //int val = statement.executeUpdate(sql);
            PreparedStatement stmt=connection.prepareStatement("INSERT INTO customer (name, balance, created, last_accessed) VALUES (?, ?, ?, ?)");
            stmt.setString(1,name);
            stmt.setDouble(2,balance);
            stmt.setTimestamp(3,created);
            stmt.setTimestamp(4,lastAccessed,Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            
            int i = stmt.executeUpdate();
            System.out.println(i+" records inserted");
           
        } catch (SQLException ex) {
            Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void insertTransaction(String name, double balance, Timestamp lastAccessed, Timestamp created){
        try {
            //statement = connection.createStatement();
            //int val = statement.executeUpdate(sql);
            
            //TODO: Will require many to one relationship
            
            PreparedStatement stmt=connection.prepareStatement("INSERT INTO customer (name, balance, created, last_accessed) VALUES (?, ?, ?, ?)");
            stmt.setString(1,name);
            stmt.setDouble(2,balance);
            stmt.setTimestamp(3,created);
            stmt.setTimestamp(4,lastAccessed,Calendar.getInstance(TimeZone.getTimeZone("UTC")));
            
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
    
    public void updateBalance(String name, double balance, Timestamp last_accessed) {
        try {        
            PreparedStatement stmt=connection.prepareStatement("UPDATE customer SET balance=?, last_accessed=? WHERE name=?");  
            stmt.setDouble(1, balance);
            stmt.setTimestamp(2, last_accessed); 
            stmt.setString(3, name);
 
            int i=stmt.executeUpdate();  
            System.out.println(i+" records updated"); 
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