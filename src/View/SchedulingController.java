package View;

import Database.Delete_Statements;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import util.DeleteMessage;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * Controls the main scheduling screen.
 */
public class SchedulingController implements Initializable {
    /**
     *  ALl appointments tableview
     */
    public TableView allAppointmentsTable;

    /**
     * Title column
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
     * Start time and date column
     */
    public TableColumn startCol;

    /**
     * End time and date column
     */
    public TableColumn endCol;

    /**
     * Customer name column
     */
    public TableColumn customerCol;

    /**
     * User ID column
     */
    public TableColumn userCol;

    /**
     * Contact ID column
     */
    public TableColumn contactCol;

    /**
     * Appointment ID column
     */
    public TableColumn appointmentIDCol;

    /**
     * Filter combo
     */
    public ComboBox filterCombo;

    /**
     * Filtered Appointments observable list.
     */
    private ObservableList<Appointments> filteredAppointments = FXCollections.observableArrayList();


    /**
     * Gets the filter object
     * @return
     */
    public ObservableList<String> getAllFilters()
    {
        return Filter;
    }

    /**
     * Observable list of possible filter options.
     */

    private ObservableList<String> Filter = FXCollections.observableArrayList("All", "Current Month", "Current Week");

    /**
     * Opens the add appointment screen.
     * @param actionEvent
     * @throws IOException
     */
    public void onAddButton(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 750, 450);
        stage.setTitle("Add Appointment");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the Update appointment screen.
     * @param actionEvent
     */
    public void onUpdateButton(ActionEvent actionEvent) {
        System.out.println("Update button clicked.");
       try {
           Appointments selectedAppointment = (Appointments) allAppointmentsTable.getSelectionModel().getSelectedItem();


           UpdateAppointmentController.receiveAppointment(selectedAppointment);

           Parent root = FXMLLoader.load(getClass().getResource("/View/UpdateAppointment.fxml"));
           Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
           Scene scene = new Scene(root, 750, 450);
           stage.setTitle("Update Appointment");
           stage.setScene(scene);
           stage.show();

       } catch(Exception e)
       {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Error Alert");
           alert.setContentText("Select an appointment from the table to update!");
           alert.showAndWait();
       }

    }

    /**
     * Deletes an appointment from the table and DB.
     * @param actionEvent
     */
    public void onDeleteButton(ActionEvent actionEvent)
    {
        Appointments selectedAppointment = (Appointments)allAppointmentsTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("Select an appointment from the table to delete!");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you certain you want to delete this appointment?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            /**
             * LAMBDA EXPRESS creates a delete message
             */
            DeleteMessage deleteMessage = (i, s) -> "Meeting ID: " + i + " of Type: " + s + " has been deleted.";

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation");

            confirmation.setContentText(deleteMessage.DeleteMessage(selectedAppointment.getAppointment_ID(), selectedAppointment.getType()));
            confirmation.showAndWait();

            Delete_Statements.deleteAppointment(selectedAppointment);

            allAppointmentsTable.getItems().remove(selectedAppointment);

        }
    }

    /**
     * Exit back to the main menu.
     * @param actionEvent
     * @throws IOException
     */
    public void onExitButton(ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 200, 240);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Initializes the tableview and filter combo box.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        allAppointmentsTable.setItems(Appointments.getAllAppointments());


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

        filterCombo.setItems(getAllFilters());
    }

    /**
     * Sets the tableview to the a current month, week, or all appointments.
     * @param actionEvent
     */
    public void onFilter(ActionEvent actionEvent)
    {

        if(filterCombo.getValue().equals("Current Month"))
        {
            System.out.println("Current Month");

            filteredAppointments.clear();

            Month currentMonth = LocalDate.now().getMonth();

            for (Appointments apps : Appointments.getAllAppointments())
            {
                if (apps.getDatePicker().getMonth() == currentMonth)
                {
                    filteredAppointments.add(apps);
                }
            }
            allAppointmentsTable.setItems(filteredAppointments);

        } else if(filterCombo.getValue().equals("Current Week"))
        {
            System.out.println("Current Week");

            filteredAppointments.clear();

            LocalDate today = LocalDate.now();

            DayOfWeek dayOfWeek = LocalDateTime.now().getDayOfWeek();

            int dayNum = dayOfWeek.getValue() + 1;

            LocalDate Saturday = today.minusDays(dayNum);
            LocalDate nextSunday = Saturday.plusDays(8);

            for (Appointments apps : Appointments.getAllAppointments())
            {
                if ((apps.getDatePicker().isAfter(Saturday) && apps.getDatePicker().isBefore(nextSunday)))
                {
                    filteredAppointments.add(apps);
                }
            }

            allAppointmentsTable.setItems(filteredAppointments);

        } else
        {
            System.out.println("All");

            allAppointmentsTable.setItems(Appointments.getAllAppointments());
        }
    }
}
