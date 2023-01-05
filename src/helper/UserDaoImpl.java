package helper;

import model.Appointment;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
/**
 * This Class creates data access objects for querying the users table in database.
 */
public class UserDaoImpl implements DAO<User>{


    public User get(String userName) throws SQLException {

            User user = null;
            JDBC.openConnection();
            String sql = "Select * From users Where User_Name = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt("User_ID");
                String userNameforObj = rs.getString("User_Name");
                String password = rs.getString("Password");

                user = new User(userID,userNameforObj,password);
                ListManager.userList.add(user);
            }
            JDBC.closeConnection();

            return user;
        }

    /** This method queries the appointments table in the client_schedule database.
     * @param userID the unique int ID that each user record holds in database
     * @return a user model object constructed from fields in users table in database.
     * @throws SQLException
     */
    @Override
    public User get(int userID) throws SQLException {
        return null;
    }
    /** This method queries the users table in the client_schedule database.
     * @return returns a List of all User objects that are constructed from fields in users table in database.
     * @throws SQLException
     */
    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }
    /** This method performs an insert on the users table in the client_schedule database.
     * @param user  the User model object that is to be added to database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int insert(User user) throws SQLException {
        return 0;
    }
    /** This method performs an update operation on the users table in the client_schedule database.
     * @param user  the User model object that is to be updated in the database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int update(User user) throws SQLException {
        return 0;
    }
    /** This method performs a delete operation on the users table in the client_schedule database.
     * @param user the User model object corresponding to the row in users table that will be deleted.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *      * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int delete(User user) throws SQLException {
        return 0;
    }
}
