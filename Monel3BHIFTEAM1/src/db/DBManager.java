package db;

import data.EventDAO;
import data.PersonDAO;
import javafx.collections.FXCollections;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DBManager {

    private static String sqlInsertPerson           = "INSERT INTO person (personentyp,anrede,titel,vorname,nachname,strasse_hausnummer,plz,ort,telefonnummer,email,geburtsdatum) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertClient           = "INSERT INTO person (personentyp,anrede,titel,vorname,nachname,strasse_hausnummer,plz,ort,telefonnummer,email,geburtsdatum,esv,notfallkontakt1,notfallkontakt2,svnr,diagnose,allergien,sonstiges,beschaeftigung) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertEmployee         = "INSERT INTO person (personentyp,anrede,titel,vorname,nachname,strasse_hausnummer,plz,ort,telefonnummer,email,geburtsdatum,svnr,amt,verwendungsgruppe,gehaltsstufe,wochenstunden,iban,bic,vorrueckdatum,einstelldatum) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertSponsor          = "INSERT INTO person (personentyp,anrede,titel,vorname,nachname,strasse_hausnummer,plz,ort,telefonnummer,email,geburtsdatum,firmenname,firmentelefonnummer,firmenemail) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertEvent            = "INSERT INTO aktivitaet (datum,aktivitaetsbezeichnung,kategorie) VALUES (?,?,?)";
    private static String sqlInsertEventprotocol    = "INSERT INTO aktivitaetsprotokoll (aktivitaet,mitarbeiter,klient,rechnung,startzeit,endzeit,jahr_Monat,stundensatz,fahrtkosten) VALUES (?,?,?,?,?,?,?,?,?)";
    private static String sqlInsertBill             = "INSERT INTO rechnung (klient,ausstellungsdatum,verwendungszweck) VALUES (?,?,?)";
    private static String sqlInsertDocument         = "INSERT INTO dokument (besitzerIdPerson,besitzerIdAktivitaet,pfad,dokumentenart,besitzer) VALUES (?,?,?,?,?)";
    private static String sqlUpdatePerson           = "UPDATE person SET anrede = ?, titel = ?, vorname = ?, nachname = ?, strasse_hausnummer = ?, plz = ?, ort = ?, telefonnummer = ?, email = ?, geburtsdatum = ? WHERE id = ?";
    private static String sqlUpdateClient           = "UPDATE person SET anrede = ?, titel = ?, vorname = ?, nachname = ?, strasse_hausnummer = ?, plz = ?, ort = ?, telefonnummer = ?, email = ?, geburtsdatum = ?, esv = ?, notfallkontakt2 = ?, svnr = ?, diagnose = ?, allergien = ?, sonstiges = ?, beschaeftigung = ? WHERE id = ?";
    private static String sqlUpdateEmployee         = "UPDATE person SET anrede = ?, titel = ?, vorname = ?, nachname = ?, strasse_hausnummer = ?, plz = ?, ort = ?, telefonnummer = ?, email = ?, geburtsdatum = ?, svnr = ? , amt = ?, verwendungsgruppe = ?, gehaltsstufe = ?, wochenstunden = ?, iban = ?, bic = ?, vorrueckdatum = ?, einstelldatum = ? WHERE id = ?";
    private static String sqlUpdateSponsor          = "UPDATE person SET anrede = ?, titel = ?, vorname = ?, nachname = ?, strasse_hausnummer = ?, plz = ?, ort = ?, telefonnummer = ?, email = ?, geburtsdatum = ?, firmenname = ?, firmentelefonnummer = ?, firmenemail = ? WHERE id = ?";
    private static String sqlUpdateEvent            = "UPDATE aktivitaet SET datum = ?, aktivitaetsbezeichnung = ? WHERE id = ?";
    private static String sqlUpdateEventprotocol    = "UPDATE aktivitaetsprotokoll SET mitarbeiter = ?, klient = ?, rechnung = ?, startzeit = ?, endzeit = ?, jahr_Monat = ?, stundensatz = ?, fahrtkosten = ? WHERE id = ?";
    private static String sqlUpdateBill             = "UPDATE rechnung SET ausstellungsdatum = ?, erwendungszweck = ? WHERE id = ?";
    private static String sqlUpdateDocument         = "UPDATE dokument SET besitzerIdPerson = ?, besitzerIdAktivitaet = ?, pfad = ?, dokumentenart = ?, besitzer = ? WHERE id = ?";
    private static String sqlDeletePerson           = "UPDATE person SET geloescht = ? WHERE id = ?"; // for person, client, employee and sponsor
    private static String sqlDeleteEvent            = "DELETE FROM aktivitaet WHERE id = ?";
    private static String sqlDeleteEventprotocol    = "DELETE FROM aktivitaetsprotokoll WHERE id = ?";
    private static String sqlDeleteBill             = "DELETE FROM rechnung WHERE id = ?";
    private static String sqlDeleteDocument         = "DELETE FROM dokument WHERE id = ?";
    private static String sqlSelectEventprotocol    = "SELECT * FROM aktivitaetsprotokoll WHERE klient = ?, jahr_Monat = ?, rechnung = ?";

    private static PreparedStatement stmtInsertPerson           = null;
    private static PreparedStatement stmtInsertClient           = null;
    private static PreparedStatement stmtInsertEmployee         = null;
    private static PreparedStatement stmtInsertSponsor          = null;
    private static PreparedStatement stmtInsertEvent            = null;
    private static PreparedStatement stmtInsertEventprotocol    = null;
    private static PreparedStatement stmtInsertBill             = null;
    private static PreparedStatement stmtInsertDocument         = null;
    private static PreparedStatement stmtUpdatePerson           = null;
    private static PreparedStatement stmtUpdateClient           = null;
    private static PreparedStatement stmtUpdateEmployee         = null;
    private static PreparedStatement stmtUpdateSponsor          = null;
    private static PreparedStatement stmtUpdateEvent            = null;
    private static PreparedStatement stmtUpdateEventprotocol    = null;
    private static PreparedStatement stmtUpdateBill             = null;
    private static PreparedStatement stmtUpdateDocument         = null;
    private static PreparedStatement stmtDeletePerson           = null;
    private static PreparedStatement stmtDeleteEvent            = null;
    private static PreparedStatement stmtDeleteEventprotocol    = null;
    private static PreparedStatement stmtDeleteBill             = null;
    private static PreparedStatement stmtDeleteDocument         = null;
    private static PreparedStatement stmtSelectEventprotocol    = null;

    private static Connection conn = null;

    /** inserts a Person into the DB
     *
     * @param p
     * @return
     * @throws SQLException
     */
    public static int insertPerson(Person p) throws SQLException {
        int newId = -1;
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
        return newId;
    } //TODO check non mandatory fields

    /** inserts a Klient into the DB
     *
     * @param c
     * @return
     * @throws SQLException
     */
    public static int insertClient(Client c) throws SQLException {
        int newId = -1;
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
        return newId;
    } //TODO check non mandatory fields

    /** inserts an Employee into the DB
     *
     * @param e
     * @return
     * @throws SQLException
     */
    public static int insertEmployee(Employee e) throws SQLException {
        int newId = -1;
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
        return newId;
    } //TODO check non mandatory fields

    /** inserts a Sponsor into the DB
     *
     * @param s
     * @return
     * @throws SQLException
     */
    public static int insertSponsor(Sponsor s) throws SQLException {
        int newId = -1;
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
        return newId;
    } //TODO check non mandatory fields

    /** inserts an Event into the DB
     *
     * @param e
     * @return
     * @throws SQLException
     */
    public static int insertEvent(Event e) throws SQLException {
        int newId = -1;
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
        return newId;
    } //TODO check non mandatory fields

    /** inserts an Eventprotocol into the DB
     *
     * @param ep
     * @return
     * @throws SQLException
     */
    public static int insertEventprotocol(EventProtocol ep) throws SQLException {
        int newId = -1;
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
        stmtInsertEventprotocol.setString(7, ep.getYear_month());
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
        return newId;
    } //TODO check non mandatory fields

    /** inserts a Bill into the DB
     *
     * @param b
     * @return
     * @throws SQLException
     */
    public static int insertBill(Bill b) throws SQLException {
        int newId = -1;
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
        return newId;
    } //TODO check non mandatory fields

    /** inserts a Document into the DB
     *
     * @param d
     * @return
     * @throws SQLException
     */
    public static int insertDocument(Document d) throws SQLException {
        int newId = -1;
        if (d.getOwnerPerson() != null) {
            stmtInsertDocument.setInt(1, d.getOwnerPerson().getId());
            stmtInsertDocument.setNull(2, Types.NULL);
        } else {
            stmtInsertDocument.setNull(1, Types.NULL);
            stmtInsertDocument.setInt(2, d.getOwnerEvent().getId());
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
        return newId;
    } //TODO check non mandatory fields

    /** updates the Data of the Person with the same id in the DB with the Data of this Person
     *
     * @param p
     * @throws SQLException
     */
    public static void updatePerson(Person p) throws SQLException {
        stmtUpdatePerson.setString(1, p.getSalutation().toString());
        if (p.getTitle() != null) {
            stmtUpdatePerson.setString(2, p.getTitle());
        } else {
            stmtUpdatePerson.setNull(2, Types.NULL);
        }
        stmtUpdatePerson.setString(3, p.getFirstName());
        stmtUpdatePerson.setString(4, p.getLastName());
        stmtUpdatePerson.setString(5, p.getStreetAndNr());
        stmtUpdatePerson.setInt(6, p.getZipCode());
        stmtUpdatePerson.setString(7, p.getPlace());
        stmtUpdatePerson.setString(8, p.getTelNr());
        if (p.getEmail() != null) {
            stmtUpdatePerson.setString(9, p.getEmail());
        } else {
            stmtUpdatePerson.setNull(9, Types.NULL);
        }
        if (p.getBirthDate() != null) {
            stmtUpdatePerson.setDate(10, Date.valueOf(p.getBirthDate()));
        } else {
            stmtUpdatePerson.setNull(10, Types.NULL);
        }
        stmtUpdatePerson.setInt(11, p.getId());
        stmtUpdatePerson.executeUpdate();
        stmtUpdatePerson.clearParameters();
    } //TODO check non mandatory fields

    /** updates the Data of the Client with the same id in the DB with the Data of this Client
     *
     * @param c
     * @throws SQLException
     */
    public static void updateClient(Client c) throws SQLException {
        stmtUpdateClient.setString(1, c.getSalutation().toString());
        if (c.getTitle() != null) {
            stmtUpdateClient.setString(2, c.getTitle());
        } else {
            stmtUpdateClient.setNull(2, Types.NULL);
        }
        stmtUpdateClient.setString(3, c.getFirstName());
        stmtUpdateClient.setString(4, c.getLastName());
        stmtUpdateClient.setString(5, c.getStreetAndNr());
        stmtUpdateClient.setInt(6, c.getZipCode());
        stmtUpdateClient.setString(7, c.getPlace());
        stmtUpdateClient.setString(8, c.getTelNr());
        if(c.getEmail() != null) {
            stmtUpdateClient.setString(9, c.getEmail());
        } else {
            stmtUpdateClient.setNull(9, Types.NULL);
        }
        if (c.getBirthDate() != null) {
            stmtUpdateClient.setDate(10, Date.valueOf(c.getBirthDate()));
        } else {
            stmtUpdateClient.setNull(10, Types.NULL);
        }
        if (c.getEsv() != null) {
            stmtUpdateClient.setInt(11, c.getEsv().getId());
        } else {
            stmtUpdateClient.setNull(11, Types.NULL);
        }
        if (c.getEmergencyContact2() != null) {
            stmtUpdateClient.setInt(12, c.getEmergencyContact2().getId());
        } else {
            stmtUpdateClient.setNull(12, Types.NULL);
        }
        stmtUpdateClient.setLong(13, c.getSsnr());
        stmtUpdateClient.setString(14, c.getDiagnose());
        if (c.getAllergies() != null) {
            stmtUpdateClient.setString(15, c.getAllergies());
        } else {
            stmtUpdateClient.setNull( 15, Types.NULL);
        }
        if (c.getOther() != null) {
            stmtUpdateClient.setString(16, c.getOther());
        } else {
            stmtUpdateClient.setNull(16, Types.NULL);
        }
        if (c.getJob() != null) {
            stmtUpdateClient.setString(17, c.getJob());
        } else {
            stmtUpdateClient.setNull(17, Types.NULL);
        }
        stmtUpdateClient.setInt(18, c.getId());
        stmtUpdateClient.executeUpdate();
        stmtUpdateClient.clearParameters();
    } //TODO check non mandatory fields

    /** updates the Data of the Employee with the same id in the DB with the Data of this Employee
     *
     * @param e
     * @throws SQLException
     */
    public static void updateEmployee(Employee e) throws SQLException {
        stmtUpdateEmployee.setString(1, e.getSalutation().toString());
        if (e.getTitle() != null) {
            stmtUpdateEmployee.setString(2, e.getTitle());
        } else {
            stmtUpdateEmployee.setNull(2, Types.NULL);
        }
        stmtUpdateEmployee.setString(3, e.getFirstName());
        stmtUpdateEmployee.setString(4, e.getLastName());
        stmtUpdateEmployee.setString(5, e.getStreetAndNr());
        stmtUpdateEmployee.setInt(6, e.getZipCode());
        stmtUpdateEmployee.setString(7, e.getPlace());
        stmtUpdateEmployee.setString(8, e.getTelNr());
        if (e.getEmail() != null) {
            stmtUpdateEmployee.setString(9, e.getEmail());
        } else {
            stmtUpdateEmployee.setString(9, e.getEmail());
        }
        if (e.getBirthDate() != null) {
            stmtUpdateEmployee.setDate(10, Date.valueOf(e.getBirthDate()));
        } else {
            stmtUpdateEmployee.setNull(10, Types.NULL);
        }
        stmtUpdateEmployee.setLong(11, e.getSsnr());
        stmtUpdateEmployee.setInt(12, (e.isVolunteering() ? 1 : 0));
        stmtUpdateEmployee.setString(13, e.getOccupationGroup().toString());
        stmtUpdateEmployee.setString(14, e.getSalaryLevel().toString());
        if (e.getHoursPerWeek() != 0) {
            stmtUpdateEmployee.setInt(15, e.getHoursPerWeek());
        } else {
            stmtUpdateEmployee.setNull(15, Types.NULL);
        }
        stmtUpdateEmployee.setString(16, e.getIban());
        stmtUpdateEmployee.setString(17, e.getBic());
        if (e.getDateSalaryLevel() != null) {
            stmtUpdateEmployee.setDate(18, Date.valueOf(e.getDateSalaryLevel()));
        } else {
            stmtUpdateEmployee.setNull(18, Types.NULL);
        }
        if (e.getDateOfEmployment() != null) {
            stmtUpdateEmployee.setDate(19, Date.valueOf(e.getDateOfEmployment()));
        } else {
            stmtUpdateEmployee.setNull(19, Types.NULL);
        }
        stmtUpdateEmployee.setInt(20, e.getId());
        stmtUpdateEmployee.executeUpdate();
        stmtUpdateEmployee.clearParameters();
    } //TODO check non mandatory fields

    /** updates the Data of the Sponsor with the same id in the DB with the Data of this Sponsor
     *
     * @param s
     * @throws SQLException
     */
    public static void updateSponsor(Sponsor s) throws SQLException {
        stmtUpdateSponsor.setString(1, s.getSalutation().toString());
        stmtUpdateSponsor.setString(2, s.getTitle());
        stmtUpdateSponsor.setString(3, s.getFirstName());
        stmtUpdateSponsor.setString(4, s.getLastName());
        stmtUpdateSponsor.setString(5, s.getStreetAndNr());
        stmtUpdateSponsor.setInt(6, s.getZipCode());
        stmtUpdateSponsor.setString(7, s.getPlace());
        stmtUpdateSponsor.setString(8, s.getTelNr());
        stmtUpdateSponsor.setString(9, s.getEmail());
        stmtUpdateSponsor.setDate(10, Date.valueOf(s.getBirthDate()));
        stmtUpdateSponsor.setString(11, s.getCompanyName());
        stmtUpdateSponsor.setString(12, s.getCompanyTelNr());
        stmtUpdateSponsor.setString(13, s.getCompanyEmail());
        stmtUpdateSponsor.setInt(14, s.getId());
        stmtUpdateSponsor.executeUpdate();
        stmtUpdateSponsor.clearParameters();
    } //TODO check non mandatory fields

    /** updates the Data of the Event with the same id in the DB with the Data of this Event
     *
     * @param e
     * @throws SQLException
     */
    public static void updateEvent(Event e) throws SQLException {
        stmtUpdateEvent.setDate(1, Date.valueOf(e.getDate()));
        stmtUpdateEvent.setString(2, e.getName());
        stmtUpdateEvent.setInt(3, e.getId());
        stmtUpdateEvent.executeUpdate();
        stmtUpdateEvent.clearParameters();
    } //TODO check non mandatory fields

    /** updates the Data of the Eventprotocol with the same id in the DB with the Data of this Eventprotocol
     *
     * @param ep
     * @throws SQLException
     */
    public static void updateEventprotocol(EventProtocol ep) throws SQLException {
        if (ep.getEmployee() != null) {
            stmtUpdateEventprotocol.setInt(1, ep.getEmployee().getId());
        } else {
            stmtUpdateEventprotocol.setNull(1, Types.NULL);
        }
        if (ep.getClient() != null) {
            stmtUpdateEventprotocol.setInt(2, ep.getClient().getId());
        } else {
            stmtUpdateEventprotocol.setNull(2, Types.NULL);
        }
        if (ep.getBill() != null) {
            stmtUpdateEventprotocol.setInt(3, ep.getBill().getNr());
        } else {
            stmtUpdateEventprotocol.setNull(3, Types.NULL);
        }
        stmtUpdateEventprotocol.setTime(4, Time.valueOf(ep.getStartTime()));
        stmtUpdateEventprotocol.setTime(5, Time.valueOf(ep.getEndTime()));
        stmtUpdateEventprotocol.setDate(6, Date.valueOf(ep.getYear_month()));
        stmtUpdateEventprotocol.setDouble(7, ep.getHourlyRate()); // must be changed to decimal
        stmtUpdateEventprotocol.setDouble(8, ep.getRideCosts()); // must be changed to decimal
        stmtUpdateEventprotocol.setInt(9, ep.getId());
        stmtUpdateEventprotocol.executeUpdate();
        stmtUpdateEventprotocol.clearParameters();
    } //TODO check non mandatory fields

    /** updates the Data of the Bill with the same id in the DB with the Data of this Bill
     *
     * @param b
     * @throws SQLException
     */
    public static void updateBill(Bill b) throws SQLException {
        stmtUpdateBill.setDate(1, Date.valueOf(b.getDateOfIssue()));
        stmtUpdateBill.setString(2, b.getUse());
        stmtUpdateBill.setInt(3, b.getNr());
        stmtUpdateBill.executeUpdate();
        stmtUpdateBill.clearParameters();
    } //TODO check non mandatory fields

    /** updates the Data of the Document with the same id in the DB with the Data of this Document
     *
     * @param d
     * @throws SQLException
     */
    public static void updateDocument(Document d) throws SQLException {
        if (d.getOwnerPerson() != null) {
            stmtUpdateDocument.setInt(1, d.getOwnerPerson().getId());
            stmtUpdateDocument.setNull(2, Types.NULL);
        } else {
            stmtUpdateDocument.setNull(1, Types.NULL);
            stmtUpdateDocument.setInt(2, d.getOwnerEvent().getId());
        }
        stmtUpdateDocument.setString(3, d.getPath());
        stmtUpdateDocument.setString(4, d.getDocumentType());
        stmtUpdateDocument.setString(5, d.getOwner().toString());
        stmtUpdateDocument.setInt(6, d.getID());
        stmtUpdateDocument.executeUpdate();
        stmtUpdateDocument.clearParameters();
    } //TODO check non mandatory fields

    /** updates the cell geloescht for the given Person (also Client, Employee and Sponsor) to 1 (with 1 it will be ignored bi the get methods)
     *
     * @param p
     * @throws SQLException
     */
    public static void deletePerson(Person p) throws SQLException {
            stmtDeletePerson.setInt(1, 1);
            stmtDeletePerson.setInt(2, p.getId());
            stmtDeletePerson.executeUpdate();
            stmtDeletePerson.clearParameters();
    }

    /** deletes the Event with the same id in the DB
     *
     * @param e
     * @throws SQLException
     */
    public static void deleteEvent(Event e) throws SQLException {
        stmtDeleteEvent.setInt(1, e.getId());
        stmtDeleteEvent.executeUpdate();
        stmtDeleteEvent.clearParameters();
    }

    /** deletes the Eventprotocol with the same id in the DB
     *
     * @param ep
     * @throws SQLException
     */
    public static void deleteEventprotocol(EventProtocol ep) throws SQLException {
        stmtDeleteEventprotocol.setInt(1, ep.getId());
        stmtDeleteEventprotocol.executeUpdate();
        stmtDeleteEventprotocol.clearParameters();
    }

    /** deletes the Bill with the same id in the DB
     *
     * @param b
     * @throws SQLException
     */
    public static void deleteBill(Bill b) throws SQLException {
        stmtDeleteBill.setInt(1, b.getNr());
        stmtDeleteBill.executeUpdate();
        stmtDeleteBill.clearParameters();
    }

    /** deletes the Document with the same id in the DB
     *
     * @param d
     * @throws SQLException
     */
    public static void deleteDocument(Document d) throws SQLException {
        stmtDeleteDocument.setInt(1, d.getID());
        stmtDeleteDocument.executeUpdate();
        stmtDeleteDocument.clearParameters();
    }

    /** creates a bill for the given Person in the given month and inserts it into the DB (it only uses Evenprotocols with no bill)
     *
     * @param c
     * @param year_month
     * @return
     * @throws SQLException
     */
    public static Bill createBill(Client c, String year_month) throws SQLException {
        Bill res = new Bill(c);
        EventProtocol ep = null;
        ArrayList<EventProtocol> evps = getAllEventProtocols(c, year_month);
        res.setNr(insertBill(res));
        res.setEventProtocols(FXCollections.observableList(evps));
        Iterator<EventProtocol> iter = evps.iterator();
        while (iter.hasNext()) {
            ep = iter.next();
            ep.setBill(res);
            updateEventprotocol(ep);
        }
        return res;
    }

    /** returns a HashMap of all Persons from the DB
     *
     * @return
     * @throws SQLException
     */
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

    /** returns an HashMap of all Clients from the DB without Objects (esv, emergencycontact1, emergencycontact2)
     *
     * @return
     * @throws SQLException
     */
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

    /** returns an ArrayList of all Clients from the DB with Objects (esv, emergencycontact1, emergencycontact2)
     *
     * @return
     * @throws SQLException
     */
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

    /** returns an HashMap of all Emplayees from the DB
     *
     * @return
     * @throws SQLException
     */
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

    /** returns an HashMap of all Sponsor from the DB
     *
     * @return
     * @throws SQLException
     */
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

    /** returns a HashMap of all Events from the DB
     *
     * @return
     * @throws SQLException
     */
    public static HashMap<Integer, Event> getAllEvents() throws SQLException {
        HashMap<Integer, Event> evns = new HashMap<Integer, Event>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM aktivitaet");
        while (rs.next()) {
            evns.put(rs.getInt("id"), Event.fromResults(rs));
        }
        return evns;
    }

    /** returns a HashMap of all Eventprotocols from the DB without Objects (event, employee, client)
     *
     * @return
     * @throws SQLException
     */
    private static HashMap<Integer, EventProtocol> getEventprotocols() throws SQLException{
        HashMap<Integer, EventProtocol> evps = new HashMap<Integer, EventProtocol>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM aktivitaetsprotokoll");
        while (rs.next()) {
            evps.put(rs.getInt("id"), EventProtocol.fromResults(rs));
        }
        return evps;
    }

    /** returns a ArrayList of all Eventprotocols from the DB with Objects (event, employee, client)
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<EventProtocol> getAllEventProtocols() throws SQLException {
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

    /** returns a ArrayList of all Eventprotocols of the given Client in the given month
     *
     * @param c
     * @param year_month
     * @return
     * @throws SQLException
     */
    public static ArrayList<EventProtocol> getAllEventProtocols(Client c, String year_month) throws SQLException {
        ArrayList<EventProtocol> evps = new ArrayList<EventProtocol>();
        stmtSelectEventprotocol.setInt(1, c.getId());
        stmtSelectEventprotocol.setString(2, year_month);
        stmtSelectEventprotocol.setNull(3, Types.INTEGER);
        ResultSet rs = stmtSelectEventprotocol.executeQuery();
        HashMap<Integer, Event> evns = getAllEvents();
        HashMap<Integer, Client> clis = getClients();
        HashMap<Integer, Employee> emps = getAllEmployees();
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
            evps.add(e);
        }
        stmtSelectEventprotocol.clearParameters();
        return evps;
    }

    /** returns a HashMap of all Bills from the DB without Objects (client)
     *
     * @return
     * @throws SQLException
     */
    private static HashMap<Integer, Bill> getBills() throws SQLException {
        HashMap<Integer, Bill> bils = new HashMap<Integer, Bill>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM rechnung");
        while (rs.next()) {
            bils.put(rs.getInt("rechnungsnummer"), Bill.fromResults(rs));
        }
        return bils;
    }

    /** returns an ArrayList of all Bills from a Client from the DB
     *
     * @param c
     * @return
     * @throws SQLException
     */
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

    /** returns an ArrayList of all Documents from the DB with Objects (ownerPerson or ownerEvent)
     *
     * @return
     * @throws SQLException
     */
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

    /** creates the DB Connection and sends the PreparedStatements to the DB
     *
     * @throws NullPointerException
     * @throws SQLException
     */
    public static void open() throws NullPointerException, SQLException {
        conn = ConnectionFactory.getInstance().getConnection();
        stmtInsertPerson        = conn.prepareStatement(sqlInsertPerson, Statement.RETURN_GENERATED_KEYS);
        stmtInsertClient        = conn.prepareStatement(sqlInsertClient, Statement.RETURN_GENERATED_KEYS);
        stmtInsertEmployee      = conn.prepareStatement(sqlInsertEmployee, Statement.RETURN_GENERATED_KEYS);
        stmtInsertSponsor       = conn.prepareStatement(sqlInsertSponsor, Statement.RETURN_GENERATED_KEYS);
        stmtInsertEvent         = conn.prepareStatement(sqlInsertEvent, Statement.RETURN_GENERATED_KEYS);
        stmtInsertEventprotocol = conn.prepareStatement(sqlInsertEventprotocol, Statement.RETURN_GENERATED_KEYS);
        stmtInsertBill          = conn.prepareStatement(sqlInsertBill, Statement.RETURN_GENERATED_KEYS);
        stmtInsertDocument      = conn.prepareStatement(sqlInsertDocument, Statement.RETURN_GENERATED_KEYS);
        stmtUpdatePerson        = conn.prepareStatement(sqlUpdatePerson);
        stmtUpdateClient        = conn.prepareStatement(sqlUpdateClient);
        stmtUpdateEmployee      = conn.prepareStatement(sqlUpdateEmployee);
        stmtUpdateSponsor       = conn.prepareStatement(sqlUpdateSponsor);
        stmtUpdateEvent         = conn.prepareStatement(sqlUpdateEvent);
        stmtUpdateEventprotocol = conn.prepareStatement(sqlUpdateEventprotocol);
        stmtUpdateBill          = conn.prepareStatement(sqlUpdateBill);
        stmtUpdateDocument      = conn.prepareStatement(sqlUpdateDocument);
        stmtDeletePerson        = conn.prepareStatement(sqlDeletePerson);
        stmtDeleteEvent         = conn.prepareStatement(sqlDeleteEvent);
        stmtDeleteEventprotocol = conn.prepareStatement(sqlDeleteEventprotocol);
        stmtDeleteBill          = conn.prepareStatement(sqlDeleteBill);
        stmtDeleteDocument      = conn.prepareStatement(sqlDeleteDocument);
        stmtSelectEventprotocol = conn.prepareStatement(sqlSelectEventprotocol);
    }

    /** closes the DB Connection and the PreparedStatements
     * 
     * @throws NullPointerException
     */
    public static void close() throws NullPointerException {
        try {
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
            if (stmtUpdatePerson != null) {
                stmtUpdatePerson.close();
                stmtUpdatePerson = null;
            }
            if (stmtUpdateClient != null) {
                stmtUpdateClient.close();
                stmtUpdateClient = null;
            }
            if (stmtUpdateEmployee != null) {
                stmtUpdateEmployee.close();
                stmtUpdateEmployee = null;
            }
            if (stmtUpdateSponsor != null) {
                stmtUpdateSponsor.close();
                stmtUpdateSponsor = null;
            }
            if (stmtUpdateEvent != null) {
                stmtUpdateEvent.close();
                stmtUpdateEvent = null;
            }
            if (stmtUpdateEventprotocol != null) {
                stmtUpdateEventprotocol.close();
                stmtUpdateEventprotocol = null;
            }
            if (stmtUpdateBill != null) {
                stmtUpdateBill.close();
                stmtUpdateBill = null;
            }
            if (stmtUpdateDocument != null) {
                stmtUpdateDocument.close();
                stmtUpdateDocument = null;
            }
            if (stmtDeletePerson != null) {
                stmtDeletePerson.close();
                stmtDeletePerson = null;
            }
            if (stmtDeleteEvent != null) {
                stmtDeleteEvent.close();
                stmtDeleteEvent = null;
            }
            if (stmtDeleteEventprotocol != null) {
                stmtDeleteEventprotocol.close();
                stmtDeleteEventprotocol = null;
            }
            if (stmtDeleteBill != null) {
                stmtDeleteBill.close();
                stmtDeleteBill = null;
            }
            if (stmtDeleteDocument != null) {
                stmtDeleteDocument.close();
                stmtDeleteDocument = null;
            }
            if (stmtSelectEventprotocol != null) {
                stmtSelectEventprotocol.close();
                stmtSelectEventprotocol = null;
            }
            if (conn != null) {
                ConnectionFactory.getInstance().close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
