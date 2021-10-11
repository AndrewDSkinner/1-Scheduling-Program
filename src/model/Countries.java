package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Calendar;

/**
 * Represents the Country objects and operations on them.
 */
public class Countries {

    /**
     * Country ID
     */
    private int Country_ID;

    /**
     * Country Name
     */
    private String Country;

    /**
     * Create Date
     */
    private Calendar Create_Date;

    /**
     * Created By
     *
     */
    private String Created_By;

    /**
     * Last Update
     */
    private Calendar Last_Update;

    /**
     * Last Updated By
     */
    private String Last_Updated_By;

    /**
     * Observable list of all countries.
     */
    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();


    public Countries(int Country_ID, String Country, Calendar Create_Date, String Created_By, Calendar Last_Update,
              String Last_Updated_By)
    {
        this.Country_ID = Country_ID;
        this.Country = Country;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;

    }

    /**
     * Retrieves a string country name based on Division ID
     * @param Division_ID - Division ID
     * @return - String country name
     */

    public static String getCountryName(int Division_ID)
    {
        String Country_Name = "Null";

        First_Level_Divisions Div = First_Level_Divisions.getDivision(Division_ID);

        for (Countries country : allCountries)
        {
            if(country.getCountry_ID() == Div.getCOUNTRY_ID())
            {
                Country_Name = country.Country;
            }
        }



        return Country_Name;
    }

    /**
     * Retrieves all countries.
     * @return
     */

    public static ObservableList<Countries>getAllCountries()
    {
        return allCountries;
    }



    public static ObservableList<Countries> getCountryList(int Country_ID)
    {
       ObservableList<Countries> country = FXCollections.observableArrayList();

        for( Countries c : allCountries)
        {
            if (c.Country_ID == Country_ID)
            {
                country.add(c);
            }
        }
        return country;
    }

    /**
     * Gets the country ID
     * @return - the country ID
     */
    public int getCountry_ID()
    {
        return Country_ID;
    }

    /**
     * Formats a country object when converted to String.
     * @return - only the Country Name is retrieved.
     */
    @Override
    public String toString()
    {
        return (Country);
    }

    /**
     * Retrieves a country object based on a division ID.
     * @param Div_ID - the division ID to check against.
     * @return - a country object.
     */
    public static Countries getCountryByDivID(int Div_ID)
    {
        Countries foundCountry = null;
        int Country_ID = 0;

        for (First_Level_Divisions div : First_Level_Divisions.getAllDivisions())
        {
            if (Div_ID == div.getDivision_ID())
            {
                Country_ID = div.getCOUNTRY_ID();
            }
            for (Countries c : allCountries)
            {
                if (c.Country_ID == Country_ID)
                {
                    foundCountry = c;
                }
            }
        }
        return foundCountry;
    }


}
