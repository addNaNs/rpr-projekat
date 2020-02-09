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


}
