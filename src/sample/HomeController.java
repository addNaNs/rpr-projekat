package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class HomeController {

    public TableView<Employee> tableViewEmployee;
    public TableView<Workshop> tableViewWorkshop;
    public TableColumn colEmployeeFirstName;
    public TableColumn colEmployeeLastName;
    public TableColumn colEmployeeEmail;
    public TableColumn colWorkshopId;
    public TableColumn colWorkshopCategories;

    private ObservableList<Employee> listEmployee;
    private ObservableList<Vehicle> listVehicle;
    private ObservableList<Customer> listCustomer;
    private ObservableList<Workshop> listWorkshop;
    private ObservableList<Checkup> listCheckup;

    public HomeController() {
        RegistrationDAO dao = RegistrationDAO.getInstance();
        listEmployee = FXCollections.observableArrayList(dao.employees());
        listVehicle = FXCollections.observableArrayList(dao.vehicles());
        listCustomer = FXCollections.observableArrayList(dao.customers());
        listWorkshop = FXCollections.observableArrayList(dao.workshops());
        listCheckup = FXCollections.observableArrayList(dao.checkups());
    }

    @FXML
    public void initialize() {
        tableViewEmployee.setItems(listEmployee);
        colEmployeeFirstName.setCellValueFactory(new PropertyValueFactory("firstName"));
        colEmployeeLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        colEmployeeEmail.setCellValueFactory(new PropertyValueFactory("email"));

        tableViewWorkshop.setItems(listWorkshop);
        colWorkshopId.setCellValueFactory(new PropertyValueFactory("id"));
        colWorkshopCategories.setCellValueFactory(new PropertyValueFactory("examinableCategories"));
    }

    public void actionAddEmployee(ActionEvent actionEvent){
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/personEditor.fxml"));
            PersonEditorController personEditorController = new PersonEditorController(null);
            loader.setController(personEditorController);
            root = loader.load();
            stage.setTitle("Novi uposlenik");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionAddWorkshop(ActionEvent actionEvent){
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/workshopEditor.fxml"));
            WorkshopEditorController workshopEditorController = new WorkshopEditorController(null);
            loader.setController(workshopEditorController);
            root = loader.load();
            stage.setTitle("Nova Radionica");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
