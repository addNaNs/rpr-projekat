package sample;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Workshop {
    SimpleIntegerProperty id = new SimpleIntegerProperty();
    SimpleStringProperty examinableCategories = new SimpleStringProperty();
    //SimpleBooleanProperty isAvailable = new SimpleBooleanProperty();

    public Workshop(int id, String examinableCategories){
        this.id.set(id);
        this.examinableCategories.set(examinableCategories);
        //this.isAvailable.set(isAvailable);
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

    public String getExaminableCategories() {
        return examinableCategories.get();
    }

    public SimpleStringProperty examinableCategoriesProperty() {
        return examinableCategories;
    }

    public void setExaminableCategories(String examinableCategories) {
        this.examinableCategories.set(examinableCategories);
    }
/*
    public boolean getIsAvailable() {
        return isAvailable.get();
    }

    public SimpleBooleanProperty isAvailableProperty() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable.set(isAvailable);
    }
*/

}
