/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akash.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author akash
 */
public class CreateAllTables {

    String url = "jdbc:derby:StockManager;create=true";
    Connection con = null;
    Statement stmt = null;
    
    public void runExecution(){
        
//        dropAllTable();

        createUsertable();
        createPhoneStockTable();
        createAccessoriesStockTable();
        createTransactionTable();
        createClientTable();
    }

    private void createUsertable() {
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = "CREATE TABLE USERS (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), NAME varchar(500) NOT NULL,USERNAME varchar(500) NOT NULL primary key,PASSWORD varchar(50) NOT NULL,GMAIL varchar(50),APP_PASSWORD varchar(25))";
            stmt.executeUpdate(userQuery);
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
    }

    private void createPhoneStockTable() {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            
            String phoneQuery = "CREATE TABLE PHONE_STOCK (ID INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), IMEI varchar(500) NOT NULL primary key,BRAND varchar(500) NOT NULL,MODEL varchar(500) NOT NULL,QUANTITY INT NOT NULL,INCOMMING_PRICE VARCHAR(500) NOT NULL,SELLING_PRICE DECIMAL(10,2) NOT NULL,LASTUPDATED varchar(50) NOT NULL)";
            stmt.execute(phoneQuery);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
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
    }

    private void createAccessoriesStockTable() {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            
            String accessQuery = "CREATE TABLE ACCESSORIES_STOCK (ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), PRODUCTID varchar(500) NOT NULL,BRAND varchar(500) NOT NULL,MODEL varchar(500) NOT NULL,QUANTITY INTEGER NOT NULL,TYPE varchar(500) NOT NULL,INCOMMING_PRICE VARCHAR(500) NOT NULL,SELLING_PRICE DECIMAL(10,2) NOT NULL,LASTUPDATED varchar(50) NOT NULL,primary key (PRODUCTID,TYPE))";
            stmt.execute(accessQuery);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
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
    }

    private void createTransactionTable() {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            
            String transQuery = "CREATE TABLE TRANSACTIONS (ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), PRODUCTID varchar(500) NOT NULL,BRAND varchar(500) NOT NULL,MODEL varchar(500) NOT NULL,QUANTITY INTEGER NOT NULL,TYPE varchar(500) NOT NULL,OPERATION varchar(500),LASTUPDATED varchar(50) NOT NULL)";
            stmt.execute(transQuery);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
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
    }

    private void dropAllTable() {
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = "DROP TABLE USERS";
            System.out.println(stmt.executeUpdate(userQuery));
            
            userQuery = "DROP TABLE PHONE_STOCK";
            System.out.println(stmt.executeUpdate(userQuery));
            
            userQuery = "DROP TABLE ACCESSORIES_STOCK";
            System.out.println(stmt.executeUpdate(userQuery));
            
            userQuery = "DROP TABLE TRANSACTIONS";
            System.out.println(stmt.executeUpdate(userQuery));
            
            userQuery = "DROP TABLE CLIENTS";
            System.out.println(stmt.executeUpdate(userQuery));
            
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
    }

    private void createClientTable() {
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            
            String query = "CREATE TABLE CLIENTS (ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), CNAME varchar(2000) NOT NULL, CPHONE varchar(2000) NOT NULL, CADDRESS varchar(2000) NOT NULL, INVOICENO varchar(2000) NOT NULL, TOTAL varchar(2000) NOT NULL, GST varchar(2000) NOT NULL, PENDING varchar(2000) NOT NULL, PL varchar(2000) NOT NULL, LASTUPDATE varchar(2000) NOT NULL)";
            stmt.execute(query);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
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
    }
    
}
