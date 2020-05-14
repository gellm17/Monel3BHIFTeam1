package db;
import data.PersonDAO;

import java.sql.*;

public class ConnectionFactory {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1/monel";

    //  Database credentials
    static final String USER = "madmin";
    static final String PASS = "admin";

    public static void loadDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Verbindungsaufbau zur Datenbank");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Verbindung wurde erfolgreich aufgebaut");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM person");
            PersonDAO.getInstance().loadPersons(rs);
            //rs = stmt.executeQuery("SELECT * FROM aktivitaet");
            //AktivitaetDAO.getInstance().loadAktivitaet(rs);
            //rs = stmt.executeQuery("SELECT * FROM aktivitaetsprotokoll");
            //AktivitaetsprotokollDAO.getInstance().laodAktivitaetsprotokoll(rs);
            //rs = stmt.executeQuery("SELECT * FROM rechnung");
            //rechnungDAO.getInstance().loadRechnung(rs);
            //rs = stmt.executeQuery("SELECT * FROM dokument");
            //dokumentDAO.getInstance().loadDokumente(rs);
            //rs = stmt.executeQuery("SELECT * FROM datenschutz");
            //DatenschutzDAO.getInstance().loadDatenschutz(rs);
            System.out.println("Daten wurden erfolgreich eingelesen");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver " + JDBC_DRIVER + " NOT loaded");
            e.printStackTrace();
        } catch (SQLException se) {
            System.out.println("could not connect " + DB_URL + " due to:");
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
        System.out.println("Goodbye!");
    }
}