package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentDao {
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime startDateTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDateTime = rs.getTimestamp("End").toLocalDateTime();
            LocalDate startDate = startDateTime.toLocalDate();
            LocalDate endDate = endDateTime.toLocalDate();
            LocalTime startTime = startDateTime.toLocalTime();
            LocalTime endTime = endDateTime.toLocalTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            allAppointments.add(new Appointment(appointmentId, title, description, location, type, startDate, endDate, startTime, endTime, customerId, userId, contactId));
        }
        return allAppointments;
    }

    public static ObservableList<Appointment> getAppointmentsByCustomer(int customerId) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointment> appointmentsByCustomer = FXCollections.observableArrayList();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime startDateTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDateTime = rs.getTimestamp("End").toLocalDateTime();
            LocalDate startDate = startDateTime.toLocalDate();
            LocalDate endDate = endDateTime.toLocalDate();
            LocalTime startTime = startDateTime.toLocalTime();
            LocalTime endTime = endDateTime.toLocalTime();
            customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            appointmentsByCustomer.add(new Appointment(appointmentId, title, description, location, type, startDate, endDate, startTime, endTime, customerId, userId, contactId));
        }
        return appointmentsByCustomer;
    }

    public static ObservableList<Appointment> getAppointmentsByContact(int contactId) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactId);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointment> appointmentsByContact = FXCollections.observableArrayList();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            LocalDateTime startDateTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDateTime = rs.getTimestamp("End").toLocalDateTime();
            LocalDate startDate = startDateTime.toLocalDate();
            LocalDate endDate = endDateTime.toLocalDate();
            LocalTime startTime = startDateTime.toLocalTime();
            LocalTime endTime = endDateTime.toLocalTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            contactId = rs.getInt("Contact_ID");
            appointmentsByContact.add(new Appointment(appointmentId, title, description, location, type, startDate, endDate, startTime, endTime, customerId, userId, contactId));
        }
        return appointmentsByContact;
    }
}
