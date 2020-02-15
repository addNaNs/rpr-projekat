package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PersonEditorController {
    public TextField fieldFirstName;
    public TextField fieldLastName;
    public TextField fieldEmail;
    public TextField fieldImage;
    public RadioButton radioMale;
    public RadioButton radioFemale;
    public ImageView image;
    private Person person;

    private boolean imgOk = false;
    final private String defaultImage = "https://oyster.ignimgs.com/mediawiki/apis.ign.com/pokemon-omega-ruby-and-alpha-sapphire/d/d7/Regigigas.jpg?width=325";

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

        setImage(fieldImage.getText());

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

        fieldImage.textProperty().addListener(((observable, oldValue, newValue) -> {
            setImage(newValue);
        }));
    }

    private void setImage(String path){
        if(path == null) return;
        try {
            image.setImage(new Image(path));
            imgOk = true;
        }catch (IllegalArgumentException e){
            image.setImage(new Image(defaultImage));
            imgOk = false;
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        person = null;
        Stage stage = (Stage) fieldFirstName.getScene().getWindow();
        stage.close();
    }

    public void clickOk(ActionEvent actionEvent) {
        if (person == null){

            person = new Person(-1,fieldFirstName.getText(),
                    fieldLastName.getText(),fieldEmail.getText(), (imgOk ? fieldImage.getText() : defaultImage),
                    radioMale.isSelected() ? Person.Gender.Male : Person.Gender.Female);
        }

        Stage stage = (Stage) fieldFirstName.getScene().getWindow();
        stage.close();
    }

    public Person getPerson(){
        return person;
    }
}
