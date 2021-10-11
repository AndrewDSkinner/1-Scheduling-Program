package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Users;
import util.JDBCHelper;

import util.DBQuery;
import util.LogInAttempt;
import util.Time;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.ResourceBundle;

import static util.Time.stringToCalendar;

/**
 * A French login controller to be called if the user's computer is set for French..
 */

public class FrenchLoginController implements Initializable {

    /**
     * Username field
     */
    public TextField usernameField;

    /**
     * Password field
     */
    public PasswordField passwordField;

    /**
     * Location label
     */
    public Label locationLabel;

    /**
     * User object
     */
    public static Users user;

    /**
     * Login Button, if password and username are correct, calls the main menu.
     * @param actionEvent
     * @throws IOException - FXMLLoader.Load
     */
    public void onLoginButton(ActionEvent actionEvent) throws  IOException {

        System.out.println("LOGIN BUTTON CLICKED");

        String userNameText = usernameField.getText();
        String passwordText = passwordField.getText();

        //receiveUserDetails();

        LogInAttempt.receiveAttemptedUser(userNameText);

        user = Users.getAllUsers().get(0);

        if (userNameText.equals(user.getUser_Name()) && passwordText.equals(user.getPassword())) {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 200, 240);
            stage.setTitle("Menu");
            stage.setScene(scene);
            stage.show();
        } else {
            loginAlert();
        }

    }

    /**
     * Exit button closes the application.
     * @param actionEvent
     */

    public void onExitButton(ActionEvent actionEvent) {

        System.exit(0);
    }
/*
    public static void receiveUserDetails() throws SQLException, ParseException {

        Connection conn = JDBCHelper.startConnection();

        String selectStatement = "SELECT * FROM users WHERE User_Name = 'test'";

        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement ps =  DBQuery.getPreparedStatement();

        ps.execute(); //Executes the prepared statement


        ResultSet rs = ps.getResultSet();

        while (rs.next()) {
            int User_ID = rs.getInt("User_ID");
            String User_Name = rs.getString("User_Name");
            String Password = rs.getString("Password");
            String Create_Date = rs.getString("Create_Date");
            //LocalDate date = rs.getDate("Create_Date").toLocalDate();
            //LocalTime time = rs.getTime("Create_Date").toLocalTime();
            String Created_By = rs.getString("Created_By");
            String Last_Update = rs.getString("Last_Update");
            String Last_Updated_By = rs.getString("Last_Updated_By");

            Calendar calendarCreateDate = stringToCalendar(Create_Date);
            Calendar calendarLastUpdate = stringToCalendar(Last_Update);

            //ADD TO USER OBJECT THEN COMPARE INPUT TO USER OBJECT

            user = new Users(User_ID, User_Name, Password,calendarCreateDate, Created_By, calendarLastUpdate, Last_Updated_By);


            //Display records
            // System.out.println(User_ID + " | " + User_Name + " | " + Password + " | " + date + " | " + time + " | "
            //       + Created_By + " | " + Last_Update);
        }


    }
    */

    public void loginAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Alerte d'erreur");
        alert.setContentText("Nom d'utilisateur / mot de passe invalide!");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        locationLabel.setText("Identifiant De Zone: " + Time.zone.toString());
    }

}
