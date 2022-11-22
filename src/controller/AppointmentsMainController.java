package controller;

import helper.AppointmentDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AppointmentsMainController implements Initializable {
    /* @FXML
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

     */
    @FXML
    private TextField EnterApptIDTxt;

    String getTextField() {
        return EnterApptIDTxt.getText();
    }
private Parent root;
    //static public int id = 0;
    @FXML
    void GoToAppointmentsUpdate(ActionEvent event) throws IOException,SQLException {
        //id  = Integer.parseInt(EnterApptIDTxt.getText());
        String variable = EnterApptIDTxt.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AppointmentsUpdate.fxml"));
        Stage stage = new Stage();
        root = loader.load();
        AppointmentUpdateController controller = loader.getController();
        AppointmentDAOImpl appointmentDAOimpl = new AppointmentDAOImpl();
        Appointment appointment = appointmentDAOimpl.get(Integer.parseInt(variable));

        controller.setCustomerIDTxt(String.valueOf(appointment.getCustomerId()));

       /* try {
            Appointment autoFillAppointmentObject = new Appointment();
            autoFillAppointmentObject = appointmentDAOimpl.get(4);
            //appointmentDAOimpl.get()
            controller.setCustomerIDTxt(String.valueOf(autoFillAppointmentObject.getCustomerId()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }*/
        //controller.setTypeTxt(variable);


                //Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentsUpdate.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add Appointments");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();

    }

   /* @FXML
    void SearchByCustomerName(ActionEvent event) {
        JDBC.openConnection();
        String sql = "Select * From appointments Where Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, enterNameTxt.getText());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customer_IDFK = rs.getInt("Customer_ID");
            String description = rs.getString("Description");
            String title = rs.getString(("Title"));
            System.out.println(customer_IDFK);
            System.out.println(description);
            System.out.println(title);

        }
    }
*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //    appointmentTable.setItems(ListManager.appointmentList);

    }

}