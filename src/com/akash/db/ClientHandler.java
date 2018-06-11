/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.akash.db;

import com.akash.src.ClientModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pratidhi Chowdhury
 */
public class ClientHandler {

    String url = "jdbc:derby:StockManager;create=true";
    Connection con = null;
    Statement stmt = null;

    public int addClient(String cname, String cphone, String caddress, String invno, String total, String gst, String pendingpayment, String pl, String lastupdated) {
        int res = 0;
        try {

            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("INSERT INTO CLIENTS(CNAME, CPHONE, CADDRESS, INVOICENO, TOTAL, GST, PENDING, PL, LASTUPDATE ) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                    cname, cphone, caddress, invno, total, gst, pendingpayment, pl, lastupdated);
            res = stmt.executeUpdate(userQuery);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return res;
    }

    public int updateClient(String id, String cname, String cphone, String caddress, String invno, String total, String gst, String pendingpayment, String pl, String lastupdated) {
        int res = 0;
        try {

            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("UPDATE CLIENTS SET CNAME = '%s', CPHONE = '%s', CADDRESS = '%s', INVOICENO = '%s', TOTAL = '%s', GST = '%s', PENDING = '%s', PL = '%s', LASTUPDATE = '%s' WHERE ID = %s",
                    cname, cphone, caddress, invno, total, gst, pendingpayment, pl, lastupdated, id);
            res = stmt.executeUpdate(userQuery);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return res;
    }

    public int deleteClient(String id) {
        int res = 0;
        try {

            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("DELETE FROM CLIENTS WHERE ID = %s", id);
            res = stmt.executeUpdate(userQuery);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return res;
    }

    public ResultSet getAllClients() {
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = "SELECT * FROM CLIENTS";
            rs = stmt.executeQuery(userQuery);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
    
    
    public ResultSet getAllClients(String type,String content) {
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = "SELECT * FROM CLIENTS WHERE "+type.toUpperCase()+" LIKE '%"+content+"%'";
            System.err.println(userQuery);
            rs = stmt.executeQuery(userQuery);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
    
    public ClientModel getClient(String id) {
        ClientModel c = new ClientModel();
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = String.format("SELECT * FROM CLIENTS WHERE ID = %s",id);
            ResultSet rs = stmt.executeQuery(userQuery);

            if(rs.next())
            {
                System.err.println(rs.getString(1));
                c.setId(rs.getString(1));
                c.setCname(rs.getString(2));
                c.setCphone(rs.getString(3));
                c.setCaddress(rs.getString(4));
                c.setInvno(rs.getString(5));
                c.setTotal(rs.getString(6));
                c.setGst(rs.getString(7));
                c.setPendingpayment(rs.getString(8));
                c.setPl(rs.getString(9));
                c.setLastupdated(rs.getString(10));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return c;
    }
    
    
    public float calculateProfit(String content) {
        ResultSet rs = null;
        float amt = 0f;
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
            String userQuery = "SELECT * FROM CLIENTS WHERE LASTUPDATE LIKE '%"+content+"%'";
            System.err.println(userQuery);
            rs = stmt.executeQuery(userQuery);
            while(rs.next())
            {
                amt += Float.parseFloat(rs.getString(9));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return amt;
    }
    
}
