package controller;

import helper.JDBC;
import helper.ListManager;
import javafx.beans.value.ObservableObjectValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Countries;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerAddController implements Initializable {
    @FXML
    private TableColumn<Customer, Integer> ID;

    @FXML
    private TableColumn<Customer, String> address;

    @FXML
    private TableColumn<Customer, LocalDateTime> createDate;

    @FXML
    private TableColumn<Customer, String> createdBy;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, Integer> divisionID;

    @FXML
    private TableColumn<Customer, LocalDateTime> lastUpdate;

    @FXML
    private TableColumn<Customer, String> lastUpdatedBy;

    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, String> phone;

    @FXML
    private TableColumn<Customer, String> postalCode;

    @FXML
    private ComboBox<Countries> countryCombo;

    @FXML
    private TextField textfield;


    @FXML
    void GoBack(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Customer");
        stage.setScene(new Scene(root, 400, 400));
        stage.show();

    }


    @FXML
    void SelectCountry(MouseEvent event)throws Exception {
        JDBC.openConnection();
        String sql = "Select * From countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            int countryID = rs.getInt("Country_ID");
            //System.out.println("I'm " + countryID);
            String country = rs.getString("Country");
            LocalDate createDate = rs.getDate("Create_Date").toLocalDate();
            String createdBy = rs.getString("Created_By");
            LocalDateTime lastUpdate = rs.getTimestamp("Last_Update").toLocalDateTime();
            String lastUpdatedBy = rs.getString("Last_Updated_By");

            Countries countryObject = new Countries(countryID, country, createDate, createdBy, lastUpdate, lastUpdatedBy);

            ListManager.countryList.add(countryObject);
            System.out.println("here " + countryObject.toString());
        }
        countryCombo.setItems(ListManager.countryList);
    }






       // countryCombo.setItems(ListManager.countryList





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerTable.setItems(ListManager.customerList);
        ID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        address.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        createDate.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdBy.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        divisionID.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        lastUpdate.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        lastUpdatedBy.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        postalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));


        //Countries country = new Countries(3, "taiwan", LocalDate.now(), "me", LocalDateTime.now(), "you");
        //ListManager.countryList.add(country);


        textfield.setText("set in Initialize");


    }
}


