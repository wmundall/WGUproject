package controller;

import com.sun.security.jgss.GSSUtil;
import helper.*;
import javafx.beans.value.ObservableObjectValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import model.Countries;
import model.Customer;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

/**
 * This controller class handles logic that adds functionality to the Customer update window. Text field setting methods are declared in this class but made
 *  *  available to the CustomerController class and CustomerViewController class so that customer objects generated in
 *  *  those classes can be used in this class.
 */
public class CustomerAddController implements Initializable {
    @FXML
    private Button DeleteBtn;
    @FXML
    private TextField addressTxt;

    @FXML
    private TextField createTxt;

    @FXML
    private TextField createdBytxt;

    @FXML
    private TextField custIDTxt;

    @FXML
    private TextField divisionIDTxt;

    @FXML
    private TextField lastUpdateTxt;

    @FXML
    private TextField lastUpdatedByTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private TextField postalCodeTxt;
    @FXML
    private Label inputErrorLbl;

    @FXML
    private ComboBox<Countries> selectCountryCombo;
    @FXML
    private ComboBox<FirstLevelDivision> selectDivisionCombo;


    /** This method updates an existing customer, or adds a new customer to database. On pressing button(save),
     * a customer will be added/updated depending on the value in the custIDTxt TextField. User input validation
     * logic is provided for TextFields ensuring proper String values are provided for TextFields requiring Strings.
     * @param event
     * @throws SQLException
     */
    @FXML
    void AddOrModifyCustomer(ActionEvent event) throws SQLException {
        CustomerDAOImpl customerDAOImplForAdd = new CustomerDAOImpl();

        Customer customer = new Customer();

        try {
            if (nameTxt.getText() == null || addressTxt.getText() == null || postalCodeTxt.getText() == null || phoneTxt.getText() == null)  {
                throw new NullPointerException();
            }

            else if(nameTxt.getText().trim().isEmpty() || addressTxt.getText().trim().isEmpty() ||
                    postalCodeTxt.getText().trim().isEmpty() || phoneTxt.getText().trim().isEmpty() ) {
                throw new InputMismatchException();
            }

            else   { customer.setCustomerName(nameTxt.getText());
                customer.setCustomerAddress(addressTxt.getText());
                customer.setPostalCode(postalCodeTxt.getText());
                customer.setCustomerPhone(phoneTxt.getText());
            }

        } catch (NullPointerException | InputMismatchException e) {
            inputErrorLbl.setVisible(true);
            inputErrorLbl.setText("Please provide input for all fields");
            return;
        }

        customer.setDivisionID(selectDivisionCombo.getSelectionModel().getSelectedItem().getDivisionId());

        if ((custIDTxt.getText()).equals("ID is auto generated")) {
            customerDAOImplForAdd.insert(customer);

        } else {
            customer.setCustomerID(Integer.parseInt(custIDTxt.getText()));
            customerDAOImplForAdd.update(customer);
        }

    }
    /** This method is used to clear the error Label. Upon clicking the text field where the user may have provided improper
     * input, the error label visibility is set to false.
     * @param event
     */
    @FXML
    void resetError(MouseEvent event) {
        inputErrorLbl.setVisible(false);

    }
    /** This method is used to clear the error Label. Upon clicking the text field where the user may have provided improper
     * input, the error label visibility is set to false.
     * @param event
     */
    @FXML
    void resetError1(MouseEvent event) {
       inputErrorLbl.setVisible(false);
    }
    /** This method is used to clear the error Label. Upon clicking the text field where the user may have provided improper
     * input, the error label visibility is set to false.
     * @param event
     */
    @FXML
    void resetError2(MouseEvent event) {
        inputErrorLbl.setVisible(false);

    }
    /** This method is used to clear the error Label. Upon clicking the text field where the user may have provided improper
     * input, the error label visibility is set to false.
     * @param event
     */
    @FXML
    void resetError3(MouseEvent event) {
        inputErrorLbl.setVisible(false);

    }

