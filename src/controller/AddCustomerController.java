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

public class AddCustomerController implements Initializable {
    public TextField customerIdTextField;
    public TextField customerNameTextField;
    public TextField addressTextField;
    public TextField postalCodeTextField;
    public TextField phoneNumberTextField;
    public ComboBox<Country> countryComboBox;
    public ComboBox<Division> divisionComboBox;
    public Button saveButton;
    public Button cancelButton;
    private int countryId;

    public void onCountryComboBox(ActionEvent actionEvent) throws SQLException {
        countryId = countryComboBox.getValue().getCountryId();
        divisionComboBox.setItems(DivisionDao.getDivisionsByCountry(countryId));
        divisionComboBox.getSelectionModel().selectFirst();
    }

    public void onSaveButton(ActionEvent actionEvent) throws SQLException, IOException {
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
        customerDao.addCustomer(Customer.getAutoCustomerId(), customerName, address, postalCode, phoneNumber, divisionId);

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
            countryComboBox.getSelectionModel().selectFirst();
            countryId = countryComboBox.getValue().getCountryId();
            divisionComboBox.setItems(DivisionDao.getDivisionsByCountry(countryId));
            divisionComboBox.getSelectionModel().selectFirst();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
