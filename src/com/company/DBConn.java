/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company;

/**
 *
 * @author Svein Erik
 */

import java.sql.*;
import java.util.Properties;

public abstract class DBConn {
    protected Connection conn;
    public DBConn () {
    }
    public void connect() {
    	try {
            java.sql.Driver d = new com.mysql.jdbc.Driver();
            // Properties for user and password. Here the user and password are both 'paulr'
            Properties p = new Properties();
            p.put("user", "root");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mydb?autoReconnect=true&useSSL=false",p);
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }
}