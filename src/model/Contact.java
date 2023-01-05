package model;
/**This class is used to create Contact objects. Contact object fields model attributes found in contacts table
 * in client_schedule database */
public class Contact {
    private int contactID;
    private String contactName;
    private String email;

    /**This Contact constructor uses all fields provided. Every Contact field is available as a parameter.*/
    public Contact(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * @return the contactID that uniquely identifies different contacts in database.
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @param contactID the int ID that uniquely identifies different contacts in database.
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * @return this contactName. The contactName String given to this Contact.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName a String that will set the contactName field of this Contact.
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return this email. The email String given to this Contact.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email  a String that will set the email field of this Contact.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /** This method overrides the toString method and returns a String that consists of the Contact object's contactName.
     * @return
     */
    public String toString()  {
        return this.contactName;
    }
}
