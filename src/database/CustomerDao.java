package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customers, first_level_divisions, countries WHERE customers.Division_ID = first_level_divisions.Division_ID AND " +
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
            String divisionName = rs.getString("Division");
            String countryName = rs.getString("Country");
            allCustomers.add(new Customer(customerId, customerName, address, postalCode, phoneNumber, divisionName, countryName));
        }
        return allCustomers;
    }

    public int addCustomer(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                "VALUE (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.setString(2, customerName);
        ps.setString(3, address);
        ps.setString(4, postalCode);
        ps.setString(5, phoneNumber);
        ps.setInt(6, divisionId);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0) {
            System.out.println("Add Successful!");
        }
        else {
            System.out.println("Add Failed!");
        }
        return rowsAffected;
    }

    public int updateCustomer(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ?" +
                "WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setInt(5, divisionId);
        ps.setInt(6, customerId);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0) {
            System.out.println("Update Successful!");
        }
        else {
            System.out.println("Update Failed!");
        }
        return rowsAffected;
    }

    public static int deleteCustomer(int customerId, String customerName) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ? AND Customer_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,customerId);
        ps.setString(2, customerName);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0) {
            System.out.println("Delete Successful!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Customer");
            alert.setHeaderText("Delete Successful!");
            alert.setContentText("Customer: (" + customerId + ") " + customerName + " is successfully deleted.");
            alert.showAndWait();
        }
        else {
            System.out.println("Delete Failed!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Customer");
            alert.setHeaderText("Delete Failed!");
            alert.setContentText("Customer: (" + customerId + ") " + customerName + " is failed to delete.");
            alert.showAndWait();
        }
        return rowsAffected;
    }
}
