package com.company;

import java.sql.SQLException;
/**
 * Created by vemund on 11.03.16.
 */
public class HentNotater extends DBConn{
    public HentNotater() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of HentNotater="+e);
        }
    }
}
