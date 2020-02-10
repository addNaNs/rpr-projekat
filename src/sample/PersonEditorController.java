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
        if(person != null){
            this.person = new Employee(person);
        } else {
            this.person=null;
        }
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

        radioMale.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if(person != null && newValue){
                person.setSex(Person.Gender.Male);
            }
        }));

        radioFemale.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if(person != null && newValue){
                person.setSex(Person.Gender.Female);
            }
        }));
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

            person = new Employee(-1,fieldFirstName.getText(),
                    fieldLastName.getText(),fieldEmail.getText(),fieldImage.getText(),
                    radioMale.isSelected() ? Person.Gender.Male : Person.Gender.Female);

            RegistrationDAO.getInstance().addEmployee((Employee) person);
        }

        Stage stage = (Stage) fieldFirstName.getScene().getWindow();
        stage.close();
    }

    public Person getPerson(){
        return person;
    }
}
