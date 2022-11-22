package helper;

import model.Appointment;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class CustomerDAOImpl implements DAO<Customer>{
    @Override
    public Customer get(int Customer_ID) throws SQLException {
       Customer customer = null;
        JDBC.openConnection();
        String sql = "Select c.Customer_ID,c.Customer_Name,c.Address,c.Postal_Code,c.Phone,c.Division_ID,fld.Country_ID" +
                " From customers c Inner Join first_level_divisions fld On  " +
                "c.Division_ID = fld.Division_ID  Where Customer_ID = ?";
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

    @Override
    public List<Customer> getAll() throws SQLException {
        JDBC.openConnection();
        String sql = "Select c.Customer_ID,c.Customer_Name,c.Address,c.Postal_Code,c.Phone,c.Division_ID,fld.Country_ID" +
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

            int divisionID = rs.getInt("Division_ID");
            int country = rs.getInt("Country_ID");

            Customer customer = new Customer (customerID, customerName, customerAddress,
                    postalCode, customerPhone, divisionID, country);
            ListManager.customerList.add(customer);
        }

        return ListManager.customerList;
    }




    @Override
    public int insert(Customer customer) throws SQLException {
        JDBC.openConnection();
        String sql = "Insert Into customers (Customer_ID,Customer_Name,Address,Postal_Code,Phone,Division_ID) Values (?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customer.getCustomerID());
        ps.setString(2, customer.getCustomerName());
        ps.setString(3, customer.getCustomerAddress());
        ps.setString(4, customer.getPostalCode());
        ps.setString(5, customer.getCustomerPhone());
        ps.setInt(6,customer.getDivisionID());

        int result = ps.executeUpdate();
        JDBC.closeConnection();

        return result;

    }

    @Override
    public int save(Customer customer) throws SQLException {
        return 0;
    }

    @Override
    public int update(Customer customer) throws SQLException {
        JDBC.openConnection();
        String sql = "Update customers set Customer_ID = ?,Customer_Name = ?,Address = ?,Postal_Code = ?,Phone = ?,Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customer.getCustomerID());
        ps.setString(2, customer.getCustomerName());
        ps.setString(3, customer.getCustomerAddress());
        ps.setString(4, customer.getPostalCode());
        ps.setString(5, customer.getCustomerPhone());
        ps.setInt(6,customer.getDivisionID());

        int result = ps.executeUpdate();
        JDBC.closeConnection();

        return result;
    }

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
