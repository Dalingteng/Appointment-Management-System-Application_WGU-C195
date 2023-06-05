package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class is for appointments in database.
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
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startDate
     * @param endDate
     * @param startTime
     * @param endTime
     * @param customerId
     * @param userId
     * @param contactId
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

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public static int getAutoAppointmentId(){
        return autoAppointmentId++;
    }
}
