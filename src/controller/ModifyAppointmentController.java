package controller;

import database.*;
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
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifyAppointmentController implements Initializable {
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
    private Appointment passSelectedAppointment;
    private int idIndex;
    private int countryId;
    private int divisionId;

    public void passAppointment(Appointment selectedAppointment) throws SQLException {
        JDBC.makeConnection();
        passSelectedAppointment = selectedAppointment;
        idIndex = AppointmentDao.getAllAppointments().indexOf(passSelectedAppointment);

        appointmentIdTextField.setText(String.valueOf(selectedAppointment.getAppointmentId()));
        titleTextField.setText(String.valueOf(selectedAppointment.getTitle()));
        descriptionTextField.setText(selectedAppointment.getDescription());
        locationTextField.setText(selectedAppointment.getLocation());
        typeTextField.setText(selectedAppointment.getType());

        for(Contact contact: contactIdComboBox.getItems()) {
            if(contact.getContactId() == selectedAppointment.getContactId()) {
                contactIdComboBox.setValue(contact);
                break;
            }
        }
        for(Customer c: customerIdComboBox.getItems()) {
            if(c.getCustomerId() == selectedAppointment.getCustomerId()) {
                customerIdComboBox.setValue(c);
                break;
            }
        }
        for(User u: userIdComboBox.getItems()) {
            if(u.getUserId() == selectedAppointment.getUserId()) {
                userIdComboBox.setValue(u);
                break;
            }
        }

    }

    public void onSaveButton(ActionEvent actionEvent) {
    }

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            JDBC.makeConnection();
            contactIdComboBox.setItems(ContactDao.getAllContacts());
            customerIdComboBox.setItems(CustomerDao.getAllCustomers());
            userIdComboBox.setItems(UserDao.getAllUsers());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
