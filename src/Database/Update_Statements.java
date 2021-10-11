package Database;


import model.Appointments;
import model.Customers;

import util.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Updates records in DB based on unique IDs.
 */

public class Update_Statements
{
    /**
     * Represents the connection to the database.
     */
    private static Connection conn = JDBCHelper.getConnection();

    /**
     * Updates a customer in the DB.
     * @param updatedCustomer - the Customer to be updated.
     */
    public static void updateCustomer(Customers updatedCustomer)
    {
       // String customer_ID = Integer.toString(updatedCustomer.getCustomer_ID());
        String sqlStatement =
                "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, " +
                        "Phone = ?, Last_Update = now(), Division_ID = ? WHERE Customer_ID = ?";

        try
        {
            PreparedStatement ps = conn.prepareStatement(sqlStatement);
            ps.setString(1, updatedCustomer.getCustomer_Name());
            ps.setString(2, updatedCustomer.getAddress());
            ps.setString(3, updatedCustomer.getPostal_Code());
            ps.setString(4, updatedCustomer.getPhone());
            ps.setInt(5, updatedCustomer.getDivision_ID());
            ps.setInt(6, updatedCustomer.getCustomer_ID());

            ps.execute();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * Updates an appointment in the DB.
     * @param updatedAppointment - the appointment to be updated.
     */

    public static void updateAppointment(Appointments updatedAppointment)
    {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?," +
                " Start = ?, End = ?, Last_Update = now(), Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";



        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, updatedAppointment.getTitle());
            ps.setString(2, updatedAppointment.getDescription());
            ps.setString(3, updatedAppointment.getLocation());
            ps.setString(4,updatedAppointment.getType());
            ps.setTimestamp(5,Timestamp.valueOf(updatedAppointment.getStartLocalDateTime()));
            ps.setTimestamp(6,Timestamp.valueOf(updatedAppointment.getEndLocalDateTime()));
            ps.setInt(7, updatedAppointment.getCustomer_ID());
            ps.setInt(8, updatedAppointment.getUser_ID());
            ps.setInt(9, updatedAppointment.getContact_ID());
            ps.setInt(10, updatedAppointment.getAppointment_ID());

            ps.execute();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

}
