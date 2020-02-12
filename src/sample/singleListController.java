package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.stream.Collector;

public class singleListController {

    public static <T> void searchSetup(ListView<T> listMain, ObservableList<T> list, TextField fieldSearch){
        listMain.setItems(list);

        fieldSearch.textProperty().addListener( (obs, oldSearch, newSearch) -> {
            if (newSearch.isEmpty()) {
                listMain.setItems(list);
            } else {
                listMain.setItems(list.stream()
                        .filter(x -> x.toString().contains(newSearch))
                        .collect(Collector.of(
                                FXCollections::observableArrayList,
                                ObservableList::add,
                                (l1, l2) -> { l1.addAll(l2); return l1; })
                        ));
            }
        } );
    }

}
