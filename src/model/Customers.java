package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Represents customer objects and their operations.
 */

public class Customers {

    /**
     * The customer ID.
     */
    private int Customer_ID;

    /**
     * The customer name.
     */
    private String Customer_Name;

    /**
     * Customer address.
     */
    private String Address;

    /**
     * Customer postal code
     */
    private String Postal_Code;

    /**
     * Customer phone number.
     */
    private String Phone;

    /**
     * Customer Division ID
     */
    private int Division_ID;

    /**
     * Customer Division name.
     */
    private String Division;

    /**
     * Customer Country name.
     */
    private String Country;


    /** All customer objects
     *
     */
    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();


    public Customers(int Customer_ID, String Customer_Name, String Address, String Postal_Code, String Phone,
                     int Division_ID, String Div, String Country)
    {
        this.Customer_ID = Customer_ID;
        this.Customer_Name = Customer_Name;
        this.Address = Address;
        this.Postal_Code = Postal_Code;
        this.Phone = Phone;
        this.Division_ID = Division_ID;
        this.Division = Div;
        this.Country = Country;
    }

    /**
     * Gets all customers
     * @return - all customer list.
     */
    public static ObservableList<Customers> getAllCustomers()
    {
        return allCustomers;
    }

    /**
     * Gets the division ID.
     * @return - an integer representing the division ID
     */
    public int getDivision_ID()
    {
        return Division_ID;
    }

    public static Customers customer;

    /**
     * Gets Customer ID
     * @return
     */
    public int getCustomer_ID()
    {
        return Customer_ID;
    }

    /**
     * Gets customer name
     * @return the customer name
     */
    public String getCustomer_Name()
    {
        return Customer_Name;
    }

    /**
     * Gets the customer address
     * @return
     */

    public String getAddress()
    {
        return Address;
    }

    /**
     * Gets the customer postal code.
     * @return the postal code
     */

    public String getPostal_Code()
    {
        return Postal_Code;
    }

    /**
     * Gets the customer phone #.
     * @return
     */
    public String getPhone()
    {
        return Phone;
    }

    /**
     * Get the country name
     * @return - the country name
     */

    public String getCountry()
    {
        return Country;
    }

    /**
     * String representation of Customer
     * @return - only displays the customer ID
     */

    @Override
   public String toString()
    {
        return(Integer.toString(Customer_ID));
    }

    /**
     * Get the division name
     * @return - the division name
     *
     */

    public String getDivision()
    {
        return Division;
    }

    /**
     * Get the customer based on a customer ID
     * @param Customer_ID - the ID to check against.
     * @return - a customer object
     */
    public static Customers getCustomer(int Customer_ID)
    {
        Customers foundCustomer = null;

        for (Customers cust : allCustomers)
        {
            if (cust.Customer_ID == Customer_ID)
            {
                foundCustomer = cust;
            }
        }
        return foundCustomer;
    }

    /**
     * Update a customer in the customer list.
     * @param updatedCustomer - the customer to be updated.
     */
    public static void updateCustomer(Customers updatedCustomer)
    {
        for (int i = 0; i < allCustomers.size() ; i++)
        {
            if (allCustomers.get(i).getCustomer_ID() == updatedCustomer.getCustomer_ID())
            {
                allCustomers.set(i, updatedCustomer);
            }
        }
    }

    /**
     * Sets customer ID
     * @param customer_ID - customer ID to set
     */

    public void setCustomer_ID(int customer_ID) {
        Customer_ID = customer_ID;
    }

    /**
     * Determines if a customer has an appointment.
     * @param Customer_ID - Customer ID to check against.
     * @return - true or false that a match was found.
     */

    public static boolean hasAppointment(int Customer_ID)
    {
        boolean appointmentFound = false;

        for (Appointments app: Appointments.getAllAppointments())
        {
            if (app.getCustomer_ID() == Customer_ID)
            {
                appointmentFound = true;
            }
        }

        return appointmentFound;
    }

}
