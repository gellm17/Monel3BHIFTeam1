package model;


import javafx.beans.property.*;

import java.time.LocalDate;

public class EventProtocol {
    private IntegerProperty id;
    private ObjectProperty<LocalDate> startTime;
    private ObjectProperty<LocalDate> endTime;
    private ObjectProperty<LocalDate> year_month;
    private DoubleProperty hourlyRate;
    private ObjectProperty<Employee> employee;
    private ObjectProperty<Client> client;
    private ObjectProperty<Event> event;

    public EventProtocol(LocalDate startTime, LocalDate endTime, LocalDate year_month, double hourlyRate, Employee employee, Client client, Event event) {
        this.id = new SimpleIntegerProperty(this, "id", 0);
        this.startTime = new SimpleObjectProperty(this, "startTime", startTime);
        this.endTime = new SimpleObjectProperty(this, "endTIme", endTime);
        this.year_month = new SimpleObjectProperty(this, "year_month", year_month);
        this.hourlyRate = new SimpleDoubleProperty(this, "hourlyRate", hourlyRate);
        this.employee = new SimpleObjectProperty<Employee>(this, "employee", employee);
        this.client = new SimpleObjectProperty<Client>(this, "client", client);
        this.event = new SimpleObjectProperty<Event>(this, "event", event);
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

    public LocalDate getStartTime() {
        return startTime.get();
    }

    public ObjectProperty<LocalDate> startTimeProperty() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime.set(startTime);
    }

    public LocalDate getEndTime() {
        return endTime.get();
    }

    public ObjectProperty<LocalDate> endTimeProperty() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
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
}
