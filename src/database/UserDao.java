package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static boolean checkLogIn(String userName, String password) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sqlStatement = "SELECT * FROM users WHERE User_Name = ? and Password = ?";

        PreparedStatement ps = JDBC.getPreparedStatement();
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            return true;
        }
        return false;
    }
}
