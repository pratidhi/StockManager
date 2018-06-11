/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akash.db;

import com.akash.src.MainUI;
import com.akash.src.UserModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author akash
 */
public class UserHandler {
    
    String url = "jdbc:derby:StockManager;create=true";
    Connection con = null;
    Statement stmt = null;
    
    public int createUser(String name, String username, String password){
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("INSERT INTO USERS(NAME,USERNAME,PASSWORD) VALUES ('%s','%s','%s')",name,username,password);
            res = stmt.executeUpdate(userQuery);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        finally{
            try {
                stmt.close();
                con.close();
                
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }        
        return res;
    }
    
    public UserModel loginUser(String username, String password){
        ResultSet rs = null;
        UserModel u = new UserModel();
        try{
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("SELECT * FROM USERS WHERE USERNAME='%s' AND PASSWORD='%s'",username,password);
//            String userQuery = "SELECT * FROM USERS";
            rs = stmt.executeQuery(userQuery);
            while(rs.next()){
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setUsername(rs.getString(3));
                u.setPassword(rs.getString(4));
                u.setGmail(rs.getString(5));
                u.setApppassword(rs.getString(6));
            
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        finally{
            try {
                stmt.close();
                con.close();
                rs.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }        
        return u;
    }
    
    public int changePasword(String username,String password)
    {
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("UPDATE USERS SET PASSWORD='%s' WHERE USERNAME='%s'",password,username);
            res = stmt.executeUpdate(userQuery);
            System.err.println(userQuery+"\n"+res);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        finally{
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }        
        return res;
    }
    
    public int updateGmailAccount(String username,String gmail,String appPassword)
    {
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("UPDATE USERS SET GMAIL='%s',APP_PASSWORD='%s' WHERE USERNAME='%s'",gmail,appPassword,username);
            res = stmt.executeUpdate(userQuery);
            System.err.println(userQuery+"\n"+res);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        finally{
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }        
        return res;
    }
}
