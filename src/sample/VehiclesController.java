package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class VehiclesController {
    public ListView<Vehicle> listMain;
    public TextField fieldSearch;
    ObservableList<Vehicle> list;
    private Vehicle vehicle;

    public VehiclesController() {
    }

    @FXML
    public void initialize() {
        list = FXCollections.observableArrayList();
        RegistrationDAO.getInstance().vehicles().stream().forEach(a -> list.add(a));
        SingleListController.searchSetup(listMain, list, fieldSearch);
    }

    public void clickCancel(ActionEvent actionEvent) {
        vehicle = null;
        Stage stage = (Stage) listMain.getScene().getWindow();
        stage.close();
    }

    public void clickOk(ActionEvent actionEvent) {

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
