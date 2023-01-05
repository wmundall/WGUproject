package helper;

import model.Appointment;
import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
/**
 * This Class creates data access objects for querying the contacts table in database.
 */
public class ContactDaoImpl implements DAO<Contact>{
    /** This method queries the contacts table in the client_schedule database.
     * @param contactID the unique int ID that each contact record holds in database
     * @return a Contact model object constructed from fields in contacts table in database.
     * @throws SQLException
     */
    @Override
    public Contact  get(int contactID) throws SQLException {
        Contact contact = null;
        JDBC.openConnection();
        String sql = "Select * From contacts Where Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactID1 = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");

            contact = new Contact(contactID1,contactName,email);
            ListManager.contactList.add(contact);
        }
        return contact;
    }
    /** This method queries the contacts table in the client_schedule database.
     * @return returns a List of all Contact objects that are constructed from fields in contacts table in database.
     * @throws SQLException
     */
    @Override
    public List<Contact> getAll() throws SQLException {
        JDBC.openConnection();
        String sql = "Select * From contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");


            Contact contact = new Contact(contactID,contactName,email);
            ListManager.contactList.add(contact);
        }

        return ListManager.contactList;
    }
    /** This method performs an insert on the contacts table in the client_schedule database.
     * @param contact  the Contact model object that is to be added to database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int insert(Contact contact) throws SQLException {
        return 0;
    }
    /** This method performs an update operation on the contacts table in the client_schedule database.
     * @param contact  the Contact model object that is to be updated in the database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int update(Contact contact) throws SQLException {
        return 0;
    }
    /** This method performs a delete operation on the contacts table in the client_schedule database.
     * @param contact the Contact model object corresponding to the row in contacts table that will be deleted.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *      * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int delete(Contact contact) throws SQLException {
        return 0;
    }


}
