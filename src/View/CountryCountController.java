package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointments;
import model.Countries;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Counts the number of customers based on country.
 */

public class CountryCountController implements Initializable

{
    /**
     * Country combo
     */
    public ComboBox countryCombo;

    /**
     * Result Field
     */
    public TextField resultField;

    /**
     * Country Combo is set upon screen call
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)

    {
        countryCombo.setItems(Countries.getAllCountries());

    }

    /**
     * When a country is selected, the amount of customers in that country are displayed in the text field
     * @param actionEvent - The selection of a country
     */
    public void onCountryCombo(ActionEvent actionEvent)
    {
        int numCountryCustomers = 0;

       Countries selectedCountry = (Countries)countryCombo.getSelectionModel().getSelectedItem();

       for (Customers c : Customers.getAllCustomers())
       {
           if (Countries.getCountryByDivID(c.getDivision_ID()).getCountry_ID() == selectedCountry.getCountry_ID())
           {
               numCountryCustomers++;
           }
       }
        resultField.setText(Integer.toString(numCountryCustomers));
    }

    /**
     * Exit button calls the reports menu
     * @param actionEvent
     * @throws IOException
     */

    public void onExitButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Exit button clicked.");

        Parent root = FXMLLoader.load(getClass().getResource("/View/ReportsMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 200, 239);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }
}
