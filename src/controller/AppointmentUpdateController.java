package controller;

import helper.AppointmentDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Appointment;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppointmentUpdateController implements Initializable {
    @FXML
    private TextField appointmentIDTxt;

    @FXML
    private TextField customerIDTxt;

    @FXML
    private TextField endDateTimeTxt;

    @FXML
    private TextField userIdTxt;

    @FXML
    private TextField contactTxt;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private TextField locationTxt;

    @FXML
    private TextField startDateTimeTxt;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextField typeTxt;


    @FXML
    void AddAppointment(ActionEvent event) {

    }

    public void setApptIDTxt(String a) {
        appointmentIDTxt.setText(a);
    }

    public void setTypeTxt(String a) {
        typeTxt.setText(a);
    }

    public void setTitleTxt(String a) {
        titleTxt.setText(a);
    }

    public void setDescriptionTxt(String a) {
        descriptionTxt.setText(a);
    }

    public void setLocationTxt(String a) {
        locationTxt.setText(a);
    }

    public void setContactTxt(String a) {
        contactTxt.setText(a);
    }

    public void setStartDateTimeTxt(String a) {
        startDateTimeTxt.setText(a);
    }

    public void setEndDateTimeTxt(String a) {
        endDateTimeTxt.setText(a);
    }

    public void setCustomerIDTxt(String a) {
        customerIDTxt.setText(a);
    }

    public void setUserIdTxt(String a) {
        typeTxt.setText(a);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentDAOImpl a = new AppointmentDAOImpl();


        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AppointmentsMain.fxml"));
        //root=loader.load();
        AppointmentsMainController controller = loader.getController();
        controller.getTextField();*/

        /*Appointment ab = null;
        try {
            ab = a.get(AppointmentsMainController.id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        AppointmentIDTxt.setText(String.valueOf(ab.getAppointmentID()));*/


    }
}
