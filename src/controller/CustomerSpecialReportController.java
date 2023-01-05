package controller;

import helper.CountryDAOImpl;
import helper.CustomerDAOImpl;
import helper.FirstLevelDivisionDAOImpl;
import helper.ListManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Countries;
import model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
/**
 * This controller class handles logic that adds functionality to the Customer Special Report window.
 */
public class CustomerSpecialReportController implements Initializable {

    @FXML
    private ComboBox<Countries> countryCombo;

    @FXML
    private Tooltip moreInfoTT;

    @FXML
    private ComboBox<FirstLevelDivision> divisionCombo;
    @FXML
    private Label resultLbl;

    @FXML
    private Label descriptionLbl;

    @FXML
    void SetCountryCombo(ActionEvent event) {

    }

    /** -- Lambda usage here.This method displays the result of Misc. Report #1.  Upon selection of first level division, the user
     * will be shown the int result of the report.  After collecting all the customers in the list, the lamda usage in the stream
     * statement will sum the number of customers that fulfill the predicate statement of the filter.
     * @param event
     * @throws SQLException
     */
    @FXML
    void displayResult(ActionEvent event) throws SQLException {

        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        ListManager.customerList.removeAll(ListManager.customerList);
        customerDAO.getAll();
        long result = 0;
        try {
            result = ListManager.customerList.stream().filter(x -> x.getDivisionID() == divisionCombo.getValue().getDivisionId()).count();
        } catch (Exception e) {

        }
        resultLbl.setText(String.valueOf(result));
    }
    @FXML
    void OnCountryComboSelect(ActionEvent event) throws SQLException {
        FirstLevelDivisionDAOImpl firstLevelDivisionDAO = new FirstLevelDivisionDAOImpl();

        int country = 0;
        try {
            country = countryCombo.getValue().getCountryId();
        } catch (Exception e) {

        }

        ListManager.firstLevelDivisionsList.removeAll(ListManager.firstLevelDivisionsList);
        firstLevelDivisionDAO.getAll(country);

        divisionCombo.setItems(ListManager.firstLevelDivisionsList);


    }
    @FXML
    void goBack(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Customer");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }

    /**This method is used to configure the CustomerSpecialReport.fxml. Upon opening the Custom Reports Window, the countryCombo Combo Box
     * is populated with Countries objects that are retrieved via database call.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountryDAOImpl countryDAO = new CountryDAOImpl();
        try {
            ListManager.countryList.removeAll(ListManager.countryList);
            countryDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        countryCombo.setItems(ListManager.countryList);
    }
}
