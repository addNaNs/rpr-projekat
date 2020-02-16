package sample;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Workshop {
    SimpleIntegerProperty id = new SimpleIntegerProperty();
    SimpleStringProperty examinableCategories = new SimpleStringProperty();

    public Workshop(int id, String examinableCategories) throws IllegalCategoryException {
        this.id.set(id);
        setExaminableCategories(examinableCategories);
    }

    public Workshop(Workshop workshop) throws IllegalCategoryException {
        this( new Integer(workshop.getId()), new String(workshop.getExaminableCategories()));
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

    public void setExaminableCategories(String examinableCategories) throws IllegalCategoryException {
        checkCategory(examinableCategories);
        this.examinableCategories.set(examinableCategories);
    }

    public String toString(){
        return getId() + "(" + getExaminableCategories() + ")";
    }

    public static boolean checkCategory(String s) throws IllegalCategoryException {
        for (char c : s.toUpperCase().toCharArray()) {
            if(!("ABCDE").contains(String.valueOf(c))) throw new IllegalCategoryException("no such category");
        }
        return true;
    }
}
