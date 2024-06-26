package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This is AppointmentDao class.
 * This class is for getting, adding, updating and deleting data of appointments in the database.
 *
 * @author Sochandaling Teng
 */
public class AppointmentDao {
    /**
     * This is the get all appointments method. This gets all appointments from the database.
     * @return the list of all appointments in database
     * @throws SQLException if the database not found
     */
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

    /**
     * This is the get appointment by customer method. This gets appointments of a specific customer from the database based on customer id.
     * @param customerId the id of a specific customer
     * @return the list of appointments of a specific customer
     * @throws SQLException if the database not found
     */
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

    /**
     * This is the get appointment by contact method. This gets appointments of a specific contact from the database based on contact id.
     * @param contactId the id of a specific contact
     * @return the list of appointments of a specific contact
     * @throws SQLException if the database not found
     */
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

    /**
     * This is the add appointment method. This adds an appointment into the database with all desired information of that appointment.
     * @param appointmentId the id of appointment
     * @param title the title of appointment
     * @param description the description of appointment
     * @param location the location of appointment
     * @param type the type of appointment
     * @param startDateTime the start date and time of appointment
     * @param endDateTime the end date and time of appointment
     * @param customerId the id of customer for appointment
     * @param userId the id of user for appointment
     * @param contactId the id of contact for appointment
     * @return the number of rows affected in database
     * @throws SQLException if the database not found
     */
    public int addAppointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        ps.setString(2, title);
        ps.setString(3, description);
        ps.setString(4, location);
        ps.setString(5, type);
        ps.setTimestamp(6, Timestamp.valueOf(startDateTime));
        ps.setTimestamp(7, Timestamp.valueOf(endDateTime));
        ps.setInt(8, customerId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0) {
            System.out.println("Add Successful!");
        }
        else {
            System.out.println("Add Failed!");
        }
        return rowsAffected;
    }

    /**
     * This is the update appointment method. This updates information of a selected appointment in the database based on appointment id.
     * @param appointmentId the id of appointment to be updated
     * @param title the title of appointment
     * @param description the description of appointment
     * @param location the location of appointment
     * @param type the type of appointment
     * @param startDateTime the start date and time of appointment
     * @param endDateTime the end date and time of appointment
     * @param customerId the id of customer for appointment
     * @param userId the id of user for appointment
     * @param contactId the id of contact for appointment
     * @return the number of rows affected in database
     * @throws SQLException if the database not found
     */
    public int updateAppointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int userId, int contactId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ? , Contact_ID = ? " +
                "WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(startDateTime));
        ps.setTimestamp(6, Timestamp.valueOf(endDateTime));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, appointmentId);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0) {
            System.out.println("Update Successful!");
        }
        else {
            System.out.println("Update Failed!");
        }
        return rowsAffected;
    }

    /**
     * This is the delete appointment method. This deletes a selected appointment from the database based on appointment id and type and alerts whether or not the appointment is deleted.
     * @param appointmentId the id of appointment to be deleted
     * @param type the type of appointment to be deleted
     * @return the number of rows affected in database
     * @throws SQLException if the database not found
     */
    public static int deleteAppointment(int appointmentId, String type) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ? AND Type = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        ps.setString(2, type);
        int rowsAffected = ps.executeUpdate();
        if(rowsAffected > 0) {
            System.out.println("Delete Successful!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Appointment");
            alert.setHeaderText("Delete Successful!");
            alert.setContentText("Appointment: (" + appointmentId + ") " + type + " is successfully deleted.");
            alert.showAndWait();
        }
        else {
            System.out.println("Delete Failed!");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete Appointment");
            alert.setHeaderText("Delete Failed!");
            alert.setContentText("Appointment: (" + appointmentId + ") " + type + " is failed to delete.");
            alert.showAndWait();
        }
        return rowsAffected;
    }
}
