package helper;

import javafx.collections.ObservableList;
import model.Appointment;
import model.Contact;

import java.sql.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
/**
 * This Class creates data access objects for querying appointments table in database.
 */
public class AppointmentDAOImpl implements DAO<Appointment> {
    ZoneId userLocalZone = ZoneId.of(TimeZone.getDefault().getID());
    ZoneId UTC = ZoneId.of("UTC");

    /** This method queries the appointments table in the client_schedule database.
     * @param Appointment_ID the unique int ID that each appointment record holds in database
     * @return an Appointment model object constructed from fields in appointment table in database.
     * @throws SQLException
     */
    @Override
    public Appointment get(int Appointment_ID) throws SQLException {
        Appointment appointment = null;
        JDBC.openConnection();
        String sql = "Select * From appointments Where Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, Appointment_ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            String description = rs.getString("Description");
            String title = rs.getString("Title");
            String location = rs.getString("Location");
            int contact = rs.getInt("Contact_ID");
            String type = rs.getString("Type");
            LocalDateTime startDateandTime = ZonedDateTime.of(rs.getTimestamp("Start").toLocalDateTime(), UTC).withZoneSameInstant(userLocalZone).toLocalDateTime();
            LocalDateTime endDatendTime = ZonedDateTime.of(rs.getTimestamp("End").toLocalDateTime(), UTC).withZoneSameInstant(userLocalZone).toLocalDateTime();


            appointment = new Appointment(appointmentID, title, description,
                    location, type, startDateandTime, endDatendTime, customerID, userID, contact);
            ListManager.appointmentList.add(appointment);
        }
        JDBC.closeConnection();

