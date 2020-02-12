package sample;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;


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
        return;
    }

    public void actionOldVehicle(ActionEvent actionEvent){
        try {
            Stage stage = new Stage();
            Parent root = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/singleList.fxml"));
            VehiclesController vehiclesController = new VehiclesController();
            loader.setController(vehiclesController);
            root = loader.load();
            stage.setTitle("Izaberite vozilo");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                if (vehiclesController.getVehicle() != null) {
                    vehicle = vehiclesController.getVehicle();
                    labelSelected.setText("Izabrali ste:" + vehicle);
                }
            } );

        } catch (IOException e) {
            e.printStackTrace();
        }
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
