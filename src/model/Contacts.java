package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents the Contacts and operations performed on their data.
 */

public class Contacts {

    private  int Contact_ID;
    private String Contact_Name;
    private String Email;
    private static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

   public Contacts(int Contact_ID, String Contact_Name, String Email)
    {
    this.Contact_ID = Contact_ID;
    this.Contact_Name = Contact_Name;
    this.Email = Email;
    }

    /**
     * Retrieves all Contacts list.
     * @return - returns all contacts.
     */
    public static ObservableList<Contacts> getAllContacts()
    {
        return allContacts;
    }

    /**
     * Gets the contact ID.
     * @return - an integer representing contact ID.
     */
    public int getContact_ID() {
        return Contact_ID;
    }

    /**
     * Formats a contact object when represented in a combo box.
     * @return - Displays only the contact name.
     */

    @Override
    public String toString()
    {
       return(Contact_Name);
    }

    /**
     * Retrieves a contact object based on a Contact ID argument.
     * @param Contact_ID - the integer representing a contact id/
     * @return - returns a contact object
     */
    public static Contacts getContact(int Contact_ID)
    {
        Contacts foundContact = null;

        for (Contacts con : getAllContacts())
        {
            if (con.Contact_ID == Contact_ID)
            {
                foundContact = con;
            }
        }

        return foundContact;
    }
}
