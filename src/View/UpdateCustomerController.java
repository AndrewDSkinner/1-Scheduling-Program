package View;

import Database.Update_Statements;
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
 * Controls the update customer screen.
 */
public class UpdateCustomerController implements Initializable {

    /**
     * Received customer.
     */
    private static Customers receivedCustomer;

    /**
     * Division combo box.
     */
    public ComboBox<First_Level_Divisions> divisionCombo;

    /**
     * Country combo box.
     */
    public ComboBox<Countries> countryCombo;

    /**
     * Customer ID field.
     */
    public TextField customerIDField;

    /**
     * Customer name field.
     */
    public TextField customerNameField;

    /**
     * Address field.
     */
    public TextField addressField;

    /**
     * Postal code field.
     */
    public TextField postalCodeField;

    /**
     * Phone field
     */
    public TextField phoneField;


    /**
     * Saves the current state of the update customer form to the customer object and database.
     * @param actionEvent
     * @throws IOException - FXMLLoader.Load
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

        int Customer_ID = Integer.parseInt(customerIDField.getText());
        String Customer_Name = customerNameField.getText();
        String Address = addressField.getText();
        String phone = phoneField.getText();
        String postal = phoneField.getText();
        int division_id = divisionCombo.getSelectionModel().getSelectedItem().getDivision_ID();
        String division_name = First_Level_Divisions.getDivisionName(division_id);
        String country_name = Countries.getCountryName(division_id);

        Customers customer = new Customers(Customer_ID, Customer_Name, Address, postal, phone, division_id,
                division_name, country_name);

        Update_Statements.updateCustomer(customer);

        Customers.updateCustomer(customer);

        Parent root = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 747, 390);
        stage.setTitle("Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Exits back to the main customer screen.
     * @param actionEvent
     * @throws IOException
     */

    public void onExitButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Exit button clicked.");

        Parent root = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 747, 390);
        stage.setTitle("Customer");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Receives a customer from the main customer screen.
     * @param receiveCust
     */

    public static void receiveCustomer(Customers receiveCust)
    {
        receivedCustomer = receiveCust;
    }

    /**
     * Initializes the update form based on the received customer attributes.
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        countryCombo.setItems(Countries.getAllCountries());
        divisionCombo.setItems(First_Level_Divisions.getAllDivisions());

        customerIDField.setText(Integer.toString(receivedCustomer.getCustomer_ID()));
        customerNameField.setText(receivedCustomer.getCustomer_Name());
        addressField.setText(receivedCustomer.getAddress());
        countryCombo.setValue(Countries.getCountryByDivID(receivedCustomer.getDivision_ID()));
        divisionCombo.setValue(First_Level_Divisions.getDivision(receivedCustomer.getDivision_ID()));
        postalCodeField.setText(receivedCustomer.getPostal_Code());
        phoneField.setText(receivedCustomer.getPhone());
    }

    /**
     * Sets the division combo box based on the selection of the country combo box.
     * @param actionEvent
     */

    public void onCountrySet(ActionEvent actionEvent)
    {
        Countries selectedCountry = (Countries)countryCombo.getSelectionModel().getSelectedItem();

        if (selectedCountry == null)
        {
            return;
        } else
        {
            divisionCombo.setPromptText("Choose a Division...");
            divisionCombo.setItems(First_Level_Divisions.findDivByCountryID(selectedCountry.getCountry_ID()));

        }

    }
}