/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akash.db;

import com.akash.src.PhoneModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author akash
 */
public class PhoneStockHandler {
    
    String url = "jdbc:derby:StockManager;create=true";
    Connection con = null;
    Statement stmt = null;
    
    public int addStock(String imei,String brand,String model, String quantity,String incommingPrice, float sellingPrice, String udate)
    {
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("INSERT INTO PHONE_STOCK(IMEI,BRAND,MODEL,QUANTITY,INCOMMING_PRICE,SELLING_PRICE,LASTUPDATED) VALUES ('%s','%s','%s',%s,'%s',%f,'%s')",
                                        imei,brand,model,quantity,incommingPrice,sellingPrice,udate);
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
    
    public PhoneModel getProduct(String imei)
    {
        PhoneModel p = new PhoneModel();
        try{
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM PHONE_STOCK WHERE IMEI='%s'",imei);

            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setImei(rs.getString(2));
                p.setBrand(rs.getString(3));
                p.setModel(rs.getString(4));
                p.setQuantity(rs.getInt(5));
                p.setIncommingPrice(rs.getString(6));
                p.setSellingPrice(rs.getFloat(7));
                p.setLastUpdatedDate(rs.getString(8));
            
            }
            
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
        
        return p;
    }

    public int updateStock(String imei,String brand,String model, String quantity,String incommingPrice, float sellingPrice, String udate)
    {
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("UPDATE PHONE_STOCK SET BRAND='%s',MODEL='%s',QUANTITY=%s,INCOMMING_PRICE='%s',SELLING_PRICE=%f,LASTUPDATED='%s' WHERE IMEI='%s'",
                                        brand,model,quantity,incommingPrice,sellingPrice,udate,imei);
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
    
    public int deleteStock(String imei)
    {
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("DELETE FROM PHONE_STOCK WHERE IMEI='%s'",imei);
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
    
    public ResultSet getAllProduct()
    {
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url);
            con.setAutoCommit(true);
            stmt = con.createStatement();
            String query = "SELECT * FROM PHONE_STOCK";

            rs = stmt.executeQuery(query);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
             
        
        return rs;
    }
    
    public ResultSet searchProductWithIndex(String index,String data)
    {
        ResultSet rs = null;
        try{
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String query = "SELECT * FROM PHONE_STOCK WHERE "+index.toUpperCase()+" LIKE '%"+data+"%' AND QUANTITY > 0";
            System.out.println(query);
            rs = stmt.executeQuery(query);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return rs;
    }

    public int decreamentStockQuantity(String imei,String qty)
    {
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("UPDATE PHONE_STOCK SET QUANTITY=QUANTITY-%s WHERE IMEI='%s'",qty,imei);
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
}
