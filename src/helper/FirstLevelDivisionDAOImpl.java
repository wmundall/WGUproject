package helper;

import model.Countries;
import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
/**
 * This Class creates data access objects for querying the first_level_divisions table in database.
 */
public class FirstLevelDivisionDAOImpl implements DAO<FirstLevelDivision> {
    /** This method queries the first_level_divisions table in the client_schedule database.
     * @param firstLevelDivisionID the unique int ID that each first level division record holds in database.
     * @return a First Level Division model object constructed from fields in first_level_divisions table in database.
     * @throws SQLException
     */
    @Override
    public FirstLevelDivision get(int firstLevelDivisionID) throws SQLException {

            FirstLevelDivision firstLevelDivision = null;
            JDBC.openConnection();
            String sql = "Select * From first_level_divisions Where Division_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1,firstLevelDivisionID);
            ResultSet rs = ps.executeQuery();
            while (rs.next())   {
                int divisionId = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy  = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryId = rs.getInt("Country_ID");

                firstLevelDivision = new FirstLevelDivision(divisionId,division,createDate,createdBy,lastUpdate,lastUpdatedBy,countryId);

            }
            return firstLevelDivision;
        }
    /** This method queries the first_level_divisions table in the client_schedule database.
     * @return returns a List of all FirstLevelDivision objects that are constructed from fields in first_level_divisions table in database.
     * @throws SQLException
     */

    public  List<FirstLevelDivision>  getAll() throws SQLException {
        JDBC.openConnection();
        String sql = "Select * from first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())   {
            int divisionID = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            LocalDateTime createDate   = rs.getTimestamp("Create_Date").toLocalDateTime();
            String createdBy  = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");

            FirstLevelDivision firstLevelDivisionObject = new FirstLevelDivision(divisionID,divisionName,createDate,
                    createdBy,lastUpdate,lastUpdatedBy,countryID);

            ListManager.firstLevelDivisionsList.add(firstLevelDivisionObject);

        }
        return ListManager.firstLevelDivisionsList;
    }
    /** This method performs an insert on the first_level_divisions table in the client_schedule database.
     * @param firstLevelDivision  the FirstLevelDivision model object that is to be added to database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */

    @Override
    public int insert(FirstLevelDivision firstLevelDivision) throws SQLException {
        return 0;
    }
    /** This method performs an update operation on the first_level_divisions table in the client_schedule database.
     * @param firstLevelDivision  the FirstLevelDivision model object that is to be updated in the database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int update(FirstLevelDivision firstLevelDivision) throws SQLException {
        return 0;
    }
    /** This method performs a delete operation on the first_level_divisions table in the client_schedule database.
     * @param firstLevelDivision the FirstLevelDivision model object corresponding to the row in first_level_divisions table that will be deleted.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *      * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int delete(FirstLevelDivision firstLevelDivision) throws SQLException {
        return 0;
    }

    /** This method queries the first_level_divisions table in the client_schedule database.
     * @param country_ID the country_ID of the Country whose FirstLevelDivision objects we are using in list.
     * @return returns a List of all FirstLevelDivision objects that are constructed from fields in first_level_divisions table in database.
     * @throws SQLException
     */

    public  List<FirstLevelDivision>  getAll(int country_ID) throws SQLException {
            JDBC.openConnection();
            String sql = "Select * from first_level_divisions Where Country_ID = ?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, country_ID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                LocalDateTime createDate = rs.getTimestamp("Create_Date").toLocalDateTime();
                String createdBy = rs.getString("Created_By");
                LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryID = rs.getInt("Country_ID");

                FirstLevelDivision firstLevelDivisionObject = new FirstLevelDivision(divisionID, divisionName, createDate,
                        createdBy, lastUpdate, lastUpdatedBy, countryID);

                ListManager.firstLevelDivisionsList.add(firstLevelDivisionObject);

            }
            return ListManager.firstLevelDivisionsList;
        }
}
