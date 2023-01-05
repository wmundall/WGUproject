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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * This controller class handles logic that adds functionality to the Appointment update window. Text field setting methods are declared in this class but made
 * available to the AppointmentMainController class and AppointmentViewController class so that Appointment objects generated in
 * those classes can be used in this class.
 */
public class AppointmentUpdateController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private DatePicker startDatePicker;

    @FXML
    private ComboBox<LocalTime> timeCombo;
    @FXML
    private ComboBox<LocalTime> endTimeCombo;

    @FXML
    private ComboBox<Contact> contactComboBox;

    @FXML
    private Button DeleteBtn;

    @FXML
    private TextField appointmentIDTxt;

    @FXML
    private TextField customerIDTxt;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private TextField locationTxt;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private TextField userIdTxt;

    @FXML
    private TextField finalStartDisplayTxt;
    @FXML
    private TextField finalEndDisplayTxt;
    @FXML
    private Button startResetBtn;
    @FXML
    private Label inputErrorLabel;
    @FXML
    private Button saveBtn;
    @FXML
    private Label saveSuccessLbl;


    private static Boolean addOrUpdateBool = true;

    /**
     * This method is used to set Boolean value. Whenever the user is sent to the Add/Update form, the addOrUpdate bool
     * will be set. A boolean param set to true will be used when adding a new Appointment and false will be used when
     * updating an existing Appointment. Setting the addOrUpdate boolean changes characteristics of the form such as
     * requiring a customerID input first when saving a new Appointment and inserting vs updating database call upon pressing
     * the save Button.
     *
     * @param bool the boolean parameter used to set addOrUpdateBool to true or false.
     */
    public static void setAddingAppointmentBool(Boolean bool) {
        addOrUpdateBool = bool;
    }

    /**
     * This method returns the addOrUpdateBool value.
     *
     * @return the addOrUpdateBool boolean value.
     */
    public static Boolean getAddingAppointmentBool() {
        return addOrUpdateBool;
    }

    DateTimeFormatter myDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm");

    ZoneId userLocalZoneID = ZoneId.of(TimeZone.getDefault().getID());
    ZonedDateTime userZoneDateTime = ZonedDateTime.of(LocalDateTime.now(), userLocalZoneID);
    Instant userInstant = userZoneDateTime.toInstant();

    ZoneId estZone = ZoneId.of("America/New_York");

    ZonedDateTime userToEastZDT = userZoneDateTime.withZoneSameInstant(estZone);

    @FXML
    void showDatePicker(MouseEvent event) {
        startDatePicker.setVisible(true);
        startDatePicker.setPromptText(finalStartDisplayTxt.getText());
        startResetBtn.setVisible(false);
    }

    /**
     * This method loads a varying list of times in the timeCombo ComboBox depending on the date selection. Upon
     * date selection, the user is provided an error message if the date selected for appointment scheduling occured in the past.
     * If the user selects the current date or a future date, then the times shown will reflect the currently available times for
     * the rest of the current date or the future date selected.  Available times shown are based off the available "open" hours in the NY time zone.
     *
     * @param event
     */
    @FXML
    void selectDate(ActionEvent event) {
           /*LocalTime currentApptStartTime = LocalDateTime.parse(finalStartDisplayTxt.getText(), myDTF).toLocalTime();
           LocalTime currentApptEndTime =  LocalDateTime.parse(finalEndDisplayTxt.getText(), myDTF).toLocalTime();*/

        startDatePicker.setVisible(false);

        if (startDatePicker.getValue().isBefore(userToEastZDT.toLocalDate())) {
            inputErrorLabel.setVisible(true);
            inputErrorLabel.setText("Invalid appointment date selection");

        } else if (startDatePicker.getValue().isEqual(userToEastZDT.toLocalDate())) {
            timeCombo.setVisible(true);
            inputErrorLabel.setVisible(false);
            GetCurrentDayAppointmentTimes(userToEastZDT);

        } else if (startDatePicker.getValue().isAfter(userToEastZDT.toLocalDate())) {
            timeCombo.setVisible(true);
            inputErrorLabel.setVisible(false);
            GetFutureDayAppointmentTimes();

        }

        if (getAddingAppointmentBool()) {
            finalStartDisplayTxt.setText(String.valueOf(startDatePicker.getValue()));
            finalEndDisplayTxt.setText(String.valueOf(startDatePicker.getValue()));
        } else {
            LocalTime currentApptStartTime = LocalDateTime.parse(finalStartDisplayTxt.getText(), myDTF).toLocalTime();
            LocalTime currentApptEndTime = LocalDateTime.parse(finalEndDisplayTxt.getText(), myDTF).toLocalTime();
            finalStartDisplayTxt.setText((startDatePicker.getValue()) + "  " + (currentApptStartTime));
            finalEndDisplayTxt.setText((startDatePicker.getValue()) + "  " + (currentApptEndTime));

        }

    }


    /**
     * This method is called by selectDate method above to load the timeCombo ComboBox. The timeCombo ComboBox will
     * be loaded with LocalTimes that reflect the remaining LocalTimes available for the current day and observes the
     * open hours constraint set by the NY time zone open hours.
     *
     * @param zdt the ZonedDateTime that reflects the user's system time adjusted into NY zone time.
     */
    void GetCurrentDayAppointmentTimes(ZonedDateTime zdt) {
        LocalTime nyHourOfClosing = LocalTime.of(22, 0);
        LocalTime userToEastLocalTime = zdt.toLocalTime();

        int userToEastHours = userToEastLocalTime.getHour();
        int userToEastModifiedMinutes = (userToEastLocalTime.getMinute() - (userToEastLocalTime.getMinute()) % 5);

        LocalTime userToEastModifiedLocalTime = LocalTime.of(userToEastHours, userToEastModifiedMinutes);


        while (userToEastModifiedLocalTime.isBefore(nyHourOfClosing)) {
            LocalTime nyToUserLocalTime = NYToUserLocalTimeConversion(userToEastModifiedLocalTime);
            timeCombo.getItems().add(nyToUserLocalTime);
            endTimeCombo.getItems().add(nyToUserLocalTime.plusMinutes(5));
            userToEastModifiedLocalTime = userToEastModifiedLocalTime.plusMinutes(5);


        }

    }

    /**
     * This method is called by selectDate method above to load the timeCombo ComboBox. The timeCombo ComboBox will
     * be loaded with all available LocalTimes from a future date and observes the
     * open hours constraint set by the NY time zone open hours.
     */
    void GetFutureDayAppointmentTimes() {

        LocalTime nyHourOfOpening = LocalTime.of(8, 0);
        LocalTime nyHourOfClosing = LocalTime.of(22, 0);


        while (nyHourOfOpening.isBefore(nyHourOfClosing)) {
            LocalTime nyToUserTime = NYToUserLocalTimeConversion(nyHourOfOpening);
            timeCombo.getItems().add(nyToUserTime);
            nyHourOfOpening = nyHourOfOpening.plusMinutes(5);
        }

    }

    /**
     * This method converts a LocalTime based off "New York' zone id to a LocalTime based off the User's system zone id.
     *
     * @param nyLocal the LocalTime that will be converted.
     * @return
     */
    LocalTime NYToUserLocalTimeConversion(LocalTime nyLocal) {
        LocalTime nyToUserLocalTime = nyLocal.atDate(LocalDate.now()).atZone(estZone).withZoneSameInstant(userLocalZoneID).toLocalTime();
        return nyToUserLocalTime;
    }

    /**
     * This method provides logic to prevent scheduling time overlapping appointments.  Upon time selection, the user will
     * be provided an error message if the selection results in a time overlap.  Time overlaps are determined by collecting the
     * appointments that occur for a given customer and date into ListManager.appointmentOccupiedList. After removing the appointment
     * that is currently being modified from this list, the start times of each appointment in the list are compared to the selected start time
     * from the Combo Box. If no conflict exists, the TextField showing the start time is populated with the selected time and date.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void selectTime(ActionEvent event) throws SQLException {
        startDatePicker.setVisible(false);
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        ArrayList<LocalTime> currentlySelectedAppointmenttimesList = new ArrayList<>();

        if (!getAddingAppointmentBool()) {
            LocalTime currentStartTime = LocalDateTime.parse(finalStartDisplayTxt.getText(), myDTF).toLocalTime();
            LocalTime currentEndTime = LocalDateTime.parse(finalEndDisplayTxt.getText(), myDTF).toLocalTime();


            while (currentStartTime.isBefore(currentEndTime)) {
                currentlySelectedAppointmenttimesList.add(currentStartTime);
                currentStartTime = currentStartTime.plusMinutes(5);
            }
            currentlySelectedAppointmenttimesList.add(currentEndTime);

        }
        if (getAddingAppointmentBool()) {
            if (customerIDTxt.getText().isEmpty()) {
                inputErrorLabel.setVisible(true);
                inputErrorLabel.setWrapText(true);
                inputErrorLabel.setText(" Customer ID input needed to check available times");
                return;
            }
        }
        LocalTime startTimeSelection = timeCombo.getValue();
        LocalTime endTimeSelection = endTimeCombo.getValue();

        ListManager.appointmentOccupiedList.removeAll(ListManager.appointmentOccupiedList);

        ListManager.appointmentOccupiedList = appointmentDAO.getFilledAppointmentsbyDate(startDatePicker.getValue(), Integer.parseInt(customerIDTxt.getText()));

        if (!getAddingAppointmentBool()) {
            ListManager.appointmentOccupiedList.removeIf(x -> x.getAppointmentID() == Integer.parseInt(appointmentIDTxt.getText()));
        }

        for (Appointment a : ListManager.appointmentOccupiedList) {

            if (startTimeSelection.compareTo(a.getStart().toLocalTime()) >= 0 & startTimeSelection.compareTo(a.getEnd().toLocalTime()) < 0) {
                inputErrorLabel.setVisible(true);
                inputErrorLabel.setText("This time is unavailable.");
            } else {
                endTimeCombo.setVisible((true));
                inputErrorLabel.setVisible(false);

                LocalTime appointmentStartTime = timeCombo.getValue();
                LocalTime nyCloseTimeAdjusted = NYToUserLocalTimeConversion(LocalTime.of(22, 0));

                while (appointmentStartTime.isBefore(nyCloseTimeAdjusted)) {

                    endTimeCombo.getItems().add(appointmentStartTime.plusMinutes(5));
                    appointmentStartTime = appointmentStartTime.plusMinutes(5);
                }

                finalStartDisplayTxt.setText(LocalDateTime.of(startDatePicker.getValue(), timeCombo.getValue()).format(myDTF));

                timeCombo.setVisible(false);


            }

        }
    }

    /**
     * This method provides logic to prevent scheduling time overlapping appointments.  Upon time selection, the user will
     * be provided an error message if the selection results in a time overlap.  Time overlaps are determined by collecting the
     * appointments that occur for a given customer and date into ListManager.appointmentOccupiedList. After removing the appointment
     * that is currently being modified from this list, the end times of each appointment in the list are compared to the selected end time
     * from the Combo Box and an error message is displayed if an overlap exists.
     * If no conflict exists, the TextField showing the end time is populated with the selected time and date.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void selectEndTime(ActionEvent event) throws SQLException {

        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();
        LocalTime endTimeSelection = endTimeCombo.getValue();
        LocalTime startTimeSelection = timeCombo.getValue();

        for (Appointment appointment : ListManager.appointmentOccupiedList) {

            if (endTimeSelection.compareTo(appointment.getStart().toLocalTime()) > 0 & endTimeSelection.compareTo(appointment.getEnd().toLocalTime()) <= 0) {
                inputErrorLabel.setVisible(true);
                inputErrorLabel.setText("This time is unavailable.");
            } else if (startTimeSelection.compareTo(appointment.getStart().toLocalTime()) < 0 & endTimeSelection.compareTo(appointment.getEnd().toLocalTime()) > 0) {
                inputErrorLabel.setVisible(true);
                inputErrorLabel.setText("This time is unavailable.");
            } else {
                finalEndDisplayTxt.setText(startDatePicker.getValue() + "  " + endTimeCombo.getValue().toString());
                endTimeCombo.setVisible(false);
                inputErrorLabel.setVisible(false);
                startResetBtn.setVisible(true);
            }
        }
    }


    @FXML
    void resetDatePicker(ActionEvent event) {

        startDatePicker.setDisable(false);
        startDatePicker.setVisible(true);
        timeCombo.setVisible(true);
        startResetBtn.setVisible(false);

    }


    /**
     * This method updates an existing appointment, or adds a new appointment to database. On pressing button(save),
     * an appointment will be added/updated depending on the value in the appointmentIDTxt TextField. User input validation
     * logic is provided for TextFields ensuring proper String values are provided for TextFields requiring Strings and int values
     * are provided for TextFields requiring int values.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void AddOrModifyAppointmentToDB(ActionEvent event) throws SQLException {

        AppointmentDAOImpl apptDAOImplForAdd = new AppointmentDAOImpl();
        Appointment appointment = new Appointment();

        try {
            if (titleTxt.getText() == null || descriptionTxt.getText() == null || locationTxt.getText() == null || typeTxt.getText() == null) {
                throw new NullPointerException();
            } else if (titleTxt.getText().trim().isEmpty() || descriptionTxt.getText().trim().isEmpty() ||
                    locationTxt.getText().trim().isEmpty() || typeTxt.getText().trim().isEmpty()) {
                throw new InputMismatchException();
            } else {
                appointment.setTitle(titleTxt.getText());
                appointment.setDescription(descriptionTxt.getText());
                appointment.setLocation(locationTxt.getText());
                appointment.setType(typeTxt.getText());
            }

        } catch (NullPointerException e) {
            inputErrorLabel.setVisible(true);
            inputErrorLabel.setText("Please provide values for all fields");
            return;
        } catch (InputMismatchException e) {
            inputErrorLabel.setVisible(true);
            inputErrorLabel.setText("input error");
        }


        try {
            appointment.setStart(LocalDateTime.parse(finalStartDisplayTxt.getText(), myDTF));
            appointment.setEnd(LocalDateTime.parse(finalEndDisplayTxt.getText(), myDTF));
            appointment.setContactId(contactComboBox.getSelectionModel().getSelectedItem().getContactID());
        } catch (NullPointerException | DateTimeParseException e) {
            inputErrorLabel.setVisible(true);
            inputErrorLabel.setText("Please provide values for all fields");
            return;
        }


        try {
            appointment.setUserId(Integer.parseInt(userIdTxt.getText()));
            appointment.setCustomerId(Integer.parseInt(customerIDTxt.getText()));

        } catch (NumberFormatException nfe) {


            inputErrorLabel.setVisible(true);
            inputErrorLabel.setText("Please provide integer value");
            return;
        }


        try {
            if (getAddingAppointmentBool()) {

                apptDAOImplForAdd.insert(appointment);
                inputErrorLabel.setVisible(false);
                saveBtn.setDisable(true);
                saveSuccessLbl.setVisible(true);
                saveSuccessLbl.setText("Save successful, please press back to exit page");

            } else {

                appointment.setAppointmentID(Integer.parseInt(appointmentIDTxt.getText()));
                apptDAOImplForAdd.update(appointment);
                inputErrorLabel.setVisible(false);
                saveBtn.setDisable(true);
                saveSuccessLbl.setVisible(true);
                saveSuccessLbl.setText("Update successful, please press back to exit page");

            }
        } catch (SQLException e) {
            inputErrorLabel.setVisible(true);
            inputErrorLabel.setText("Observe Foreign Key Constraints");
        }


    }

    @FXML
    void ResetErrorMessage(MouseEvent event) {
        if (inputErrorLabel.isVisible() == true)
            inputErrorLabel.setVisible(false);
    }

    @FXML
    void ResetErrorMessage1(MouseEvent event) {
        if (inputErrorLabel.isVisible())
            inputErrorLabel.setVisible(false);
    }

    /**
     * This method is called on pressing button(Back). Upon press, the user will be directed back to the Appointments window.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void BackToAppointmentsMain(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsMain.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Appointments");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    /**
     * This method deletes an appointment record from the appointments table in the database. The appointment deleted
     * has a field that matches the appoinmentIDTxt TextField in this Window.  Alerts are provided to ensure the user's intentions.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    void OnDelete(ActionEvent event) throws SQLException {
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();

        Alert askAlert = new Alert(Alert.AlertType.CONFIRMATION);
        askAlert.setTitle("Cancel Appointment Confirmation");
        askAlert.setContentText("Are you sure you want to Cancel Appointment");

        Optional<ButtonType> result = askAlert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Appointment appointment = appointmentDAO.get(Integer.parseInt(appointmentIDTxt.getText()));
            try {
                appointmentDAO.delete(appointment);
            } catch (NullPointerException e) {
                inputErrorLabel.setText("This appointment does not exist");
                inputErrorLabel.setVisible(true);
                return;
            }

            Alert remindAlert = new Alert(Alert.AlertType.INFORMATION, "Appointment ID: " + appointment.getAppointmentID() +
                    " type: " + appointment.getType() + " is cancelled");

            remindAlert.setTitle("Appointment Cancelled");
            remindAlert.setHeaderText("Cancelled!");
            Optional<ButtonType> responce = remindAlert.showAndWait();

        }
    }

    /**
     * This method is used to set the appointmentIDTxt text field.
     *
     * @param a
     */
    public void setApptIDTxt(String a) {
        appointmentIDTxt.setText(a);
    }

    /**
     * This method is used to set the typeTxt text field.
     *
     * @param a
     */
    public void setTypeTxt(String a) {
        typeTxt.setText(a);
    }

    /**
     * This method is used to set the titleTxt text field.
     *
     * @param a
     */
    public void setTitleTxt(String a) {
        titleTxt.setText(a);
    }

    /**
     * This method is used to set the descriptionTxt text field.
     *
     * @param a
     */
    public void setDescriptionTxt(String a) {
        descriptionTxt.setText(a);
    }

    /**
     * This method is used to set the locationTxt text field.
     *
     * @param a
     */
    public void setLocationTxt(String a) {
        locationTxt.setText(a);
    }

    /**
     * This method is used to set the finalStartDisplayTxt text field.
     *
     * @param a
     */
    public void setFinalStartDisplayTxt(String a) {
        finalStartDisplayTxt.setText(a);
    }

    /**
     * This method is used to set the finalEndDisplayTxt text field.
     *
     * @param a
     */
    public void setFinalEndDisplayTxt(String a) {
        finalEndDisplayTxt.setText(a);
    }

    /**
     * This method is used to set the customerIDTxt text field.
     *
     * @param a
     */
    public void setCustomerIDTxt(String a) {
        customerIDTxt.setText(a);
    }

    /**
     * This method is used to set the userIdTxt text field.
     *
     * @param a
     */
    public void setUserIdTxt(String a) {
        userIdTxt.setText(a);
    }

    /**
     * This method is used to set the contactComboBox ComboBox.
     *
     * @param c
     */
    public void setContactComboBox(Contact c) {
        contactComboBox.setValue(c);
    }

    public void HideDelete() {
        DeleteBtn.isDisable();
        DeleteBtn.setVisible(false);
    }

    /**
     * This method is used to clear the error Label. Upon clicking the text field where the user may have provided improper
     * input, the error label visibility is set to false.
     *
     * @param event
     */
    @FXML
    void reset(MouseEvent event) {
        inputErrorLabel.setVisible(false);
    }

    /**
     * This method is used to clear the error Label. Upon clicking the text field where the user may have provided improper
     * input, the error label visibility is set to false.
     *
     * @param event
     */
    @FXML
    void reset1(MouseEvent event) {
        inputErrorLabel.setVisible(false);
    }

    /**
     * This method is used to clear the error Label. Upon clicking the text field where the user may have provided improper
     * input, the error label visibility is set to false.
     *
     * @param event
     */
    @FXML
    void reset2(MouseEvent event) {
        inputErrorLabel.setVisible(false);
    }

    /**
     * This method is used to clear the error Label. Upon clicking the text field where the user may have provided improper
     * input, the error label visibility is set to false.
     *
     * @param event
     */
    @FXML
    void reset3(MouseEvent event) {
        inputErrorLabel.setVisible(false);
    }

    /**
     * This method is used to configure the AppointmentUpdate.fxml. Upon opening the Update Appointment Window,
     * the contactComboBox will be filled with Contact objects with a database call to the contacts table.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        AppointmentDAOImpl a = new AppointmentDAOImpl();
        ContactDaoImpl contactDao = new ContactDaoImpl();
        try {
            ListManager.contactList.removeAll(ListManager.contactList);
            contactDao.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        contactComboBox.setItems(ListManager.contactList);


    }
}
