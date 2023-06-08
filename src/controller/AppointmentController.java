package controller;

import database.AppointmentDao;
import database.JDBC;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is AppointmentController class.
 * This class is for the Appointment Screen of the application.
 *
 * @author Sochandaling Teng
 */
public class AppointmentController implements Initializable {
    /**
     * the radio button for weekly appointments
     */
    public RadioButton weekRadioButton;
    /**
     * the radio button for monthly appointments
     */
    public RadioButton monthRadioButton;
    /**
     * the radio button for all appointments
     */
    public RadioButton allRadioButton;
    /**
     * the table view for appointment
     */
    public TableView<Appointment> appointmentTable;
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
     * the contact id column for appointment
     */
    public TableColumn<Appointment, Integer> contactIdColumn;
    /**
     * the button for switching to Customer Screen
     */
    public Button customerButton;
    /**
     * the button for switching to Report Screen
     */
    public Button reportButton;
    /**
     * the button for logging out
     */
    public Button logOutButton;
    /**
     * the button for adding appointment
     */
    public Button addAppointmentButton;
    /**
     * the button for modifying appointment
     */
    public Button modifyAppointmentButton;
    /**
     * the button for deleting appointment
     */
    public Button deleteAppointmentButton;
    /**
     * the toggle group for selecting radio buttons
     */
    @FXML
    ToggleGroup view = new ToggleGroup();

    /**
     * This is the week radio button method.
     * This method populates the report by contact table by filtering all appointments in the database, then using lambda expression
     * to add appointments that occur within a week from the date of log in to the filtered list in order to populate the table.<br>
     *
     * <i>Lambda expression:</i> to append the filtered list of appointments within a week from the date of log in.
     *
     * @param actionEvent the week radio button action
     * @throws SQLException if database not found
     */
    public void onWeekRadioButton(ActionEvent actionEvent) throws SQLException {
        weekRadioButton.setToggleGroup(view);
        LocalDate now = LocalDate.now(ZoneId.systemDefault());

        FilteredList<Appointment> filteredList = new FilteredList<>(AppointmentDao.getAllAppointments());
        filteredList.setPredicate(a -> {
            LocalDate appointmentDate = a.getStartDate();
            return ((appointmentDate.isEqual(now) || appointmentDate.isAfter(now)) && appointmentDate.isBefore(now.plusWeeks(1)));
        });
        appointmentTable.setItems(filteredList);
    }

    /**
     * This is the month radio button method.
     * This method populates the report by contact table by filtering all appointments in the database, then using lambda expression
     * to add appointments that occur within a month to the filtered list in order to populate the table.<br>
     *
     * <i>Lambda expression:</i> to append the filtered list of appointments within a month from the date of log in.
     *
     * @param actionEvent the month radio button action
     * @throws SQLException if database not found
     */
    public void onMonthRadioButton(ActionEvent actionEvent) throws SQLException {
        monthRadioButton.setToggleGroup(view);
        LocalDate now = LocalDate.now(ZoneId.systemDefault());

        FilteredList<Appointment> filteredList = new FilteredList<>(AppointmentDao.getAllAppointments());
        filteredList.setPredicate(a -> {
            LocalDate appointmentDate = a.getStartDate();
            return ((appointmentDate.isEqual(now) || appointmentDate.isAfter(now)) && appointmentDate.isBefore(now.plusMonths(1)));
        });
        appointmentTable.setItems(filteredList);
    }

    /**
     * This is the all radio button method.
     * This method populates the report by contact table with all appointments by calling the get all appointments from AppointmentDao
     * class when the user selects the all radio button.
     *
     * @param actionEvent the all radio button action
     * @throws SQLException if database not found
     */
    public void onAllRadioButton(ActionEvent actionEvent) throws SQLException {
        allRadioButton.setToggleGroup(view);
        appointmentTable.setItems(AppointmentDao.getAllAppointments());
    }

    /**
     * This is the customer button method.
     * This method loads the Customer Screen when the user clicks on customer button.
     *
     * @param actionEvent the customer button action
     * @throws IOException if fxml file not found
     */
    public void onCustomerButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Customer.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * This is the report button method.
     * This method loads the Report Screen when the user clicks on report button.
     *
     * @param actionEvent the report button action
     * @throws IOException if fxml file not found
     */
    public void onReportButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/Report.fxml"));
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
     * This is the add appointment method.
     * This method loads the Add Appointment Screen when the user clicks on add appointment button.
     *
     * @param actionEvent the add appointment button action
     * @throws IOException if fxml file not found
     */
    public void onAddAppointmentButton(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddAppointment.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * This is the modify appointment method. This method loads the Modify Appointment Screen when the user clicks on modify appointment button.
     * It retrieves the information of the selected appointment by calling the pass appointment method from ModifyAppointmentController
     * and displays an error message if no appointment is selected.
     *
     * @param actionEvent the modify appointment button action
     * @throws IOException if fxml file not found
     * @throws SQLException if database not found
     */
    public void onModifyAppointmentButton(ActionEvent actionEvent) throws IOException, SQLException {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        if(selectedAppointment != null) {
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("../view/ModifyAppointment.fxml"));
            Parent parent = loader.load();
            stage.setScene(new Scene(parent));
            stage.show();

            ModifyAppointmentController modifyAppointmentController = loader.getController();
            modifyAppointmentController.passAppointment(selectedAppointment);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Appointment Selected");
            alert.setContentText("Please select an appointment to modify.");
            alert.showAndWait();
        }
    }

    /**
     * This is the delete appointment method.
     * This method deletes a selected appointment when the user clicks on delete appointment button and confirms to delete.
     * It displays an error message if no appointment is selected.
     *
     * @param actionEvent the delete appointment button action
     * @throws SQLException if database not found
     */
    public void onDeleteAppointmentButton(ActionEvent actionEvent) throws SQLException {
        JDBC.makeConnection();
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        if(selectedAppointment != null) {
            int appointmentId = selectedAppointment.getAppointmentId();
            String type = selectedAppointment.getType();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Delete Confirmation");
            alert.setContentText("Are you sure you want to delete?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                AppointmentDao.deleteAppointment(appointmentId, type);
                appointmentTable.setItems(AppointmentDao.getAllAppointments());
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No Appointment Selected");
            alert.setContentText("Please select an appointment to delete.");
            alert.showAndWait();
        }
    }

    /**
     * This is the initialize method.
     * This method initializes the appointment controller by making connection to the database and populates the appointment table
     * by calling the get all appointments method from AppointmentDao class.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        contactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        try {
            JDBC.makeConnection();
            appointmentTable.setItems(AppointmentDao.getAllAppointments());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
