package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    public Label usernameLabel;
    public Label passwordLabel;
    public Label timeZoneLabel;
    public Label userTimeZoneLabel;
    public TextField usernameText;
    public PasswordField passwordText;
    public Button logInButton;
    public Button resetButton;
    public Button cancelButton;

    public void onLogInButton(ActionEvent actionEvent) {
    }

    public void onResetButton(ActionEvent actionEvent) {
        System.out.println("Reset button clicked.");
        usernameText.setText("");
        passwordText.setText("");
    }

    public void onCancelButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
