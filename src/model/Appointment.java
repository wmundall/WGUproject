package model;

import java.time.LocalDateTime;
/**This class is used to create Appointment objects. Appointment object fields model attributes found in appointment table
 * in client_schedule database */
public class Appointment {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;
    private int contactId;
/**This Appointment constructor uses all fields provided. Every Appointment field is available as a parameter.*/
    public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }
    /**No-argument constructor provided for convenience.*/
    public Appointment() {
    }

    /**
     * @return the appointmentID that uniquely identifies different appointments in database.
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * @param appointmentID the int ID that uniquely identifies different appointments in database.
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * @return this title. The title string given to this Appointment.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title a String that will set the title field of this Appointment.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return this description. The description String given to this Appointment.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description a String that will set the description field of this Appointment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return this location. The location String given to this Appointment.
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location a String that will set the location field of this Appointment.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return this type. The type String given to this Appointment.
     */
    public String getType() {
        return type;
    }
    /**
     * @param type a String that will set the type field of this Appointment.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return this start. The start LocalDateTime given to this Appointment.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @param start a LocalDateTime that will set the start field of this Appointment.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return this end. The end LocalDateTime given to this Appointment.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @param end a LocalDateTime that will set the end field of this Appointment.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * @return this customerID. The customerID int given to this Appointment.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId a int that will set the customerID field of this Appointment.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return this userID. The userID int given to this Appointment.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId a int that will set the userID field of this Appointment.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return this contactID. The contactID int given to this Appointment.
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId a int that will set the contactID field of this Appointment.
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** This method overrides the toString method and returns a String that consists of the Appointment object's title.
     * @return
     */
    /*public String toString(){
       return "Appointment Name: " + this.getTitle();

    }*/
}
