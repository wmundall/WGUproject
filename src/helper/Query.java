package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Query {
    public static int Insert(String Description) throws SQLException {
        String sql = "INSERT INTO appointments (Description) Values (?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, Description);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int Update(String Description, int Customer_ID) throws SQLException {
        String sql = "Update appointments SET Description = ? Where Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, Description);
        ps.setInt(2, Customer_ID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    public static int Delete(int Customer_ID) throws SQLException {
        String sql = "Delete From appointments Where Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, Customer_ID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void Select(int Customer_ID) throws SQLException {
        String sql = "Select * From appointments Where Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, Customer_ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customer_IDFK = rs.getInt("Customer_ID");
            String description = rs.getString("Description");
            String title = rs.getString(("Title"));
            System.out.println(customer_IDFK);
            System.out.println(description);
            System.out.println(title);

        }
    }
    public static String Select(String User_Name,String Password) throws SQLException   {
        String userName = "";
        String password = "";
        String sql = "Select User_Name,Password From users Where User_Name = ? AND Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, User_Name);
        ps.setString(2,Password);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
          userName = rs.getString("User_Name");
          password = rs.getString("Password");

            System.out.println("Welcome " + userName);
        }
        return userName;
    }
}

