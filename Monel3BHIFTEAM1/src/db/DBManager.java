package db;

import data.EventDAO;
import data.PersonDAO;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DBManager {

    private static String sqlInsertPerson = "INSERT INTO person (personentyp,anrede,titel,vorname,nachname,strasse_hausnummer,plz,ort,telefonnummer,email,geburtsdatum) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertClient = "INSERT INTO person (Personentyp,anrede,titel,vorname,nachname,strasse_hausnummer,plz,ort,telefonnummer,email,geburtsdatum,esv,notfallkontakt1,notfallkontakt2,svnr,diagnose,allergien,sonstiges,beschaeftigung) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertEmployee = "INSERT INTO person (personentyp,anrede,titel,vorname,nachname,strasse_hausnummer,plz,ort,telefonnummer,email,geburtsdatum,svnr,amt,verwendungsgruppe,gehaltsstufe,wochenstunden,iban,bic,vorrueckdatum,einstelldatum) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertSponsor = "INSERT INTO person (personentyp,anrede,titel,vorname,nachname,strasse_hausnummer,plz,ort,telefonnummer,email,geburtsdatum,firmenname,firmentelefonnummer,firmenemail) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertEvent = "INSERT INTO aktivitaet (datum,aktivitaetsbezeichnung,kategorie) VALUES (?,?,?)";
    private static String sqlInsertEventprotocol = "INSERT INTO aktivitaetsprotokoll (aktivitaet,mitarbeiter,klient,rechnung,startzeit,endzeit,jahr_Monat,stundensatz,fahrtkosten) VALUES (?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertBill = "INSERT INTO rechnung (klient,ausstellungsdatum,verwendungszweck) VALUES (?,?,?)";
    private static String sqlInsertDocument = "INSERT INTO dokument (besitzerIdPerson,besitzerIdAktivitaet,pfad,dokumentenart,besitzer) VALUES (?,?,?,?,?)";

    private static PreparedStatement stmtInsertPerson = null;
    private static PreparedStatement stmtInsertClient = null;
    private static PreparedStatement stmtInsertEmployee = null;
    private static PreparedStatement stmtInsertSponsor = null;
    private static PreparedStatement stmtInsertEvent = null;
    private static PreparedStatement stmtInsertEventprotocol = null;
    private static PreparedStatement stmtInsertBill = null;
    private static PreparedStatement stmtInsertDocument = null;

    private static Connection conn = null;

    // inserts a Person into the DB
    public static int insertPerson(Person p) {
        int newId = -1;
        try {
            stmtInsertPerson.setString(1, "SONSTIGES");
            stmtInsertPerson.setString(2, p.getSalutation().toString());
            if (p.getTitle() != null) {
                stmtInsertPerson.setString(3, p.getTitle());
            } else {
                stmtInsertPerson.setNull(3, Types.NULL);
            }
            stmtInsertPerson.setString(4, p.getFirstName());
            stmtInsertPerson.setString(5, p.getLastName());
            stmtInsertPerson.setString(6, p.getStreetAndNr());
            stmtInsertPerson.setInt(7, p.getZipCode());
            stmtInsertPerson.setString(8, p.getPlace());
            stmtInsertPerson.setString(9, p.getTelNr());
            if (p.getEmail() != null) {
                stmtInsertPerson.setString(10, p.getEmail());
            } else {
                stmtInsertPerson.setNull(10, Types.NULL);
            }
            if (p.getBirthDate() != null) {
                stmtInsertPerson.setDate(11, Date.valueOf(p.getBirthDate()));
            } else {
                stmtInsertPerson.setNull(11, Types.NULL);
            }
            boolean added = (stmtInsertPerson.executeUpdate() == 1);
            ResultSet rs = stmtInsertPerson.getGeneratedKeys();
            if(rs != null && rs.next()) {
                newId = rs.getInt(1);
            }
            stmtInsertPerson.clearParameters();
            if (!added) {
                newId = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    // inserts a Klient into the DB
    public static int insertClient(Client c) {
        int newId = -1;
        try {
            stmtInsertClient.setString(1, "KLIENT");
            stmtInsertClient.setString(2, c.getSalutation().toString());
            if (c.getTitle() != null) {
                stmtInsertClient.setString(3, c.getTitle());
            } else {
                stmtInsertClient.setNull(3, Types.NULL);
            }
            stmtInsertClient.setString(4, c.getFirstName());
            stmtInsertClient.setString(5, c.getLastName());
            stmtInsertClient.setString(6, c.getStreetAndNr());
            stmtInsertClient.setInt(7, c.getZipCode());
            stmtInsertClient.setString(8, c.getPlace());
            stmtInsertClient.setString(9, c.getTelNr());
            if(c.getEmail() != null) {
                stmtInsertClient.setString(10, c.getEmail());
            } else {
                stmtInsertClient.setNull(10, Types.NULL);
            }
            if (c.getBirthDate() != null) {
                stmtInsertClient.setDate(11, Date.valueOf(c.getBirthDate()));
            } else {
                stmtInsertClient.setNull(11, Types.NULL);
            }
            if (c.getEsv() != null) {
                stmtInsertClient.setInt(12, c.getEsv().getId());
            } else {
                stmtInsertClient.setNull(12, Types.NULL);
            }
            stmtInsertClient.setInt(13, c.getEmergencyContact1().getId());
            if (c.getEmergencyContact2() != null) {
                stmtInsertClient.setInt(14, c.getEmergencyContact2().getId());
            } else {
                stmtInsertClient.setNull(14, Types.NULL);
            }
            stmtInsertClient.setLong(15, c.getSsnr());
            stmtInsertClient.setString(16, c.getDiagnose());
            if (c.getAllergies() != null) {
                stmtInsertClient.setString(17, c.getAllergies());
            } else {
                stmtInsertClient.setNull( 17, Types.NULL);
            }
            if (c.getOther() != null) {
                stmtInsertClient.setString(18, c.getOther());
            } else {
                stmtInsertClient.setNull(18, Types.NULL);
            }
            if (c.getJob() != null) {
                stmtInsertClient.setString(19, c.getJob());
            } else {
                stmtInsertClient.setNull(19, Types.NULL);
            }
            boolean added = (stmtInsertClient.executeUpdate() == 1);
            ResultSet rs = stmtInsertClient.getGeneratedKeys();
            if(rs != null && rs.next()) {
                newId = rs.getInt(1);
            }
            stmtInsertClient.clearParameters();
            if (!added) {
                newId = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    // inserts an Employee into the DB
    public static int insertEmployee(Employee e) {
        int newId = -1;
        try {
            stmtInsertEmployee.setString(1, "MITARBEITER");
            stmtInsertEmployee.setString(2, e.getSalutation().toString());
            if (e.getTitle() != null) {
                stmtInsertEmployee.setString(3, e.getTitle());
            } else {
                stmtInsertEmployee.setNull(3, Types.NULL);
            }
            stmtInsertEmployee.setString(4, e.getFirstName());
            stmtInsertEmployee.setString(5, e.getLastName());
            stmtInsertEmployee.setString(6, e.getStreetAndNr());
            stmtInsertEmployee.setInt(7, e.getZipCode());
            stmtInsertEmployee.setString(8, e.getPlace());
            stmtInsertEmployee.setString(9, e.getTelNr());
            if (e.getEmail() != null) {
                stmtInsertEmployee.setString(10, e.getEmail());
            } else {
                stmtInsertEmployee.setString(10, e.getEmail());
            }
            if (e.getBirthDate() != null) {
                stmtInsertEmployee.setDate(11, Date.valueOf(e.getBirthDate()));
            } else {
                stmtInsertEmployee.setNull(11, Types.NULL);
            }
            stmtInsertEmployee.setLong(12, e.getSsnr());
            stmtInsertEmployee.setInt(13, (e.isVolunteering() ? 1 : 0));
            stmtInsertEmployee.setString(14, e.getOccupationGroup().toString());
            stmtInsertEmployee.setString(15, e.getSalaryLevel().toString());
            if (e.getHoursPerWeek() != 0) {
                stmtInsertEmployee.setInt(16, e.getHoursPerWeek());
            } else {
                stmtInsertEmployee.setNull(16, Types.NULL);
            }
            stmtInsertEmployee.setString(17, e.getIban());
            stmtInsertEmployee.setString(18, e.getBic());
            if (e.getDateSalaryLevel() != null) {
                stmtInsertEmployee.setDate(19, Date.valueOf(e.getDateSalaryLevel()));
            } else {
                stmtInsertEmployee.setNull(19, Types.NULL);
            }
            if (e.getDateOfEmployment() != null) {
                stmtInsertEmployee.setDate(20, Date.valueOf(e.getDateOfEmployment()));
            } else {
                stmtInsertEmployee.setNull(20, Types.NULL);
            }
            boolean added = (stmtInsertEmployee.executeUpdate() == 1);
            ResultSet rs = stmtInsertEmployee.getGeneratedKeys();
            if(rs != null && rs.next()) {
                newId = rs.getInt(1);
            }
            stmtInsertEmployee.clearParameters();
            if (!added) {
                newId = -1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return newId;
    }

    // inserts a Sponsor into the DB
    public static int insertSponsor(Sponsor s) {
        int newId = -1;
        try {
            stmtInsertSponsor.setString(1, "SPONSOR");
            stmtInsertSponsor.setString(2, s.getSalutation().toString());
            stmtInsertSponsor.setString(3, s.getTitle());
            stmtInsertSponsor.setString(4, s.getFirstName());
            stmtInsertSponsor.setString(5, s.getLastName());
            stmtInsertSponsor.setString(6, s.getStreetAndNr());
            stmtInsertSponsor.setInt(7, s.getZipCode());
            stmtInsertSponsor.setString(8, s.getPlace());
            stmtInsertSponsor.setString(9, s.getTelNr());
            stmtInsertSponsor.setString(10, s.getEmail());
            stmtInsertSponsor.setDate(11, Date.valueOf(s.getBirthDate()));
            stmtInsertSponsor.setString(12, s.getCompanyName());
            stmtInsertSponsor.setString(13, s.getCompanyTelNr());
            stmtInsertSponsor.setString(14, s.getCompanyEmail());
            boolean added = (stmtInsertSponsor.executeUpdate() == 1);
            ResultSet rs = stmtInsertSponsor.getGeneratedKeys();
            if(rs != null && rs.next()) {
                newId = rs.getInt(1);
            }
            stmtInsertSponsor.clearParameters();
            if (!added) {
                newId = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    // inserts an Event into the DB
    public static int insertEvent(Event e) {
        int newId = -1;
        try {
            stmtInsertEvent.setDate(1, Date.valueOf(e.getDate()));
            stmtInsertEvent.setString(2, e.getName());
            stmtInsertEvent.setInt(3, (e.getIsGroup() ? 1 : 0));
            boolean added = (stmtInsertEvent.executeUpdate() == 1);
            ResultSet rs = stmtInsertEvent.getGeneratedKeys();
            if(rs != null && rs.next()) {
                newId = rs.getInt(1);
            }
            stmtInsertEvent.clearParameters();
            if (!added) {
                newId = -1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return newId;
    }

    // inserts an Eventprotocol into the DB
    public static int insertEventprotocol(EventProtocol ep) {
        int newId = -1;
        try {
            if (ep.getEvent() != null) {
                stmtInsertEventprotocol.setInt(1, ep.getEvent().getId());
            } else {
                stmtInsertEventprotocol.setNull(1, Types.NULL);
            }
            if (ep.getEmployee() != null) {
                stmtInsertEventprotocol.setInt(2, ep.getEmployee().getId());
            } else {
                stmtInsertEventprotocol.setNull(2, Types.NULL);
            }
            if (ep.getClient() != null) {
                stmtInsertEventprotocol.setInt(3, ep.getClient().getId());
            } else {
                stmtInsertEventprotocol.setNull(3, Types.NULL);
            }
            if (ep.getBill() != null) {
                stmtInsertEventprotocol.setInt(4, ep.getBill().getNr());
            } else {
                stmtInsertEventprotocol.setNull(4, Types.NULL);
            }
            stmtInsertEventprotocol.setTime(5, Time.valueOf(ep.getStartTime()));
            stmtInsertEventprotocol.setTime(6, Time.valueOf(ep.getEndTime()));
            stmtInsertEventprotocol.setDate(7, Date.valueOf(ep.getYear_month()));
            stmtInsertEventprotocol.setDouble(8, ep.getHourlyRate()); // must be changed to decimal
            stmtInsertEventprotocol.setDouble(9, ep.getRideCosts()); // must be changed to decimal
            boolean added = (stmtInsertEventprotocol.executeUpdate() == 1);
            ResultSet rs = stmtInsertEventprotocol.getGeneratedKeys();
            if(rs != null && rs.next()) {
                newId = rs.getInt(1);
            }
            stmtInsertEventprotocol.clearParameters();
            if (!added) {
                newId = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    // inserts a Bill into the DB
    public static int insertBill(Bill b) {
        int newId = -1;
        try {
            stmtInsertBill.setInt(1, b.getClient().getId());
            stmtInsertBill.setDate(2, Date.valueOf(b.getDateOfIssue()));
            stmtInsertBill.setString(3, b.getUse());
            boolean added = (stmtInsertBill.executeUpdate() == 1);
            ResultSet rs = stmtInsertBill.getGeneratedKeys();
            if(rs != null && rs.next()) {
                newId = rs.getInt(1);
            }
            stmtInsertBill.clearParameters();
            if (!added) {
                newId = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    // inserts a Document into the DB
    public static int insertDocument(Document d) {
        int newId = -1;
        try {
            if (d.getOwnerPerson() != null) {
                stmtInsertDocument.setInt(1, d.getOwnerPerson().getId());
            } else {
                stmtInsertDocument.setNull(1, Types.NULL);
            }
            if (d.getOwnerEvent() != null) {
                stmtInsertDocument.setInt(2, d.getOwnerEvent().getId());
            } else {
                stmtInsertDocument.setNull(2, Types.NULL);
            }
            stmtInsertDocument.setString(3, d.getPath());
            stmtInsertDocument.setString(4, d.getDocumentType());
            stmtInsertDocument.setString(5, d.getOwner().toString());
            boolean added = (stmtInsertDocument.executeUpdate() == 1);
            ResultSet rs = stmtInsertDocument.getGeneratedKeys();
            if(rs != null && rs.next()) {
                newId = rs.getInt(1);
            }
            stmtInsertDocument.clearParameters();
            if (!added) {
                newId = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId;
    }

    public static Bill createBill(Client c, Date month) { return null; } //TODO

    // returns a HashMap of all Persons from the DB
    private static HashMap<Integer, Person> getPersons() throws SQLException {
        HashMap<Integer,Person> pers = new HashMap<Integer, Person>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM person");
        while (rs.next()) {
            if (rs.getString("personentyp").equals("SONSTIGES") && rs.getInt("geloescht") != 1) {
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
            if (rs.getString("personentyp").equals("KLIENT") && rs.getInt("geloescht") != 1) {
                clis.put(rs.getInt("id"), Client.fromResults(rs));
            }
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
            if (rs.getString("personentyp").equals("KLIENT") && rs.getInt("geloescht") != 1) {
                c = Client.fromResults(rs);
                if (rs.getInt("esv") != 0) {
                    c.setEsv(pers.get(rs.getInt("esv")));
                }
                if (rs.getInt("notfallkontakt1") != 0) {
                    c.setEmergencyContact1(pers.get(rs.getInt("notfallkontakt1")));
                }
                if (rs.getInt("notfallkontakt2") != 0) {
                    c.setEmergencyContact2(pers.get(rs.getInt("notfallkontakt2")));
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
            if (rs.getString("personentyp").equals("MITARBEITER") && rs.getInt("geloescht") != 1) {
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
            if (rs.getString("personentyp").equals("SPONSOR") && rs.getInt("geloescht") != 1) {
                spns.put(rs.getInt("id"), Sponsor.fromResults(rs));
            }
        }
        return spns;
    }

    // returns a HashMap of all Events from the DB
    public static HashMap<Integer, Event> getAllEvents() throws SQLException {
        HashMap<Integer, Event> evns = new HashMap<Integer, Event>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM aktivitaet");
        while (rs.next()) {
            evns.put(rs.getInt("id"), Event.fromResults(rs));
        }
        return evns;
    }

    // returns a HashMap of all Eventprotokolls from the DB without Objects (event, employee, client)
    private static HashMap<Integer, EventProtocol> getEventprotocols() throws SQLException{
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
        HashMap<Integer, Event> evns = getAllEvents();
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

    // returns an ArrayList of all Bills from a Client from the DB
    public static ArrayList<Bill> getAllBills(Client c) throws SQLException {
        ArrayList<Bill> bils = new ArrayList<Bill>();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM rechnung WHERE klient = " + c.getId());
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
        HashMap<Integer, EventProtocol> evps = getEventprotocols();
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
    public static void open() throws NullPointerException, SQLException {
        conn = ConnectionFactory.getInstance().getConnection();
        stmtInsertPerson = conn.prepareStatement(sqlInsertPerson, Statement.RETURN_GENERATED_KEYS);
        stmtInsertClient = conn.prepareStatement(sqlInsertClient, Statement.RETURN_GENERATED_KEYS);
        stmtInsertEmployee = conn.prepareStatement(sqlInsertEmployee, Statement.RETURN_GENERATED_KEYS);
        stmtInsertSponsor = conn.prepareStatement(sqlInsertSponsor, Statement.RETURN_GENERATED_KEYS);
        stmtInsertEvent = conn.prepareStatement(sqlInsertEvent, Statement.RETURN_GENERATED_KEYS);
        stmtInsertEventprotocol = conn.prepareStatement(sqlInsertEventprotocol, Statement.RETURN_GENERATED_KEYS);
        stmtInsertBill = conn.prepareStatement(sqlInsertBill, Statement.RETURN_GENERATED_KEYS);
        stmtInsertDocument = conn.prepareStatement(sqlInsertDocument, Statement.RETURN_GENERATED_KEYS);
    }

    // closes the DB Connection and the PreparedStatements
    public static void close() throws NullPointerException, SQLException {
        if (stmtInsertPerson != null) {
            stmtInsertPerson.close();
            stmtInsertPerson = null;
        }
        if (stmtInsertClient != null) {
            stmtInsertClient.close();
            stmtInsertClient = null;
        }
        if (stmtInsertEmployee != null) {
            stmtInsertEmployee.close();
            stmtInsertEmployee = null;
        }
        if (stmtInsertSponsor != null) {
            stmtInsertSponsor.close();
            stmtInsertSponsor = null;
        }
        if (stmtInsertEvent != null) {
            stmtInsertEvent.close();
            stmtInsertEvent = null;
        }
        if (stmtInsertEventprotocol != null) {
            stmtInsertEventprotocol.close();
            stmtInsertEventprotocol = null;
        }
        if (stmtInsertBill != null) {
            stmtInsertBill.close();
            stmtInsertBill = null;
        }
        if (stmtInsertDocument != null) {
            stmtInsertDocument.close();
            stmtInsertDocument = null;
        }
        if (conn != null) {
            ConnectionFactory.getInstance().close();
            conn.close();
        }
    }
}
