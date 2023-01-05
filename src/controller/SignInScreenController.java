package controller;

import helper.AppointmentDAOImpl;
import helper.JDBC;
//import helper.Query;
import helper.ListManager;
import helper.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.User;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * This controller class handles logic that adds functionality to the Sign In window.
 */
public class SignInScreenController extends JDBC implements Initializable {

    @FXML
    private Label userInputMessage;
    @FXML
    private Button button;
    @FXML
    private AnchorPane userID;
    @FXML
    private TextField userNameTxt;
    @FXML
    private TextField userPasswordTxt;
    @FXML
    private Label ZnID;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label userLabel;

    private String userName;
    private String password;
    ResourceBundle rb = ResourceBundle.getBundle("main/LanguageSupport_fr", Locale.getDefault());

    @FXML
    void verifyUserCredentials(ActionEvent event) {
        try {
            JDBC.openConnection();
            BufferedWriter br = new BufferedWriter(new FileWriter("login_activity.txt", true));
            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.get(userNameTxt.getText());
            String userName = user.getUserName();

            if (user.getPassword().equals(userPasswordTxt.getText())) {
                br.append("\n" + userName + " successfully logged in at " + LocalDateTime.now());
                br.close();
                SwitchToWelcomeScreen(event);

            } else {
                br.write("\n" + userName + " failed log in attempt at " + LocalDateTime.now());
                br.close();
                throw new SQLException();

            }
        } catch (SQLException | IOException | NullPointerException exception) {

            userInputMessage.setVisible(true);
            if (Locale.getDefault().getLanguage().equals("fr")) {
                userInputMessage.setText(rb.getString("IncorrectUserNameorPassword"));
            } else {
                userInputMessage.setText("Incorrect User Name or Password");
            }
        }
    }



    @FXML
    void ResetErrorMessage(MouseEvent event) {
        userInputMessage.setVisible(false);
    }

    @FXML
    void ResetErrorMessage1(MouseEvent event) {
        userInputMessage.setVisible(false);
    }

    public void SwitchToWelcomeScreen(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        CheckForUpcomingAppointments();
    }

    void CheckForUpcomingAppointments() {
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();

        try {
            ListManager.appointmentList.removeAll(ListManager.appointmentList);
            appointmentDAO.getAll();
        } catch (SQLException e) {
            e.getMessage();
        }
        for (Appointment a : ListManager.appointmentList) {
            ListManager.appointmentTimeListForAlert.add(a);
        }

        LocalDate today = LocalDate.now();
        LocalTime userTimeNow = LocalTime.now();
        int numAppointments = 0;
        int apptIDForalert=0;
        LocalDate dateForAlert = null;
        LocalTime timeForAlert= null;



        for (Appointment l : ListManager.appointmentTimeListForAlert) {

            if (l.getStart().toLocalDate().equals(today)) {

                if (userTimeNow.isAfter(l.getStart().toLocalTime().minusMinutes(15)) && userTimeNow.isBefore(l.getStart().toLocalTime())) {
                    numAppointments += 1;
                    apptIDForalert = l.getAppointmentID();
                    dateForAlert = l.getStart().toLocalDate();
                    timeForAlert = l.getStart().toLocalTime();
                }
            }

        }
        if (numAppointments > 0) {
            Alert upcomingAppointmentAlert = new Alert(Alert.AlertType.INFORMATION);
            upcomingAppointmentAlert.setHeaderText("Upcoming appointments");
            upcomingAppointmentAlert.setContentText("The following appointment is starting within 15 min:\n" +
                                                      "Appointment ID: " + apptIDForalert + "\n" +
                                                      "Date: " + (dateForAlert) + "\n" +
                                                      "Time: " + (timeForAlert));
            upcomingAppointmentAlert.show();

        }
        else {Alert upcomingAppointmentAlert = new Alert(Alert.AlertType.INFORMATION);
            upcomingAppointmentAlert.setHeaderText("Upcoming appointments");
            upcomingAppointmentAlert.setContentText("You have no upcoming appointments");
        }
    }

    /**
     * This method is used to configure the SigninScreen.fxml. Upon opening the Sign In Window,
     * User Locale is determined and on screen language prompts reflect the Locale.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





        if (Locale.getDefault().getLanguage().equals("fr")) {
            userLabel.setText(rb.getString("UserName"));
            passwordLabel.setText(rb.getString("Password"));
            button.setText(rb.getString("LogIn"));
            userNameTxt.setPromptText(rb.getString("enterusername"));
            userPasswordTxt.setPromptText(rb.getString("enterpassword"));

        }


        ZnID.setText("Your Zone ID is " + ZoneId.of(TimeZone.getDefault().getID()));


    }

}
