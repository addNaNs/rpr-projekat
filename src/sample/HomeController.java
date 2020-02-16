package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
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

    private RegistrationDAO dao;

    public HomeController() {
        dao = RegistrationDAO.getInstance();
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
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/personEditor.fxml"), bundle);
            PersonEditorController personEditorController = new PersonEditorController(null);
            loader.setController(personEditorController);
            root = loader.load();
            stage.setTitle("New Employee");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                if (personEditorController.getPerson() != null) {
                    RegistrationDAO.getInstance().addEmployee( new Employee( personEditorController.getPerson()));
                    listEmployee.setAll(dao.employees());
                }
            } );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionUpdateEmployee(ActionEvent actionEvent){
        Employee employee = tableViewEmployee.getSelectionModel().getSelectedItem();
        if(employee==null) return;
        Stage stage = new Stage();
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/personEditor.fxml"), bundle);
            PersonEditorController personEditorController = new PersonEditorController(employee);
            loader.setController(personEditorController);
            root = loader.load();
            stage.setTitle("Employee");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

            stage.setOnHiding( event -> {
                if (personEditorController.getPerson() != null) {
                    dao.updateEmployee( (Employee) personEditorController.getPerson());
                    listEmployee.setAll(dao.employees());
                }
            } );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionDeleteEmployee(ActionEvent actionEvent) {
        Employee employee = tableViewEmployee.getSelectionModel().getSelectedItem();
        if (employee == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert!");
        alert.setHeaderText("Deleting "+employee.getFirstName() + " " + employee.getLastName());
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dao.deleteEmployee(employee);
            listEmployee.setAll(dao.employees());
        }
    }

    public void actionAddWorkshop(ActionEvent actionEvent){
        Stage stage = new Stage();
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/workshopEditor.fxml"), bundle);
            WorkshopEditorController workshopEditorController = new WorkshopEditorController(null);
            loader.setController(workshopEditorController);
            root = loader.load();
            stage.setTitle("New Workshop");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();

                stage.setOnHiding(event -> {
                    try {
                        if (workshopEditorController.getWorkshop() != null) {
                            listWorkshop.setAll(dao.workshops());
                        }
                    } catch (IllegalCategoryException e) {
                        e.printStackTrace();
                    }
                });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalCategoryException e) {
            e.printStackTrace();
        }
    }

    public void actionUpdateWorkshop(ActionEvent actionEvent){
        Workshop workshop = tableViewWorkshop.getSelectionModel().getSelectedItem();
        if(workshop == null) return;
        Stage stage = new Stage();
        Parent root = null;
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/workshopEditor.fxml"), bundle);
            WorkshopEditorController workshopEditorController = new WorkshopEditorController(workshop);
            loader.setController(workshopEditorController);
            root = loader.load();
            stage.setTitle("Workshop");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
                stage.setOnHiding(event -> {
                    try {
                        if (workshopEditorController.getWorkshop() != null) {
                            dao.updateWorkshop(workshopEditorController.getWorkshop());
                            listWorkshop.setAll(dao.workshops());
                        }
                    } catch (IllegalCategoryException e) {
                        e.printStackTrace();
                    }
                });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalCategoryException e) {
            e.printStackTrace();
        }
    }

    public void actionDeleteWorkshop(ActionEvent actionEvent) {
        Workshop workshop = tableViewWorkshop.getSelectionModel().getSelectedItem();
        if (workshop == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert!");
        alert.setHeaderText("Deleting Workshop "+workshop.getId());
        alert.setContentText("Are you sure?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dao.deleteWorkshop(workshop);
            listWorkshop.setAll(dao.workshops());
        }
    }

    public void actionCustomers(ActionEvent actionEvent){
        try {
            Stage stage = new Stage();
            Parent root = null;
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/singleList.fxml"),bundle);
            CustomersController customersController = new CustomersController();
            loader.setController(customersController);
            root = loader.load();
            stage.setTitle("Customers");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionVehicles(ActionEvent actionEvent){
        try {
            Stage stage = new Stage();
            Parent root = null;
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/singleList.fxml"), bundle);
            VehiclesController vehiclesController = new VehiclesController();
            loader.setController(vehiclesController);
            root = loader.load();
            stage.setTitle("Vehicles");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void actionNewCheckup(ActionEvent actionEvent){
        try {
            Stage stage = new Stage();
            Parent root = null;
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/newCheckup.fxml"), bundle);
            NewCheckupController newCheckupController = new NewCheckupController();
            loader.setController(newCheckupController);
            root = loader.load();
            stage.setTitle("New Checkup");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPrintReport(ActionEvent actionEvent) {
        try {
            new PrintReport().showReport(dao.getConn());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void actionSave(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(tableViewWorkshop.getScene().getWindow());
        dao.saveFile(file);

    }

    public void actionHelp(ActionEvent actionEvent) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String url = "https://github.com/addNaNs/rpr-projekat";
        String[] browsers = { "epiphany", "firefox", "mozilla", "konqueror",
                "netscape", "opera", "links", "lynx" };
        StringBuffer cmd = new StringBuffer();
        for (int i = 0; i < browsers.length; i++)
            if(i == 0)
                cmd.append(String.format(    "%s \"%s\"", browsers[i], url));
            else
                cmd.append(String.format(" || %s \"%s\"", browsers[i], url));
        rt.exec(new String[] { "sh", "-c", cmd.toString() });
    }

    public void setBosAction(ActionEvent actionEvent){
        selectLanguage(new Locale("bs","BA"));
    }

    public void setEngAction(ActionEvent actionEvent){
        selectLanguage(new Locale("en","US"));
    }

    private void selectLanguage(Locale locale) {
        Stage primaryStage = (Stage) tableViewWorkshop.getScene().getWindow();
        Locale.setDefault(locale);
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"), bundle);
        loader.setController(this);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
    }
}
