/**
 * Contains Delete functions for removing specific customers or appointments from the database based on a unique ID.
 */

package Database;

import model.Appointments;
import model.Customers;
import util.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.PropertyPermission;


/**
 * Delete Statements class containing functions for database removal.
 */
public class Delete_Statements
{
    /**
     * Represents the connection to the database.
     */
    private static Connection conn = JDBCHelper.getConnection();

    /**
     * Deletes a customer from the database.
     * @param deletedCustomer - the customer to be deleted
     */
    public static void deleteCustomer(Customers deletedCustomer)
    {
        String sqlStatement = "DELETE FROM customers WHERE Customer_ID = ?";

        try
        {
            PreparedStatement ps = conn.prepareStatement(sqlStatement);

            ps.setInt(1, deletedCustomer.getCustomer_ID());
            ps.execute();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an appointment from the database.
     * @param deletedAppointment the appointment to be deleted.
     */
    public static void deleteAppointment(Appointments deletedAppointment) {
        String sqlStatement = "DELETE FROM appointments WHERE Appointment_ID = ?";

       try {
           PreparedStatement ps = conn.prepareStatement(sqlStatement);

           ps.setInt(1, deletedAppointment.getAppointment_ID());

           ps.execute();
       } catch (SQLException e)
       {
           e.printStackTrace();
       }
    }
}
