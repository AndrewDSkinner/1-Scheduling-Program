package View;

import Database.Select_Statements;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import util.JDBCHelper;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Locale;


/**
 * Calls the login screen based on user's language settings
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        if (Locale.getDefault().toString().equals("en_US")) {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root, 475, 400));
            primaryStage.show();
        } else if(Locale.getDefault().toString().equals("fr_FR")){
            Parent root = FXMLLoader.load(getClass().getResource("FrenchLogin.fxml"));
            primaryStage.setTitle("Connexion");
            primaryStage.setScene(new Scene(root, 475, 400));
            primaryStage.show();
        }

    }

    /**
     * Establishes connection to the database and pulls all details of the DB.
     * @param args
     */
    public static void main(String[] args)  {

       // Locale.setDefault(Locale.FRANCE);
        System.out.println(Locale.getDefault());

        JDBCHelper.startConnection();
        try
        {
            Select_Statements.selectContactDetails();
            Select_Statements.selectUserDetails();
            Select_Statements.selectCountryDetails();
            Select_Statements.selectDivisionDetails();
            Select_Statements.selectCustomerDetails();
            Select_Statements.selectAppointmentDetails();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        //Locale.setDefault(new Locale("fr"));

        launch(args);

        JDBCHelper.closeConnection();

    }
}
