package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import database.JDBC;

/**
 * This is Main class of Appointment Management System.
 * This class connects to database and launches the application.
 *
 * @author Sochandaling Teng
 */
public class Main extends Application {
    /**
     * This is the start method. This creates FXML stage and loads the log in screen of the application.
     *
     * @param stage the stage that the scene can be set when launching the application
     * @throws Exception if fxml file not found
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/LogIn.fxml"));
        stage.setTitle("Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * This is the main method. This launches the application by making a connection to JDBC, then closing the connection after exiting the program.
     * @param args the command line arguments passed to the application
     */
    public static void main(String[] args) {
        JDBC.makeConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
