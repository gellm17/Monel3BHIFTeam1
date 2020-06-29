package model;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Costs {
    private IntegerProperty id;
    private ObjectProperty<EventProtocol> eventprotocol;
    private StringProperty description;
    private DoubleProperty amount;
    private IntegerProperty taxrate;

    public Costs(Integer id, EventProtocol eventprotocol, String description, Double amount, Integer taxrate) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.eventprotocol = new SimpleObjectProperty<EventProtocol>(this, "eventprotocol", eventprotocol);
        this.description = new SimpleStringProperty(this, "description", description);
        this.amount = new SimpleDoubleProperty(this, "amount", amount);
        this.taxrate = new SimpleIntegerProperty(this, "taxrate", taxrate);
    }

    public Costs() {
        this.id = new SimpleIntegerProperty(this, "id");
        this.eventprotocol = new SimpleObjectProperty<EventProtocol>(this, "eventprotocol");
        this.description = new SimpleStringProperty(this, "description");
        this.amount = new SimpleDoubleProperty(this, "amount");
        this.taxrate = new SimpleIntegerProperty(this, "taxrate");
    }

    public static Costs fromResults(ResultSet rs) throws SQLException {
        return new Costs(rs.getInt("id"), null, rs.getString("bezeichnung"), rs.getDouble("betrag"), rs.getInt("steuersatz"));
    }

    public Integer getId() { return id.get(); }

    public IntegerProperty idProperty() { return id; }

    public void setid(Integer id) { this.id.set(id); }

    public EventProtocol getEventprotocol() { return eventprotocol.get(); }

    public ObjectProperty<EventProtocol> eventprotocolProperty() { return eventprotocol; }

    public void setEventprotocol(EventProtocol eventprotocol) { this.eventprotocol.set(eventprotocol); }

    public String getDescription() { return description.get(); }

    public StringProperty descriptionProperty() { return description; }

    public void setDescription(String description) { this.description.set(description); }

    public Double getAmount() { return amount.get(); }

    public DoubleProperty amountProperty() { return amount; }

    public void setamount(Double amount) { this.amount.set(amount); }

    public Integer getTaxrate() { return taxrate.get(); }

    public IntegerProperty taxrateProperty() { return taxrate; }

    public void setTaxrate(Integer taxrate) { this.taxrate.set(taxrate); }

    @Override
    public String toString() {
        return getDescription() + " " + getAmount();
    }
}