    /** This method populates the OnVDivisionComboSelect ComboBox with FirstLevelDivision objects. On OnCountryComboSelect selection,
     * a database call is made to populate the OnVDivisionComboSelect ComboBox with FirstLevelDivisions objects that match country given in the database call.
     * @param event
     * @throws SQLException
     */
    @FXML
    void OnCountryComboSelect(ActionEvent event) throws SQLException {
        FirstLevelDivisionDAOImpl firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();
        int country = selectCountryCombo.getSelectionModel().getSelectedItem().getCountryId();

        ListManager.firstLevelDivisionsList.removeAll(ListManager.firstLevelDivisionsList);
        firstLevelDivisionDAO.getAll(country);

        selectDivisionCombo.setItems(ListManager.firstLevelDivisionsList);

    }

    /** This method is called on pressing button(Back). Upon press, the user will be directed back to the Customers Main window.
     * @param event
     * @throws IOException
     */
    @FXML
    void BackToCustomersMain(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Customers Main");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    /** --Lambda usage here.This method deletes a customer record from the customers table in the database. The customer deleted
     * has a field that matches the custIDTxt TextField in this Window.  Alerts are provided to ensure the user's intentions.
     * This method enforces SQL foreign key contstraints by deleting appointments that have a matching customer ID.
     * @param event
     * @throws SQLException
     */
    @FXML
    void OnDelete(ActionEvent event) throws SQLException {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();

        appointmentDAO.custIDSelect(Integer.parseInt(custIDTxt.getText()));

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Customer Confirmation");
        confirmAlert.setContentText("Are you sure you want to Delete " + customerDAO.get(Integer.parseInt(custIDTxt.getText())));


        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                ListManager.appointmentsToDelete.forEach(x -> {
                    try {
                        appointmentDAO.delete(appointmentDAO.get(x));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                try {
                    customerDAO.delete(customerDAO.get(Integer.parseInt(custIDTxt.getText())));
                } catch (SQLException|NullPointerException e) {
                    inputErrorLbl.setText("This customer does not exist");
                    inputErrorLbl.setVisible(true);
                    return;
                }
            }
        });
    }

    /** This method is used to set the custIDTxt text field.
     * @param a
     */
    public void setCustID(String a) {
        custIDTxt.setText(a);
    }

    /** This method is used to set the nameTxt text field.
     * @param a
     */
    public void setName(String a) {
        nameTxt.setText(a);
    }

    /** This method is used to set the addressTxt text field.
     * @param a
     */
    public void setAddress(String a) {
        addressTxt.setText(a);
    }

    /** This method is used to set the postalCodeTxt text field.
     * @param a
     */
    public void setPostal(String a) {
        postalCodeTxt.setText(a);
    }

    /** This method is used to set the phoneTxt text field.
     * @param a
     */
    public void setPhone(String a) {
        phoneTxt.setText(a);
    }

    /** This method is used to set the selectCountryCombo ComboBox.
     * @param c
     */
    public void setSelectCountryCombo(Countries c) {
        selectCountryCombo.setValue(c);
    }

    /** This method is used to set the selectDivisionCombo ComboBox.
     * @param f
     */
    public void setSelectDivisionCombo(FirstLevelDivision f) {
        selectDivisionCombo.setValue(f);
    }

    public void DisableDelete() {
        DeleteBtn.isDisable();
        DeleteBtn.setVisible(false);
    }

    /**This method is used to configure the CustomerAdd.fxml. Upon opening the Customer Add/Update Window, the selectCountryCombo Combo Box
     * is populated with Countries objects that are retrieved via database call.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        CountryDAOImpl countryDAOimpl = new CountryDAOImpl();

        try {
            ListManager.countryList.removeAll(ListManager.countryList);
            countryDAOimpl.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        selectCountryCombo.setItems(ListManager.countryList);


    }


}

