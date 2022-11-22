package controller;

import helper.ListManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsViewController implements Initializable {
    @FXML
    private TableColumn<?, ?> appointmentID;

    @FXML
    private TableView<Appointment> appointmentTable;

    @FXML
    private TableColumn<?, ?> contact;

    @FXML
    private TableColumn<?, ?> customerID;

    @FXML
    private TableColumn<?, ?> description;

    @FXML
    private TableColumn<?, ?> enddatetime;

    @FXML
    private TableColumn<?, ?> location;

    @FXML
    private TableColumn<?, ?> startdatetime;

    @FXML
    private TableColumn<?, ?> title;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private TableColumn<?, ?> userID;

    @FXML
    void GoToAppointments(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsMain.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Weird");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTable.setItems(ListManager.appointmentList);

    }
}
