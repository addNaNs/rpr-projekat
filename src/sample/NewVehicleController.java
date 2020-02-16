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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class NewVehicleController {

    public TextField fieldPlates;
    public TextField fieldModel;
    public Label labelOwner;
    public ChoiceBox<Character> cbCategory;
    private Vehicle vehicle;
    private Customer owner;
    ObservableList<Character> listCategory;

    public NewVehicleController() {
        vehicle = null; owner = null;
    }

    @FXML
    public void initialize() {
        listCategory = FXCollections.observableArrayList();
        listCategory.add('A');
        listCategory.add('B');
        listCategory.add('C');
        listCategory.add('D');
        listCategory.add('E');
        cbCategory.setItems(listCategory);
        cbCategory.getSelectionModel().selectFirst();
    }

    public void actionNewOwner(ActionEvent actionEvent){
        try {
            Stage stage = new Stage();
            Parent root = null;
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/personEditor.fxml"), bundle);
            PersonEditorController personEditorController = new PersonEditorController(null);
            loader.setController(personEditorController);
            root = loader.load();
            stage.setTitle("New Customer");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                if (personEditorController.getPerson() != null) {
                    owner = new Customer( personEditorController.getPerson());
                    RegistrationDAO.getInstance().addCustomer(owner);
                    labelOwner.setText("Owner("+owner+")");
                }
            } );

        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    Person person = (Customer) customersController.getPerson();
                    owner = new Customer(person);
                    labelOwner.setText("Owner("+owner+")");
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
        if (owner == null){
            return;
        }

        vehicle = new Vehicle(fieldPlates.getText(), fieldModel.getText(), owner.getId(), cbCategory.getValue());

        Stage stage = (Stage) fieldPlates.getScene().getWindow();
        stage.close();
    }

    public Vehicle getVehicle(){return vehicle;}

    public Customer getOwner(){return owner;}
}
