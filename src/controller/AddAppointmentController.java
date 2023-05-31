package controller;

import database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    public void onSaveButton(ActionEvent actionEvent) throws SQLException, IOException {
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        int contactId = contactIdComboBox.getSelectionModel().getSelectedItem().getContactId();
        int customerId = customerIdComboBox.getSelectionModel().getSelectedItem().getCustomerId();
        int userId = userIdComboBox.getSelectionModel().getSelectedItem().getUserId();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        LocalTime startTime = startTimeComboBox.getSelectionModel().getSelectedItem();
        LocalTime endTime = endTimeComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime startDateTime = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startTime.getHour(), startTime.getMinute());
        LocalDateTime endDateTime = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), endTime.getHour(), endTime.getMinute());

        AppointmentDao appointmentDao = new AppointmentDao();
        appointmentDao.addAppointment(Appointment.getAutoAppointmentId(), title, description, location, type, startDateTime, endDateTime, customerId, userId, contactId);

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
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
            startDatePicker.setValue(LocalDate.now());
            endDatePicker.setValue(LocalDate.now());

            ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
            ObservableList<LocalTime> endTimeList = FXCollections.observableArrayList();

            LocalTime time = LocalTime.of(8,0);
            while(!time.equals(LocalTime.of(22,30))) {
                startTimeList.add(time);
                endTimeList.add(time);
                time = time.plusMinutes(30);
            }
            startTimeList.remove(startTimeList.size() - 1);
            endTimeList.remove(0);

            startTimeComboBox.setItems(startTimeList);
            startTimeComboBox.getSelectionModel().select(LocalTime.of(8,0));
            endTimeComboBox.setItems(endTimeList);
            endTimeComboBox.getSelectionModel().select(LocalTime.of(8, 30));


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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
