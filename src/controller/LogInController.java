package controller;

import database.UserDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    static ObservableList<User> users;

    public void onLogInButton(ActionEvent actionEvent) throws SQLException, IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        boolean validUser = false;
        for(User u: users) {
            if(u.getUserName().equals(username) && u.getPassword().equals(password)) {
                validUser = true;
                break;
            }
        }
        if(validUser) {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Invalid Username or Password");
            alert.showAndWait();
        }
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

        try {
            users = UserDao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
