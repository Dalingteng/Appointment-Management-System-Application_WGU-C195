package controller;

import database.CountryDao;
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
    public String customerName;
    public String address;
    public String postalCode;
    public String phoneNumber;
    public String countryName;
    public String divisionName;

    public void onCountryComboBox(ActionEvent actionEvent) throws SQLException {
        countryId = countryComboBox.getValue().getCountryId();
        divisionComboBox.setItems(DivisionDao.getDivisionsByCountry(countryId));
        divisionComboBox.getSelectionModel().selectFirst();
    }

    public void onSaveButton(ActionEvent actionEvent) {
        customerName = customerNameTextField.getText();
        address = addressTextField.getText();
        postalCode = postalCodeTextField.getText();
        phoneNumber = phoneNumberTextField.getText();
        countryName = countryComboBox.getValue().getCountryName();
        divisionName = divisionComboBox.getValue().getDivisionName();

        if(customerName.isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
        }

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
