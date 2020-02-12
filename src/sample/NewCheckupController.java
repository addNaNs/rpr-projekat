package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class NewCheckupController {
    public Label labelSelected;
    public CheckBox cbBrake, cbSteering, cbLighting, cbEngine, cbElectric;

    private Vehicle vehicle;

    public NewCheckupController() {
        vehicle = null;
    }

    @FXML
    public void initialize() {
        labelSelected.setText("Please select a vehicle");
    }

    public void actionNewVehicle(ActionEvent actionEvent){
        //dodaj novo TODO
        return;
    }

    public void actionOldVehicle(ActionEvent actionEvent){
        //dodaj novo TODO
        return;
    }

    public void clickCancel(ActionEvent actionEvent){
        vehicle = null;
        Stage stage = (Stage) labelSelected.getScene().getWindow();
        stage.close();
    }

    public void clickOk(javafx.event.ActionEvent actionEvent) {
        if (vehicle == null){
            return;
        }

        Stage stage = (Stage) labelSelected.getScene().getWindow();
        stage.close();
    }

    public Vehicle getVehicle(){return vehicle;}


}
