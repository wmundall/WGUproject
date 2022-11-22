package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeScreenController implements Initializable {
    @FXML
    private Button goToAppointmentsButton;

    @FXML
    private Button goToCustomerButton;

    @FXML
    void GoToAppointmentsMain(ActionEvent event) throws IOException {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsMain.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("Customer");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
    }

    @FXML
    void GoToCustomers(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Customer");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
