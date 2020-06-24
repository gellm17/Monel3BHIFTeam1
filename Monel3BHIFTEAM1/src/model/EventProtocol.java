package model;


import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventProtocol {
    private IntegerProperty id;
    private ObjectProperty<LocalTime> startTime;
    private ObjectProperty<LocalTime> endTime;
    private StringProperty year_month;
    private DoubleProperty hourlyRate;
    private ObjectProperty<Employee> employee;
    private ObjectProperty<Client> client;
    private ObjectProperty<Event> event;
    private ObjectProperty<Bill> bill;
    private DoubleProperty mileage;
    private IntegerProperty km;
    private StringProperty note;

    public EventProtocol(Integer id, LocalTime startTime, LocalTime endTime, String year_month, Double hourlyRate, Employee employee, Client client, Event event, Bill bill, Double mileage, Integer km, String note) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.startTime = new SimpleObjectProperty<LocalTime>(this, "startTime", startTime);
        this.endTime = new SimpleObjectProperty<LocalTime>(this, "endTIme", endTime);
        this.year_month = new SimpleStringProperty(this, "year_month", year_month);
        this.hourlyRate = new SimpleDoubleProperty(this, "hourlyRate", hourlyRate);
        this.employee = new SimpleObjectProperty<Employee>(this, "employee", employee);
        this.client = new SimpleObjectProperty<Client>(this, "client", client);
        this.event = new SimpleObjectProperty<Event>(this, "event", event);
        this.bill = new SimpleObjectProperty<Bill>(this, "bill", bill);
        this.mileage = new SimpleDoubleProperty(this, "mileage", mileage);
        this.km = new SimpleIntegerProperty(this, "km", km);
        this.note = new SimpleStringProperty(this, "note", note);
    }

    public EventProtocol(Integer id, LocalTime startTime, LocalTime endTime, String year_month, Double hourlyRate, Employee employee, Client client, Event event, Bill bill, Double mileage, String note) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.startTime = new SimpleObjectProperty<LocalTime>(this, "startTime", startTime);
        this.endTime = new SimpleObjectProperty<LocalTime>(this, "endTIme", endTime);
        this.year_month = new SimpleStringProperty(this, "year_month", year_month);
        this.hourlyRate = new SimpleDoubleProperty(this, "hourlyRate", hourlyRate);
        this.employee = new SimpleObjectProperty<Employee>(this, "employee", employee);
        this.client = new SimpleObjectProperty<Client>(this, "client", client);
        this.event = new SimpleObjectProperty<Event>(this, "event", event);
        this.bill = new SimpleObjectProperty<Bill>(this, "bill", bill);
        this.mileage = new SimpleDoubleProperty(this, "mileage", mileage);
        this.note = new SimpleStringProperty(this, "note", note);
    }

    public EventProtocol(Integer id, LocalTime startTime, LocalTime endTime, String year_month, Double hourlyRate, Employee employee, Client client, Event event, Bill bill, Double mileage) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.startTime = new SimpleObjectProperty<LocalTime>(this, "startTime", startTime);
        this.endTime = new SimpleObjectProperty<LocalTime>(this, "endTIme", endTime);
        this.year_month = new SimpleStringProperty(this, "year_month", year_month);
        this.hourlyRate = new SimpleDoubleProperty(this, "hourlyRate", hourlyRate);
        this.employee = new SimpleObjectProperty<Employee>(this, "employee", employee);
        this.client = new SimpleObjectProperty<Client>(this, "client", client);
        this.event = new SimpleObjectProperty<Event>(this, "event", event);
        this.bill = new SimpleObjectProperty<Bill>(this, "bill", bill);
        this.mileage = new SimpleDoubleProperty(this, "mileage", mileage);
    }

    public EventProtocol(Integer id, LocalTime startTime, LocalTime endTime, String year_month, Double hourlyRate, Employee employee, Client client, Event event, Double mileage) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.startTime = new SimpleObjectProperty<LocalTime>(this, "startTime", startTime);
        this.endTime = new SimpleObjectProperty<LocalTime>(this, "endTIme", endTime);
        this.year_month = new SimpleStringProperty(this, "year_month", year_month);
        this.hourlyRate = new SimpleDoubleProperty(this, "hourlyRate", hourlyRate);
        this.employee = new SimpleObjectProperty<Employee>(this, "employee", employee);
        this.client = new SimpleObjectProperty<Client>(this, "client", client);
        this.event = new SimpleObjectProperty<Event>(this, "event", event);
        this.mileage = new SimpleDoubleProperty(this, "mileage", mileage);
    }

    public EventProtocol() {
        this.id = new SimpleIntegerProperty(this, "id");
        this.startTime = new SimpleObjectProperty<LocalTime>(this, "startTime");
        this.endTime = new SimpleObjectProperty<LocalTime>(this, "endTIme");
        this.year_month = new SimpleStringProperty(this, "year_month");
        this.hourlyRate = new SimpleDoubleProperty(this, "hourlyRate");
        this.employee = new SimpleObjectProperty<Employee>(this, "employee");
        this.client = new SimpleObjectProperty<Client>(this, "client");
        this.event = new SimpleObjectProperty<Event>(this, "event");
        this.bill = new SimpleObjectProperty<Bill>(this, "bill", null);
        this.mileage = new SimpleDoubleProperty(this, "mileage");
        this.note = new SimpleStringProperty(this, "note");
    }

    public static EventProtocol fromResults(ResultSet rs) throws SQLException { //mandatory startTime, endTime and year_month
        return new EventProtocol(rs.getInt("id"), LocalTime.parse(rs.getString("startzeit")), LocalTime.parse(rs.getString("endzeit")), rs.getString("jahr_Monat"), rs.getDouble("stundensatz"), null, null, null, null, rs.getDouble("kilometersatz"), rs.getInt("km"), rs.getString("notiz"));
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

    public LocalTime getStartTime() {
        return startTime.get();
    }

    public ObjectProperty<LocalTime> startTimeProperty() { return startTime; }

    public void setStartTime(LocalTime startTime) {
        this.startTime.set(startTime);
    }

    public LocalTime getEndTime() {
        return endTime.get();
    }

    public ObjectProperty<LocalTime> endTimeProperty() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime.set(endTime);
    }

    public String getYear_month() {
        return year_month.get();
    }

    public StringProperty year_monthProperty() {
        return year_month;
    }

    public void setYear_month(String year_month) {
        this.year_month.set(year_month);
    }

    public double getHourlyRate() {
        return hourlyRate.get();
    }

    public DoubleProperty hourlyRateProperty() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate.set(hourlyRate);
    }

    public Employee getEmployee() {
        return employee.get();
    }

    public ObjectProperty<Employee> employeeProperty() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee.set(employee);
    }

    public Client getClient() {
        return client.get();
    }

    public ObjectProperty<Client> clientProperty() {
        return client;
    }

    public void setClient(Client client) {
        this.client.set(client);
    }

    public Event getEvent() {
        return event.get();
    }

    public ObjectProperty<Event> eventProperty() {
        return event;
    }

    public void setEvent(Event event) {
        this.event.set(event);
    }

    public double getMileage() { return mileage.get(); }

    public DoubleProperty mileageProperty() { return mileage; }

    public void setMileage(double mileage) { this.mileage.set(mileage); }

    public Bill getBill() { return bill.get(); }

    public ObjectProperty<Bill> billProperty() { return bill; }

    public void setBill(Bill bill) { this.bill.set(bill); }

    public String getNote() { return note.get(); }

    public StringProperty noteProperty() { return note; }

    public void setNote(String note) { this.note.set(note); }

    public Integer getKm() { return km.get(); }

    public IntegerProperty kmProperty() { return km; }

    public void setKm(Integer km) { this.km.set(km); }
}
