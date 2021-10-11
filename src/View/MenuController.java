package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Appointments;
import model.Users;
import util.AppointmentMessage;
import util.IncreaseTime;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controls the menu screen
 */
public class MenuController implements Initializable {

    /**
     * Boolean value that controls if the appointment message is called again.
     */
   public static Boolean firstTime = true;

    /**
     * Opens the main customer screen.
     * @param actionEvent
     * @throws IOException - FMXLLoader
     */

    public void onCustomerButton(ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Customer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 747, 390);
        stage.setTitle("Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the schedule screen for appointments
     * @param actionEvent
     * @throws IOException
     */
    public void onScheduleButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Schedule button clicked.");
        Parent root = FXMLLoader.load(getClass().getResource("/View/Scheduling.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 914, 390);
        stage.setTitle("Scheduling");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Returns to the login screen.
     * @param actionEvent
     * @throws IOException - FXMLLoader.Load
     */

    public void onLogoutButton(ActionEvent actionEvent) throws IOException {
        if (Locale.getDefault().toString().equals("en_US")) {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 475, 400);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        } else if(Locale.getDefault().toString().equals("fr_FR")){
            Parent root = FXMLLoader.load(getClass().getResource("FrenchLogin.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 475, 400);
            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * When this screen is called, checks if the user has an upcoming appointment.
     * 1st LAMDA INCREASES CURRENT TIME BY 15 MINUTES
     * 2nd LAMBDA CREATES AN APPOINTMENT ALERT MESSAGE
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        Boolean hasAppointment = false;
        Appointments foundAppointment = null;
        LocalDateTime loginTime = LocalDateTime.now();
        System.out.println(loginTime);
        ZonedDateTime zdt = loginTime.atZone(ZoneId.of("US/Eastern"));
        ZonedDateTime targetDt = zdt.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        //loginTime = targetDt.toLocalDateTime();
        IncreaseTime plus15 = ldt ->
        {
           LocalDateTime result = ldt.plusMinutes(15);

           return result;
        };


        if (firstTime) {
            for (Appointments app : Appointments.getAllAppointments())
            {
                if (app.getUser_ID() == Users.getAllUsers().get(0).getUser_ID())
                {
                    System.out.println("Appointment local time: " + app.getStartLocalDateTime());
                    if (app.getStartLocalDateTime().isAfter(loginTime) && app.getStartLocalDateTime().isBefore(plus15.Increase(loginTime))) {
                        hasAppointment = true;
                        foundAppointment = app;
                    }
                }
            }


            System.out.println(hasAppointment);
            AppointmentMessage message = (i, s) -> "Appointment #" + i + " will begin at " + s + ".";

            Alert appointAlert = new Alert(Alert.AlertType.INFORMATION);
            if (hasAppointment) {
                appointAlert.setTitle("Appointment Soon!");
                appointAlert.setContentText(message.AppointmentMessage(foundAppointment.getAppointment_ID(), foundAppointment.getStart()));
            } else {
                appointAlert.setTitle("No upcoming appointments!");
                appointAlert.setContentText("No appointments within the next 15 minutes.");
            }
            appointAlert.showAndWait();

            firstTime = false;

        }

    }

    /**
     * Opens the report menu.
     * @param actionEvent
     * @throws IOException - FXMLLoader.Load
     */
    public void onReportButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Report Button");

        Parent root = FXMLLoader.load(getClass().getResource("/View/ReportsMenu.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 300, 239);
        stage.setTitle("Reports");
        stage.setScene(scene);
        stage.show();


    }

}