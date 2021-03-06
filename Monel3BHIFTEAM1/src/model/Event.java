package model;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Event {
    private IntegerProperty id;
    private ObjectProperty<LocalDate> date;
    private StringProperty name;                //activity
    private BooleanProperty isGroup;            //if the event is a group event
    private StringProperty note;

    public Event(Integer id, LocalDate date, String name, boolean isGroup) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.date = new SimpleObjectProperty<LocalDate>(this, "date", date);
        this.name = new SimpleStringProperty(this, "name", name);
        this.isGroup = new SimpleBooleanProperty(this, "isGroup", isGroup);
        this.note = new SimpleStringProperty(this, "note");
    }

    public Event(Integer id, LocalDate date, String name, boolean isGroup, String note) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.date = new SimpleObjectProperty<LocalDate>(this, "date", date);
        this.name = new SimpleStringProperty(this, "name", name);
        this.isGroup = new SimpleBooleanProperty(this, "isGroup", isGroup);
        this.note = new SimpleStringProperty(this, "note", note);
    }

    public Event(Integer id) {
        this.id = new SimpleIntegerProperty(this, "id", id);
    }

    public Event() {
        this.id = new SimpleIntegerProperty(this, "id");
        this.date = new SimpleObjectProperty<LocalDate>(this, "date");
        this.name = new SimpleStringProperty(this, "name");
        this.isGroup = new SimpleBooleanProperty(this, "isGroup");
        this.note = new SimpleStringProperty(this, "note");
    }

    public static Event fromResults(ResultSet rs) throws SQLException {
        return new Event(rs.getInt("id"), LocalDate.parse(rs.getString("datum")), rs.getString("aktivitaetsbezeichnung"), (rs.getInt("kategorie") == 1 ? true : false), rs.getString("notiz"));
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

    public boolean getIsGroup() {
        return isGroup.get();
    }

    public BooleanProperty isGroupProperty() {
        return isGroup;
    }

    public void setIsGroup(boolean isGroup) {
        this.isGroup.set(isGroup);
    }

    public String getNote() { return note.get(); }

    public StringProperty noteProperty() { return note; }

    public void setNote(String note) { this.note.set(note); }

    @Override
    public String toString() {
        return this.name.getValue() + " (" + (this.isGroup.getValue() ? "Gruppenakt." : "Einzelakt.") + ")";
    }
}
