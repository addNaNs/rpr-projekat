package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PersonEditorController {
    public TextField fieldFirstName;
    public TextField fieldLastName;
    public TextField fieldEmail;
    public TextField fieldImage;
    public RadioButton radioMale;
    public RadioButton radioFemale;
    private Person person;

    public PersonEditorController(Person person) {
        this.person = person;
    }

    @FXML
    public void initialize() {
        ToggleGroup groupSex = new ToggleGroup();
        radioMale.setToggleGroup(groupSex);
        radioFemale.setToggleGroup(groupSex);

        if (person != null) {
            fieldFirstName.textProperty().bindBidirectional(person.firstNameProperty());
            fieldLastName.textProperty().bindBidirectional(person.lastNameProperty());
            fieldEmail.textProperty().bindBidirectional(person.emailProperty());
            fieldImage.textProperty().bindBidirectional(person.profileImagePathProperty());
            if(person.getSex() == Person.Gender.Male){
                radioMale.fire();
            }else{
                radioFemale.fire();
            }
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        person = null;
        Stage stage = (Stage) fieldFirstName.getScene().getWindow();
        stage.close();
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean allGood = true;
        if (!allGood) return;

        if (person == null){
            //dodaj; TODO
        }

        Stage stage = (Stage) fieldFirstName.getScene().getWindow();
        stage.close();
    }
}
