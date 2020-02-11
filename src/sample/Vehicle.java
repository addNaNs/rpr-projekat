package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vehicle {

    SimpleStringProperty plates = new SimpleStringProperty();
    SimpleStringProperty model = new SimpleStringProperty();
    SimpleIntegerProperty ownerId = new SimpleIntegerProperty();
    SimpleStringProperty category = new SimpleStringProperty();

    public Vehicle(String plates, String model, int ownerId, Character category){
        this.plates.set(plates);
        this.model.set(model);
        this.ownerId.set(ownerId);
        this.category.set(String.valueOf(category));
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

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperties() {
        return model;
    }

    public void setPlatesetModel(String model) {
        this.model.set(model);
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

    public String toString(){
        return plates.get() + "(" + category.get() + ")";
    }
}
