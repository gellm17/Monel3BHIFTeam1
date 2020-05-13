package model;

import com.intellij.util.config.StringProperty;

import java.time.LocalDate;

public class Event {
    private IntegerProperty id;
    private ObjectProperty<LocalDate> date;
    private StringProperty name;                //activity
    private StringProperty description;
    private DoubleProperty rideCosts;
    private BooleanProperty isGruop;            //if the event is a group event

    public Event(LocalDate date, string name, string description, double rideCosts, boolean isGroup) {
        this.id = new SimpelIntProperty(this, "id", 0);
        this.date = new SimpelObjectProperty<LocalDate>(this, "date", date);
        this.name = new SimpleStringProperty(this, "name", name);
        this.description = new SimpleStringProperty(this, "description", description);
        this.rideCosts = new SimpleDoubleProperty(this, "rideCosts", rideCosts);
        this.isGruop = new SimpleBooleanProperty(this, 'isGroup', isGroup);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    public int getID() {
        return this.id.get();
    }
    public void setId(int id) {
        this.id.set(id);
    }

    public ObjectProperty<LocalDate> datumProperty() {
        return date;
    }
    public LocalDate getDatum() {
        return this.date.get();
    }
    public void setDatum(LocalDate date) {
        this.date.set(date);
    }

    public StringProperty nameProperty() {
        return name;
    }
    public String getName() {
        return this.name.get();
    }
    public void setName(string name) {
        this.name.set(name);
    }

    public StringProperty descriptionProperty() {
        return description;
    }
    public String getDescription() {
        return this.description.get();
    }
    public void setDescription(string description) {
        this.description.set(description);
    }

    public DoubleProperty rideCostsProperty() {
        return rideCosts;
    }
    public int getRideCosts() {
        return this.rideCosts.get();
    }
    public void setRideCosts(double rideCosts) {
        this.rideCosts.set(rideCosts);
    }

    public BooleanProperty isGroupProperty() {
        return this.isGruop;
    }
    public int getIsGroup() {
        return this.isGruop.get();
    }
    public void setIsGroup(boolean isGruop) {
        this.isGruop.set(isGroup);
    }
}
