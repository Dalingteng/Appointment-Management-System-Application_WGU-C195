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
import java.sql.Timestamp;
import java.time.*;
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
//        System.out.println("LocalStartDT: " + startDateTime);
//        System.out.println("LocalEndDT: " + endDateTime);

        //Convert to EST
        ZonedDateTime estStartDateTime = startDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("US/Eastern"));
        ZonedDateTime estEndDateTime = endDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("US/Eastern"));
//        System.out.println("Start EST: " + businessStartDateTime);
//        System.out.println("End EST: " + businessEndDateTime);

        LocalTime appointmentStartTime = estStartDateTime.toLocalTime();
        LocalTime appointmentEndTime = estEndDateTime.toLocalTime();

        int startDay = estStartDateTime.toLocalDate().getDayOfWeek().getValue();
        int endDay = estEndDateTime.toLocalDate().getDayOfWeek().getValue();

        int monday = DayOfWeek.MONDAY.getValue();
        int friday = DayOfWeek.FRIDAY.getValue();

        if(startDay < monday || startDay > friday || endDay < monday || endDay > friday) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Appointment day is not in business operations (Monday - Friday).");
            alert.showAndWait();
            return;
        }

        LocalTime businessStartTime = LocalTime.of(8, 0);
        LocalTime businessEndTime = LocalTime.of(22, 0);

        if(appointmentStartTime.isBefore(businessStartTime) || appointmentStartTime.isAfter(businessEndTime) || appointmentEndTime.isBefore(businessStartTime) || appointmentEndTime.isAfter(businessEndTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Appointment time (" + appointmentStartTime + "-" + appointmentEndTime + " EST) is not in business hours (8:00-22:00 EST).");
            alert.showAndWait();
            return;
        }

        if(startTime.isAfter(endTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Start Time cannot be after End Time.");
            alert.showAndWait();
            return;
        }

        if(startTime.equals(endTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Start Time and End Time cannot be the same.");
            alert.showAndWait();
            return;
        }

//        for(Appointment a: AppointmentDao.getAppointmentsByCustomer(customerId)) {
//
//        }

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

//            LocalTime time = LocalTime.MIN;
            LocalTime time = LocalTime.of(0, 0);
//            while(!time.equals(LocalTime.MAX)){
            while(!time.equals(LocalTime.of(23, 30))) {
                startTimeList.add(time);
                endTimeList.add(time);
                time = time.plusMinutes(30);
            }
            startTimeList.remove(startTimeList.size() - 1);
            endTimeList.remove(0);

            startTimeComboBox.setItems(startTimeList);
            startTimeComboBox.getSelectionModel().select(LocalTime.of(0, 0));
            endTimeComboBox.setItems(endTimeList);
            endTimeComboBox.getSelectionModel().select(LocalTime.of(0, 30));


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
