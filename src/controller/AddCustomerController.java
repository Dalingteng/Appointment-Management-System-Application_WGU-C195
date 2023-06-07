package controller;

import database.CountryDao;
import database.CustomerDao;
import database.DivisionDao;
import database.JDBC;
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
 * This is AddCustomerController class.
 * This class is for the Add Customer Screen of the application.
 *
 * @author Sochandaling Teng
 */
public class AddCustomerController implements Initializable {
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
     * the button for saving adding customer
     */
    public Button saveButton;
    /**
     * the button for cancelling adding customer
     */
    public Button cancelButton;
    /**
     * the id of country of customer
     */
    private int countryId;

    /**
     * This is the country combo box method.
     * This method gets the id of selected country, then calls the get divisions by country method from DivisionDao class of
     * selected country to populate division combo box and pre-select the first division on the list.
     *
     * @param actionEvent the country combo box action
     * @throws SQLException if database not found
     */
    public void onCountryComboBox(ActionEvent actionEvent) throws SQLException {
        countryId = countryComboBox.getValue().getCountryId();
        divisionComboBox.setItems(DivisionDao.getDivisionsByCountry(countryId));
        divisionComboBox.getSelectionModel().selectFirst();
    }

    /**
     * This is the save adding customer method.
     * This method
     *
     * @param actionEvent the save button action
     * @throws SQLException if database not found
     * @throws IOException if fxml file not found
     */
    public void onSaveButton(ActionEvent actionEvent) throws SQLException, IOException {
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
        for(Division division: DivisionDao.getAllDivisions()) {
            if(division.getDivisionName().equals(divisionName)) {
                divisionId = division.getDivisionId();
            }
        }

        CustomerDao customerDao = new CustomerDao();
        customerDao.addCustomer(Customer.getAutoCustomerId(), customerName, address, postalCode, phoneNumber, divisionId);

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Customer.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * This is the cancel adding customer method.
     * This method cancels adding customer when the user clicks on cancel button and confirms to cancel, then shifts back to
     * Customer Screen of the application.
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
     * The method initializes the add customer controller by making connection to the database, then calling the get all countries
     * method from CountryDao class to populate country combo box and pre-select the first country on the list. Then, it calls
     * the get divisions by country method from DivisionDao class to populate division combo box of the selected country and
     * pre-select the first division on the list.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JDBC.makeConnection();
            countryComboBox.setItems(CountryDao.getAllCountries());
            countryComboBox.getSelectionModel().selectFirst();
            countryId = countryComboBox.getValue().getCountryId();
            divisionComboBox.setItems(DivisionDao.getDivisionsByCountry(countryId));
            divisionComboBox.getSelectionModel().selectFirst();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
