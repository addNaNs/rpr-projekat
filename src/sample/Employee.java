package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {

    public enum Gender{
        Male,
        Female
    }

    SimpleIntegerProperty id = new SimpleIntegerProperty();
    SimpleStringProperty firstName = new SimpleStringProperty();
    SimpleStringProperty lastName = new SimpleStringProperty();
    SimpleStringProperty email = new SimpleStringProperty();
    SimpleStringProperty profileImagePath = new SimpleStringProperty();
    Gender sex;

    public Employee(int id, String firstName, String lastName, String email, String profileImagePath, Gender sex){
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.email.set(email);
        this.profileImagePath.set(profileImagePath);
        this.sex = sex;
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

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
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

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }
}
