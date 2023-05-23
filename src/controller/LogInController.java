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
import utility.DataProvider;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLOutput;
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
//        boolean validUser = UserDao.checkLogIn(username, password);
        boolean match = false;
//        if(validUser) {

        for(User u: users) {
            if(u.getUserName().equals(username) && u.getPassword().equals(password)) {
                match = true;
                DataProvider.username = username;
                break;
            }
        }
        if(match) {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/Appointment.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        }
        else {
            System.out.println("ERROR");
        }
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

        try {
//            if(users.isEmpty()) {
                users = UserDao.select();
//                System.out.println("First Log In");
//            }
//            else {
//                System.out.println("Not First");
//            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
