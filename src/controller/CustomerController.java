package controller;

import database.CustomerDao;
import database.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    public TableView<Customer> customerTable;
    public TableColumn<Customer, Integer> customerIdColumn;
    public TableColumn<Customer, String> customerNameColumn;
    public TableColumn<Customer, String> addressColumn;
    public TableColumn<Customer, String> postalCodeColumn;
    public TableColumn<Customer, String> phoneNumberColumn;
    public TableColumn<Customer, String> divisionColumn;
    public TableColumn<Customer, String> countryColumn;
    public Button backButton;
    public Button logOutButton;
    public Button addCustomerButton;
    public Button modifyCustomerButton;
    public Button deleteCustomerButton;

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

    public void onAddCustomerButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddCustomer.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void onModifyCustomerButton(ActionEvent actionEvent) {
    }

    public void onDeleteCustomerButton(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        divisionColumn.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));

        try {
            JDBC.makeConnection();
            customerTable.setItems(CustomerDao.getAllCustomers());
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
