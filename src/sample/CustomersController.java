package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CustomersController {
    public ListView<Person> listMain;
    public TextField fieldSearch;
    ObservableList<Person> list;
    private Person person;

    public CustomersController() {
    }

    @FXML
    public void initialize() {
        list = FXCollections.observableArrayList();
        RegistrationDAO.getInstance().customers().stream().forEach(a -> list.add(a));
        SingleListController.searchSetup(listMain, list, fieldSearch);
    }

    public void clickCancel(ActionEvent actionEvent) {
        person = null;
        Stage stage = (Stage) listMain.getScene().getWindow();
        stage.close();
    }

    public void clickOk(ActionEvent actionEvent) {
        if (person == null){
            person = listMain.getSelectionModel().getSelectedItem();
        }

        if(person != null) {
            Stage stage = (Stage) listMain.getScene().getWindow();
            stage.close();
        }
    }

    public Person getPerson(){
        return person;
    }
}
