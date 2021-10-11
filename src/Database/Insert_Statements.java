/**
 * Inserts customers into the database based on a unique ID.
 */

package Database;

import model.Appointments;
import model.Customers;

import util.JDBCHelper;

import java.sql.*;

/**
 * Inserts customers into the database based on a unique ID.
 */
public class Insert_Statements
{
    /**
     * Represents the connection to the database.
     */
    private static Connection conn = JDBCHelper.getConnection();

    /**
     * Inserts a customer into the database.
     * @param newCustomer - the new customer to be added.
     * @return - Returns the ID created from the last customer added to the database.
     */
    public static int insertCustomer(Customers newCustomer)
    {
        int Customer_ID = 0;
        String sqlStatement = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, now(), 'test', now(), 'test', ?)";

        try
        {
            PreparedStatement ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, newCustomer.getCustomer_Name()); //Customer Name
            ps.setString(2, newCustomer.getAddress()); // Customer Address
            ps.setString(3, newCustomer.getPostal_Code()); // Customer postal code
            ps.setString(4, newCustomer.getPhone()); //Customer phone
            ps.setString(5, Integer.toString(newCustomer.getDivision_ID())); // Customer division ID

            ps.execute();
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID() FROM customers");

            ResultSet rs = ps.executeQuery();
            rs.next();
            Customer_ID = rs.getInt(1);

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return Customer_ID;
    }

    /**
     * Appointment to be inserted into the DB.
     * @param appointment - the appointment to be added.
     * @return - returns an integer ID from the newest appointment in the database.
     */
    public static int insertAppointment(Appointments appointment)
    {
        int appointment_ID = 0;

        String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, now(), 'test', now(), 'test', ?, ?, ?)";

        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, appointment.getTitle());
            ps.setString(2, appointment.getDescription());
            ps.setString(3, appointment.getLocation());
            ps.setString(4, appointment.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointment.getStartLocalDateTime()));
            ps.setTimestamp(6, Timestamp.valueOf(appointment.getEndLocalDateTime()));
            ps.setInt(7, appointment.getCustomer_ID());
            ps.setInt(8, appointment.getUser_ID());
            ps.setInt(9, appointment.getContact_ID());

            ps.execute();
            ps = conn.prepareStatement("SELECT LAST_INSERT_ID() FROM appointments");

            ResultSet rs = ps.executeQuery();
            rs.next();

            appointment_ID = rs.getInt(1);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return appointment_ID;
    }
}
