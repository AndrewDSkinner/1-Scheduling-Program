package View;

import Database.Delete_Statements;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;
import util.DeleteMessage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * Controller for the main customer screen.
 */
public class CustomerController implements Initializable {


    /**
     * Customer ID column.
     */
    public TableColumn customerIDCol;

    /**
     * Customer name column.
     */
    public TableColumn customerNameCol;

    /**
     * Address column.
     */
    public TableColumn addressCol;

    /**
     * Division Column.
     */
    public TableColumn divisionCol;

    /**
     * Postal Column.
     */
    public TableColumn postalCol;

    /**
     * Phone column.
     */
    public TableColumn phoneCol;

    /**
     * All customers tableview.
     */
    public TableView allCustomersTable;

    /**
     * Country column
     */
    public TableColumn countryCol;

    /**
     * Exit button
     */
    public Button exitButton;

    /**
     * Initializes the customer tableview when the screen is called.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        //System.out.println(Customer.getAllCustomers());

        allCustomersTable.setItems(Customers.getAllCustomers());



        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("Customer_ID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("Customer_Name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("Division"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));
        postalCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));


    }

    /**
     * Returns to the main menu when clicked.
     * @param actionEvent
     * @throws IOException - thrown by FXMLLoader
     */

    public void onExitButton(ActionEvent actionEvent) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 200, 240);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();

        }

    /**
     * Calls the add customer screen
     * @param actionEvent
     * @throws IOException - FXMLLoader
     */

    public void onAddButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Add button clicked.");

        Parent root = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 460, 460);
        stage.setTitle("Add Customer");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Calls the update customer screen for the selected customer
     * @param actionEvent
     * @throws IOException - FXMLLoader
     */

    public void onUpdateButton(ActionEvent actionEvent) throws IOException {
        try
        {
            Customers selectedCustomer = (Customers) allCustomersTable.getSelectionModel().getSelectedItem();

            UpdateCustomerController.receiveCustomer(selectedCustomer);

            Parent root = FXMLLoader.load(getClass().getResource("/View/UpdateCustomer.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 460, 460);
            stage.setTitle("Update Customer");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("Select a customer from the table to update!");
            alert.showAndWait();
        }
    }

    /**
     * Deletes the selected customer.
     * @param actionEvent - When the delete button is clicked.
     */
    public void onDeleteButton(ActionEvent actionEvent)
    {

        System.out.println("On Delete Part clicked");

        Customers selectedCustomer = (Customers)allCustomersTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null)
        {
            return;
        }

        if (Customers.hasAppointment(selectedCustomer.getCustomer_ID()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Alert");
            alert.setContentText("This customer has associated appointments and cannot be deleted.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you certain you want to delete this customer?");
        Optional<ButtonType> result = alert.showAndWait();

        /**
         * LAMBDA CREATES A DELETE MESSAGE
         */
        DeleteMessage deleteMessage = (i, s) -> "Customer ID: " + i + ",  Name: " + s + ", has been deleted.";

        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            Alert confirmation  = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Customer Deleted");
           // confirmation.setContentText("Customer " + selectedCustomer.getCustomer_Name() + " has been deleted.");
            confirmation.setContentText(deleteMessage.DeleteMessage(selectedCustomer.getCustomer_ID(), selectedCustomer.getCustomer_Name()));
            confirmation.showAndWait();
            Delete_Statements.deleteCustomer(selectedCustomer);
            allCustomersTable.getItems().remove(selectedCustomer);

        }
    }

}
