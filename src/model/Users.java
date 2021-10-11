package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Calendar;


/**
 * Represents Users objects and operations associated with them.
 */
public class Users {

    /**
     * User ID
     */
    private int User_ID;

    /**
     * User name
     */
    private String User_Name;

    /**
     * Password
     */
    private String Password;

    /**
     * Create Date
     */
    private Calendar Create_Date;

    /**
     * Created By
     */
    private String Created_By;

    /**
     * Last Update
     */
    private Calendar Last_Update;

    /**
     * Last Update By
     */
    private String Last_Update_By;

    /**
     * Array of all users
     */
    private static ObservableList<Users> allUsers = FXCollections.observableArrayList();


    public Users(int User_ID, String User_Name, String Password, Calendar Create_Date,
                 String Created_By, Calendar Last_Update, String Last_Update_By) {
        
        this.User_ID = User_ID;
        this.User_Name = User_Name;
        this.Password = Password;
        //this.active = Active;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Update_By = Last_Update_By;
        
    }

    /**
     * Gets all users.
     * @return - returns allUsers array
     */
    public static ObservableList<Users> getAllUsers()
    {
        return allUsers;
    }

    /**
     * Gets User ID
     * @return - User_ID
     */

    public int getUser_ID()
    {
        return User_ID;
    }

    /**
     * Get User Name
     * @return - User_Name
     */

    public String getUser_Name()
    {
        return User_Name;
    }

    /**
     * Get Password
     * @return - Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * String representation of a User object.
     * @return - Only the User ID is returned.
     */
    @Override
    public String toString()
    {
        return(Integer.toString(User_ID));
    }

    /**
     * Get User ID
     * @param User_ID
     * @return
     */
    public static Users getUser(int User_ID)
    {
        Users foundUser = null;

        for (Users u : allUsers)
        {
            if (u.User_ID == User_ID)
            {
                foundUser = u;
            }
        }

        return foundUser;
    }

}
