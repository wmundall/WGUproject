package helper;

import model.Appointment;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
/**
 * This Class creates data access objects for querying the customers table in database.
 */
public class CustomerDAOImpl implements DAO<Customer>{
    /** This method queries the customers table in the client_schedule database.
     * @param Customer_ID the unique int ID that each custumer record holds in database.
     * @return a Customer model object constructed from fields in customers table in database.
     * @throws SQLException
     */
    @Override
    public Customer get(int Customer_ID) throws SQLException {
       Customer customer = null;
        JDBC.openConnection();
        String sql = "Select customers.Customer_ID,customers.Customer_Name,customers.Address,customers.Postal_Code,customers.Phone,customers.Division_ID,first_level_divisions.Country_ID\n" +
                "                 From ((customers  Inner Join first_level_divisions  ON   \n" +
                "                customers.Division_ID = first_level_divisions.Division_ID)  \n" +
                "                Inner Join countries  ON first_level_divisions.Country_ID = countries.Country_ID)\n" +
                "                Where Customer_ID = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, Customer_ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            int divisionID = rs.getInt("Division_ID");
            int country = rs.getInt("Country_ID");


            customer = new Customer(customerID, customerName, customerAddress,
                    postalCode, customerPhone, divisionID, country);

            ListManager.customerList.add(customer);
        }
        JDBC.closeConnection();

        return customer;
    }
    /** This method queries the customers table in the client_schedule database.
     * @return returns a List of all Customer objects that are constructed from fields in customers table in database.
     * @throws SQLException
     */
    @Override
    public List<Customer> getAll() throws SQLException {
        JDBC.openConnection();
        String sql = "Select c.Customer_ID,c.Customer_Name,c.Address,c.Postal_Code,c.Phone,c.Division_ID,c.Create_Date,c.Created_By,c.Last_Update,c.Last_Updated_By,fld.Country_ID" +
                " From customers c Inner Join first_level_divisions fld On  " +
                "c.Division_ID = fld.Division_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            /*String createDateBy = rs.getString("Created_By");
            String lastUpdateBy = rs.getString("Last_Updated_BY");
            LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();*/
            int divisionID = rs.getInt("Division_ID");
            int country = rs.getInt("Country_ID");

            Customer customer = new Customer (customerID, customerName, customerAddress,
                    postalCode, customerPhone,/*createDate,createDateBy,lastUpdate,lastUpdateBy*/ divisionID,country);

            ListManager.customerList.add(customer);
        }

        return ListManager.customerList;
    }
    /** This method performs an insert on the customers table in the client_schedule database.
     * @param customer  the Customer model object that is to be added to database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int insert(Customer customer) throws SQLException {
        JDBC.openConnection();
        String sql = "Insert Into customers (Customer_Name,Address,Postal_Code,Phone,Division_ID) Values (?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        //ps.setInt(1, customer.getCustomerID());
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getCustomerAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getCustomerPhone());
        ps.setInt(5,customer.getDivisionID());

        int result = ps.executeUpdate();
        JDBC.closeConnection();

        return result;

    }
    /** This method performs an update operation on the customers table in the client_schedule database.
     * @param customer  the Customer model object that is to be updated in the database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int update(Customer customer) throws SQLException {
        JDBC.openConnection();
        String sql = "Update customers set Customer_Name = ?,Address = ?,Postal_Code = ?,Phone = ?,Division_ID = ? Where Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        //ps.setInt(1, customer.getCustomerID());
        ps.setString(1, customer.getCustomerName());
        ps.setString(2, customer.getCustomerAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getCustomerPhone());
        ps.setInt(5,customer.getDivisionID());
        ps.setInt(6,customer.getCustomerID());

        int result = ps.executeUpdate();
        JDBC.closeConnection();

        return result;
    }
    /** This method performs a delete operation on the customers table in the client_schedule database.
     * @param customer the Customer model object corresponding to the row in customers table that will be deleted.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *      * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int delete(Customer customer) throws SQLException {
        JDBC.openConnection();
        String sql = "Delete from customers Where Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customer.getCustomerID());

        int result = ps.executeUpdate();

        return result;
    }
}
