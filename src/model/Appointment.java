package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This is Appointment model class.
 * This class is for appointments in database.
 *
 * @author Sochandaling Teng
 */
public class Appointment {
    /**
     * the id of appointment
     */
    private int appointmentId;
    /**
     * the title of appointment
     */
    private String title;
    /**
     * the description of appointment
     */
    private String description;
    /**
     * the location of appointment
     */
    private String location;
    /**
     * the type of appointment
     */
    private String type;
    /**
     * the start date of appointment
     */
    private LocalDate startDate;
    /**
     * the end date of appointment
     */
    private LocalDate endDate;
    /**
     * the start time of appointment
     */
    private LocalTime startTime;
    /**
     * the end time of appointment
     */
    private LocalTime endTime;
    /**
     * the id of customer of appointment
     */
    private int customerId;
    /**
     * the id of user of appointment
     */
    private int userId;
    /**
     * the id of contact of appointment
     */
    private int contactId;
    /**
     * the auto-generated appointment id
     */
    private static int autoAppointmentId = 1;

    /**
     * Creates a new object of Appointment class.
     * @param appointmentId the id of appointment
     * @param title the title of appointment
     * @param description the description of appointment
     * @param location the location of appointment
     * @param type the type of appointment
     * @param startDate the start date of appointment
     * @param endDate the end date of appointment
     * @param startTime the start time of appointment
     * @param endTime the end time of appointment
     * @param customerId the id of customer of appointment
     * @param userId the id of user of appointment
     * @param contactId the id of contact of appointment
     */
    public Appointment(int appointmentId, String title, String description, String location, String type,
                       LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime,
                       int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * @return the id of appointment
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @param appointmentId the id of appointment
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * @return the title of appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title of appointment
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description of appointment
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description of appointment
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location of appointment
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location of appointment
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the type of appointment
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type of appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the start date of appointment
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the start date of appointment
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the end date of appointment
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the end date of appointment
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the start time of appointment
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the start time of appointment
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the end time of appointment
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the end time of appointment
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the id of customer of appointment
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the id of customer of appointment
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the id of user of appointment
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the id of user of appointment
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the id of contact of appointment
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the id of contact of appointment
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * @return the auto-generated appointment id
     */
    public static int getAutoAppointmentId(){
        return autoAppointmentId++;
    }
}
