package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;


public class NewCheckupController {
    public Label labelSelected;
    public ChoiceBox cbAssignee, cbWorkshop;
    public CheckBox cbBrake, cbSteering, cbLighting, cbEngine, cbElectric;
    private ObservableList<Employee> listEmployees;
    private ObservableList<Workshop> listWorkshops;
    private Checkup checkup;
    private Vehicle vehicle;

    public NewCheckupController() {
        vehicle = null;
        checkup = null;
    }

    @FXML
    public void initialize() {
        listEmployees = FXCollections.observableArrayList();
        listEmployees.setAll(RegistrationDAO.getInstance().employees());
        cbAssignee.setItems(listEmployees);

        listWorkshops = FXCollections.observableArrayList();
        listWorkshops.setAll(RegistrationDAO.getInstance().workshops());
        cbWorkshop.setItems(listWorkshops);

        labelSelected.setText("Please select a vehicle");
    }

    public void actionNewVehicle(ActionEvent actionEvent){
        try {
            Stage stage = new Stage();
            Parent root = null;
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/newVehicle.fxml"), bundle);
            NewVehicleController newVehicleController = new NewVehicleController();
            loader.setController(newVehicleController);
            root = loader.load();
            stage.setTitle("New Vehicle");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding(event -> {
                if (newVehicleController.getVehicle() != null) {
                    vehicle = newVehicleController.getVehicle();
                    RegistrationDAO.getInstance().addVehicle(vehicle);
                    labelSelected.setText("Izabrali ste:" + vehicle);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (vehicle == null || cbAssignee.getSelectionModel().isEmpty() || cbWorkshop.getSelectionModel().isEmpty()){
            return;
        }

        checkup = new Checkup( (Employee) cbAssignee.getSelectionModel().getSelectedItem(), vehicle,
                (Workshop) cbWorkshop.getSelectionModel().getSelectedItem(), cbBrake.isSelected(),
                cbSteering.isSelected(), cbLighting.isSelected(), cbEngine.isSelected(), cbElectric.isSelected());
        RegistrationDAO.getInstance().addCheckup(checkup);

        Stage stage = (Stage) labelSelected.getScene().getWindow();
        stage.close();
    }

    public Vehicle getVehicle(){return vehicle;}

    public Checkup getCheckup(){return checkup;}
}
