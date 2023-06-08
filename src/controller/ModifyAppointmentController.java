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
import model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is ModifyAppointmentController class.
 * This class is for the Modify Appointment Screen of the application.
 *
 *  @author Sochandaling Teng
 */
public class ModifyAppointmentController implements Initializable {
    /**
     * the text field for appointment id
     */
    public TextField appointmentIdTextField;
    /**
     * the text field for title
     */
    public TextField titleTextField;
    /**
     * the text field for description
     */
    public TextField descriptionTextField;
    /**
     * the text field for location
     */
    public TextField locationTextField;
    /**
     * the text field for type
     */
    public TextField typeTextField;
    /**
     * the combo box for selecting contact id
     */
    public ComboBox<Contact> contactIdComboBox;
    /**
     * the combo box for selecting customer id
     */
    public ComboBox<Customer> customerIdComboBox;
    /**
     * the combo box for selecting user id
     */
    public ComboBox<User> userIdComboBox;
    /**
     * the date picker for selecting start date
     */
    public DatePicker startDatePicker;
    /**
     * the date picker for selecting end date
     */
    public DatePicker endDatePicker;
    /**
     * the combo box for selecting start time
     */
    public ComboBox<LocalTime> startTimeComboBox;
    /**
     * the combo box for selecting end time
     */
    public ComboBox<LocalTime> endTimeComboBox;
    /**
     * the button for saving modifying appointment
     */
    public Button saveButton;
    /**
     * the button for cancelling modifying appointment
     */
    public Button cancelButton;
    /**
     * the appointment selected from Main Appointment Screen
     */
    private Appointment passSelectedAppointment;

    /**
     * This is the pass appointment method.
     * This method retrieves information of the appointment selected from the Main Appointment Screen to the Modify Appointment Screen.<br>
     *
     * <i>Lambda Expression:</i> to iterate through the contact id combo box, the customer id combo box and the user id combo box,
     * then set value of combo boxes based on information of the selected appointment.
     *
     * @param selectedAppointment the appointment selected from Main Appointment Screen
     * @throws SQLException if database not found
     */
    public void passAppointment(Appointment selectedAppointment) throws SQLException {
        JDBC.makeConnection();
        passSelectedAppointment = selectedAppointment;
        appointmentIdTextField.setText(String.valueOf(selectedAppointment.getAppointmentId()));
        titleTextField.setText(String.valueOf(selectedAppointment.getTitle()));
        descriptionTextField.setText(selectedAppointment.getDescription());
        locationTextField.setText(selectedAppointment.getLocation());
        typeTextField.setText(selectedAppointment.getType());

        contactIdComboBox.getItems().forEach(contact -> {
            if(contact.getContactId() == selectedAppointment.getContactId()) {
                contactIdComboBox.setValue(contact);
            }
        });
        customerIdComboBox.getItems().forEach(customer -> {
            if(customer.getCustomerId() == selectedAppointment.getCustomerId()) {
                customerIdComboBox.setValue(customer);
            }
        });
        userIdComboBox.getItems().forEach(user -> {
            if(user.getUserId() == selectedAppointment.getUserId()) {
                userIdComboBox.setValue(user);
            }
        });

        startDatePicker.setValue(selectedAppointment.getStartDate());
        endDatePicker.setValue(selectedAppointment.getEndDate());
        startTimeComboBox.getSelectionModel().select(selectedAppointment.getStartTime());
        endTimeComboBox.getSelectionModel().select(selectedAppointment.getEndTime());
    }

