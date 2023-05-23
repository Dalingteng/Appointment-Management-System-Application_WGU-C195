package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;

public class CustomerController {
    public TableView customerTable;
    public TableColumn customerIdColumn;
    public TableColumn customerNameColumn;
    public TableColumn addressColumn;
    public TableColumn postalCodeColumn;
    public TableColumn phoneNumberColumn;
    public TableColumn divisionColumn;
    public TableColumn countryColumn;
    public Button appointmentButton;
    public Button reportButton;
    public Button logOutButton;
    public Button addCustomerButton;
    public Button modifyCustomerButton;
    public Button deleteCustomerButton;
    public Label timeZoneLabel;

    public void onAppointmentButton(ActionEvent actionEvent) {
    }

    public void onReportButton(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Report.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void onLogOutButton(ActionEvent actionEvent) {
    }

    public void onAddCustomerButton(ActionEvent actionEvent) {
    }

    public void onModifyCustomerButton(ActionEvent actionEvent) {
    }

    public void onDeleteCustomerButton(ActionEvent actionEvent) {
    }
}
