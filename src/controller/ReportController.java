package controller;

import database.AppointmentDao;
import database.ContactDao;
import database.JDBC;
import database.ReportDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Report;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    public TableView<Appointment> reportContactTable;
    public TableColumn<Appointment, Integer> appointmentIdColumn;
    public TableColumn<Appointment, String> titleColumn;
    public TableColumn<Appointment, String> descriptionColumn;
    public TableColumn<Appointment, String> locationColumn;
    public TableColumn<Appointment, String> typeColumn;
    public TableColumn<Appointment, LocalDate> startDateColumn;
    public TableColumn<Appointment, LocalDate> endDateColumn;
    public TableColumn<Appointment, LocalTime> startTimeColumn;
    public TableColumn<Appointment, LocalTime> endTimeColumn;
    public TableColumn<Appointment, Integer> customerIdColumn;
    public TableColumn<Appointment, Integer> userIdColumn;
    public TableView<Report> reportMonthTypeTable;
    public TableColumn<Report, String> reportMonthColumn;
    public TableColumn<Report, String> reportTypeColumn;
    public TableColumn<Report, Integer> countMonthTypeColumn;
    public TableView<Report> reportCountryTable;
    public TableColumn<Report, String> reportCountryColumn;
    public TableColumn<Report, Integer> countCountryColumn;
    public ComboBox<Contact> contactIdComboBox;
    public Button backButton;
    public Button logOutButton;

    public void onContactIdComboBox(ActionEvent actionEvent) throws SQLException {
        int contactId = contactIdComboBox.getSelectionModel().getSelectedItem().getContactId();
        reportContactTable.setItems(AppointmentDao.getAppointmentsByContact(contactId));
    }

    public void onBackButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public void onLogOutButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit The Application");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            JDBC.makeConnection();
            appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
            endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
            customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
            userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
            contactIdComboBox.setItems(ContactDao.getAllContacts());
            reportContactTable.setItems(AppointmentDao.getAllAppointments());

            reportMonthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
            reportTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            countMonthTypeColumn.setCellValueFactory(new PropertyValueFactory<>("monthTypeCount"));
            reportMonthTypeTable.setItems(ReportDao.getReportsByMonthType());

            reportCountryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
            countCountryColumn.setCellValueFactory(new PropertyValueFactory<>("countryCount"));
            reportCountryTable.setItems(ReportDao.getReportsByCountry());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
