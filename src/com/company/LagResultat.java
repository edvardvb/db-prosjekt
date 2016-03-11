package com.company;

import java.sql.SQLException;

public class LagResultat extends DBConn {
    public Resultat resultat;

    public LagResultat() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagResultat="+e);
            return;
        }
    }

    public void lagResultat (String dato, int øvelse){
        this.resultat = new Resultat(dato, øvelse);
    }

    public void fullførResultat () {
        resultat.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagResultat="+e);
            return;
        }
    }
}
