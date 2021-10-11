package View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the contact schedule screen
 */

public class ContactSchedulesController implements Initializable
{
    /**
     * The appointment tableview
     */
    public TableView allAppointmentsTable;

    /**
     * Appointment ID column
     */
    public TableColumn appointmentIDCol;

    /**
     * Title Column
     */
    public TableColumn titleCol;

    /**
     * Description column
     */
    public TableColumn descriptionCol;

    /**
     * Location column
     */
    public TableColumn locationCol;

    /**
     * Type column
     */
    public TableColumn typeCol;

    /**
     * Start column
     */
    public TableColumn startCol;

    /**
     * End Column
     */
    public TableColumn endCol;

    /**
     * Customer column
     */
    public TableColumn customerCol;

    /**
     * User column
     */
    public TableColumn userCol;

    /**
     * Contact column
     */
    public TableColumn contactCol;

    /**
     * Contact combo
     */
    public ComboBox contactCombo;

    /**
     * Observable list of contacts that match the selection.
     */
    private ObservableList<Appointments> matchingContacts = FXCollections.observableArrayList();

    /**
     * Sets the tableview to show appointments for the selected contact
     * @param actionEvent
     */

    public void onContactCombo(ActionEvent actionEvent)
    {
        matchingContacts.clear();
        Contacts selectedContact = (Contacts)contactCombo.getSelectionModel().getSelectedItem();

        if(selectedContact == null)
        {
            return;
        }

        for (Appointments app : Appointments.getAllAppointments())
        {
            if (app.getContact_ID() == selectedContact.getContact_ID())
            {
                matchingContacts.add(app);
            }
        }


        allAppointmentsTable.setItems(matchingContacts);

        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        customerCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("User_ID"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("Contact_ID"));

    }

    /**
     * Initializes the contact combo when the screen is called.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
     contactCombo.setItems(Contacts.getAllContacts());
    }

    /**
     * Exit button calls the reports menu.
     * @param actionEvent
     * @throws IOException
     */
    public void onExitButton(ActionEvent actionEvent) throws IOException
    {

        Parent root = FXMLLoader.load(getClass().getResource("/View/ReportsMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 200, 239);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}
