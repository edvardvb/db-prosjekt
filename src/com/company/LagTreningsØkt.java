package com.company;

import java.sql.SQLException;
import java.util.Date;

public class LagTreningsØkt extends DBConn {
    public TreningsØkt økt;
    public LagTreningsØkt () {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagTreningsØkt="+e);
            return;
        }
    }

    public void lagTreningsØkt (Date dato, int varighet){
        this.økt = new TreningsØkt (dato, varighet);
    }

    public String fullførØkt () throws Exception {
        String fDato = økt.save(conn);
        try {
            conn.commit();
            return fDato;
        } catch (SQLException e) {
            System.out.println("db error during commit of LagTreningsØkt="+e);
            throw new Exception();
        }
    }
}
