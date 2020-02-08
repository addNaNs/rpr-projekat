package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee extends Person {
    public Employee(int id, String firstName, String lastName, String email, String profileImagePath, Gender sex) {
        super(id, firstName, lastName, email, profileImagePath, sex);
    }
}
