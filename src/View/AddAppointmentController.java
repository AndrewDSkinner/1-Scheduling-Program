package View;

import Database.Insert_Statements;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;
import model.Customers;
import model.Users;
import util.Time;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable
{
    /**
     * Title field
     */
    public TextField titleField;

    /**
     * Location field
     */
    public TextField locationField;

    /**
     * Description Area
     */
    public TextArea descriptionArea;

    /**
     * Contact combo
     */
    public ComboBox<Contacts> contactCombo;


    /**
     * Date picker
     */
    public DatePicker datePicker;

    /**
     * Start time combo
     */
    public ComboBox<LocalTime> startCombo;

    /**
     * End time combo
     */
    public ComboBox<LocalTime> endCombo;

    /**
     * Customer ID combo
     */
    public ComboBox<Customers> customerIDCombo;

    /**
     * User ID combo
     */
    public ComboBox<Users> userIDCombo;

    /**
     * Type combo
     */
    public ComboBox typeCombo;

    /**
     * Initializes the combo boxes when the screen is called.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {


      contactCombo.setItems(Contacts.getAllContacts());
      startCombo.setItems(Time.getConvertedStartTimes());
      endCombo.setItems(Time.getConvertedEndTimes());
      customerIDCombo.setItems(Customers.getAllCustomers());
      userIDCombo.setItems(Users.getAllUsers());
      typeCombo.setItems(Appointments.getAllTypes());
      
    }

    /**
     * Saves the current state of all fields and boxes to create an appointment and returns to scheduling screen.
     * @param actionEvent - Fired when the save button is clicked
     */
    public void onSaveButton(ActionEvent actionEvent)  {
        System.out.println("Save button clicked.");

        if(titleField.getText().isBlank() ||
                descriptionArea.getText().isBlank() ||
                locationField.getText().isBlank())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("Only Appointment ID Field can be blank.");
            alert.showAndWait();
            return;
        }

        try
        {
            int appointmentID = Users.getAllUsers().get(Users.getAllUsers().size() - 1).getUser_ID() + 1;
            String title = titleField.getText();
            String description = descriptionArea.getText();
            String location = locationField.getText();
            String type = typeCombo.getSelectionModel().getSelectedItem().toString();
            LocalDate date = datePicker.getValue();
            LocalTime start = startCombo.getSelectionModel().getSelectedItem();
            LocalTime end = endCombo.getSelectionModel().getSelectedItem();
            int contactID = contactCombo.getSelectionModel().getSelectedItem().getContact_ID();
            int userID = userIDCombo.getSelectionModel().getSelectedItem().getUser_ID();
            int customerID = customerIDCombo.getSelectionModel().getSelectedItem().getCustomer_ID();

            if (start.equals(end) || start.isAfter(end)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Alert");
                alert.setContentText("Start Time must be before End Time");
                alert.showAndWait();
                return;
            }


            LocalDateTime startDateTime = LocalDateTime.of(date, start);
            LocalDateTime endDateTime = LocalDateTime.of(date, end);

            Appointments newAppoint = new Appointments(appointmentID, title, description, location, type, startDateTime,
                    endDateTime, customerID, userID, contactID);



            for (Appointments apps : Appointments.getAllAppointments()) {
                if (apps.getCustomer_ID() == newAppoint.getCustomer_ID()) {
                    if (apps.getDatePicker().equals(newAppoint.getDatePicker())) {
                        if ((newAppoint.getStartTime().equals(apps.getStartTime()) || newAppoint.getStartTime().isAfter(apps.getStartTime()))
                                && newAppoint.getStartTime().isBefore(apps.getEndTime())) {
                            overlapAlert();
                            return;
                        } else if (newAppoint.getEndTime().isAfter(apps.getStartTime()) && ((newAppoint.getEndTime().isBefore(apps.getEndTime()) ||
                                newAppoint.getEndTime().equals(apps.getEndTime())))) {
                            overlapAlert();
                            return;
                        } else if ((newAppoint.getStartTime().equals(apps.getStartTime()) || newAppoint.getStartTime().isBefore(apps.getStartTime()))
                                && (newAppoint.getEndTime().isAfter(apps.getEndTime()) || newAppoint.getEndTime().equals(apps.getEndTime()))) {
                            overlapAlert();
                            return;
                        }
                    }
                }
            }


            appointmentID = Insert_Statements.insertAppointment(newAppoint);

            newAppoint.setAppointment_ID(appointmentID);

            Appointments.getAllAppointments().add(newAppoint);

            Parent root = FXMLLoader.load(getClass().getResource("/View/Scheduling.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 914, 390);
            stage.setTitle("Scheduling");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("Only Appointment ID Field can be blank.");
            alert.showAndWait();
        }

    }

    /**
     * Returns to the scheduling screen.
     * @param actionEvent - when the exit button is clicked
     * @throws IOException - thrown by FXMLLoader.load
     */

    public void onExitButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Exit button clicked.");

        Parent root = FXMLLoader.load(getClass().getResource("/View/Scheduling.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 914, 390);
        stage.setTitle("Scheduling");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * An alert for overlapping appointments.
     */
    public void overlapAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Alert");
        alert.setContentText("This customer already has an appointment during this time!");
        alert.showAndWait();
    }
}
