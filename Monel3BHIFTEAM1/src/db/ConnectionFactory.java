package db;
import data.PersonDAO;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {

    // Keys
    private static final String DB_DRIVER_KEY = "dbDriver";
    private static final String DB_HOST_KEY = "dbHost";
    private static final String DB_NAME_KEY = "dbName";
    private static final String DB_USER_KEY = "dbUser";
    private static final String DB_PASSWD_KEY = "dbPawweord";

    // Values
    private static final String DB_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_HOST = "jdbc:mariadb://127.0.0.1";
    private static final String DB_NAME = "/monel;";
    private static final String DB_USER = "madmin";
    private static final String DB_PASSWD = "admin";

    private static ConnectionFactory instance = new ConnectionFactory();
    private static Connection con = null;
    private static Exception ex = null;

    private ConnectionFactory() {}

    /*
     * Returns a set of properties (key values pairs) to connect a data base
     * Provides keys: dbDriver, dbUser, dbPassword, dbHost, dbName
     */
    private Properties getDatabaseProperties(String filename) {
        Properties p = new Properties();
        try {
            p.load(new FileInputStream(new File(filename)));
            ex = null;
        } catch (FileNotFoundException e) {
            System.out.println("property file " + filename + " not found, will be created ...");
            p.setProperty(DB_DRIVER_KEY, DB_DRIVER);
            p.setProperty(DB_HOST_KEY, DB_HOST);
            p.setProperty(DB_NAME_KEY, DB_HOST_KEY);
            p.setProperty(DB_USER_KEY, DB_USER);
            p.setProperty(DB_PASSWD_KEY, DB_PASSWD);
            try {
                p.store(new FileOutputStream(new File(filename)), "");
                System.out.println("property file " + filename + "created succesfully");
                ex = null;
            } catch (IOException exc) {
                ex = exc;
            }
        } catch (IOException e) {
            ex = e;
        }
        return p;
    }

    // Returns data base connection using a property file
    public Connection getConnection() {
        Properties props = getDatabaseProperties("db.properties");
        String drv = props.getProperty(DB_DRIVER_KEY, DB_DRIVER);
        String host = props.getProperty(DB_HOST_KEY, DB_HOST);
        String name = props.getProperty(DB_NAME_KEY, DB_NAME);
        String user = props.getProperty(DB_USER_KEY, DB_USER);
        String passwd = props.getProperty(DB_PASSWD_KEY, DB_PASSWD);

        if (con == null) {
            System.out.println("loading driver" + drv);
            try {
                Class.forName(drv);
                System.out.println("driver " + drv + " loaded successfully");
                con = DriverManager.getConnection(host + name, user, passwd);
                ex = null;
            } catch (ClassNotFoundException e) {
                ex = e;
            } catch (SQLException e) {
                ex = e;
            }
        }

        return con;
    }

    public Exception getLastException() {
        return ex;
    }

    /*
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
    }*/
}