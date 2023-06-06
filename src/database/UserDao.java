package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is UserDao class.
 * This class is for getting data of users from the database.
 */
public class UserDao {
    /**
     * This is the get all users method. This gets all users from the database.
     * @return the list of all users in database
     * @throws SQLException if the database not found
     */
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
