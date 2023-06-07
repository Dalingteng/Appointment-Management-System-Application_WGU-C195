package controller;

import database.AppointmentDao;
import database.UserDao;
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
import model.User;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.*;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This is LogInController class.
 * This class is for the log in screen of the application
 *
 * //To do: add more description
 *
 * @author Sochandaling Teng
 */
public class LogInController implements Initializable {
    /**
     * the label for username
     */
    public Label usernameLabel;
    /**
     * the label for password
     */
    public Label passwordLabel;
    /**
     * the label for time zone
     */
    public Label timeZoneLabel;
    /**
     * the label for user's system default time zone
     */
    public Label userTimeZoneLabel;
    /**
     * the text field for username
     */
    public TextField usernameTextField;
    /**
     * the text field for password
     */
    public PasswordField passwordTextField;
    /**
     * the button to log in
     */
    public Button logInButton;
    /**
     * the button to reset
     */
    public Button resetButton;
    /**
     * the button to cancel
     */
    public Button cancelButton;
    /**
     * the list of all users in database
     */
    static ObservableList<User> Users;

    /**
     *
     * @param actionEvent the log in button action
     * @throws IOException if fxml file not found
     * @throws SQLException if database not found
     */
    public void onLogInButton(ActionEvent actionEvent) throws IOException, SQLException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        boolean validUser = false;

        FileWriter fileWriter = new FileWriter("login_activity.txt", true);
        PrintWriter outputFile = new PrintWriter(fileWriter);

        for(User u: Users) {
            if(u.getUserName().equals(username) && u.getPassword().equals(password)) {
                validUser = true;
                User user = new User(u.getUserId(), username, password);
                ObservableList<Appointment> allAppointments = AppointmentDao.getAllAppointments();
                ObservableList<Appointment> upcomingAppointments = FXCollections.observableArrayList();

                LocalDateTime localNow = LocalDateTime.now();
                ZonedDateTime now = localNow.atZone(ZoneId.systemDefault());
                ZonedDateTime next15 = now.plusMinutes(15);

                for(Appointment appointment: allAppointments) {
                    LocalDate startDate = appointment.getStartDate();
                    LocalTime startTime = appointment.getStartTime();
                    ZonedDateTime appointmentTime = startTime.atDate(startDate).atZone(ZoneId.systemDefault());
                    if(user.getUserId() == appointment.getUserId()) {
                        if(appointmentTime.isAfter(now) && appointmentTime.isBefore(next15)) {
                            upcomingAppointments.add(appointment);
                        }
                    }
                }

                if(upcomingAppointments.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Upcoming Appointments Alert");
                    alert.setHeaderText("No Upcoming Appointments");
                    alert.setContentText("There is no appointment within 15 minutes.");
                    alert.showAndWait();
                }
                else {
                    String reminder = "";
                    for(Appointment a: upcomingAppointments) {
                        reminder = ("Appointment ID: " + a.getAppointmentId() + " , Date & Time: " + a.getStartDate() + " (" + a.getStartTime() + ")\n") + reminder;
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Upcoming Appointments Alert");
                    alert.setHeaderText("Appointments Reminder within 15 minutes");
                    alert.setContentText(reminder);
                    alert.showAndWait();
                }
                break;
            }
        }

        if(validUser) {
            Stage stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
            stage.setScene(new Scene(parent));
            stage.show();
            outputFile.println("User: [" + username + "] is successfully logged in at " + LocalDateTime.now() + " (" + ZoneId.systemDefault()+")");
        }
        else {
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("language/language_fr", Locale.getDefault());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(rb.getString("Invalid") + " " + rb.getString("Username") + " " +
                                rb.getString("or") + " " + rb.getString("Password"));
                alert.showAndWait();
                outputFile.println("User: [" + username + "] is failed to log in at " + LocalDateTime.now() + " (" + ZoneId.systemDefault()+")");
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Invalid Username or Password");
                alert.showAndWait();
                outputFile.println("User: [" + username + "] is failed to log in at " + LocalDateTime.now() + " (" + ZoneId.systemDefault()+")");
            }
        }
        outputFile.close();
    }

    /**
     *
     * @param actionEvent the reset button action
     */
    public void onResetButton(ActionEvent actionEvent) {
        usernameTextField.clear();
        passwordTextField.clear();
    }

    /**
     *
     * @param actionEvent the cancel button action
     */
    public void onCancelButton(ActionEvent actionEvent) {
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
     * This initializes the controller by
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTimeZoneLabel.setText(String.valueOf(ZoneId.systemDefault()));

        try {
            Users = UserDao.getAllUsers();
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("language/language_fr", Locale.getDefault());
                usernameLabel.setText(rb.getString("Username"));
                passwordLabel.setText(rb.getString("Password"));
                timeZoneLabel.setText(rb.getString("TimeZone"));
                logInButton.setText(rb.getString("LogIn"));
                resetButton.setText(rb.getString("Reset"));
                cancelButton.setText(rb.getString("Cancel"));
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
