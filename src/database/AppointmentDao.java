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
    public ObservableList<Appointment> getAllAppointments() throws SQLException {
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
            LocalDate endDate = startDateTime.toLocalDate();
            LocalTime startTime = startDateTime.toLocalTime();
            LocalTime endTime = startDateTime.toLocalTime();
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
            int contactId = rs.getInt("Contact_ID");
            allAppointments.add(new Appointment(appointmentId, title, description, location, type, startDate, endDate, startTime, endTime, customerId, userId, contactId));
        }
        return allAppointments;
    }
}
