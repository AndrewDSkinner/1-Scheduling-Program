package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.Time;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Calendar;

/**
 * Represents the appointments and operations performed on them.
 */

public class Appointments {

    /**
     * The appointment ID.
     */
    private int Appointment_ID;

    /**
     * The appointment title.
     */
    private String Title;

    /**
     * The appointment description.
     */
    private String Description;

    /**
     * The appointment location.
     */
    private String Location;

    /**
     * The appointment type.
     */
    private String Type;

    /**
     * The appointment start time and date.
     */
    private LocalDateTime Start;
    /**
     * The appointment end time and date.
     */
    private LocalDateTime End;

    /**
     * An associated customer ID.
     */
    private int Customer_ID;

    /**
     * An associated User_ID.
     */

    private int User_ID;

    /**
     * An associated Contact_ID.
     */
    private int Contact_ID;

    /**
     * A list of all appointments
     */
    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    /**
     * A list of all appointment types.
     */

    private static ObservableList<String> allTypes = FXCollections.observableArrayList("One-on-One",
                                                                                        "Stand-Up Meeting",
                                                                                        "Coffee Break", "De-Briefing",
                                                                                        "Planning Session");


    public Appointments(int appointment_ID, String title, String description, String location, String type,
                        LocalDateTime start, LocalDateTime end, int customer_ID, int user_ID, int contact_ID)
    {
        Appointment_ID = appointment_ID;
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        Customer_ID = customer_ID;
        User_ID = user_ID;
        Contact_ID = contact_ID;
    }

    /**
     *
     * @return - returns all appointments.
     */
    public static ObservableList<Appointments> getAllAppointments()
    {
        return allAppointments;
    }

    /**
     *
     * @return - returns appointment ID
     */

    public int getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     *
     * @return - returns appointment title
     */

    public String getTitle() {
        return Title;
    }

    /**
     *
     * @return returns appointment description
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @return - returns appointment location
     */
    public String getLocation() {
        return Location;
    }

    /**
     *
     * @return - returns appointment type
     */
    public String getType() {
        return Type;
    }

    /**
     *
     * @return - returns a string formatted representation of the start time
     */

    public String getStart() {

        String formatStartDate = Time.formatDateTime(Start);


        return formatStartDate ;
    }

    /**
     *
     * @return - returns appointment start LocalDateTime
     */
    public LocalDateTime getStartLocalDateTime()
    {
        return Start;
    }

    /**
     *
     * @return - returns appointment LocalTime
     */

    public LocalTime getStartTime()
    {
        return Start.toLocalTime();
    }

    /**
     *
     * @return - returns appointment end time formatted string
     */

    public String getEnd()
    {
        String formatEndDate = Time.formatDateTime(End);

        return formatEndDate;
    }

    /**
     *
     * @return - - returns appointment end LocalDateTime
     */
    public LocalDateTime getEndLocalDateTime()
    {
        return End;
    }

    /**
     *
     * @return - returns appointment end LocalTime
     */

    public LocalTime getEndTime()
    {
        return End.toLocalTime();
    }

    /**
     *
     * @return - returns appointment LocalDate
     */

    public LocalDate getDatePicker()
    {
        return Start.toLocalDate();
    }

    /**
     *
     * @return - - returns appointment customer ID
     */

    public int getCustomer_ID()
    {
        return Customer_ID;
    }

    /**
     *
     * @return - returns appointment user ID
     */
    public int getUser_ID()
    {
        return User_ID;
    }

    /**
     *
     * @return - - returns appointment contact ID
     */
    public int getContact_ID()
    {
        return Contact_ID;
    }

    /**
     *
     * @return - all appointment types
     */
    public static ObservableList<String> getAllTypes()
    {
        return allTypes;
    }

    /**
     *
     * @param UpdatedAppointment - updates appointment matching appointment ID of appointment passed in against all appointments
     */

    public static void updateAppointment(Appointments UpdatedAppointment)
    {
        for (int i = 0; i < allAppointments.size(); i++)
        {
            if (allAppointments.get(i).Appointment_ID == UpdatedAppointment.getAppointment_ID())
            {
                allAppointments.set(i, UpdatedAppointment);
            }
        }
    }

    /**
     * Counts the number of customers with appointments based on type and month.
     * @param selectedType - the selected type
     * @param selectedMonth - the selected month
     * @return - the total number based on type and month
     */
    public static int typeAndMonthCount(String selectedType, Month selectedMonth)
    {
        int numCustomers = 0;

        System.out.println("Selected Type: " + selectedType);
        System.out.println("Selected Month: " + selectedMonth);

        for (Appointments app : getAllAppointments())
        {
            System.out.println("App Type: " + app.getType());
            System.out.println("App Month: " + app.getStartLocalDateTime().getMonth());
            if (app.getType().equals(selectedType) && app.getStartLocalDateTime().getMonth().equals(selectedMonth))
            {
                numCustomers++;
            }
        }

        return numCustomers;
    }

    /**
     * Sets the appointment ID, received from the DB.
     * @param appointment_ID - the appointment ID
     */
    public void setAppointment_ID(int appointment_ID)
    {
        Appointment_ID = appointment_ID;
    }
}
