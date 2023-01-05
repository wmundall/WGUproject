package controller;

import helper.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Countries;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
/**
 * This controller class handles logic that adds functionality to the Customer Browser window.
 */
public class CustomersViewController implements Initializable {
    @FXML
    private TableColumn<Customer, String> addressCol;

    @FXML
    private TableView<Customer> customerTable;


    @FXML
    private TableColumn<Customer,LocalDateTime > createDateCol;

    @FXML
    private TableColumn<Customer, String> createdByCol;

    @FXML
    private TableColumn<Customer, Integer> customerIDCol;

    @FXML
    private TableColumn<Customer, Integer> divisionIDCol;

    @FXML
    private TableColumn<Customer, LocalDateTime> lastUpdateCol;

    @FXML
    private TableColumn<Customer, String> lastUpdatedByCol;

    @FXML
    private TableColumn<Customer, String> nameCol;

    @FXML
    private TableColumn<Customer, String> phoneCol;

    @FXML
    private TableColumn<Customer, String> postalCodeCol;
    @FXML
    private Label errorLbl;
    @FXML
    void BackToCustomers(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Customer");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
    @FXML
    void GoToCustomerModify(ActionEvent event) throws SQLException, IOException {

        CountryDAOImpl countryDaoimpl = new CountryDAOImpl();
        FirstLevelDivisionDAOImpl firstDaoimpl = new FirstLevelDivisionDAOImpl();

        Customer customer = customerTable.getSelectionModel().getSelectedItem();


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerAdd.fxml"));
        Parent root = loader.load();
        CustomerAddController custAddController = loader.getController();


        try {
            custAddController.setCustID(String.valueOf(customer.getCustomerID()));
            custAddController.setName(customer.getCustomerName());
            custAddController.setAddress(customer.getCustomerAddress());
            custAddController.setPostal(String.valueOf(customer.getPostalCode()));
            custAddController.setPhone(String.valueOf(customer.getCustomerPhone()));
            custAddController.setSelectCountryCombo(countryDaoimpl.get(customer.getCountry()));
            custAddController.setSelectDivisionCombo((firstDaoimpl.get(customer.getDivisionID())));
        } catch (NullPointerException e) {
            errorLbl.setVisible(true);
            errorLbl.setText("Select Customer before trying to modify");
            return;
        }

        Stage stage = new Stage();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Customer");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }
    @FXML
    void goToSpecialQueryWindow(ActionEvent event) throws IOException {Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerSpecialReport.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Custom Reports");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    /**This method is used to configure the CustomersView.fxml. Upon opening the Customer Browser Window, the customerTable tableview
     * is populated with Customer Objects via a database call to customers table.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CustomerDAOImpl custTableFiller = new CustomerDAOImpl();

        try {ListManager.customerList.removeAll(ListManager.customerList);
            custTableFiller.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        customerTable.setItems(ListManager.customerList);

        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        divisionIDCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

    }
}


