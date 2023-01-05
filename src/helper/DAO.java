package helper;

import java.sql.SQLException;
import java.util.List;
/** This interface is a template for all other database accessing classes in application.
 * The basic database operations are defined in this interface */
public interface DAO<T> {
     /** This method signature defines an operation that will construct an object from a record in the relvant table in the client_schedule database.
      * @param id the unique int ID that is used in each table to make each record distinct.
      * @return a model object constructed from fields in relevant table in database.
      * @throws SQLException
      */
     T get(int id) throws SQLException;
     /** This method queries the relevant table in the client_schedule database.
      * @return returns a List of all objects that are constructed from fields in relevant table in database.
      * @throws SQLException
      */
     List<T> getAll() throws SQLException;
     /** This method performs an insert on the relevant table in the client_schedule database.
      * @param t  the model object that is to be added to database
      * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
      * or (2) 0 for SQL statements that return nothing
      * @throws SQLException
      */
     int insert(T t)throws SQLException;
     /** This method performs an update operation on the relevant table in the client_schedule database.
      * @param t  the  model object that is to be updated in the database
      * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
      * or (2) 0 for SQL statements that return nothing
      * @throws SQLException
      */
     int update(T t)throws SQLException;

     /** This method performs a delete operation on the relevant table in the client_schedule database.
      * @param t the  model object corresponding to the row in the relevant table that will be deleted.
      * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
      *      * or (2) 0 for SQL statements that return nothing
      * @throws SQLException
      */
     int delete (T t) throws SQLException;
}
