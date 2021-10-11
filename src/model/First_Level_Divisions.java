package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Calendar;


/**
 * Represents first level division objects and their operations.
 */
public class First_Level_Divisions {

    /**
     * Division_ID
     */
    private int Division_ID;

    /**
     * Division Name
     */
    private String Division_Name;

    /**
     * Create Date
     */
    private Calendar Create_Date;

    /**
     * Created By
     */
    private String Created_By;

    /**
     * Last Updated
     */
    private Calendar Last_Updated;

    /**
     * Last Updated By
     */
    private String Last_Updated_By;

    /**
     * Country ID
     */
    private int COUNTRY_ID;

    /**
     * Array of all first level divisions.
     */
    private static ObservableList<First_Level_Divisions> allDivisions = FXCollections.observableArrayList();



    public First_Level_Divisions(int Division_ID, String Division_Name, Calendar Create_Date, String Created_By,
                                 Calendar Last_Updated, String Last_Updated_By, int COUNTRY_ID)
    {
        this.Division_ID = Division_ID;
        this.Division_Name = Division_Name;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Updated = Last_Updated;
        this.Last_Updated_By = Last_Updated_By;
        this.COUNTRY_ID = COUNTRY_ID;
    }

    /**
     * Retrieves all first level divisions
     * @return - allDivisions array.
     */
    public static ObservableList<First_Level_Divisions> getAllDivisions()
    {
        return allDivisions;
    }

    /**
     * Gets the Division Name by division ID
     * @param Division_ID - integer Division ID
     * @return - A string representing a division name
     */

    public static String getDivisionName(int Division_ID)
    {
        String Division = "";

        for (First_Level_Divisions Div : allDivisions)
        {
            if(Div.Division_ID == Division_ID)
            {
                Division = Div.Division_Name;
            }
        }

        return Division ;
    }

    /**
     * Get Division object by division ID
     * @param Division_ID - integer representing a division.
     * @return - Return division object.
     */
    public static First_Level_Divisions getDivision(int Division_ID)
    {
        First_Level_Divisions Division = null;
        for( First_Level_Divisions Div : allDivisions)
        {
            if(Div.Division_ID == Division_ID)
            {
                Division = Div;
            }
        }
        return Division;
    }

    /**
     * Gets the division ID
     * @return - an integer Division ID
     */

    public int getDivision_ID()
    {
        return Division_ID;
    }

    /**
     * Get Country ID
     * @return - an integer country ID
     */
    public int getCOUNTRY_ID()
    {
        return COUNTRY_ID;
    }

    /**
     * Formatted String representation of a Division object
     * @return - Only the Division_Name is returned
     */
    @Override
    public String toString()
    {
        return (Division_Name);
    }


    /**
     * Finds a division by country ID
     * @param COUNTRY_ID - Integer representing a country ID
     * @return - First level division object
     */
    public static ObservableList<First_Level_Divisions> findDivByCountryID(int COUNTRY_ID)
    {
        ObservableList<First_Level_Divisions> foundDivs = FXCollections.observableArrayList();

        for (First_Level_Divisions div : allDivisions)
        {
            if( div.getCOUNTRY_ID() == COUNTRY_ID)
            {
                foundDivs.add(div);
            }
        }

        return foundDivs;

    }



}
