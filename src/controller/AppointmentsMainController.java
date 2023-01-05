package controller;

import helper.AppointmentDAOImpl;
import helper.ContactDaoImpl;
import helper.ListManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This controller class handles logic that adds functionality to the main Appointment window.
 */
public class AppointmentsMainController implements Initializable {
    @FXML
    private TextField EnterApptIDTxt;
    @FXML
    private Label inputErrorLbl;


    /** This method is called on button(Search) press. Upon press, the user will be directed to the Update Appointments window. This action requires
     * valid appointmentID input or an error message is given. With proper input, the Appointment Update window is loaded and it's TextField are
     * populated with the values from an Appointment object whose appointmentID field matches the int input found in
     * EnterApptIDTxt TextBox.

     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    void GoToAppointmentsUpdate(ActionEvent event) throws IOException,SQLException {
        String apptToModify = EnterApptIDTxt.getText();
        AppointmentDAOImpl appointmentDAOimpl = new AppointmentDAOImpl();

        Appointment appointment = null;
        try {
            appointment = appointmentDAOimpl.get(Integer.parseInt(apptToModify));
        } catch (SQLException | NumberFormatException e) {
            inputErrorLbl.setVisible(true);
            inputErrorLbl.setText("Enter valid appointment ID");
            return;
        }

        ContactDaoImpl contactDao = new ContactDaoImpl();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AppointmentsUpdate.fxml"));
        Parent root = loader.load();
        AppointmentUpdateController apptUpdateMethod = loader.getController();

        apptUpdateMethod.setUserIdTxt(String.valueOf(appointment.getUserId()));
        apptUpdateMethod.setTypeTxt(appointment.getType());
        apptUpdateMethod.setTitleTxt(appointment.getTitle());
        apptUpdateMethod.setApptIDTxt(String.valueOf(appointment.getAppointmentID()));
        apptUpdateMethod.setCustomerIDTxt(String.valueOf(appointment.getCustomerId()));
        apptUpdateMethod.setLocationTxt(appointment.getLocation());
        apptUpdateMethod.setDescriptionTxt(appointment.getDescription());
        apptUpdateMethod.setFinalStartDisplayTxt(appointment.getStart().toString());
        apptUpdateMethod.setFinalEndDisplayTxt(appointment.getEnd().toString());
        apptUpdateMethod.setContactComboBox(contactDao.get(appointment.getContactId()) );

        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Update Appointments");
        stage.setScene(new Scene(root, 600, 425));
        stage.show();
        apptUpdateMethod.setAddingAppointmentBool(false);

    }

    /** This method resets the error message found in inputErrorLbl when the user clicks on the EnterApptIDTxt TextField.
     * @param event
     */
    @FXML
    public void resetErrorLbl(MouseEvent event) {
      inputErrorLbl.setVisible(false);
    }

    /** This method is called on button(Add New Appointments) press. Upon press, the user will be directed to the Add Appointments window.
     * @param event
     * @throws IOException
     */
    @FXML
    public void GoToAddAppointments(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AppointmentsUpdate.fxml"));
        Parent root = loader.load();
        AppointmentUpdateController auc = loader.getController();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Add Appointments");
        stage.setScene(new Scene(root, 600, 425));
        stage.show();
        auc.HideDelete();
        auc.setApptIDTxt("ID is auto generated");
        auc.setAddingAppointmentBool(true);

    }

    /** This method is called on button(Browse Scheduled Appointments) press. Upon press, the user will be directed back to the Appointments Browser window.
     * @param event
     * @throws IOException
     */
    @FXML
    void GoToAppointmentsView(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Appointments Browser");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();

    }

    /** This method is called on button(Back) press. Upon press, the user will be directed back to the Appointments Main Window.
     * @param event
     * @throws IOException
     */
    @FXML
    void GoBackToMain(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome Screen");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    /** No additional logic is used in this intialize method.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}