package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class AppointmentController {

    public DatePicker datePicker;
    public RadioButton weekRadioButton;
    public RadioButton monthRadioButton;
    public RadioButton allRadioButton;
    public TableView appointmentTable;
    public TableColumn appointmentIdColumn;
    public TableColumn titleColumn;
    public TableColumn descriptionColumn;
    public TableColumn locationColumn;
    public TableColumn contactColumn;
    public TableColumn typeColumn;
    public TableColumn startDateColumn;
    public TableColumn endDateColumn;
    public TableColumn startTimeColumn;
    public TableColumn endTimeColumn;
    public TableColumn customerIdColumn;
    public TableColumn userIdColumn;
    public Button customerButton;
    public Button reportButton;
    public Button logOutButton;
    public Button addAppointmentButton;
    public Button modifyAppointmentButton;
    public Button deleteAppointmentButton;
    public Label timeZoneLabel;

    public void onWeekRadioButton(ActionEvent actionEvent) {
    }

    public void onMonthRadioButton(ActionEvent actionEvent) {
    }

    public void onAllRadioButton(ActionEvent actionEvent) {
    }

    public void onCustomerButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("view.Customer.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void onReportButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("view.Report.fxml"));
        Stage stage = new Stage();
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

    public void onAddAppointmentButton(ActionEvent actionEvent) throws IOException {
//        Parent parent = FXMLLoader.load(getClass().getResource("view.AddAppointment.fxml"));
//        Stage stage = new Stage();
//        stage.setScene(new Scene(parent));
//        stage.show();

//        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
//        parent = FXMLLoader.load(getClass().getResource("view/AddAppointment.fxml"));
//        stage.setScene(new Scene(parent));
//        stage.show();

//        Parent parent = FXMLLoader.load(getClass().getResource("view/AddAppointment.fxml"));
//        Scene scene = new Scene(parent);
//        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        window.setScene(scene);
//        window.show();

//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("view/AddAppointment.fxml"));
//        Scene scene = loader.load();
//        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
//        stage.setScene(scene);
    }

    public void onModifyAppointmentButton(ActionEvent actionEvent) {
    }

    public void onDeleteAppointmentButton(ActionEvent actionEvent) {
    }
}
