package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
//    public static boolean checkLogIn(String username, String password) throws SQLException {
//        Connection conn = JDBC.getConnection();
//        String sqlStatement = "SELECT * FROM users WHERE User_Name = ? and Password = ?";
//
//        PreparedStatement ps = JDBC.getPreparedStatement();
//        ps.setString(1, username);
//        ps.setString(2, password);
//        ResultSet rs = ps.executeQuery();
//        while(rs.next()) {
//            return true;
//        }
//        return false;
//    }

    public static ObservableList<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<User> allUsers = FXCollections.observableArrayList();
        while(rs.next()) {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
            allUsers.add(new User(userId, userName, password));
        }
        return allUsers;
    }


}
