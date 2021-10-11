package util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * Represents time objects and conversions
 */
public class Time {
    /**
     * Formats a string date and returns a calendar
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Calendar stringToCalendar(String strDate) throws  ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf. parse(strDate);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Formats LocalDateTimes and returns a String
     * @param dateTime
     * @return
     */
    public static String formatDateTime(LocalDateTime dateTime)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String date = dtf.format(dateTime);


        return date;
    }



    /*
    private static ObservableList<LocalTime> allValidTimes =
            FXCollections.observableArrayList(LocalTime.of(8,0), LocalTime.of(9,0),
                    LocalTime.of(10,0), LocalTime.of(11,0), LocalTime.of(12,0),
                    LocalTime.of(13, 0), LocalTime.of(14,0), LocalTime.of(15,0),
                    LocalTime.of(16,0), LocalTime.of(17,0), LocalTime.of(18,0),
                    LocalTime.of(19,0), LocalTime.of(20,0), LocalTime.of(21, 0),
                    LocalTime.of(22,0));

     */

    /**
     * All valid start date times for an appointment
     */
    private static ObservableList<LocalDateTime> allValidStartDateTimes =
            FXCollections.observableArrayList(
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(8, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(9, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(10, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(11, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(12, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(13, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(14, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(15, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(16, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(17, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(18, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(19, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(20, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(21, 0)));

    /**
     * All valid end times for an appointment.
     */

    private static ObservableList<LocalDateTime> allValidEndDateTimes =
            FXCollections.observableArrayList(
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(9, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(10, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(11, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(12, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(13, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(14, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(15, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(16, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(17, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(18, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(19, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(20, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(21, 0)),
                    LocalDateTime.of(LocalDate.now(),LocalTime.of(22, 0)));

    /**
     * Convert Start LocalDateTimes to EST
     * @return
     */
    private static ObservableList<LocalDateTime> convertLocalStartToEST()
    {
        ObservableList<LocalDateTime> convertedTimes = FXCollections.observableArrayList();

        for (LocalDateTime dates : allValidStartDateTimes)
        {
            //ZonedDateTime zdt = dates.atZone((ZoneId.of(ZoneId.systemDefault().toString())));
            ZonedDateTime zdt = dates.atZone(ZoneId.of("US/Eastern"));
            ZonedDateTime targetDt = zdt.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            LocalDateTime ldtIn = targetDt.toLocalDateTime();

            convertedTimes.add(ldtIn);
        }


        return convertedTimes;
    }

    /**
     * Convert End LocalDateTimes to EST
     * @return
     */
    private static ObservableList<LocalDateTime> convertLocalEndToEST()
    {
        ObservableList<LocalDateTime> convertedTimes = FXCollections.observableArrayList();

        for (LocalDateTime dates : allValidEndDateTimes)
        {
            //ZonedDateTime zdt = dates.atZone((ZoneId.of(ZoneId.systemDefault().toString())));
            ZonedDateTime zdt = dates.atZone(ZoneId.of("US/Eastern"));
            ZonedDateTime targetDt = zdt.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
            LocalDateTime ldtIn = targetDt.toLocalDateTime();

            convertedTimes.add(ldtIn);
        }


        return convertedTimes;
    }



    /**
     * Get converted Start Times
     * @return
     */

    public static ObservableList<LocalTime> getConvertedStartTimes()
    {
        ObservableList<LocalTime> hours = FXCollections.observableArrayList();

        for (LocalDateTime convertedDates : convertLocalStartToEST())
        {
            hours.add(convertedDates.toLocalTime());
        }

        return hours;
    }

    /**
     * Get converted End Times
     * @return
     */

    public static ObservableList<LocalTime> getConvertedEndTimes()
    {
        ObservableList<LocalTime> hours = FXCollections.observableArrayList();

        for (LocalDateTime convertedDates : convertLocalEndToEST())
        {
            hours.add(convertedDates.toLocalTime());
        }

        return hours;
    }

    /**
     * All valid months
     */

    private static ObservableList<Month> allMonths = FXCollections.observableArrayList(Month.JANUARY, Month.FEBRUARY,
            Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER,
    Month.NOVEMBER, Month.DECEMBER);

    /**
     * Get all valid months
     * @return
     */

    public static ObservableList<Month> getAllMonths()
    {
        return allMonths;
    }



/*
    public static ObservableList<LocalTime> getAllValidTimes()
    {
        return allValidTimes;
    }

 */



    public static ZoneId zone = TimeZone.getDefault().toZoneId();
}