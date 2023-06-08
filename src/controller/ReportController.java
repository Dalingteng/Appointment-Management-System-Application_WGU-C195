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

/**
 * This is ReportController class.
 * This class is for the Report Screen of the application.
 *
 * @author Sochandaling Teng
 */
public class ReportController implements Initializable {
    /**
     * the table view for report by contact
     */
    public TableView<Appointment> reportContactTable;
    /**
     * the id column of appointment
     */
    public TableColumn<Appointment, Integer> appointmentIdColumn;
    /**
     * the title column of appointment
     */
    public TableColumn<Appointment, String> titleColumn;
    /**
     * the description column of appointment
     */
    public TableColumn<Appointment, String> descriptionColumn;
    /**
     * the location column of appointment
     */
    public TableColumn<Appointment, String> locationColumn;
    /**
     * the type column of appointment
     */
    public TableColumn<Appointment, String> typeColumn;
    /**
     * the start date column of appointment
     */
    public TableColumn<Appointment, LocalDate> startDateColumn;
    /**
     * the end date column of appointment
     */
    public TableColumn<Appointment, LocalDate> endDateColumn;
    /**
     * the start time column of appointment
     */
    public TableColumn<Appointment, LocalTime> startTimeColumn;
    /**
     * the end time column of appointment
     */
    public TableColumn<Appointment, LocalTime> endTimeColumn;
    /**
     * the customer id column for appointment
     */
    public TableColumn<Appointment, Integer> customerIdColumn;
    /**
     * the user id column for appointment
     */
    public TableColumn<Appointment, Integer> userIdColumn;
    /**
     * the table view of report by month and type
     */
    public TableView<Report> reportMonthTypeTable;
    /**
     * the month column of appointment
     */
    public TableColumn<Report, String> reportMonthColumn;
    /**
     * the type column of appointment
     */
    public TableColumn<Report, String> reportTypeColumn;
    /**
     * the total appointments column by month and type
     */
    public TableColumn<Report, Integer> countMonthTypeColumn;
    /**
     * the table view of report by country
     */
    public TableView<Report> reportCountryTable;
    /**
     * the country column of customer for appointment
     */
    public TableColumn<Report, String> reportCountryColumn;
    /**
     * the total appointments column by country
     */
    public TableColumn<Report, Integer> countCountryColumn;
    /**
     * the combo box for selecting contact id
     */
    public ComboBox<Contact> contactIdComboBox;
    /**
     * the button for returning to Appointment Screen
     */
    public Button backButton;
    /**
     * the button for logging out
     */
    public Button logOutButton;

    /**
     * This is the contact id combo box method.
     * This method gets the id of selected contact from the contact id combo box and populates the report by contact table by calling
     * the get appointment by contact method from AppointmentDao class.
     *
     * @param actionEvent the contact id combo box action
     * @throws SQLException if database not found
     */
    public void onContactIdComboBox(ActionEvent actionEvent) throws SQLException {
        int contactId = contactIdComboBox.getSelectionModel().getSelectedItem().getContactId();
        reportContactTable.setItems(AppointmentDao.getAppointmentsByContact(contactId));
    }

    /**
     * This is the back method.
     * This method returns from Report Screen back to Appointment Screen when the user clicks on back button.
     *
     * @param actionEvent the back button action
     * @throws IOException if fxml file not found
     */
    public void onBackButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * This is the log out method.
     * This method exits the application when the user clicks on log out button and confirms to exit.
     *
     * @param actionEvent the log out button action
     */
    public void onLogOutButton(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exit The Application");
        alert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    /**
     * This is the initialize method.
     * This method
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
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
