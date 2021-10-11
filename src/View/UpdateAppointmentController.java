package View;

import Database.Update_Statements;
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


/**
 * Controls the appointment update screen.
 */
public class UpdateAppointmentController implements Initializable
{
    /**
     * End time combo
     */
    public ComboBox<LocalTime> endCombo;

    /**
     * Customer ID combo.
     */
    public ComboBox<Customers> customerIDCombo;

    /**
     * User ID combo.
     */
    public ComboBox<Users> userIDCombo;

    /**
     * Start time combo.
     */
    public ComboBox<LocalTime> startCombo;

    /**
     * Date picker
     */
    public DatePicker datePicker;

    /**
     * Title field
     */
    public TextField titleField;

    /**
     * Location field.
     */
    public TextField locationField;

    /**
     * Description area.
     */
    public TextArea descriptionArea;

    /**
     * Contact combo.
     */
    public ComboBox<Contacts> contactCombo;

    /**
     * Appointment ID Field
     */
    public TextField appointmentIDField;

    /**
     * Received appointment object.
     */
    private static Appointments receivedAppointment;

    /**
     * Type combo box
     */
    public ComboBox typeCombo;

    /**
     * Saves the current state of the appointment form to the appointment object and in the DB.
     * @param actionEvent
     * @throws IOException
     */
    public void onSaveButton(ActionEvent actionEvent) throws IOException {

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

        try {
            int appointmentID = Integer.parseInt(appointmentIDField.getText());
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

            Appointments updatedAppointment = new Appointments(appointmentID, title, description, location, type, startDateTime,
                    endDateTime, customerID, userID, contactID);

            for (Appointments apps : Appointments.getAllAppointments()) {
                if (apps.getCustomer_ID() == updatedAppointment.getCustomer_ID() && apps.getAppointment_ID() != updatedAppointment.getAppointment_ID()) {
                    if (apps.getDatePicker().equals(updatedAppointment.getDatePicker())) {
                        if ((updatedAppointment.getStartTime().equals(apps.getStartTime()) || updatedAppointment.getStartTime().isAfter(apps.getStartTime()))
                                && updatedAppointment.getStartTime().isBefore(apps.getEndTime())) {
                            overlapAlert();
                            return;
                        } else if (updatedAppointment.getEndTime().isAfter(apps.getStartTime()) && ((updatedAppointment.getEndTime().isBefore(apps.getEndTime()) ||
                                updatedAppointment.getEndTime().equals(apps.getEndTime())))) {
                            overlapAlert();
                            return;
                        } else if ((updatedAppointment.getStartTime().equals(apps.getStartTime()) || updatedAppointment.getStartTime().isBefore(apps.getStartTime()))
                                && (updatedAppointment.getEndTime().isAfter(apps.getEndTime()) || updatedAppointment.getEndTime().equals(apps.getEndTime()))) {
                            overlapAlert();
                            return;
                        }
                    }
                }
            }

            Update_Statements.updateAppointment(updatedAppointment);

            Appointments.updateAppointment(updatedAppointment);

            Parent root = FXMLLoader.load(getClass().getResource("/View/Scheduling.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 914, 390);
            stage.setTitle("Scheduling");
            stage.setScene(scene);
            stage.show();
        } catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("Only Appointment ID Field can be blank.");
            alert.showAndWait();
        }
    }

    /**
     * Exits to the scheduling screen.
     * @param actionEvent
     * @throws IOException
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
     * Initializes the fields and combo boxes per the appointment object to be updated.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) 
    {
        appointmentIDField.setText(Integer.toString(receivedAppointment.getAppointment_ID()));
        titleField.setText(receivedAppointment.getTitle());
        locationField.setText(receivedAppointment.getLocation());


        descriptionArea.setText(receivedAppointment.getDescription());

        contactCombo.setItems(Contacts.getAllContacts());
        userIDCombo.setItems(Users.getAllUsers());
        customerIDCombo.setItems(Customers.getAllCustomers());
        typeCombo.setItems(Appointments.getAllTypes());
        startCombo.setItems(Time.getConvertedStartTimes());
        endCombo.setItems(Time.getConvertedEndTimes());

        contactCombo.setValue(Contacts.getContact(receivedAppointment.getContact_ID()));
        userIDCombo.setValue(Users.getUser(receivedAppointment.getUser_ID()));
        customerIDCombo.setValue(Customers.getCustomer(receivedAppointment.getCustomer_ID()));
        typeCombo.setValue(receivedAppointment.getType());

        startCombo.setValue(receivedAppointment.getStartTime());
        endCombo.setValue(receivedAppointment.getEndTime());
        datePicker.setValue(receivedAppointment.getDatePicker());
        
    }

    /**
     * Receives an appointment object from the scheduling controller.
     * @param receive
     */
    public static void receiveAppointment(Appointments receive)

    {
        receivedAppointment = receive;
    }

    /**
     * Creates an overlap alert for appointments.
     */

    public void overlapAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Alert");
        alert.setContentText("This customer already has an appointment during this time!");
        alert.showAndWait();
    }
}
