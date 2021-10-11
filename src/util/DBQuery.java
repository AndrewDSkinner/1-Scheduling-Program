package util;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Represents queries made by the application to the DB
 */

public class DBQuery {

    /**
     * A Statement reference
     */
    private static PreparedStatement statement;  // statement reference

    /**
     * Sets the prepared statement
     * @param conn - the connection to the database
     * @param sqlStatement - the SQL Query
     * @throws SQLException - an exception thrown by prepared statement
     */
    //Create statement object
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException
    {

        statement = conn.prepareStatement(sqlStatement);

    }

    /**
     * Get prepared Statement
     * @return - the prepared statement
     */
    //Return the statement object
    public static PreparedStatement getPreparedStatement() {
        return statement;
    }

}
