package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by edvardvb on 10.03.16.
 */
public class Resultat {

    private String dato; //"YYYY-MM-DD HH:MM:SS"
    private int øvelse;
    private String kommentar;
    private String belastning;
    private int antall_rep;
    private int antall_sett;
    private int lengde;
    private String varighet; //"HH:MM:SS"

    public Resultat(String dato, int øvelse) {
        this.dato = dato;
        this.øvelse = øvelse;
    }

    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select kommentar from Resultat where øvelse=" + øvelse + " and dato='" + dato + "';");
            while (rs.next()) {
                this.kommentar = rs.getString("kommentar");
            }


        } catch (Exception e) {
            System.out.println("db error during select of Resultat= " + e);
            return;
        }

    }

    public void refresh(Connection conn) {
        initialize(conn);
    }

    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            //kan bygge opp string i forkant for å lage en 'modulær' string
            stmt.executeUpdate("insert into Resultat values ('" + dato + "'," + øvelse + ",'" + kommentar + "','" + belastning + "'," + antall_rep + "," + antall_sett + "," + lengde + ",'" + varighet + "');");
        } catch (Exception e) {
            System.out.println("db error during insert of resultat=" + e);
            return;
        }
    }
    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public int getØvelse() {
        return øvelse;
    }

    public void setØvelse(int øvelse) {
        this.øvelse = øvelse;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public String getBelastning() {
        return belastning;
    }

    public void setBelastning(String belastning) {
        this.belastning = belastning;
    }

    public int getAntall_rep() {
        return antall_rep;
    }

    public void setAntall_rep(int antall_rep) {
        this.antall_rep = antall_rep;
    }

    public int getAntall_sett() {
        return antall_sett;
    }

    public void setAntall_sett(int antall_sett) {
        this.antall_sett = antall_sett;
    }

    public int getLengde() {
        return lengde;
    }

    public void setLengde(int lengde) {
        this.lengde = lengde;
    }

    public String getVarighet() {
        return varighet;
    }

    public void setVarighet(String varighet) {
        this.varighet = varighet;
    }

}