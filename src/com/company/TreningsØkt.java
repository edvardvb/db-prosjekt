package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by edvardvb on 10.03.16.
 */
public class TreningsØkt {

    private Date dato;
    private int varighet;
    private String personlig_form;
    private String treningstips;
    private String værforhold;
    private String mal;
    private int temperatur;
    private int tilskuere;
    private String luftkvalitet;

    public TreningsØkt (Date dato, int varighet) {
        this.dato = dato;
        this.varighet = varighet;
    }

    public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select dato, varighet from Treningsøkt where dato=" + dato);
            while (rs.next()) {
                dato =  rs.getDate("dato");
                varighet = rs.getInt("varighet");
            }

        } catch (Exception e) {
            System.out.println("db error during select of treningsøkt= "+e);
            return;
        }

    }

    public void refresh (Connection conn) {
        initialize (conn);
    }

    public String save (Connection conn) throws Exception {
        try {
            Statement stmt = conn.createStatement();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fDato = df.format(dato);
            //kan bygge opp string i forkant for å lage en 'modulær' string
            stmt.executeUpdate("insert into Treningsøkt values ('"+fDato+"',"+varighet+",'"+personlig_form+"','"+treningstips+"','"+mal+"','"+værforhold+"',"+temperatur+","+tilskuere+",'"+luftkvalitet+"');");
            return fDato;
        } catch (Exception e) {
            System.out.println("db error during insert of Treningsøkt="+e);
            throw new Exception();
        }
    }
    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public String getPersonlig_form() {
        return personlig_form;
    }

    public void setPersonlig_form(String personlig_form) {
        this.personlig_form = personlig_form;
    }

    public String getTreningstips() {
        return treningstips;
    }

    public void setTreningstips(String treningstips) {
        this.treningstips = treningstips;
    }

    public String getVærforhold() {
        return værforhold;
    }

    public void setVærforhold(String værforhold) {
        this.værforhold = værforhold;
    }

    public String getMal() {
        return mal;
    }

    public void setMal(String mal) {
        this.mal = mal;
    }

    public int getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(int temperatur) {
        this.temperatur = temperatur;
    }

    public int getTilskuere() {
        return tilskuere;
    }

    public void setTilskuere(int tilskuere) {
        this.tilskuere = tilskuere;
    }

    public String getLuftkvalitet() {
        return luftkvalitet;
    }

    public void setLuftkvalitet(String luftkvalitet) {
        this.luftkvalitet = luftkvalitet;
    }

    public int getVarighet() {
        return varighet;
    }

    public void setVarighet(int varighet) {
        this.varighet = varighet;
    }
}
