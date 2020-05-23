package db;

import data.EventDAO;
import data.PersonDAO;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DBManager {

    private static String sqlInsertPerson = "INSERT INTO person (id,esv,notfallkontkt1,notfallkontakt2,personentyp,anrede,titel,vorname,nachname,strasse_hausnummer,plz,ort,telefonnummer,email,geburtsdatum,svnr,diagnose,allergien,sonstiges,beschaeftigung,amt,verwendungsgruppe,gehaltsstufe,wochenstunden,iban,bic,vorrueckdatum,einstelldatum,firmenname,firmentelefonnummer,firmenemail,geloescht) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertAktivitaet = "INSERT INTO aktivitaet (id,datum,aktivitaetsbezeichnung,kategorie) VALUES (?,?,?,?)";
    private static String sqlInsertAktivitaetsprotokoll = "INSERT INTO aktivitaetsprotokoll (id,aktivitaet,mitarbeiter,klient,rechnung,startzeit,endzeit,jahr_Monat,stundensatz,fahrtkosten) VALUES (?,?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertRechnung = "INSERT INTO rechnung (rechnungsnummer,klient,ausstellungsdatum,verwendungszweck) VALUES (?,?,?,?)";
    private static String sqlInsertDokument = "INSERT INTO dokument (id,besitzerIdPerson,besitzerIdAktivitaet,pfad,dokumentenart,besitzer) VALUES (?,?,?,?,?,?)";

    private static PreparedStatement stmtInsertPerson = null;
    private static PreparedStatement stmtInsertAktivitaet = null;
    private static PreparedStatement stmtInsertAktiivitaetsprotokoll = null;
    private static PreparedStatement stmtInsertRechnung = null;
    private static PreparedStatement stmtInsertDokument = null;

    private static Connection conn = null;

    // returns a HashMap of all Persons from the DB
    private static HashMap<Integer, Person> getPersons() throws SQLException {
        HashMap<Integer,Person> pers = new HashMap<Integer, Person>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM person");
        while (rs.next()) {
            if (rs.getString("personentyp").equals("SONSTIGES")) {
                pers.put(rs.getInt("id"), Person.fromResults(rs));
            }
        }
        return pers;
    }

    // returns an HashMap of all Clients from the DB without Objects (esv, emergencycontact1, emergencycontact2)
    private static HashMap<Integer, Client> getClients() throws SQLException {
        HashMap<Integer, Client> clis = new HashMap<Integer, Client>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM person");
        while (rs.next()) {
            clis.put(rs.getInt("id"), Client.fromResults(rs));
        }
        return clis;
    }

    // returns an ArrayList of all Clients from the DB with Objects (esv, emergencycontact1, emergencycontact2)
    public static ArrayList<Client> getAllClients() throws SQLException {
        ArrayList<Client> clis = new ArrayList<Client>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM person");
        HashMap<Integer, Person> pers = getPersons();
        Client c = null;
        while (rs.next()) {
            if (rs.getString("personentyp").equals("KLIENT")) {
                c = Client.fromResults(rs);
                if (rs.getInt("esv") != 0) {
                    c.setEsv(pers.get(rs.getInt("esv")));
                }
                if (rs.getInt("notfall1") != 0) {
                    c.setEmergencyContact1(pers.get(rs.getInt("notfall1")));
                }
                if (rs.getInt("notfall2") != 0) {
                    c.setEmergencyContact2(pers.get(rs.getInt("notfall2")));
                }
                clis.add(c);
            }
        }
        return clis;
    }

    // returns an HashMap of all Emplayees from the DB
    public static HashMap<Integer, Employee> getAllEmployees() throws SQLException {
        HashMap<Integer, Employee> emps = new HashMap<Integer, Employee>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM person");
        while (rs.next()) {
            if (rs.getString("personentyp").equals("MITARBEITER")) {
                emps.put(rs.getInt("id"), Employee.fromResults(rs));
            }
        }
        return emps;
    }

    // returns an HashMap of all Sponsor from the DB
    public static HashMap<Integer, Sponsor> getAllSponsors() throws SQLException {
        HashMap<Integer, Sponsor> spns = new HashMap<Integer, Sponsor>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM person");
        while (rs.next()) {
            if (rs.getString("personentyp").equals("SPONSOR")) {
                spns.put(rs.getInt("id"), Sponsor.fromResults(rs));
            }
        }
        return spns;
    }

    // returns a HashMap of all Events from the DB
    private static HashMap<Integer, Event> getEvent() throws SQLException {
        HashMap<Integer, Event> evns = new HashMap<Integer, Event>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM aktivitaet");
        while (rs.next()) {
            evns.put(rs.getInt("id"), Event.fromResults(rs));
        }
        return evns;
    }

    // returns a HashMap of all Eventprotokolls from the DB without Objects (event, employee, client)
    private static HashMap<Integer, EventProtocol> getEventProtocolls() throws SQLException{
        HashMap<Integer, EventProtocol> evps = new HashMap<Integer, EventProtocol>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM aktivitaetsprotokoll");
        while (rs.next()) {
            evps.put(rs.getInt("id"), EventProtocol.fromResults(rs));
        }
        return evps;
    }

    // returns a ArrayList of all Eventprotokolls from the DB with Objects (event, employee, client)
    public static ArrayList<EventProtocol> getAllEventProtokolls() throws SQLException {
        ArrayList<EventProtocol> evps = new ArrayList<EventProtocol>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM aktivitaetsprotokoll");
        HashMap<Integer, Event> evns = getEvent();
        HashMap<Integer, Client> clis = getClients();
        HashMap<Integer, Employee> emps = getAllEmployees();
        HashMap<Integer, Bill> bils = getBills();
        EventProtocol e;
        while (rs.next()) {
            e = EventProtocol.fromResults(rs);
            if (rs.getInt("aktivitaet") != 0) {
                e.setEvent(evns.get(rs.getInt("aktivitaet")));
            }
            if (rs.getInt("mitarbeiter") != 0) {
                e.setEmployee(emps.get(rs.getInt("mitarbeiter")));
            }
            if (rs.getInt("klient") != 0) {
                e.setClient(clis.get(rs.getInt("klient")));
            }
            if (rs.getInt("rechnung") != 0) {
                e.setBill(bils.get(rs.getInt("rechnung")));
            }
            evps.add(e);
        }
        return evps;
    }

    // returns a HashMap of all Bills from the DB without Objects (client)
    private static HashMap<Integer, Bill> getBills() throws SQLException {
        HashMap<Integer, Bill> bils = new HashMap<Integer, Bill>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM rechnung");
        while (rs.next()) {
            bils.put(rs.getInt("rechnungsnummer"), Bill.fromResults(rs));
        }
        return bils;
    }

    // returns an ArrayList of all Bills from the DB
    public static ArrayList<Bill> getAllBills() throws SQLException {
        ArrayList<Bill> bils = new ArrayList<Bill>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM rechnung");
        HashMap<Integer, Client> clis = getClients();
        Bill b;
        while (rs.next()) {
            b = Bill.fromResults(rs);
            if (rs.getInt("klient") != 0) {
                b.setClient(clis.get(rs.getInt("klient")));
            }
            bils.add(b);
        }
        return bils;
    }

    // returns an ArrayList of all Documents from the DB with Objects (ownerPerson or ownerEvent)
    public static ArrayList<Document> getAllDocuments() throws SQLException {
        ArrayList<Document> docs = new ArrayList<Document>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM dokument");
        HashMap<Integer, EventProtocol> evps = getEventProtocolls();
        HashMap<Integer, Client> clis = getClients();
        HashMap<Integer, Employee> emps = getAllEmployees();
        HashMap<Integer, Sponsor> spns = getAllSponsors();
        Document d;
        while (rs.next()) {
            d = Document.fromResults(rs);
            if (Owner.valueOf(rs.getString("besitzer")) == Owner.Aktivitaet) {
                d.setOwnerEvent(evps.get(rs.getInt("besitzerIdAktivitaet")));
            }
            if (Owner.valueOf(rs.getString("besitzer")) == Owner.Klient) {
                d.setOwnerPerson(clis.get(rs.getInt("besitzerIdPerson")));
            }
            if (Owner.valueOf(rs.getString("besitzer")) == Owner.Mitarbeiter) {
                d.setOwnerPerson(emps.get(rs.getInt("besitzerIdPerson")));
            }
            if (Owner.valueOf(rs.getString("besitzer")) == Owner.Sponsor) {
                d.setOwnerPerson(spns.get(rs.getInt("besitzerIdPerson")));
            }
            docs.add(d);
        }
        return docs;
    }

    // creates the DB Connection and sends the PreparedStatements to the DB
    public void open() throws SQLException {
        conn = ConnectionFactory.getInstance().getConnection();
        stmtInsertPerson.executeUpdate(sqlInsertPerson, Statement.RETURN_GENERATED_KEYS);
        stmtInsertAktivitaet.executeUpdate(sqlInsertAktivitaet, Statement.RETURN_GENERATED_KEYS);
        stmtInsertAktiivitaetsprotokoll.executeUpdate(sqlInsertAktivitaetsprotokoll, Statement.RETURN_GENERATED_KEYS);
        stmtInsertRechnung.executeUpdate(sqlInsertRechnung, Statement.RETURN_GENERATED_KEYS);
        stmtInsertDokument.executeUpdate(sqlInsertDokument, Statement.RETURN_GENERATED_KEYS);
    }

    // closes the DB Connection and the PreparedStatements
    public void close() throws SQLException {
        if (conn != null) {
            ConnectionFactory.getInstance().close();
            conn.close();
        }
        if (stmtInsertPerson != null) {
            stmtInsertPerson.close();
            stmtInsertPerson = null;
        }
        if (stmtInsertAktivitaet != null) {
            stmtInsertAktivitaet.close();
            stmtInsertAktivitaet = null;
        }
        if (stmtInsertAktiivitaetsprotokoll != null) {
            stmtInsertAktiivitaetsprotokoll.close();
            stmtInsertAktiivitaetsprotokoll = null;
        }
        if (stmtInsertRechnung != null) {
            stmtInsertRechnung.close();
            stmtInsertRechnung = null;
        }
        if (stmtInsertDokument != null) {
            stmtInsertDokument.close();
            stmtInsertDokument = null;
        }
    }
}
