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
import util.Time;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;


/**
 * Controls the Customer Report screen which calculates the amount of customer appointments based on type and month.
 */
public class CustomerReportsController implements Initializable
{
    /**
     * ResultField
     */
    public TextField resultField;


    /**
     * Month Combo box.
     */
    public ComboBox monthCombo;

    /**
     * Type combo box
     */
    public ComboBox typeCombo;

    /**
     * Exit to the reports menu.
     * @param actionEvent
     * @throws IOException
     */

    public void onExit(ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ReportsMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 200, 239);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Calculates customer amount based on type and month selected
     * @param actionEvent
     */
    public void onCalculator(ActionEvent actionEvent)
    {
        int numCustomers = 0;

        String selectedType = typeCombo.getSelectionModel().getSelectedItem().toString();
        Month selectedMonth = (Month) monthCombo.getSelectionModel().getSelectedItem();

        numCustomers = Appointments.typeAndMonthCount(selectedType, selectedMonth);

        resultField.setText(Integer.toString(numCustomers));

    }

    /**
     * Initializes the combo boxes when the screen is called.
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        typeCombo.setItems(Appointments.getAllTypes());
        monthCombo.setItems(Time.getAllMonths());
    }
}
