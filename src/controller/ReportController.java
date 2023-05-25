package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class ReportController {
    public TableView reportContactTable;
    public TableColumn appointmentIdColumn;
    public TableColumn titleColumn;
    public TableColumn descriptionColumn;
    public TableColumn locationColumn;
    public TableColumn typeColumn;
    public TableColumn startDateColumn;
    public TableColumn endDateColumn;
    public TableColumn startTimeColumn;
    public TableColumn endTimeColumn;
    public TableColumn customerIdColumn;
    public TableColumn userIdColumn;
    public TableView reportMonthTypeTable;
    public TableColumn appointmentMonthColumn;
    public TableColumn appointmentTypeColumn;
    public TableColumn countMonthTypeColumn;
    public TableView reportCountryTable;
    public TableColumn appointmentCountryColumn;
    public TableColumn countCountryColumn;
    public ComboBox contactIdComboBox;
    public Button backButton;
    public Button logOutButton;

    public void onContactIdComboBox(ActionEvent actionEvent) {
    }

    public void onBackButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void onLogOutButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit The Application");
        alert.setHeaderText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}
