package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Establishes a connection to the DB.
 */
public abstract class JDBCHelper
{
    /**
     * Connection protocol
     */
    private static final String protocol = "jdbc";

    /**
     * Connnection vendor
     */
    private static final String vendor = ":mysql:";

    /**
     * Connection location
     */
    private static final String location = "//localhost/";

    /**
     * The database name
     */
    private static final String databaseName = "client_schedule";

    /**
     * The database URL
     */
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL

    /**
     * MySQL driver
     */
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference

    /**
     * MySQL userName
     */
    private static final String userName = "sqlUser"; // Username

    /**
     * Password
     */
    private static String password = "Passw0rd!"; // Password

    /**
     * the connection interface
     */
    public static Connection connection;  // Connection Interface

    /**
     * Starts the connection to the database
     */
    public static void startConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * Closes the connection
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * Retrieves the current connection
     * @return - the current connection
     */
    //get connection
    public static Connection getConnection(){

        return connection;

    }
}
