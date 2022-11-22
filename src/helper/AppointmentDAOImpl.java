package helper;

import model.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentDAOImpl implements DAO<Appointment> {
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
            LocalDateTime startDateandTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDatendTime = rs.getTimestamp("End").toLocalDateTime();


            appointment = new Appointment(appointmentID, title, description,
                    location, type, startDateandTime, endDatendTime, customerID, userID, contact);
            ListManager.appointmentList.add(appointment);
        }
        JDBC.closeConnection();

        return appointment;
    }


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
            LocalDateTime startDateandTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endDatendTime = rs.getTimestamp("End").toLocalDateTime();

            Appointment appointment = new Appointment(appointmentID, title, description, location, type, startDateandTime,
                    endDatendTime, customerID, userID, contact);
            ListManager.appointmentList.add(appointment);
        }

        return ListManager.appointmentList;
    }

    @Override
    public int save(Appointment appointment) {
        return 0;
    }

    @Override
    public int insert(Appointment appointment) throws SQLException {
        JDBC.openConnection();
        String sql = "Insert Into appointments (Appointment_ID,Title,Description,Location,Type,Start,End,Customer_ID,User_ID,Contact_ID) Values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointment.getAppointmentID());
        ps.setString(2, appointment.getTitle());
        ps.setString(3, appointment.getDescription());
        ps.setString(4, appointment.getLocation());
        ps.setString(5, appointment.getType());
        ps.setTimestamp(6,Timestamp.valueOf(appointment.getStart()));
        ps.setTimestamp(7,Timestamp.valueOf(appointment.getEnd()));
        ps.setInt(8,appointment.getCustomerId());
        ps.setInt(9,appointment.getUserId());
        ps.setInt(10,appointment.getContactId());

        int result = ps.executeUpdate();
        JDBC.closeConnection();

        return result;
    }

    @Override
    public int update(Appointment appointment) throws SQLException {
        JDBC.openConnection();
        String sql = "Update appointments set Appointment_ID = ?,Title = ?,Description = ?,Location=?,Type = ?,Start = ?,End = ?,Customer_ID = ?,User_ID = ?,Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,appointment.getAppointmentID());
        ps.setString(2, appointment.getTitle());
        ps.setString(3, appointment.getDescription());
        ps.setString(4, appointment.getLocation());
        ps.setString(5, appointment.getType());
        ps.setTimestamp(6,Timestamp.valueOf(appointment.getStart()));
        ps.setTimestamp(7,Timestamp.valueOf(appointment.getEnd()));
        ps.setInt(8,appointment.getCustomerId());
        ps.setInt(9,appointment.getUserId());
        ps.setInt(10,appointment.getContactId());

        int result = ps.executeUpdate();
        JDBC.closeConnection();

        return 0;
    }

    @Override
    public int delete(Appointment appointment) throws SQLException {
        JDBC.openConnection();
        String sql = "Delete from appointments Where Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,appointment.getAppointmentID());

        int result = ps.executeUpdate();

        return result;
    }
}
