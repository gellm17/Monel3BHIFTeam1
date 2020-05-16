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
    private ObjectProperty<LocalDate> year_month;
    private DoubleProperty hourlyRate;
    private ObjectProperty<Employee> employee;
    private ObjectProperty<Client> client;
    private ObjectProperty<Event> event;
    private DoubleProperty rideCosts;

    public EventProtocol(Integer id, LocalTime startTime, LocalTime endTime, LocalDate year_month, Double hourlyRate, Employee employee, Client client, Event event, Double rideCosts) {
        this.id = new SimpleIntegerProperty(this, "id", 0);
        this.startTime = new SimpleObjectProperty(this, "startTime", startTime);
        this.endTime = new SimpleObjectProperty(this, "endTIme", endTime);
        this.year_month = new SimpleObjectProperty(this, "year_month", year_month);
        this.hourlyRate = new SimpleDoubleProperty(this, "hourlyRate", hourlyRate);
        this.employee = new SimpleObjectProperty<Employee>(this, "employee", employee);
        this.client = new SimpleObjectProperty<Client>(this, "client", client);
        this.event = new SimpleObjectProperty<Event>(this, "event", event);
        this.rideCosts = new SimpleDoubleProperty(this, "rideCosts", rideCosts);
    }

    public EventProtocol() {
        this.id = new SimpleIntegerProperty(this, "id");
        this.startTime = new SimpleObjectProperty(this, "startTime");
        this.endTime = new SimpleObjectProperty(this, "endTIme");
        this.year_month = new SimpleObjectProperty(this, "year_month");
        this.hourlyRate = new SimpleDoubleProperty(this, "hourlyRate");
        this.employee = new SimpleObjectProperty<Employee>(this, "employee");
        this.client = new SimpleObjectProperty<Client>(this, "client");
        this.event = new SimpleObjectProperty<Event>(this, "event");
        this.rideCosts = new SimpleDoubleProperty(this, "rideCosts");
    }

    public static EventProtocol fromResults(ResultSet rs) throws SQLException { //mandatory startTime, endTime and year_month
        Employee e = null;
        Client c = null;
        Event ev = null;
        if (rs.getInt("mitarbeiter") != 0) {
            e = new Employee(rs.getInt("mitarbeiter"));
        }
        if (rs.getInt("klient") != 0) {
            c = new Client(rs.getInt("klient"));
        }
        if (rs.getInt("aktivitaet") != 0) {
            ev = new Event(rs.getInt("aktivitaet"));
        }
        return new EventProtocol(rs.getInt("id"), LocalTime.parse(rs.getString("startzeit")), LocalTime.parse(rs.getString("endzeit")), LocalDate.parse(rs.getString("jahr_Monat")), rs.getDouble("stundensatz"), e, c, ev, rs.getDouble("fahrtkosten"));
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

    public LocalDate getYear_month() {
        return year_month.get();
    }

    public ObjectProperty<LocalDate> year_monthProperty() {
        return year_month;
    }

    public void setYear_month(LocalDate year_month) {
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

    public double getRideCosts() { return rideCosts.get(); }

    public DoubleProperty rideCostsProperty() { return rideCosts; }

    public void setRideCosts(double rideCosts) { this.rideCosts.set(rideCosts); }
}
