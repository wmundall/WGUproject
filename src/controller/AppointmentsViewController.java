package controller;

import helper.AppointmentDAOImpl;
import helper.ContactDaoImpl;
import helper.ListManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
/** This controller class handles logic that adds functionality to the Appointments Browser window.
 * The main feature is a selectable table view prefilled with Appointments.  */
public class AppointmentsViewController implements Initializable {
    @FXML
    public TableColumn<Appointment, Integer> appointmentID;
    @FXML
    public TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<Appointment, Integer> contactID;

    @FXML
    private TableColumn<Appointment, Integer> customerID;

    @FXML
    private TableColumn<Appointment, String> description;

    @FXML
    private TableColumn<Appointment, LocalDateTime> enddatetime;

    @FXML
    private TableColumn<Appointment, String> location;

    @FXML
    private TableColumn<Appointment, String> startdatetime;

    @FXML
    private TableColumn<Appointment, String> title;

    @FXML
    private TableColumn<Appointment, String> type;

    @FXML
    private TableColumn<Appointment, Integer> userID;


    /** This method is called on pressing button(Update Appointment). Upon press, the selected Appointment in the tableview
     * will be used to take the user to the Appointment Update window and populate text fields on that window.
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void GoToAppointmentUpdate(ActionEvent event) throws SQLException, IOException {

        DateTimeFormatter myDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");

        Appointment appointmentObjToFillText = appointmentTable.getSelectionModel().getSelectedItem();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AppointmentsUpdate.fxml"));
        Parent root = loader.load();
        AppointmentUpdateController apptUpdateMethod = loader.getController();

        ContactDaoImpl cdi = new ContactDaoImpl();


        apptUpdateMethod.setUserIdTxt(String.valueOf(appointmentObjToFillText.getUserId()));
        apptUpdateMethod.setTypeTxt(appointmentObjToFillText.getType());
        apptUpdateMethod.setTitleTxt(appointmentObjToFillText.getTitle());
        apptUpdateMethod.setApptIDTxt(String.valueOf(appointmentObjToFillText.getAppointmentID()));
        apptUpdateMethod.setCustomerIDTxt(String.valueOf(appointmentObjToFillText.getCustomerId()));
        apptUpdateMethod.setDescriptionTxt(appointmentObjToFillText.getDescription());
        apptUpdateMethod.setLocationTxt(appointmentObjToFillText.getLocation());
        apptUpdateMethod.setFinalEndDisplayTxt(appointmentObjToFillText.getEnd().format(myDTF));
        apptUpdateMethod.setFinalStartDisplayTxt(appointmentObjToFillText.getStart().format(myDTF));
        apptUpdateMethod.setContactComboBox(cdi.get(appointmentObjToFillText.getContactId()) );


        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Update Appointment");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
       apptUpdateMethod.setAddingAppointmentBool(false);


    }


    /** This method is called on button(Back) press. Upon press, the user will be directed back to the Appointments Main
     * Window.
     * @param event
     * @throws IOException
     */
    @FXML
    void BackToAppointments(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsMain.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appointments");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    /** This method is used to configure the AppointmentView.fxml. Upon opening Appointment Browser Window, the tableview
     * will be filled with Appointment objects retrieved from a database call.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppointmentDAOImpl apptTableFiller = new AppointmentDAOImpl();

        try {ListManager.appointmentList.removeAll(ListManager.appointmentList);
            apptTableFiller.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        appointmentTable.setItems(ListManager.appointmentList);

        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactID.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        startdatetime.setCellValueFactory(new PropertyValueFactory<>("start"));
        enddatetime.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userID.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    /** This method is called on radio button(Month) select. Upon selection, the tableview will repopulate with Appointments
     * from database that occur in month following and including the current day.
     * @param event
     * @throws SQLException
     */
    @FXML
    void FilterByMonth(ActionEvent event) throws SQLException {
        ListManager.appointmentList.removeAll(ListManager.appointmentList);
        System.out.println(ListManager.appointmentList);

        AppointmentDAOImpl monthDao  = new AppointmentDAOImpl();
        monthDao.getMonthApptList(LocalDateTime.now());


        appointmentTable.setItems(ListManager.appointmentList);

        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactID.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        startdatetime.setCellValueFactory(new PropertyValueFactory<>("start"));
        enddatetime.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userID.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    /** This method is called on radio button(Week) select. Upon selection, the tableview will repopulate with Appointments
     * from databsase that occur in the week following and including the current day.
     * @param event
     * @throws SQLException
     */
    @FXML
    void FilterByWeek(ActionEvent event) throws SQLException {
        ListManager.appointmentList.removeAll(ListManager.appointmentList);

        AppointmentDAOImpl weekDao  = new AppointmentDAOImpl();
        weekDao.getWeekApptList();


        appointmentTable.setItems(ListManager.appointmentList);

        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactID.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        startdatetime.setCellValueFactory(new PropertyValueFactory<>("start"));
        enddatetime.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userID.setCellValueFactory(new PropertyValueFactory<>("userId"));

    }

    /** This method is called on radio button(All) select. Upon selection, the tableview will repopulate with all Appointments
     * that currently reside in database.
     * @param event
     * @throws SQLException
     */
    @FXML
    void ShowAll(ActionEvent event) throws SQLException {
        ListManager.appointmentList.removeAll(ListManager.appointmentList);
        System.out.println(ListManager.appointmentList);

        AppointmentDAOImpl appointmentDAO  = new AppointmentDAOImpl();
        appointmentDAO.getAll();


        appointmentTable.setItems(ListManager.appointmentList);

        appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactID.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        startdatetime.setCellValueFactory(new PropertyValueFactory<>("start"));
        enddatetime.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userID.setCellValueFactory(new PropertyValueFactory<>("userId"));

        System.out.println("I'm showing all appts");
    }

    /** This method is called on button(Custom Report) press. Upon press, the user will be directed to the Custom Reports Window.
     * @param event
     * @throws IOException
     */
    @FXML
    void goToSpecialQueryWindow(ActionEvent event) throws IOException {Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/SpecialQueryWindow.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Custom Reports");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }
}
