package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VehiclesController {
    public ListView<Vehicle> listMain;
    private Vehicle vehicle;

    public VehiclesController() {
    }

    @FXML
    public void initialize() {
        ObservableList<Vehicle> list = FXCollections.observableArrayList();
        RegistrationDAO.getInstance().vehicles().stream().forEach(a -> list.add(a));
        listMain.setItems(list);
    }

    public void clickCancel(ActionEvent actionEvent) {
        vehicle = null;
        Stage stage = (Stage) listMain.getScene().getWindow();
        stage.close();
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean allGood = true;
        if (!allGood) return;

        if (vehicle == null){
            vehicle = listMain.getSelectionModel().getSelectedItem();
        }

        if(vehicle != null) {
            Stage stage = (Stage) listMain.getScene().getWindow();
            stage.close();
        }
    }

    public Vehicle getVehicle(){
        return vehicle;
    }
}
