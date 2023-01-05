package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
/** This class is serves as a common area to store Lists.  No methods are defined for this class.*/
public class ListManager {
    public static ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public static ObservableList<User> userList = FXCollections.observableArrayList();
    public static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    public static ObservableList<Countries> countryList = FXCollections.observableArrayList();

    public static ObservableList<FirstLevelDivision> firstLevelDivisionsList = FXCollections.observableArrayList();

    public static ArrayList<Integer> appointmentsToDelete = new ArrayList<>();

    public static ObservableList<Contact> contactList = FXCollections.observableArrayList();

    public static ArrayList<Appointment> appointmentOccupiedList = new ArrayList<>();

    //deleted Local Time array list and replaced with above
    public static ArrayList<Appointment> appointmentTimeListForAlert = new ArrayList<>();

    public static ObservableList<Appointment> monthAppointmentList = FXCollections.observableArrayList();

}
