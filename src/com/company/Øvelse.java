package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by edvardvb on 10.03.16.
 */
public class Øvelse {

    private int øid;
    private String navn;
    private String beskrivelse;
    private String type;
    private String resultat;
    private String belastning;
    private String antall_rep;
    private String antall_sett;
    private int lengde;
    private Date varighet;
    private String øvelsesGruppe;

    public Øvelse (String navn, String beskrivelse, String øvelsesGruppe, Connection conn) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.øvelsesGruppe = øvelsesGruppe;
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select max(id) from Øvelse");
                this.øid = rs.findColumn("max(id)") + 1;
            }
            catch(Exception e){
                System.out.println(e);
            }

    }

    public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select navn, beskrivelse, ØvelsesGruppe from Treningsøkt where id=" + øid);
            while (rs.next()) {
                this.navn =  rs.getString("navn");
                this.beskrivelse = rs.getString("beskrivelse");
                this.øvelsesGruppe = rs.getString("ØvelsesGruppe");
            }

        } catch (Exception e) {
            System.out.println("db error during select of øvelse= "+e);
            return;
        }

    }

    public void refresh (Connection conn) {
        initialize (conn);
    }

    public void save (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            if(varighet == null) {
                stmt.executeUpdate("insert into Øvelse values ("+øid+",'"+navn+"','"+beskrivelse+"','"+type+"','"+resultat+"','"+belastning+"',"+antall_rep+","+antall_sett+","+lengde+",NULL,'"+øvelsesGruppe+"');");
            }
            else{
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                String fVarighet = df.format(varighet);
                stmt.executeUpdate("insert into Øvelse values ("+øid+",'"+navn+"','"+beskrivelse+"','"+type+"','"+resultat+"','"+belastning+"',"+antall_rep+","+antall_sett+","+lengde+",'"+fVarighet+"','"+øvelsesGruppe+"');");
            }
            //kan bygge opp string i forkant for å lage en 'modulær' string
        } catch (Exception e) {
            System.out.println("db error during insert of øvelse="+e);
            return;
        }
    }

    public int getØid() {
        return øid;
    }

    public void setØid(int øid) {
        this.øid = øid;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public String getBelastning() {
        return belastning;
    }

    public void setBelastning(String belastning) {
        this.belastning = belastning;
    }

    public String getAntall_rep() {
        return antall_rep;
    }

    public void setAntall_rep(String antall_rep) {
        this.antall_rep = antall_rep;
    }

    public String getAntall_sett() {
        return antall_sett;
    }

    public void setAntall_sett(String antall_sett) {
        this.antall_sett = antall_sett;
    }

    public int getLengde() {
        return lengde;
    }

    public void setLengde(int lengde) {
        this.lengde = lengde;
    }

    public Date getVarighet() {
        return varighet;
    }

    public void setVarighet(Date varighet) {
        this.varighet = varighet;
    }

}
