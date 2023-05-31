package controller;

import database.ContactDao;
import database.CustomerDao;
import database.JDBC;
import database.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contact;
import model.Customer;
import model.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    public TextField appointmentIdTextField;
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField locationTextField;
    public TextField typeTextField;
    public ComboBox<Contact> contactIdComboBox;
    public ComboBox<Customer> customerIdComboBox;
    public ComboBox<User> userIdComboBox;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public ComboBox<LocalTime> startTimeComboBox;
    public ComboBox<LocalTime> endTimeComboBox;
    public Button saveButton;
    public Button cancelButton;

    public void onSaveButton(ActionEvent actionEvent) {
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        int contactId = contactIdComboBox.getSelectionModel().getSelectedItem().getContactId();
        int customerId = customerIdComboBox.getSelectionModel().getSelectedItem().getCustomerId();
        int userId = userIdComboBox.getSelectionModel().getSelectedItem().getUserId();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();


    }

    public void onCancelButton(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Confirmation");
        alert.setHeaderText("Are you sure you want to cancel?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JDBC.makeConnection();
            contactIdComboBox.setItems(ContactDao.getAllContacts());
            contactIdComboBox.getSelectionModel().selectFirst();
            customerIdComboBox.setItems(CustomerDao.getAllCustomers());
            customerIdComboBox.getSelectionModel().selectFirst();
            userIdComboBox.setItems(UserDao.getAllUsers());
            userIdComboBox.getSelectionModel().selectFirst();

            //Get the start date from datepicker
            //Get the start time from timeComboBox
            //Convert to string DateTimeFormatter
            //Then convert to LocalDateTime
            //insert to database using Timestamp.valueOf(LocalDateTime)
            //convert to EST to check for business hour using ZonedDateTime
            //alert if the appointment collides with the existing appointment

            //check must be in business hour
            //check not to overlap with existing appointment
            //using for loop

            //appointment ID - disabled

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
