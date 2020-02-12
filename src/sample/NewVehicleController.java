package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class NewVehicleController {

    public TextField fieldPlates;
    private Vehicle vehicle;
    private Customer owner;

    public NewVehicleController() {
        vehicle = null;
    }

    @FXML
    public void initialize() {
    }

    public void actionNewOwner(ActionEvent actionEvent){

    }

    public void actionOldOwner(ActionEvent actionEvent){
        try {
            Stage stage = new Stage();
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/singleList.fxml"));
            CustomersController customersController = new CustomersController();
            loader.setController(customersController);
            root = loader.load();
            stage.setTitle("Izaberite mušteriju");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                if (customersController.getPerson() != null) {
                    owner = (Customer) customersController.getPerson();
                }
            } );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickCancel(ActionEvent actionEvent){
        vehicle = null;
        Stage stage = (Stage) fieldPlates.getScene().getWindow();
        stage.close();
    }

    public void clickOk(javafx.event.ActionEvent actionEvent) {
        if (vehicle == null){
            return;
        }

        Stage stage = (Stage) fieldPlates.getScene().getWindow();
        stage.close();
    }

    public Vehicle getVehicle(){return vehicle;}
}
