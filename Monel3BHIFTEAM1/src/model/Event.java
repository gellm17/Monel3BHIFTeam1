package model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Event {
    private IntegerProperty id;
    private ObjectProperty<LocalDate> date;
    private StringProperty name;                //activity
    private StringProperty description;
    private DoubleProperty rideCosts;
    private BooleanProperty isGroup;            //if the event is a group event

    public Event(LocalDate date, String name, String description, double rideCosts, boolean isGroup) {
        this.id = new SimpleIntegerProperty(this, "id", 0);
        this.date = new SimpleObjectProperty<LocalDate>(this, "date", date);
        this.name = new SimpleStringProperty(this, "name", name);
        this.description = new SimpleStringProperty(this, "description", description);
        this.rideCosts = new SimpleDoubleProperty(this, "rideCosts", rideCosts);
        this.isGroup = new SimpleBooleanProperty(this, "isGroup", isGroup);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public double getRideCosts() {
        return rideCosts.get();
    }

    public DoubleProperty rideCostsProperty() {
        return rideCosts;
    }

    public void setRideCosts(double rideCosts) {
        this.rideCosts.set(rideCosts);
    }

    public boolean getIsGroup() {
        return isGroup.get();
    }

    public BooleanProperty isGroupProperty() {
        return isGroup;
    }

    public void setIsGroup(boolean isGroup) {
        this.isGroup.set(isGroup);
    }
}
