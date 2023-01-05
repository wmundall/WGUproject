package controller;

import helper.AppointmentDAOImpl;
import helper.ContactDaoImpl;
import helper.ListManager;
//import helper.MonthOfYear;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
/**
 * This controller class handles logic that adds functionality to the Appointment Special Report window.
 */
public class AppointmentSpecialReportController implements Initializable {
 /*   @FXML
    private ComboBox<Month> monthCombo;*/

    @FXML
    private ComboBox<Month> monthCombo;
    @FXML
    private ComboBox<String> typeCombo;
    @FXML
    private ComboBox<Contact> contactCombo;

    @FXML
    private Label descriptionLbl;
    @FXML
    private Label descriptionLbl2;
    @FXML
    private Label label;
    @FXML
    private TextField resultTxt;
    @FXML
    private TableView<Appointment> contactscheduleTbl;
    @FXML
    private TableColumn<Appointment, Integer> apptID;

    @FXML
    private TableColumn<Appointment, Integer> customerID;

    @FXML
    private TableColumn<Appointment, String> description;

    @FXML
    private TableColumn<Appointment, String> end;

    @FXML
    private TableColumn<Appointment, String> start;
    @FXML
    private TableColumn<Appointment, String> title;

    @FXML
    private TableColumn<Appointment, String> type;
    @FXML
    private Label clearLbl;
    @FXML
    private Tooltip moreInfoTT;

    @FXML
    private Tooltip moreInfoTT1;
    @FXML
    void setToolTip(DragEvent event) {

    }

    /**  This method generates and populates a tableview that shows appointments by contact. Upon selecting a contact from contactCombo ComboBox,
     * a tableview will be populated with appointments whose contact name matches the ComboBox selection.
     * @param event
     * @throws SQLException
     */
    @FXML
    void getAppointmentsByContact(ActionEvent event) throws SQLException {
        ListManager.appointmentList.removeAll(ListManager.appointmentList);

        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        appointmentDAO.GetAppointmentsByContactName(contactCombo.getValue());

        contactscheduleTbl.setItems(ListManager.appointmentList);

        apptID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        contactscheduleTbl.setVisible(true);
        clearLbl.setVisible(true);

    }

    /** This method clears the contactscheduleTbl TableView.  Upon clicking the AnchorPane, the tableview visibility will be set to false.
     * @param event
     */
    @FXML
    void clearApptTable(MouseEvent event) {
        contactscheduleTbl.setVisible(false);
        clearLbl.setVisible(false);
    }

    /** This method populated the typeCombo ComboBox with Appointment types. Upon monthCombo selection, the typeCombo ComboBox will
     * be populated with the types of Appointments that are scheduled in selected month. The types are collected via the Appointment class getType method.
     * @param event
     * @throws SQLException
     */
    @FXML
    void SetTypeCombo(ActionEvent event) throws SQLException {
        LocalDateTime today = LocalDateTime.now();
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();

        ListManager.monthAppointmentList.removeAll(ListManager.monthAppointmentList);



        if (monthCombo.getValue().getValue() >= today.getMonthValue()) {
           ListManager.monthAppointmentList = appointmentDAO.getMonthApptList(LocalDateTime.of(today.getYear(), monthCombo.getValue(), 01, 0, 0));

        } else {
            ListManager.monthAppointmentList = appointmentDAO.getMonthApptList(LocalDateTime.of(today.getYear() + 1, monthCombo.getValue(), 1, 0, 0));                                          //monthList.add(LocalDateTime.of(today.getYear() + 1, m.getValue(), 1, 0, 0));

        }

        List<String> titleList = ListManager.monthAppointmentList.stream()
                                            .map(Appointment::getType)
                                            .distinct()
                                            .collect(Collectors.toList());

        ObservableList<String> obsFilteredTitleList = FXCollections.observableList(titleList);

        typeCombo.setItems(obsFilteredTitleList);
    }

    /** This method returns a sum of appointments that are aggregated by month and appointmentType.  Upon type selection
     * the sum will reflect how many appointments occur with the given selections in the monthCombo and typecombo ComboBoxes.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void displayResult(ActionEvent event) throws SQLException {
        int result;
        LocalDateTime today = LocalDateTime.now();
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        if (monthCombo.getValue().getValue() >= today.getMonthValue()) {
             result = appointmentDAO.GetAppointmentCountByMonthAndType(LocalDateTime.of(today.getYear(), monthCombo.getValue(), 01, 0, 0),typeCombo.getValue());

        } else {
             result = appointmentDAO.GetAppointmentCountByMonthAndType(LocalDateTime.of(today.getYear() + 1, monthCombo.getValue(), 1, 0, 0),typeCombo.getValue());
        }
        resultTxt.setText(String.valueOf(result));
    }

    /** This method is called on button(Back) press. Upon press, the user will be directed back to the Appointments Browser
     * Window
     * @param event
     * @throws IOException
     */
    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appointments Browser");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }

    /**This method is used to configure the SpecialQueryWindow.fxml. Upon opening the Custom Reports Window, monthCombo Combobox and contactCombo Comboboxes
     * are populated with month objects and contact objects respectively.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moreInfoTT.setFont(Font.font(12.0));
        moreInfoTT.setText("This report returns the number of appointments by month and type." +
                " Only Appointment Types that are currently stored in database for the given month are shown.");
        moreInfoTT.setHideDelay(Duration.seconds(3.0));
        descriptionLbl.setTooltip(moreInfoTT);

        moreInfoTT1.setFont(Font.font(13.0));
        moreInfoTT1.setText("This report shows a schedule of appointments by Contact Name");
        moreInfoTT1.setHideDelay(Duration.seconds(3.0));
        descriptionLbl2.setTooltip(moreInfoTT1);



        ObservableList<Month> monthList = FXCollections.observableArrayList();

        for (Month m : Month.values())  {
            monthList.add(m);
        }

        monthCombo.setItems(monthList);


        ContactDaoImpl contactDao = new ContactDaoImpl();
        try {
            ListManager.contactList.removeAll(ListManager.contactList);
            contactDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        contactCombo.setItems(ListManager.contactList);
    }

}