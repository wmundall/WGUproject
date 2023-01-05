package main;

import controller.AppointmentUpdateController;
import controller.CustomerAddController;
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

import java.io.*;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**This Class creates an app for managing customers and appointments*/
public class main extends Application {
    /**
     * This method is the entrypoint for the application.
     * @param stage the stage upon which the scene graph will be placed.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SignInScreen.fxml"));
        stage.setTitle("Sign In");
        stage.setScene(new Scene(root, 400, 400));
        stage.show();
    }

    /**
     * This is the Main Method. This is the entrypoint for all other method calls
     */
    public static void main(String[] args) throws SQLException, IOException {
        launch(args);

    }
}
