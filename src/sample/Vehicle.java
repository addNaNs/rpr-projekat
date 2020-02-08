package sample;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vehicle {

    SimpleStringProperty plates = new SimpleStringProperty();
    SimpleIntegerProperty ownerId = new SimpleIntegerProperty();
    SimpleStringProperty category = new SimpleStringProperty();

    public Vehicle(String plates, int ownerId, Character category){
        this.plates.set(plates);
        this.ownerId.set(ownerId);
        this.ownerId.set(category);
    }

    public String getPlates() {
        return plates.get();
    }

    public SimpleStringProperty platesProperty() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates.set(plates);
    }

    public int getOwnerId() {
        return ownerId.get();
    }

    public SimpleIntegerProperty ownerIdProperty() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId.set(ownerId);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

}
