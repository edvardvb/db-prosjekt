package com.company;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);

    private void registrer_økt_usecase() throws Exception {
        LagTreningsØkt conn = new LagTreningsØkt();

        System.out.println("Velg dato - YYYY MM DD HH MM");
        int year = Integer.parseInt(sc.nextLine())-1900;
        int month = Integer.parseInt(sc.nextLine())-1;
        int day = Integer.parseInt(sc.nextLine());
        int hour = Integer.parseInt(sc.nextLine());
        int minutes = Integer.parseInt(sc.nextLine());
        System.out.println("Du skal nå fylle inn informasjon om økta, skriv NULL for å hoppe over et felt");
        System.out.println("Ønsker du å følge en mal? - ja/nei");
        String line = sc.nextLine();
        if(line.equals("ja")){
            System.out.println("Nei, det gjør du ikke");
        }
        System.out.println("Varighet - Tall i minutter - Kan ikke hoppe over");
        int varighet = Integer.parseInt(sc.nextLine());
        conn.lagTreningsØkt(new Date(year, month, day, hour, minutes, 0), varighet);
        System.out.println("Personlig form - Tekst");
        line = sc.nextLine();;
        if(!line.equals("NULL")) {
            conn.økt.setPersonlig_form(line);
        }
        System.out.println("Treningstips - Tekst");
        line = sc.nextLine();
        if(!line.equals("NULL")) {
            conn.økt.setTreningstips(line);
        }
        System.out.println("Værforhold - Tekst");
        line = sc.nextLine();
        if(!line.equals("NULL")) {
            conn.økt.setVærforhold(line);
        }
        System.out.println("Temperatur - heltall");
        line = sc.nextLine();
        if(!line.equals("NULL")) {
            conn.økt.setTemperatur(Integer.parseInt(line));
        }
        System.out.println("Tilskuere - heltall");
        line = sc.nextLine();
        if(!line.equals("NULL")) {
            conn.økt.setTilskuere(Integer.parseInt(line));
        }
        System.out.println("Luftkvalitet - tekst");
        line = sc.nextLine();
        if(!line.equals("NULL")) {
            conn.økt.setLuftkvalitet(line);
        }
        String øktString = "";
        try{
            øktString = conn.fullførØkt();
        }
        catch (Exception e){
            throw new Exception();
        }
        HashMap<Integer, String> øvelser = new HashMap<Integer, String>();
        Statement stmt = conn.conn.createStatement();
        while (true) {
            System.out.println("Vil du legge til en eller flere øvelser? Skriv navnet på en øvelse, eller NULL for å avbryte");
            line = sc.nextLine();
            if(line.equals("NULL")) {
                break;
            }
            ResultSet rs = stmt.executeQuery("select id, navn from Øvelse where navn ='"+line+"';");
            rs.next();
            øvelser.put(rs.getInt("id"), rs.getString("navn"));
        }
        for(Integer id : øvelser.keySet()){
            System.out.println("Fyll inn litt informasjon om øvelse "+ øvelser.get(id) +" - NULL for å hoppe over");
            LagResultat rConn = new LagResultat();
            rConn.lagResultat(øktString, id);
            System.out.println("Belastning - Tekst eller tall ");
            line = sc.nextLine();
            if(!line.equals("NULL")){
                rConn.resultat.setBelastning(line);
            }
            System.out.println("Antall repetisjoner - heltall ");
            line = sc.nextLine();
            if(!line.equals("NULL")){
                rConn.resultat.setAntall_rep(Integer.parseInt(line));
            }
            System.out.println("Antall sett - heltall ");
            line = sc.nextLine();
            if(!line.equals("NULL")){
                rConn.resultat.setAntall_sett(Integer.parseInt(line));
            }
            System.out.println("Lengde - heltall ");
            line = sc.nextLine();
            if(!line.equals("NULL")){
                rConn.resultat.setLengde(Integer.parseInt(line));
            }
            System.out.println("Varighet - HH:MM:SS");
            line = sc.nextLine();
            if(!line.equals("NULL")){
                rConn.resultat.setVarighet(line);
            }
            System.out.println("Kommentar - Tekst");
            line = sc.nextLine();
            if(!line.equals("NULL")){
                rConn.resultat.setKommentar(line);
            }
            rConn.fullførResultat();
        }

    }

    private void getTopTen(){
        HentResultater hr = new HentResultater();
        System.out.println("Hvor mange vil du ha med i lista?");
        int number = Integer.parseInt(sc.nextLine());
        try {
            Statement stmt = hr.conn.createStatement();
            System.out.println("Styrke eller utholdenhet?");
            String line = sc.nextLine();
            switch (line) {
                case "styrke":
                    System.out.println("Velg øvelse, skriv navnet nøyaktig");
                    String line3 = sc.nextLine();
                    int id = 0;
                    try {
                        ResultSet rs = stmt.executeQuery("select id from Øvelse where navn ='" + line3 + "';");
                        rs.next();
                        id = rs.getInt("id");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println("Skriv inn startdato - YYYY-MM-DD HH:MM:SS");
                    String startDato = sc.nextLine();
                    System.out.println("Skriv inn sluttdato - YYYY-MM-DD HH:MM:SS");
                    String sluttDato = sc.nextLine();
                    hr.getTopStrength(number, id, startDato, sluttDato);
                case "utholdenhet":
                    System.out.println("Velg øvelse, skriv navnet nøyaktig");
                    String line2 = sc.nextLine();
                    id = 0;
                    try {
                        ResultSet rs = stmt.executeQuery("select id from Øvelse where navn ='" + line2 + "';");
                        rs.next();
                        id = rs.getInt("id");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println("Skriv inn startdato - YYYY-MM-DD HH:MM:SS");
                    startDato = sc.nextLine();
                    System.out.println("Skriv inn sluttdato - YYYY-MM-DD HH:MM:SS");
                    sluttDato = sc.nextLine();
                    hr.getTopEndurance(number, id, startDato, sluttDato);
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }

    }


    private void getTreningsNotater() throws Exception{
        HentNotater conn = new HentNotater();
        Statement stmt = conn.conn.createStatement();
        ResultSet resultSet = stmt.executeQuery("select Treningsøkt.dato, Treningsøkt.treningstips, Resultat.kommentar from Treningsøkt left join Resultat on Treningsøkt.dato = Resultat.treningsøkt;");
        while (resultSet.next()) {
            System.out.print("Tidspunkt: ");
            System.out.println(resultSet.getString(1));
            System.out.println("Treningsnotat fra økten");
            System.out.println(resultSet.getString(2));
            System.out.println("Kommentar fra resultatet");
            System.out.println(resultSet.getString(3));
            System.out.println("-------------------------------");
        }
    }


    public static void main(String[] args) throws Exception {
        Main m = new Main();
        System.out.println("1 - Legg inn ny treningsøkt med resultater");
        System.out.println("2 - Hent top X for øvelse Y i periode A->B");
        System.out.println("Type 1, 2 or 3");
        int scase = Integer.parseInt(m.sc.nextLine());
        switch (scase) {
            case 1:
                m.registrer_økt_usecase();
                break;
            case 2:
                m.getTopTen();
                break;
            case 3:
                m.getTreningsNotater();
                break;
        }
    }


}


