package model;

/**
 * This is Contact model class.
 * This class is for contacts in database.
 *
 * @author Sochandaling Teng
 */
public class Contact {
    /**
     * the id of contact
     */
    private int contactId;
    /**
     * the name of contact
     */
    private String contactName;
    /**
     * the email of contact
     */
    private String email;

    /**
     * Creates a new object of Contact class.
     * @param contactId the id of contact
     * @param contactName the name of contact
     * @param email the email of contact
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * @return the id of contact
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the id of contact
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * @return the name of contact
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the name of contact
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the email of contact
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email of contact
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the string of contact id and contact name
     */
    @Override
    public String toString() {
        return "(" + contactId + ") " + contactName;
    }
}
