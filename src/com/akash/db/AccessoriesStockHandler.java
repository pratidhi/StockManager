/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akash.db;

import com.akash.src.AccessoriesModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author akash
 */
public class AccessoriesStockHandler {
    String url = "jdbc:derby:StockManager;create=true";
    Connection con = null;
    Statement stmt = null;
    
    public int addStock(String pid,String brand,String model, String quantity,String incommingPrice, float sellingPrice, String udate, String pType)
    {
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("INSERT INTO ACCESSORIES_STOCK(PRODUCTID,BRAND,MODEL,QUANTITY,TYPE,INCOMMING_PRICE,SELLING_PRICE,LASTUPDATED) VALUES ('%s','%s','%s',%s,'%s','%s',%f,'%s')",
                                        pid,brand,model,quantity,pType,incommingPrice,sellingPrice,udate);
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
    
    public AccessoriesModel getProduct(String pid,String pType)
    {
        AccessoriesModel p = new AccessoriesModel();
        try{
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String query = String.format("SELECT * FROM ACCESSORIES_STOCK WHERE PRODUCTID='%s' AND TYPE='%s'",pid,pType);

            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setpID(rs.getString(2));
                p.setBrand(rs.getString(3));
                p.setModel(rs.getString(4));
                p.setQuantity(rs.getInt(5));
                p.setpType(rs.getString(6));
                p.setIncommingPrice(rs.getString(7));
                p.setSellingPrice(rs.getFloat(8));
                p.setLastUpdatedDate(rs.getString(9));
            
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

    public int updateStock(String pid,String brand,String model, String quantity,String incommingPrice, float sellingPrice, String udate,String pType)
    {
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("UPDATE ACCESSORIES_STOCK SET BRAND='%s',MODEL='%s',QUANTITY=%s,INCOMMING_PRICE='%s',SELLING_PRICE=%f,LASTUPDATED='%s' WHERE PRODUCTID='%s' AND TYPE='%s'",
                                        brand,model,quantity,incommingPrice,sellingPrice,udate,pid,pType);
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
    
    public int deleteStock(String pid, String pType)
    {
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("DELETE FROM ACCESSORIES_STOCK WHERE PRODUCTID='%s' AND TYPE='%s'",pid,pType);
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
            stmt = con.createStatement();
            String query = "SELECT * FROM ACCESSORIES_STOCK";

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
            String query = "SELECT * FROM ACCESSORIES_STOCK WHERE "+index.toUpperCase()+" LIKE '%"+data+"%' AND QUANTITY > 0";
            System.out.println(query);
            rs = stmt.executeQuery(query);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return rs;
    }
    
    public int decreamentStockQuantity(String pid,String type,String qty)
    {
        int res = 0;
        try{
           
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("UPDATE ACCESSORIES_STOCK SET QUANTITY=QUANTITY-%s WHERE PRODUCTID='%s' AND TYPE='%s'",qty,pid,type);
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
