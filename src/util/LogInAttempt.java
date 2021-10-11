package util;

import model.Users;

import java.io.*;
import java.time.LocalDateTime;

/**
 * Creates a txt file of all log in attempts
 */
public class LogInAttempt
{
    private static String attemptedUser;
    /**
     * Filename and attempt string
     */
    private static String filename = "Log_In_Attempts.txt", attempt;

    /**
     * FileWriter
     */
    private static FileWriter writeFile;

    /**
     * Set to append to the file
     */
    static {
        try {
            writeFile = new FileWriter(filename, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The output file
     */

    private static PrintWriter outputFile = new PrintWriter(writeFile);

    /**
     * Login successful
     */
    public static void loginSuccessful()
    {
        attempt = "Login Successful @ " + LocalDateTime.now() + " by Username: " + getAttemptedUser() + ".";
        outputFile.println(attempt);
        outputFile.close();
    }

    /**
     * Login failed
     */
    public static void loginFailed()
    {
        attempt = "Login Failed @ " + LocalDateTime.now() + " by Username: " + getAttemptedUser() + ".";
        outputFile.println(attempt);
        outputFile.close();
    }

    public static void receiveAttemptedUser(String user)
    {
        attemptedUser = user;
    }

    public static String getAttemptedUser()
    {
       return attemptedUser;
    }
}
