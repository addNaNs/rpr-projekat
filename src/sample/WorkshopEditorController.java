package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class WorkshopEditorController {
    public TextField fieldCategories;
    private Workshop workshop;

    public WorkshopEditorController(Workshop workshop) {
        this.workshop = workshop;
    }

    @FXML
    public void initialize() {
        if (workshop != null) {
            fieldCategories.textProperty().bindBidirectional(workshop.examinableCategoriesProperty());
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        workshop = null;
        Stage stage = (Stage) fieldCategories.getScene().getWindow();
        stage.close();
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean allGood = true;
        if (!allGood) return;

        if (workshop == null){
            RegistrationDAO.getInstance().addWorkshop(new Workshop(-1,fieldCategories.getText()));
        }

        Stage stage = (Stage) fieldCategories.getScene().getWindow();
        stage.close();
    }

    public Workshop getWorkshop(){
        return workshop;
    }
}
