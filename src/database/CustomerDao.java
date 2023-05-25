package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customers, first_level_divisions, countries WHERE" +
                "customers.Division_ID = first_level_divisions.Division_ID AND" +
                "first_level_divisions.Country_ID = countries.Country_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phoneNumber = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            allCustomers.add(new Customer(customerId, customerName, address, postalCode, phoneNumber, divisionId, divisionName, countryId, countryName));
        }
        return allCustomers;
    }

    public int addCustomer(String customerName, String address, String postalCode, String phoneNumber, int divisionId) {
        int rowsAffected = 0;
        String sql = "INSERT INTO customers";
    }
}
