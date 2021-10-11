package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
public class DBConnection {

    //JDBC URL PARTS

    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String databaseName = "client_schedule";
    private static final String serverName = "//3.227.166.251:3306/";

    //JDBC URL
    private static final String jdbcURL = protocol + vendorName + serverName + databaseName;
    //Driver interface reference
    private static final String MYSQLJDBCDRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;
    // USER NAME
    private static final String username = "sqlUser";
    // PASSWORD
    private static final String password = "Passw0rd!";

    public static Connection startConnection(){
        try {
            Class.forName(MYSQLJDBCDRIVER);
            conn = (Connection)DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection successful!");
        }catch (ClassNotFoundException e){

            //System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
            //System.out.println("Error" + e.getMessage());
        }

        return conn;

    }

    public static void closeConnection() {
        try {

            conn.close();

            System.out.println("Connection closed.");

        } catch (SQLException e) {

            System.out.println("Error" + e.getMessage());

        }
    }
/*
    //get connection
    public static Connection getConnection(){

        return conn;

    }

}

 */
