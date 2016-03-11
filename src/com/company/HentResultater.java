package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by edvardvb on 11.03.16.
 */
public class HentResultater extends DBConn{

    public HentResultater() {
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAutoCommit of HentResultater="+e);
            return;
        }
    }

    public void getTopStrength(int number, int øvelse, String startDato, String sluttDato){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select treningsøkt, belastning, antall_rep, antall_sett from Resultat where øvelse="+øvelse+" and treningsøkt>'"+startDato+"' and treningsøkt<'"+sluttDato+"' order by belastning desc limit "+number+";");
            while(rs.next()) {
                System.out.println("| "+rs.getDate("treningsøkt") + " | " + rs.getString("belastning") + " | " + rs.getInt("antall_rep") + " | " + rs.getInt("antall_sett") + " |");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    public void getTopEndurance(int number, int øvelse, String startDato, String sluttDato){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select treningsøkt, lengde, varighet from Resultat where øvelse="+øvelse+" and treningsøkt>'"+startDato+"' and treningsøkt<'"+sluttDato+"' order by varighet asc limit "+number+";");
            while(rs.next()) {
                System.out.println("| "+rs.getDate("treningsøkt") + " | " + rs.getString("varighet") + " | " + rs.getInt("lengde") + " |" );
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
}
