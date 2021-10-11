package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the Report menu screen
 */
public class ReportsMenuController
{
    /**
     * Opens the Customer Reports screen.
     * @param actionEvent
     * @throws IOException
     */
    public void onCustomerReport(ActionEvent actionEvent) throws IOException
    {

        System.out.println("Customer Report button clicked.");

        Parent root = FXMLLoader.load(getClass().getResource("/View/CustomerReports.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 625, 250);
        stage.setTitle("Customer Report");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Exits back to the main menu.
     * @param actionEvent
     * @throws IOException
     */

    public void onExitButton(ActionEvent actionEvent) throws IOException
    {
        System.out.println("Exit button clicked.");

        Parent root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 180, 240);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the Contacts Schedule screen.
     * @param actionEvent
     * @throws IOException - FXMLLoader.Load
     */
    public void onContactScheduleButton(ActionEvent actionEvent) throws IOException
    {
        System.out.println("Contact Schedule Button clicked.");

        Parent root = FXMLLoader.load(getClass().getResource("/View/ContactSchedules.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 914, 390);
        stage.setTitle("Contact Schedules");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the customer country count controller.
     * @param actionEvent
     * @throws IOException - FXMLLoader.Load
     */
    public void onCustomerCountButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Customer Count Button clicked.");

        Parent root = FXMLLoader.load(getClass().getResource("/View/CountryCount.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 625, 350);
        stage.setTitle("Country Count");
        stage.setScene(scene);
        stage.show();

    }
}
