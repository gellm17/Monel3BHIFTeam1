package db;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {

    // Keys
    private static final String DB_DRIVER_KEY = "dbDriver";
    private static final String DB_HOST_KEY = "dbHost";
    private static final String DB_NAME_KEY = "dbName";
    private static final String DB_USER_KEY = "dbUser";
    private static final String DB_PASSWD_KEY = "dbPasswd";

    // Values
    private static final String DB_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_HOST = "jdbc:mariadb://127.0.0.1";
    private static final String DB_NAME = "/monel";
    private static final String DB_USER = "madmin";
    private static final String DB_PASSWD = "admin";

    private static ConnectionFactory instance = new ConnectionFactory();
    private static Connection con = null;
    private static Exception ex = null;

    private ConnectionFactory() {}

    public static ConnectionFactory getInstance() { return instance; }

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
            p.setProperty(DB_NAME_KEY, DB_NAME);
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

    public void close() throws SQLException {
        con.close();
        con = null;
    }

    public Exception getLastException() { return ex; }
}