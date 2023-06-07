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

/**
 * This is CustomerController class.
 * This class is for the Customer Screen of the application.
 *
 * @author Sochandaling Teng
 */
public class CustomerController implements Initializable {
    /**
     * the table view for customer
     */
    public TableView<Customer> customerTable;
    /**
     * the id column of customer
     */
    public TableColumn<Customer, Integer> customerIdColumn;
    /**
     * the name column of customer
     */
    public TableColumn<Customer, String> customerNameColumn;
    /**
     * the address column of customer
     */
    public TableColumn<Customer, String> addressColumn;
    /**
     * the postal code of customer
     */
    public TableColumn<Customer, String> postalCodeColumn;
    /**
     * the phone number of customer
     */
    public TableColumn<Customer, String> phoneNumberColumn;
    /**
     * the division name of customer
     */
    public TableColumn<Customer, String> divisionColumn;
    /**
     * the country name of customer
     */
    public TableColumn<Customer, String> countryColumn;
    /**
     * the button for returning to Appointment Screen
     */
    public Button backButton;
    /**
     * the button for logging out
     */
    public Button logOutButton;
    /**
     * the button for adding customer
     */
    public Button addCustomerButton;
    /**
     * the button for modifying customer
     */
    public Button modifyCustomerButton;
    /**
     * the button for deleting customer
     */
    public Button deleteCustomerButton;

    /**
     * This is the back method.
     * This method returns from Customer Screen back to Appointment Screen when the user clicks on back button.
     *
     * @param actionEvent the back button action
     * @throws IOException if fxml file not found
     */
    public void onBackButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * This is the log out method.
     * This method exits the application when the user clicks on log out button and confirms to exit.
     *
     * @param actionEvent the log out button action
     */
    public void onLogOutButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit The Application");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    /**
     * This is the add customer method.
     * This method loads the Add Customer Screen when the user clicks on add customer button.
     *
     * @param actionEvent the add customer button action
     * @throws IOException if fxml file not found
     */
    public void onAddCustomerButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddCustomer.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * This is the modify customer method.
     * This method loads the Modify Customer Screen when the user clicks on modify customer button.
     * It retrieves the information of the selected customer and displays an error message if no customer is selected.
     *
     * @param actionEvent the modify customer button action
     * @throws IOException if fxml file not found
     * @throws SQLException if database not found
     */
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
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer to modify.");
            alert.showAndWait();
        }
    }

    /**
     * This is the delete method.
     * This method deletes a selected customer when the user clicks on delete customer button and confirms to delete.
     * It prevents the user to delete the customer that has associated appointments and displays an error message if
     * no customer is selected or if the customer has associated appointments.
     *
     * @param actionEvent the delete customer button action
     * @throws SQLException if database not found
     */
    public void onDeleteCustomerButton(ActionEvent actionEvent) throws SQLException {
        JDBC.makeConnection();
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

        if(selectedCustomer != null) {
            int customerId = selectedCustomer.getCustomerId();
            String customerName = selectedCustomer.getCustomerName();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Delete Confirmation");
            alert.setContentText("Are you sure you want to delete?");
            Optional<ButtonType> result = alert.showAndWait();
            if(!AppointmentDao.getAppointmentsByCustomer(customerId).isEmpty()) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText("Unable to Delete Customer");
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
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer to delete.");
            alert.showAndWait();
        }
    }

    /**
     * This is the initialize method.
     * This method initializes the customer controller by assigning values to all columns, then making connection to the database and
     * calling the get all customers method from CustomerDao class to populate the customer table based on the values of all columns.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
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
