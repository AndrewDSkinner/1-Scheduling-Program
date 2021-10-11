package Database;

//Retrieves info from the database to build model classes.

import model.*;

import util.DBQuery;
import util.JDBCHelper;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Calendar;


import static util.Time.stringToCalendar;

/**
 * Reads the current state of each table in the database upon application start.
 */
public class Select_Statements
{
    /**
     * Represents a user.
     */
    private static Users user;

    /**
     * Represents the connection to the database.
     */
    private static Connection conn = JDBCHelper.getConnection();

    /**
     * Represents a first level division.
     */
    private static First_Level_Divisions division;

    /**
     * Represents a selectStatement query;
     */

    private static String selectStatement;

    /**
     * Represents a customer.
     */

    private static Customers customer;

    /**
     * Represents a country.
     */
    private static Countries country;

    /**
     * Represents a contact.
     */
    private static Contacts contact;

    /**
     * Retrieves all appointment details from DB.
     * @throws SQLException - Exception thrown by PreparedStatement and ResultSet
     */
    //Retrieve all appointment data.
    public static void selectAppointmentDetails() throws SQLException {
        selectStatement = "SELECT * FROM appointments";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.execute();

        ResultSet rs = ps.getResultSet();

        while (rs.next())
        {
            int Appointment_ID = rs.getInt("Appointment_ID");
            String Title = rs.getString("Title");
            String Description = rs.getString("Description");
            String Location = rs.getString("Location");
            String Type = rs.getString("Type");
            LocalDateTime Start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime End = rs.getTimestamp("End").toLocalDateTime();
            int Customer_ID = rs.getInt("Customer_ID");
            int User_ID = rs.getInt("User_ID");
            int Contact = rs.getInt("Contact_ID");

            //Calendar calendarStart = stringToCalendar(Start);
            //Calendar calendarEnd = stringToCalendar(End);

            Appointments appointment = new Appointments(Appointment_ID, Title, Description, Location, Type,
                    Start, End, Customer_ID, User_ID, Contact);

            Appointments.getAllAppointments().add(appointment);
        }
    }

    /**
     * Retrieves Contact details from DB.
     * @throws SQLException - Exception thrown by PreparedStatement and ResultSet
     */
    //Retrieve all contacts data.
    public static void selectContactDetails() throws SQLException
    {
        selectStatement = "SELECT * from contacts";

        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute();

        ResultSet rs = ps.getResultSet();

        while (rs.next())
        {
            int Contact_ID = rs.getInt("Contact_ID");
            String Contact_Name = rs.getString("Contact_Name");
            String Email = rs.getString("Email");

            contact = new Contacts(Contact_ID, Contact_Name, Email);
            Contacts.getAllContacts().add(contact);
        }

    }

    /**
     * Retrieves Country details from DB.
     * @throws SQLException - Exception thrown by PreparedStatement and ResultSet
     * @throws ParseException - Thrown by stringToCalendar function
     */
    //Retrieve all countries data.
    public static void selectCountryDetails() throws SQLException, ParseException
    {
        selectStatement = "SELECT * FROM countries";

        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute();

        ResultSet rs = ps.getResultSet();

        while (rs.next())
        {
            int Country_ID = rs.getInt("Country_ID");
            String Country = rs.getString("Country");
            String Create_Date = rs.getString("Create_Date");
            String Created_By = rs.getString("Created_By");
            String Last_Update = rs.getString("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");

            Calendar calendarCreateDate = stringToCalendar(Create_Date);
            Calendar calendarLastUpdate = stringToCalendar(Last_Update);

            country = new Countries(Country_ID, Country, calendarCreateDate, Created_By, calendarLastUpdate,
                    Last_Updated_By);

            Countries.getAllCountries().add(country);

        }
    }

    /**
     * Retrieves Customer details from DB.
     * @throws SQLException - Exception thrown by PreparedStatement and ResultSet
     * @throws ParseException - Thrown by stringToCalendar function
     */

    //Retrieve all customers data.
    public static void selectCustomerDetails() throws SQLException, ParseException
    {



        selectStatement = "SELECT * FROM customers";

        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute(); //Executes the prepared statement


        ResultSet rs = ps.getResultSet();

        while (rs.next())
        {
            int Customer_ID = rs.getInt("Customer_ID");
            String Customer_Name = rs.getString("Customer_Name");
            String Address = rs.getString("Address");
            String Postal_Code = rs.getString("Postal_Code");
            String Phone = rs.getString("Phone");
            int Division_ID = rs.getInt("Division_ID");
            String Division_Name = First_Level_Divisions.getDivisionName(Division_ID);
            String Country_Name = Countries.getCountryName(Division_ID);





            customer = new Customers(Customer_ID, Customer_Name, Address, Postal_Code, Phone,
                    Division_ID, Division_Name, Country_Name);

            Customers.getAllCustomers().add(customer);


        }

    }

    /**
     * Retrieves First Level Division details from DB.
     * @throws SQLException - Exception thrown by PreparedStatement and ResultSet
     * @throws ParseException - Thrown by stringToCalendar function
     */

    //Retrieve all First Level Divisions data.
    public static void selectDivisionDetails() throws SQLException, ParseException {



        selectStatement = "SELECT * FROM first_level_divisions";

        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();

        ps.execute(); //Executes the prepared statement


        ResultSet rs = ps.getResultSet();

        while (rs.next()) {
            int Division_ID = rs.getInt("Division_ID");
            String Division = rs.getString("Division");
            String Create_Date = rs.getString("Create_Date");
            String Created_By = rs.getString("Created_By");
            String Last_Update = rs.getString("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");
            int COUNTRY_ID = rs.getInt("COUNTRY_ID");


            Calendar calendarCreateDate = stringToCalendar(Create_Date);
            Calendar calendarLastUpdate = stringToCalendar(Last_Update);



            division  = new First_Level_Divisions(Division_ID, Division, calendarCreateDate,
                    Created_By, calendarLastUpdate, Last_Updated_By, COUNTRY_ID);

            First_Level_Divisions.getAllDivisions().add(division);


        }

    }

    /**
     * Retrieves User details from DB.
     * @throws SQLException - Exception thrown by PreparedStatement and ResultSet
     * @throws ParseException - Thrown by stringToCalendar function
     */

    //Retrieve all Users data.
    public static void selectUserDetails() throws SQLException, ParseException {



        selectStatement = "SELECT * FROM users";

        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps =  DBQuery.getPreparedStatement();

        ps.execute(); //Executes the prepared statement


        ResultSet rs = ps.getResultSet();

        while (rs.next()) {
            int User_ID = rs.getInt("User_ID");
            String User_Name = rs.getString("User_Name");
            String Password = rs.getString("Password");
            String Create_Date = rs.getString("Create_Date");
            //LocalDate date = rs.getDate("Create_Date").toLocalDate();
            //LocalTime time = rs.getTime("Create_Date").toLocalTime();
            String Created_By = rs.getString("Created_By");
            String Last_Update = rs.getString("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");

            Calendar calendarCreateDate = stringToCalendar(Create_Date);
            Calendar calendarLastUpdate = stringToCalendar(Last_Update);



            user = new Users(User_ID, User_Name, Password,calendarCreateDate, Created_By, calendarLastUpdate, Last_Updated_By);



            Users.getAllUsers().add(user);


        }


    }

}
