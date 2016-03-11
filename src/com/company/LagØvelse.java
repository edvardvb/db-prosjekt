package com.company;

import java.sql.SQLException;
import java.util.Date;

public class LagØvelse extends DBConn {
    public Øvelse øvelse;
    public LagØvelse () {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagØvelse="+e);
            return;
        }
    }

    public void lagØvelse (String navn, String beskrivelse, String øvelsesGruppe){
        this.øvelse = new Øvelse(navn, beskrivelse, øvelsesGruppe, this.conn);
    }

    public void fullførØvelse () {
        øvelse.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagØvelse="+e);
            return;
        }
    }
}
