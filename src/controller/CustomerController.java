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
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {


    @FXML
    void AddCustomer(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        System.out.println("add");
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerAdd.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Customer");
        stage.setScene(new Scene(root, 800, 400));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
}