        return appointment;
    }

    public ArrayList<Integer> custIDSelect(int Customer_ID) throws SQLException {
        JDBC.openConnection();
        String sql = "Select Appointment_ID From appointments Where Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, Customer_ID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            ListManager.appointmentsToDelete.add(appointmentID);
        }
        JDBC.closeConnection();

        return ListManager.appointmentsToDelete;
    }

    public ArrayList<Appointment> getFilledAppointmentsbyDate(LocalDate dateOfStart, int customerIDparam) throws SQLException {
        JDBC.openConnection();
        // 1. String sql = "Select hour(timediff(End,Start)),minute(timediff(End,Start)),End,Start from appointments Where date(start) = ? And Customer_ID = ?;";
        String sql = "Select * from appointments Where date(start) = ? And Customer_ID = ?;";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(dateOfStart));
        ps.setInt(2, customerIDparam);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            String description = rs.getString("Description");
            String title = rs.getString("Title");
            String location = rs.getString("Location");
            int contact = rs.getInt("Contact_ID");
            String type = rs.getString("Type");
            LocalDateTime endDatendTime = UTCToUserTimeConversion(rs.getTimestamp("End").toLocalDateTime());
            LocalDateTime startDateandTime = UTCToUserTimeConversion(rs.getTimestamp("Start").toLocalDateTime());

            Appointment appointment = new Appointment(appointmentID, title, description, location, type, startDateandTime,
                    endDatendTime, customerID, userID, contact);
            ListManager.appointmentOccupiedList.add(appointment);
        }
            /*  2. LocalTime End = UTCToUserTimeConversion(rs.getTimestamp("End").toLocalDateTime()).toLocalTime();

            ListManager.appointmentOccupiedTimeList.add(End);

            int hours = rs.getInt("hour(timediff(End,Start))");

            int minutes = rs.getInt("minute(timediff(End,Start))");
            minutes = minutes + (hours * 60);

            int y =  minutes / 5;

            while (y > 0) {
                ListManager.appointmentOccupiedTimeList.add(End.minusMinutes(5));
                End = End.minusMinutes(5);
                y -= 1;*/



        return ListManager.appointmentOccupiedList;
    }

    /** This method queries the appointments table in the client_schedule database.
     * @return returns a List of all Appointment objects that are constructed from fields in appointments table in database.
     * @throws SQLException
     */
    @Override
    public List<Appointment> getAll() throws SQLException {
        JDBC.openConnection();
        String sql = "Select * From appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            String description = rs.getString("Description");
            String title = rs.getString("Title");
            String location = rs.getString("Location");
            int contact = rs.getInt("Contact_ID");
            String type = rs.getString("Type");
            LocalDateTime endDatendTime = UTCToUserTimeConversion(rs.getTimestamp("End").toLocalDateTime());
            LocalDateTime startDateandTime = UTCToUserTimeConversion(rs.getTimestamp("Start").toLocalDateTime());

            Appointment appointment = new Appointment(appointmentID, title, description, location, type, startDateandTime,
                    endDatendTime, customerID, userID, contact);
            ListManager.appointmentList.add(appointment);
        }

        return ListManager.appointmentList;
    }

    LocalDateTime UTCToUserTimeConversion(LocalDateTime utc) {
        ZonedDateTime utcZDT = ZonedDateTime.of(utc, UTC);
        ZonedDateTime userZDT = utcZDT.withZoneSameInstant(userLocalZone);
        LocalDateTime userLDT = userZDT.toLocalDateTime();
        return userLDT;
    }

    ZonedDateTime UserToUTCTimeConversion(LocalDateTime userLocal) {

        ZonedDateTime userZDT = ZonedDateTime.of(userLocal, ZoneId.of(TimeZone.getDefault().getID()));
        ZonedDateTime UTC = userZDT.withZoneSameInstant(ZoneId.of("UTC"));
        return UTC;
    }
    /** This method performs an insert on the appointments table in the client_schedule database.
     * @param appointment  the Appointment model object that is to be added to database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int insert(Appointment appointment) throws SQLException {
        JDBC.openConnection();
        String sql = "Insert Into appointments (Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID) Values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        //ps.setInt(1, appointment.getAppointmentID());
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, Timestamp.valueOf(UserToUTCTimeConversion(appointment.getStart()).toLocalDateTime()));
        ps.setTimestamp(6, Timestamp.valueOf(UserToUTCTimeConversion(appointment.getEnd()).toLocalDateTime()));
        ps.setInt(7, appointment.getCustomerId());
        ps.setInt(8, appointment.getUserId());
        ps.setInt(9, appointment.getContactId());

        int result = ps.executeUpdate();
        JDBC.closeConnection();

        return result;
    }
    /** This method performs an update operation on the appointments table in the client_schedule database.
     * @param appointment  the Appointment model object that is to be updated in the database
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int update(Appointment appointment) throws SQLException {
        JDBC.openConnection();
        String sql = "Update appointments set Title = ?,Description = ?,Location=?,Type = ?,Start = ?,End = ?,Customer_ID = ?,User_ID = ?,Contact_ID = ? Where Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, appointment.getTitle());
        ps.setString(2, appointment.getDescription());
        ps.setString(3, appointment.getLocation());
        ps.setString(4, appointment.getType());
        ps.setTimestamp(5, Timestamp.valueOf(UserToUTCTimeConversion(appointment.getStart()).toLocalDateTime()));
        ps.setTimestamp(6, Timestamp.valueOf(UserToUTCTimeConversion(appointment.getEnd()).toLocalDateTime()));
        ps.setInt(7, appointment.getCustomerId());
        ps.setInt(8, appointment.getUserId());
        ps.setInt(9, appointment.getContactId());
        ps.setInt(10, appointment.getAppointmentID());

        int result = ps.executeUpdate();
        JDBC.closeConnection();

        return 0;
    }
    /** This method performs a delete operation on the appintments table in the client_schedule database.
     * @param appointment the Appointment model object corresponding to the row in appointments table that will be deleted.
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *      * or (2) 0 for SQL statements that return nothing
     * @throws SQLException
     */
    @Override
    public int delete(Appointment appointment) throws SQLException {
        JDBC.openConnection();
        String sql = "Delete from appointments Where Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointment.getAppointmentID());

        int result = ps.executeUpdate();

        return result;
    }

    public List<Appointment> getWeekApptList() throws SQLException {
        JDBC.openConnection();
        String sql = "Select * FROM appointments where Start Between ? AND date_add(?, Interval 7 DAY);";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
        ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            String description = rs.getString("Description");
            String title = rs.getString("Title");
            String location = rs.getString("Location");
            int contact = rs.getInt("Contact_ID");
            String type = rs.getString("Type");
            LocalDateTime startDateandTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDatendTime = rs.getTimestamp("End").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentID, title, description, location, type, startDateandTime,
                    endDatendTime, customerID, userID, contact);
            ListManager.appointmentList.add(appointment);
        }

        return ListManager.appointmentList;
    }

    public ObservableList<Appointment> getMonthApptList(LocalDateTime ldt) throws SQLException {
        JDBC.openConnection();

        String sql = "Select * FROM appointments where Start Between ? AND date_add(?, Interval 1 Month);";

        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setTimestamp(1,Timestamp.valueOf(ldt));
        ps.setTimestamp(2,Timestamp.valueOf(ldt));
        //ps.setInt(2, monthAsInt);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            String description = rs.getString("Description");
            String title = rs.getString("Title");
            String location = rs.getString("Location");
            int contact = rs.getInt("Contact_ID");
            String type = rs.getString("Type");
            LocalDateTime startDateandTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDateandTime = rs.getTimestamp("End").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentID, title, description, location, type, startDateandTime,
                    endDateandTime, customerID, userID, contact);
            ListManager.monthAppointmentList.add(appointment);
        }

        return ListManager.monthAppointmentList;
    }

    public int GetAppointmentCountByMonthAndType(LocalDateTime ldt, String type) throws SQLException {
        JDBC.openConnection();
        String sql = "Select Count(*) from appointments Where Type = ? And month(Start) = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, type);
        ps.setInt(2, ldt.getMonth().getValue());
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("count(*)");

    }
    public List<Appointment> GetAppointmentsByContactName(Contact c) throws SQLException {

        JDBC.openConnection();
        String sql = "Select a.Appointment_ID from appointments a Inner Join contacts c On a.Contact_ID = c.Contact_ID Where c.Contact_Name = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, c.getContactName());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("a.Appointment_ID");
            Appointment a = get(appointmentID);

        }


             return ListManager.appointmentList;
        }
    }
