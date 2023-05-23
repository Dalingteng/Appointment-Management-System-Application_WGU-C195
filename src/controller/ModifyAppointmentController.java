package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ModifyAppointmentController {
    public TextField appointmentIdTextField;
    public TextField titleTextField;
    public TextField descriptionTextField;
    public TextField locationTextField;
    public TextField typeTextField;
    public ComboBox contactComboBox;
    public ComboBox customerIdComboBox;
    public ComboBox userIdComboBox;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public ComboBox startTimeComboBox;
    public ComboBox endTimeComboBox;
    public Button saveButton;
    public Button cancelButton;

    public void onSaveButton(ActionEvent actionEvent) {
    }

    public void onCancelButton(ActionEvent actionEvent) {
    }
}
