package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Countries;
import model.Customer;

public class ListManager {
    public static ObservableList<Customer> customerList = FXCollections.observableArrayList();
    public static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    public static ObservableList<Countries> countryList = FXCollections.observableArrayList();
    

}
