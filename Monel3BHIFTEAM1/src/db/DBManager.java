package db;

import data.EventDAO;
import data.PersonDAO;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class DBManager {

    public static void loadDB() {
        Connection conn = ConnectionFactory.getInstance().getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM person");
            loadPersons(rs);
            rs = stmt.executeQuery("SELECT * FROM aktivitaet");
            loadAktivitaet(rs);
            rs = stmt.executeQuery("SELECT * FROM aktivitaetsprotokoll");
            laodAktivitaetsprotokoll(rs);
            //rs = stmt.executeQuery("SELECT * FROM rechnung");
            //loadRechnung(rs);
            //rs = stmt.executeQuery("SELECT * FROM dokument");
            //loadDokumente(rs);
            //rs = stmt.executeQuery("SELECT * FROM datenschutz");
            //loadDatenschutz(rs);
            System.out.println("Daten wurden erfolgreich eingelesen");
        } catch (Exception se) {
            System.out.println("could not execute a stmt");
            se.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {}
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void loadPersons(ResultSet rs) throws SQLException {
        String persontyp;
        Client c;
        while (rs.next()) {
            persontyp = rs.getString("personentyp");
            if(persontyp.equals("KLIENT")) {
                PersonDAO.getInstance().addPerson(Client.fromResults(rs));
            } else if(persontyp.equals("MITARBEITER")) {
                PersonDAO.getInstance().addPerson(Employee.fromResults(rs));
            } else if(persontyp.equals("SPONSOR")) {
                PersonDAO.getInstance().addPerson(Sponsor.fromResults(rs));
            } else {
                PersonDAO.getInstance().addPerson(Person.fromResults(rs));
            }
        }
        Iterator<Client> it = PersonDAO.getInstance().getClients().iterator();
        while (it.hasNext()) {
            c = it.next();
            if (c.getEsv() != null) {
                c.setEsv(PersonDAO.getInstance().getPersonFromId(c.getEsv().getId()));
            }
            if (c.getEmergencyContact1() != null) {
                c.setEmergencyContact1(PersonDAO.getInstance().getPersonFromId(c.getEmergencyContact1().getId()));
            }
            if (c.getEmergencyContact2() != null) {
                c.setEmergencyContact2(PersonDAO.getInstance().getPersonFromId(c.getEmergencyContact2().getId()));
            }
        }
    }

    private static void loadAktivitaet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            EventDAO.getInstance().addEvent(Event.fromResults(rs));
        }
    }

    private static void laodAktivitaetsprotokoll(ResultSet rs) throws SQLException {
        EventProtocol e;
        while (rs.next()) {
            EventDAO.getInstance().addEventProtcol(EventProtocol.fromResults(rs));
        }
        Iterator<EventProtocol> it = EventDAO.getInstance().getEventProtocols().iterator();
        while (it.hasNext()) {
            e = it.next();
            if (e.getClient() != null) {
                e.setClient(PersonDAO.getInstance().getClientFromId(e.getClient().getId()));
            }
            if (e.getEmployee() != null) {
                e.setEmployee(PersonDAO.getInstance().getEmployeeFromId(e.getEmployee().getId()));
            }
            if (e.getEvent() != null) {
                e.setEvent(EventDAO.getInstance().getEventFromId(e.getEvent().getId()));
            }
        }
    }
}
