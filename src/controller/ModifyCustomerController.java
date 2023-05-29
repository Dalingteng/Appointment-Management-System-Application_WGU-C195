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

public class ModifyCustomerController implements Initializable {
    public TextField customerIdTextField;
    public TextField customerNameTextField;
    public TextField addressTextField;
    public TextField postalCodeTextField;
    public TextField phoneNumberTextField;
    public ComboBox<Country> countryComboBox;
    public ComboBox<Division> divisionComboBox;
    public Button saveButton;
    public Button cancelButton;
    private Customer passSelectedCustomer;
    private int idIndex;
    private int countryId;
    private int divisionId;

    public void passCustomer(Customer selectedCustomer) throws SQLException {
        JDBC.makeConnection();
        passSelectedCustomer = selectedCustomer;
        idIndex = CustomerDao.getAllCustomers().indexOf(passSelectedCustomer);

        customerIdTextField.setText(String.valueOf(selectedCustomer.getCustomerId()));
        customerNameTextField.setText(selectedCustomer.getCustomerName());
        addressTextField.setText(selectedCustomer.getAddress());
        postalCodeTextField.setText(selectedCustomer.getPostalCode());
        phoneNumberTextField.setText(selectedCustomer.getPhoneNumber());

        Country selectedCountry = null;
        for(Country country: CountryDao.getAllCountries()) {
            if(country.getCountryName().equals(selectedCustomer.getCountryName())) {
                selectedCountry = country;
                break;
            }
        }
        countryComboBox.getSelectionModel().select(selectedCountry);
        countryId = selectedCountry.getCountryId();

        divisionComboBox.setItems(DivisionDao.getDivisionsByCountry(countryId));
        Division selectedDivision = null;
        for(Division division: DivisionDao.getDivisionsByCountry(countryId)) {
            if(division.getDivisionName().equals(selectedCustomer.getDivisionName())) {
                selectedDivision = division;
                break;
            }
        }
        divisionComboBox.getSelectionModel().select(selectedDivision);
        divisionId = selectedDivision.getDivisionId();
    }

    public void onCountryComboBox(ActionEvent actionEvent) throws SQLException {
        countryId = countryComboBox.getValue().getCountryId();
        divisionComboBox.setItems(DivisionDao.getDivisionsByCountry(countryId));
        divisionComboBox.getSelectionModel().selectFirst();
    }

    public void onSaveButton(ActionEvent actionEvent) throws SQLException, IOException {
        int customerId = passSelectedCustomer.getCustomerId();
        String customerName = customerNameTextField.getText();
        String address = addressTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String countryName = countryComboBox.getValue().getCountryName();
        String divisionName = divisionComboBox.getValue().getDivisionName();

        if(customerName.isEmpty() || address.isEmpty() || postalCode.isEmpty() || phoneNumber.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Text field cannot be empty");
            alert.setContentText("Please fill out all empty fields to save.");
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
        customerDao.updateCustomer(customerId, customerName, address, postalCode, phoneNumber, divisionId);

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Customer.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void onCancelButton(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Confirmation");
        alert.setHeaderText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getResource("../view/Customer.fxml"));
            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JDBC.makeConnection();
            countryComboBox.setItems(CountryDao.getAllCountries());
            countryComboBox.getSelectionModel().getSelectedItem();
            countryId = countryComboBox.getValue().getCountryId();
            divisionComboBox.setItems(DivisionDao.getDivisionsByCountry(countryId));
            divisionComboBox.getSelectionModel().getSelectedItem();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
