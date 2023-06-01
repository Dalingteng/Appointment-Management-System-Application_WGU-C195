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
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    public Label usernameLabel;
    public Label passwordLabel;
    public Label timeZoneLabel;
    public Label userTimeZoneLabel;
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Button logInButton;
    public Button resetButton;
    public Button cancelButton;
    static ObservableList<User> Users;

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

    public void onResetButton(ActionEvent actionEvent) {
        usernameTextField.clear();
        passwordTextField.clear();
    }

    public void onCancelButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTimeZoneLabel.setText(String.valueOf(ZoneId.systemDefault()));

        try {
            Users = UserDao.getAllUsers();
            if (Locale.getDefault().getLanguage().equals("fr")) {
                ResourceBundle rb = ResourceBundle.getBundle("language/language_fr", Locale.getDefault());
                usernameLabel.setText(rb.getString("Username"));
                passwordLabel.setText(rb.getString("Password"));
                timeZoneLabel.setText(rb.getString("Time Zone"));
                logInButton.setText(rb.getString("Log In"));
                resetButton.setText(rb.getString("Reset"));
                cancelButton.setText(rb.getString("Cancel"));
            }
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
