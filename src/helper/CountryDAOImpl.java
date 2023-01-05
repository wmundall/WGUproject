package helper;

import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This Class creates data access objects for querying country table in database.
 */
public class CountryDAOImpl implements DAO<Countries>{
    /** This method queries the countries table in the client_schedule database.
     * @param Country_ID the unique int ID that each country record holds in database
     * @return a Countries model object constructed from fields in countries table in database.
     * @throws SQLException
     */
    @Override
    public Countries get(int Country_ID) throws SQLException {
        Countries country = null;
        JDBC.openConnection();
        String sql = "Select * From countries Where Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,Country_ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next())   {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy  = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            country = new Countries(countryID,countryName,createDate,createdBy,lastUpdate,lastUpdatedBy);

        }
        return country;
    }

    /** This method queries the Countries table in the client_schedule database.
     * @return returns a List of all Countries objects that are constructed from fields in countries table in database.
     * @throws SQLException
     */
    @Override
    public List<Countries> getAll() throws SQLException {
        JDBC.openConnection();
        String sql = "Select * From countries;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        //String country =
        ResultSet rs = ps.executeQuery();
        while (rs.next())  {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy  = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            Countries countryObject = new Countries(countryID,countryName,createDate,createdBy,lastUpdate,lastUpdatedBy);

            ListManager.countryList.add(countryObject);

        }

        return ListManager.countryList;
    }

    /** This method performs an insert on the Countries table in the client_schedule database.
     * @param countries  the country model object that is to be added to database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int insert(Countries countries) throws SQLException {
        return 0;
    }

    /** This method performs an update operation on the Countries table in the client_schedule database.
     * @param countries  the country model object that is to be updated in the database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int update(Countries countries) throws SQLException {
        return 0;
    }

    /** This method performs a delete operation on the Countries table in the client_schedule database.
     * @param countries the county model object corresponding to the row in countries table that will be deleted.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *      * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int delete(Countries countries) throws SQLException {
        return 0;
    }
}
