package db;

import data.EventDAO;
import data.PersonDAO;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DBManager {

    private static String sqlInsertPerson = "INSERT INTO person (id,esv,notfallkontkt1,notfallkontakt2,personentyp,anrede,titel,vorname,nachname,strasse_hausnummer,plz,ort,telefonnummer,email,geburtsdatum,svnr,diagnose,allergien,sonstiges,beschaeftigung,amt,verwendungsgruppe,gehaltsstufe,wochenstunden,iban,bic,vorrueckdatum,einstelldatum,firmenname,firmentelefonnummer,firmenemail,geloescht) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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

    // returns a HashMap of all Eventprotokolls from the DB with Objects (event, employee, client)
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

    public void open() {
        conn = ConnectionFactory.getInstance().getConnection();
    }

    public void close() throws SQLException {
        ConnectionFactory.getInstance().close();
        conn.close();
    }
}
