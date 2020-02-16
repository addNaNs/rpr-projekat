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
import javafx.scene.layout.Border;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.regex.Pattern;

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

        fieldFirstName.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.isEmpty() && Pattern.matches("[A-Za-z|[-]|[ ]|[ć]|[č]|[Ć]|[Č]|[š]|[Š]|[đ]|[Đ]|[ž]|[Ž]]+", newValue) && newValue.length()>=3) {
                fieldFirstName.setStyle("-fx-text-inner-color: black;");
            } else {
                fieldFirstName.setStyle("-fx-text-inner-color: red;");
            }
        });

        fieldLastName.textProperty().addListener((obs, oldValue, newValue) -> {
            System.out.println("ssssss");
            if (!newValue.isEmpty() && Pattern.matches("[A-Za-z|[-]|[ ]|[ć]|[č]|[Ć]|[Č]|[š]|[Š]|[đ]|[Đ]|[ž]|[Ž]]+", newValue) && newValue.length()>=3) {
                fieldLastName.setStyle("-fx-text-inner-color: black;");
            } else {
                fieldLastName.setStyle("-fx-text-inner-color: red;");
            }
        });

        fieldEmail.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.isEmpty() && newValue.matches("(.*)+[A-Za-z0-9]+@+[A-Za-z0-9]+(.*)"))  {
                fieldEmail.setStyle("-fx-text-inner-color: black;");
            } else {
                fieldEmail.setStyle("-fx-text-inner-color: red;");
            }
        });


    }

    private boolean isFieldValid(TextField textField){
        return !textField.getStyle().equals("-fx-text-inner-color: red;");
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

        if(!(isFieldValid(fieldFirstName) && isFieldValid(fieldLastName) && isFieldValid(fieldEmail))) return;

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
