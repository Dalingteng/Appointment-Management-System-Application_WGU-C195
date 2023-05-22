package controller;

import database.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.ZoneId;
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

    public void onLogInButton(ActionEvent actionEvent) {
//        String username =
//        UserDao.checkLogIn(usernameText, passwordText);
        //if true
        //4 menu item (one separate main menu fxml)
        //appointment
        //customer
        //report button
        //exit button

        //addAppointment
        //addCustomer
        //modifyAppointment
        //modifyCustomer
        //MainView: show all appointment (left) and all customer right(right)

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
        ZoneId zone = ZoneId.systemDefault();
        userTimeZoneLabel.setText(String.valueOf(zone));


    }
}
