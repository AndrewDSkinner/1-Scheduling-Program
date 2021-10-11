package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Users;
import util.LogInAttempt;
import util.Time;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;


/**
 * Controls the login screen.
 */

public class LoginController implements Initializable
{
    /**
     * Location Label
     */
    public Label locationLabel;

    /**
     * User object
     */
    public static Users user;

    /**
     * Username Field
     */
    public TextField usernameField;

    /**
     * Password field
     */
    public PasswordField passwordField;

    /**
     * Login button
     */
    public Button loginButton;

    /**
     * Exit button
     */
    public Button exitButton;


    /**
     * Checks username/password and opens customer screen
     * @param actionEvent
     * @throws IOException
     */
    //Checks username/password and opens Customer Screen
    public void onLoginButton(ActionEvent actionEvent) throws IOException
    {

        System.out.println("LOGIN BUTTON CLICKED");

        String userNameText = usernameField.getText();
        String passwordText = passwordField.getText();

        LogInAttempt.receiveAttemptedUser(userNameText);

        user = Users.getAllUsers().get(0);

        MenuController.firstTime = true;

        if (userNameText.equals(user.getUser_Name()) && passwordText.equals(user.getPassword())) {

            LogInAttempt.loginSuccessful();
            Parent root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 180, 240);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
        } else {
            loginAlert();
            LogInAttempt.loginFailed();
        }

    }

    /**
     * Closes the application.
     * @param actionEvent
     */

    //Exit Login Screen
    public void onExitButton(ActionEvent actionEvent)
    {

        System.out.println("EXIT BUTTON CLICKED");


        System.exit(0);
    }



    public void loginAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Alert");
        alert.setContentText("Invalid Username / Password!");
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

            locationLabel.setText("Zone ID: " + Time.zone.getId());

        //zone.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

    }
}
