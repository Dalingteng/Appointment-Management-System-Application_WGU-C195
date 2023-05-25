package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
//    public static ObservableList<Customer> getAllCustomers() throws SQLException {
//        String sql = "SELECT * FROM countries";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
//        while (rs.next()) {
//            int customerId = rs.getInt("Customer_ID");
//            String customerName = rs.getString("Customer_Name");
//            String address = rs.getString("Address");
//            String postalCode = rs.getString("Postal_Code");
//            String phoneNumber = rs.getString("Phone");
//
//            allCustomers.add(new Customer());
//        }
//        return allCustomers;
//    }
}
