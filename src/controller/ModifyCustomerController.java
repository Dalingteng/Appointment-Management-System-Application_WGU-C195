package controller;

import database.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Division;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is ModifyCustomerController class.
 * This class is for the Modify Customer Screen of the application.
 *
 * @author Sochandaling Teng
 */
public class ModifyCustomerController implements Initializable {
    /**
     * the text field for customer id
     */
    public TextField customerIdTextField;
    /**
     * the text field for customer name
     */
    public TextField customerNameTextField;
    /**
     * the text field for address
     */
    public TextField addressTextField;
    /**
     * the text field for postal code
     */
    public TextField postalCodeTextField;
    /**
     * the text field for phone number
     */
    public TextField phoneNumberTextField;
    /**
     * the combo box for selecting country
     */
    public ComboBox<Country> countryComboBox;
    /**
     * the combo box for selecting division
     */
    public ComboBox<Division> divisionComboBox;
    /**
     * the button for saving modifying customer
     */
    public Button saveButton;
    /**
     * the button for cancelling modifying customer
     */
    public Button cancelButton;
    /**
     * the customer selected from Main Customer Screen
     */
    private Customer passSelectedCustomer;

    /**
     * This is the pass customer method.
     * This method retrieves information of the customer selected from the Main Customer Screen to the Modify Customer Screen.
     * LAMBDA Expression:
     *
     * @param selectedCustomer the customer selected from Main Customer Screen
     * @throws SQLException if database not found
     */
    public void passCustomer(Customer selectedCustomer) throws SQLException {
        JDBC.makeConnection();
        passSelectedCustomer = selectedCustomer;
        customerIdTextField.setText(String.valueOf(selectedCustomer.getCustomerId()));
        customerNameTextField.setText(selectedCustomer.getCustomerName());
        addressTextField.setText(selectedCustomer.getAddress());
        postalCodeTextField.setText(selectedCustomer.getPostalCode());
        phoneNumberTextField.setText(selectedCustomer.getPhoneNumber());

        countryComboBox.getItems().forEach(country -> {
            if(country.getCountryId() == selectedCustomer.getCountryId()) {
                countryComboBox.setValue(country);
            }
        });
        divisionComboBox.getItems().forEach(division -> {
            if(division.getDivisionId() == selectedCustomer.getDivisionId()) {
                divisionComboBox.setValue(division);
            }
        });
    }

    /**
     * This is the country combo box method.
     * This method gets the id of selected country, then calls the get divisions by country method from DivisionDao class of
     * selected country to populate division combo box and pre-select the first division on the list.
     *
     * @param actionEvent the country combo box action
     * @throws SQLException if database not found
     */
    public void onCountryComboBox(ActionEvent actionEvent) throws SQLException {
        int countryId = countryComboBox.getValue().getCountryId();
        divisionComboBox.setItems(DivisionDao.getDivisionsByCountry(countryId));
        divisionComboBox.getSelectionModel().selectFirst();
    }

    /**
     * <p>This is the save modifying customer method.
     * This method updates the selected customer to the database with all modified information of all text fields and combo boxes
     * except customer id and checking the validation whether or not text fields is empty.</p>
     *
     * <p>If none of text fields is empty, it calls the update customer method from CustomerDao class to update the customer to
     * the database, then the customer table in the Main Customer Screen repopulates. Otherwise, if any text field is empty,
     * it alerts an error message to fill out that text field.</p>
     *
     * @param actionEvent the save button action
     * @throws SQLException if database not found
     * @throws IOException if fxml file not found
     */
    public void onSaveButton(ActionEvent actionEvent) throws SQLException, IOException {
        int customerId = passSelectedCustomer.getCustomerId();
        String customerName = customerNameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String countryName = countryComboBox.getValue().getCountryName();
        String divisionName = divisionComboBox.getValue().getDivisionName();

        if(customerName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Field Cannot Be Empty");
            alert.setContentText("Please fill out 'Customer Name' field.");
            alert.showAndWait();
            return;
        }
        if(address.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Field Cannot Be Empty");
            alert.setContentText("Please fill out 'Address' field.");
            alert.showAndWait();
            return;
        }
        if(postalCode.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Field Cannot Be Empty");
            alert.setContentText("Please fill out 'Postal Code' field.");
            alert.showAndWait();
            return;
        }
        if(phoneNumber.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Field Cannot Be Empty");
            alert.setContentText("Please fill out 'Phone Number' field.");
            alert.showAndWait();
            return;
        }

        int divisionId = 0;
        for(Division d: DivisionDao.getAllDivisions()) {
            if(d.getDivisionName().equals(divisionName)) {
                divisionId = d.getDivisionId();
            }
        }

        CustomerDao customerDao = new CustomerDao();
        customerDao.updateCustomer(customerId, customerName, address, postalCode, phoneNumber, divisionId);

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Customer.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * This is the cancel modifying method.
     * This method cancels modifying customer when the user clicks on cancel button and confirms to cancel, then switches back to
     * the Main Customer Screen of the application.
     *
     * @param actionEvent the cancel button action
     * @throws IOException if fxml file not found
     */
    public void onCancelButton(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Cancel Confirmation");
        alert.setContentText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getResource("../view/Customer.fxml"));
            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

    /**
     * This is the initialize method.
     * This method initializes the modify customer controller by making connection to the database, then calling the get all countries
     * method from CountryDao class to populate country combo box.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JDBC.makeConnection();
            countryComboBox.setItems(CountryDao.getAllCountries());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
