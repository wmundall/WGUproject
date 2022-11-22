package controller;

import helper.JDBC;
import helper.Query;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TimeZone;


public class SignInScreenController extends JDBC implements Initializable {

    @FXML
    private Button button;

    @FXML
    private AnchorPane userID;

    @FXML
    private TextField userNameTxt;


    @FXML
    private TextField userPasswordTxt;

    @FXML
    private Label ZnID;

    @FXML
    private Label PasswordLabel;

    @FXML
    private Label userLabel;

    private String userName;
    private String password;


    /*@FXML
    void onActionButtonPress(ActionEvent event) {
        Scanner scanner = new Scanner(System.in);
        //scanner = userNameTxt;
        System.out.println("button working");
    }*/
    @FXML
    void parseUserInputAndPassword(ActionEvent event) {
        try {
            openConnection();
            Query.Select(userNameTxt.getText(), userPasswordTxt.getText());
            SwitchToWelcomeScreen(event);
            //FXMLLoader.load(getClass().getResource(""))
            // System.out.println(userName);
            // System.out.println(password);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }

    public void SwitchToWelcomeScreen(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/WelcomeScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Welcome Screen");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Locale.setDefault(Locale.FRANCE);// for demo purposes only
        ResourceBundle rb = ResourceBundle.getBundle("main/LanguageSupport_fr", Locale.getDefault());
        System.out.println("default locale is  " + Locale.getDefault());

        if (Locale.getDefault().getLanguage().equals("fr")) {
            System.out.println(rb.getString("Hello"));
            System.out.println(rb.getString("World"));
            userLabel.setText(rb.getString("UserName"));
            PasswordLabel.setText(rb.getString("Password"));
        }


        ZnID.setText("Your Zone ID is " + ZoneId.of(TimeZone.getDefault().getID()));


    }


    //public void onAction(){}
}
