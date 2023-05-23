package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import database.JDBC;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
        stage.setTitle("Appointment Management System");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        JDBC.makeConnection();
        launch(args);
        JDBC.closeConnection();
    }
}
