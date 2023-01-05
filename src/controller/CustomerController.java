package controller;


import helper.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Countries;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
/**
 * This controller class handles logic that adds functionality to the Main Customer window.
 */
public class CustomerController implements Initializable {
    @FXML
    private TextField enterCustIDTxt;


    @FXML
    void GoToCustomersUpdate(ActionEvent event) throws IOException, SQLException {
        CountryDAOImpl countryDaoimpl = new CountryDAOImpl();
        FirstLevelDivisionDAOImpl firstDaoimpl = new FirstLevelDivisionDAOImpl();


        String custToModify = enterCustIDTxt.getText();
        CustomerDAOImpl custModifyDao = new CustomerDAOImpl();
        Customer customer = custModifyDao.get(Integer.parseInt(custToModify));


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerAdd.fxml"));
        Parent root = loader.load();
        CustomerAddController custAddController = loader.getController();

        custAddController.setCustID(String.valueOf(customer.getCustomerID()));
        custAddController.setName(customer.getCustomerName());
        custAddController.setAddress(customer.getCustomerAddress());
        custAddController.setPostal(String.valueOf(customer.getPostalCode()));
        custAddController.setPhone(String.valueOf(customer.getCustomerPhone()));
        custAddController.setSelectCountryCombo(countryDaoimpl.get(customer.getCountry()));
        custAddController.setSelectDivisionCombo((firstDaoimpl.get(customer.getDivisionID())));

        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Customer");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }
    @FXML
    void GoToCustomersAdd(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerAdd.fxml"));
        Parent root = loader.load();
        CustomerAddController cac = loader.getController();


        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Customer Add/Update");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

        cac.setCustID("ID is auto generated");
        cac.DisableDelete();
    }
    @FXML
    void GoToCustomersView(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomersView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Customer ");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }



    @FXML
    void GoBackToMain(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome Screen");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    /**  No additional logic is used in the intialize method.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
