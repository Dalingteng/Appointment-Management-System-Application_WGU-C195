package controller;

import database.AppointmentDao;
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
import java.sql.SQLException;
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

    public void onModifyCustomerButton(ActionEvent actionEvent) throws IOException, SQLException {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        if(selectedCustomer != null) {
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("../view/ModifyCustomer.fxml"));
            Parent parent = loader.load();
            stage.setScene(new Scene(parent));
            stage.show();

            ModifyCustomerController modifyCustomerController = loader.getController();
            modifyCustomerController.passCustomer(selectedCustomer);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer to modify.");
            alert.showAndWait();
        }
    }

    public void onDeleteCustomerButton(ActionEvent actionEvent) throws SQLException {
        JDBC.makeConnection();
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        int customerId = selectedCustomer.getCustomerId();
        String customerName = selectedCustomer.getCustomerName();

        if(selectedCustomer != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Are you sure you want to delete?");
            Optional<ButtonType> result = alert.showAndWait();
            if(!AppointmentDao.getAppointmentsByCustomer(customerId).isEmpty()) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Error");
                alertError.setHeaderText("Unable to delete customer");
                alertError.setContentText("All customer's appointments must be deleted first before continue.");
                alertError.showAndWait();
            }
            else {
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    CustomerDao.deleteCustomer(customerId, customerName);
                    customerTable.setItems(CustomerDao.getAllCustomers());
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer to delete.");
            alert.showAndWait();
        }
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
