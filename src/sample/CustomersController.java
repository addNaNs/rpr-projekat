package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CustomersController {
    public ListView<Person> listMain;
    private Person person;

    public CustomersController() {
    }

    @FXML
    public void initialize() {
        ObservableList<Person> list = FXCollections.observableArrayList();
        RegistrationDAO.getInstance().customers().stream().forEach(a -> list.add(a));
        listMain.setItems(list);
    }

    public void clickCancel(ActionEvent actionEvent) {
        person = null;
        Stage stage = (Stage) listMain.getScene().getWindow();
        stage.close();
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean allGood = true;
        if (!allGood) return;

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
