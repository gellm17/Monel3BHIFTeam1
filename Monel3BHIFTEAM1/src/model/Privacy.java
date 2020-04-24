package model;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Privacy {
    private IntegerProperty id;
    private ObservableList<Boolean> privacies;

    private static int counterForID = 0;

    public Privacy(List<Boolean> privacies) {
        this.privacies = FXCollections.observableList(privacies);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public ObservableList<Boolean> getPrivacies() {
        return privacies;
    }

    public void setPrivacies(ObservableList<Boolean> privacies) {
        this.privacies = privacies;
    }
}
