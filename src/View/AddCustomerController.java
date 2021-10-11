package View;

import Database.Insert_Statements;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.First_Level_Divisions;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the add customer screen.
 */
public class AddCustomerController implements Initializable {

    /**
     * Division Combo
     */
    public ComboBox<First_Level_Divisions> divisionCombo;

    /**
     * Country combo
     */
    public ComboBox<Countries> countryCombo;

    /**
     * Customer ID Field
     */
    public TextField customerIDField;

    /**
     * Customer Name field
     */
    public TextField customerNameField;

    /**
     * Address Field
     */
    public TextField addressField;

    /**
     * Phone field
     */
    public TextField phoneField;

    /**
     * Postal Code field
     */
    public TextField postalCodeField;


    /**
     * Saves the current state of the form and creates a customer object
     * @param actionEvent - fires when the save button is clicked
     * @throws IOException - Thrown by FXMLLoader.Load
     */
    public void onSaveButton(ActionEvent actionEvent) throws IOException {

        System.out.println("Save button clicked.");

        if (customerNameField.getText().isBlank() ||
        addressField.getText().isBlank() ||
        phoneField.getText().isBlank() ||
        postalCodeField.getText().isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("Only Customer ID Field can be blank.");
            alert.showAndWait();
            return;
        }

        try {

            int Customer_ID = Customers.getAllCustomers().get(Customers.getAllCustomers().size() - 1).getCustomer_ID() + 1;
            String Customer_Name = customerNameField.getText();
            String Address = addressField.getText();
            String phone = phoneField.getText();
            String postal = postalCodeField.getText();
            int division_id = divisionCombo.getSelectionModel().getSelectedItem().getDivision_ID();
            String division_name = First_Level_Divisions.getDivisionName(division_id);
            String country_name = Countries.getCountryName(division_id);

            Customers newCustomer = new Customers(Customer_ID, Customer_Name, Address, postal, phone, division_id,
                    division_name, country_name);

            Customer_ID = Insert_Statements.insertCustomer(newCustomer);

            newCustomer.setCustomer_ID(Customer_ID);

            Customers.getAllCustomers().add(newCustomer);

            Parent root = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 747, 390);
            stage.setTitle("Customer");
            stage.setScene(scene);
            stage.show();
        } catch(NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("Only Customer ID Field can be blank.");
            alert.showAndWait();
            return;
        }
    }

    /**
     * The exit button returns to the customer screen
     * @param actionEvent - when the exit button is clicked
     * @throws IOException - FXMLLoader.Load
     */
    public void onExitButton(ActionEvent actionEvent) throws IOException
    {

        System.out.println("Exit button clicked.");

        Parent root = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 747, 390);
        stage.setTitle("Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the combos when the Add customer screen is called.
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        countryCombo.setItems(Countries.getAllCountries());
        countryCombo.setPromptText("Choose country...");



        divisionCombo.setItems(First_Level_Divisions.getAllDivisions());
        divisionCombo.setPromptText("Choose division...");


    }

    /**
     * When the Country combo is set the division combo is adjusted to reflect the right divisions
     * @param actionEvent - when a combobox is selected
     */
    public void onCountrySet(ActionEvent actionEvent) {

        Countries selectedCountry = countryCombo.getSelectionModel().getSelectedItem();


        if (selectedCountry == null)
        {
            return;
        } else
        {
        divisionCombo.setItems(First_Level_Divisions.findDivByCountryID(selectedCountry.getCountry_ID()));
        }

    }


}
