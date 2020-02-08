package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {

    SimpleIntegerProperty id = new SimpleIntegerProperty();
    SimpleStringProperty firstName = new SimpleStringProperty();
    SimpleStringProperty lastName = new SimpleStringProperty();
    SimpleStringProperty eMail = new SimpleStringProperty();
    SimpleStringProperty profileImagePath = new SimpleStringProperty();

    public Employee(int id, String firstName, String lastName, String eMail, String profileImagePath){
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.eMail.set(eMail);
        this.profileImagePath.set(profileImagePath);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEMail() {
        return eMail.get();
    }

    public SimpleStringProperty eMailProperty() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail.set(eMail);
    }

    public String getProfileImagePath() {
        return profileImagePath.get();
    }

    public SimpleStringProperty profileImagePathProperty() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath.set(profileImagePath);
    }
}