    /**
     * <p>This is the save modifying appointment method. This method updates the selected appointment to the database with all modified
     * information of all text fields and combo boxes except appointment id and checking the validation whether or not text fields
     * is empty or the start date and time is before the end date and time.</p>
     *
     * <p>If any text field is empty or start date/time is the same or after end date/time, it alerts an error message to fill out that
     * text field or modify start/end date and time. Otherwise, if there is no input error, it checks to see if the appointment date and
     * time is within business operation which is Monday to Friday from 08:00 to 22:00 EST and alerts an error message if the appointment
     * date and time is outside of business operation. Then, it checks to see if the appointment overlaps with any existed appointment
     * of the same customer by calling the get appointments by customer method from AppointmentDao class and alerts an error message if
     * the appointment is overlapping.</p>
     *
     * <p>After validating all inputs and checking logical errors, it calls the update appointment method from AppointmentDao class to
     * update the appointment to the database, then loads to the Main Appointment Screen and the appointment table repopulates.</p>
     *
     * @param actionEvent the save button action
     * @throws SQLException if database not found
     * @throws IOException if fxml file not found
     */
    public void onSaveButton(ActionEvent actionEvent) throws SQLException, IOException {
        int appointmentId = passSelectedAppointment.getAppointmentId();
        String title = titleTextField.getText();
        String description = descriptionTextField.getText();
        String location = locationTextField.getText();
        String type = typeTextField.getText();
        int contactId = contactIdComboBox.getValue().getContactId();
        int customerId = customerIdComboBox.getValue().getCustomerId();
        int userId = userIdComboBox.getValue().getUserId();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        LocalTime startTime = startTimeComboBox.getSelectionModel().getSelectedItem();
        LocalTime endTime = endTimeComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime startDateTime = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startTime.getHour(), startTime.getMinute());
        LocalDateTime endDateTime = LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), endTime.getHour(), endTime.getMinute());

        if(title.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Field Cannot Be Empty");
            alert.setContentText("Please fill out 'Title' field.");
            alert.showAndWait();
            return;
        }
        else if(description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Field Cannot Be Empty");
            alert.setContentText("Please fill out 'Description' field.");
            alert.showAndWait();
            return;
        }
        else if(location.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Field Cannot Be Empty");
            alert.setContentText("Please fill out 'Location' field.");
            alert.showAndWait();
            return;
        }
        else if(type.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Field Cannot Be Empty");
            alert.setContentText("Please fill out 'Type' field.");
            alert.showAndWait();
            return;
        }
        else if(startDateTime.isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid Date/Time");
            alert.setContentText("The appointment \"Start Date/Time\" must be before \"End Date/Time\".");
            alert.showAndWait();
            return;
        }

        //Convert to EST
        ZonedDateTime estStartDateTime = startDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("US/Eastern"));
        ZonedDateTime estEndDateTime = endDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("US/Eastern"));

        //Check business day
        int startDay = estStartDateTime.getDayOfWeek().getValue();
        int endDay = estEndDateTime.getDayOfWeek().getValue();
        int monday = DayOfWeek.MONDAY.getValue();
        int friday = DayOfWeek.FRIDAY.getValue();
        if(startDay < monday || startDay > friday || endDay < monday || endDay > friday) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Business Not In Operation");
            alert.setContentText("The appointment day is outside of business operations (Monday-Friday between 08:00 and 22:00 EST).");
            alert.showAndWait();
            return;
        }

        //Check business hours
        LocalTime appointmentStartTime = estStartDateTime.toLocalTime();
        LocalTime appointmentEndTime = estEndDateTime.toLocalTime();
        LocalTime businessStartTime = LocalTime.of(8, 0);
        LocalTime businessEndTime = LocalTime.of(22, 0);
        if(appointmentStartTime.isBefore(businessStartTime) || appointmentStartTime.isAfter(businessEndTime) || appointmentEndTime.isBefore(businessStartTime) || appointmentEndTime.isAfter(businessEndTime)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Business Not In Operation");
            alert.setContentText("The appointment time [" + appointmentStartTime + "-" + appointmentEndTime + " EST] is outside of business operations (Monday-Friday between 08:00 and 22:00 EST).");
            alert.showAndWait();
            return;
        }

        //Check for overlapping
        for(Appointment a: AppointmentDao.getAppointmentsByCustomer(customerId)) {
            LocalDateTime existedStartDateTime = LocalDateTime.of(a.getStartDate(), a.getStartTime());
            LocalDateTime existedEndDateTime = LocalDateTime.of(a.getEndDate(), a.getEndTime());
            if (a.getAppointmentId() == appointmentId) {
                continue;
            }
            else {
                if ((startDateTime.isBefore(existedStartDateTime) || startDateTime.isEqual(existedStartDateTime)) &&
                        (endDateTime.isAfter(existedEndDateTime) || endDateTime.isEqual(existedEndDateTime))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Overlapping Appointments");
                    alert.setContentText("The appointment overlaps with existing appointment.");
                    alert.showAndWait();
                    return;
                }
                else if ((startDateTime.isAfter(existedStartDateTime) || startDateTime.isEqual(existedStartDateTime)) &&
                        startDateTime.isBefore(existedEndDateTime)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Overlapping Appointments");
                    alert.setContentText("The appointment \"Start Time\" overlaps with existing appointment.");
                    alert.showAndWait();
                    return;
                }
                else if (endDateTime.isAfter(existedStartDateTime) &&
                        (endDateTime.isBefore(existedEndDateTime) || endDateTime.isEqual(existedEndDateTime))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Overlapping Appointments");
                    alert.setContentText("The appointment \"End Time\" overlaps with existing appointments");
                    alert.showAndWait();
                    return;
                }
            }
        }

        AppointmentDao appointmentDao = new AppointmentDao();
        appointmentDao.updateAppointment(appointmentId, title, description, location, type, startDateTime, endDateTime, customerId, userId, contactId);

        Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * This is the cancel modifying appointment method.
     * This method cancels modifying appointment when the user clicks on cancel button and confirms to cancel, then switches back to
     * the Main Appointment Screen of the application.
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
            Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
            stage.setScene(new Scene(parent));
            stage.show();
        }
    }

    /**
     * <p>This is the initialize method. This method initializes the modify appointment controller by making connection to the database.</p>
     *
     * <p>Then, it populates contact id combo box by calling the get all contacts method from ContactDao class, customer id combo box by
     * calling the get all customers method from CustomerDao class and user id combo box by calling the get all users method from UserDao
     * class.</p>
     *
     * <p>Furthermore, start time combo box and end time combo box are populated with 24 hours of the day in Local Time showing in
     * 30 minutes interval. The start time combo box is populated from the beginning of the day (00:00) to 30 minutes before
     * the end of the day (23:30) and the end time combo box is populated from 30 minutes after the beginning of the day (00:30) to
     * the beginning of next day (00:00).</p>
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JDBC.makeConnection();
            contactIdComboBox.setItems(ContactDao.getAllContacts());
            customerIdComboBox.setItems(CustomerDao.getAllCustomers());
            userIdComboBox.setItems(UserDao.getAllUsers());

            ObservableList<LocalTime> startTimeList = FXCollections.observableArrayList();
            ObservableList<LocalTime> endTimeList = FXCollections.observableArrayList();
            for(LocalTime time = LocalTime.of(0, 0); time.isBefore(LocalTime.of(23, 30)); time = time.plusMinutes(30)) {
                startTimeList.add(time);
                endTimeList.add(time.plusMinutes(30));
            }
            startTimeList.add(LocalTime.of(23, 30));
            endTimeList.add(LocalTime.of(0, 0));
            startTimeComboBox.setItems(startTimeList);
            endTimeComboBox.setItems(endTimeList);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
