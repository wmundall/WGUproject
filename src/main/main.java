package main;

import helper.AppointmentDAOImpl;
import helper.CustomerDAOImpl;
import helper.ListManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.FileNotFoundException;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.time.*;
import java.util.*;


public class main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SignInScreen.fxml"));
        stage.setTitle("Sign In");
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    public static void main(String[] args) throws SQLException, FileNotFoundException {




        //Get(ID) works
        // Customer customer1 = customerDaoTest.get(4);
        // Getall() works System.out.println(customerDaoTest.getAll().toString());
        //Customer customer1 = new Customer(4,"Spencer","address","96744",
        //        "222-222-2222",6,7);
        //Insert works and Spencer is now in Table. customerDaoTest.insert(customer1);
        //Delete works customerDaoTest.delete(customer1);





        Appointment appointment1 = new Appointment(5, "title", "urgent", "jamica",
                "type", LocalDateTime.now(), LocalDateTime.now(), 3, 2, 1);
        Appointment appointment2 = new Appointment(4, "I", "am", "new",
                "dbrow", LocalDateTime.now(), LocalDateTime.now(), 3, 2, 1);

        //ListManager.appointmentList.add(appointment);

// create emplyee DAO then create emplyee object, call appintmentdao.get and put into emplyee object

        AppointmentDAOImpl appointmentDAOtest = new AppointmentDAOImpl();
        Appointment appointment = appointmentDAOtest.get(5);
        System.out.println(appointment + " you suck");
        System.out.println(ListManager.appointmentList);
        //INSERT works appointmentDAOtest.insert(appointment2);
        //UPDATE works appointmentDAOtest.update(appointment2);
       // System.out.println(appointmentDAOtest.get(3));
        //DELETE works appointmentDAOtest.delete(appointment2);


        launch(args);


        //ZoneId.getAvailableZoneIds().stream().forEach(System.out::println);
        //ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("America")).forEach(System.out::println);
        LocalDate parisDate = LocalDate.of(2022, 11, 3);
        LocalTime parisTime = LocalTime.of(6, 38);
        ZoneId parisZoneID = ZoneId.of("Europe/Paris");
        ZonedDateTime parisZDT = ZonedDateTime.of(parisDate, parisTime, parisZoneID);
        System.out.println(parisZDT);
        ZoneId localZoneID = ZoneId.of(TimeZone.getDefault().getID());
        System.out.println(localZoneID);
        Instant parisToGmt = parisZDT.toInstant();
        System.out.println(parisToGmt);
        ZonedDateTime parisToLocalZDT = parisZDT.withZoneSameLocal(localZoneID);
        System.out.println(parisToLocalZDT);
        ZonedDateTime gmtToLocalZDT = parisToGmt.atZone(localZoneID);
        System.out.println(gmtToLocalZDT);


        /*Query.Insert(1);
        int rowsAffected = Query.Delete(1);

        if (rowsAffected > 0) {
            System.out.println("yes");
        }
        else {System.out.println("no");}

        JDBC.closeConnection();*/

        /*   String filename = "todo.txt",items;
            Scanner keyboard = new Scanner(System.in);
            System.out.println("How many");
            int  numItems = keyboard.nextInt();
            keyboard.nextLine();
            PrintWriter outputFile = new PrintWriter(filename);
             for(int i=0;i<numItems;i++){
            System.out.println("enter item");
            items = keyboard.nextLine();
            outputFile.println(items); */


    }
}
