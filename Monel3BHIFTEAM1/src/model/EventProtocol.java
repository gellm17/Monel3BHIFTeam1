package model;

import gnu.trove.TByteDoubleProcedure;
import org.jetbrains.debugger.ObjectProperty;

import java.time.LocalDate;

public class EventProtocol {
    private IntegerProperty id;
    private ObjectProperty<LocalDate> startTime;
    private ObjectProperty<LocalDate> endTime;
    private ObjectProperty<LocalDate> year_month;
    private DoubleProperty hourlyRate;
    private ObjectProperty<Employee> employee;
    private ObjectProperty<Client> client;
    private ObjectProperty<event> event;

    public EventProtocol(LocalDate startTime, LocalDate endTime, LocalDate year_month, double hourlyRate, Employee employee, Client client, Event event) {
        this.id = new SimpleIntProperty(this, "id", 0);
        this.startTime = new SimpleObjectProperty(this, "startTime", startTime);
        this.endTime = new SimpleObjectProperty(this, "endTIme", endTime);
        this.year_month = new SimpleObjectProperty(this, "year_month", year_month);
        this.hourlyRate = new SimpleDoubleProperty(this, "hourlyRate", hourlyRate);
        this.employee = new SimpleObjectProperty<Employee>(this, "employee", employee);
        this.client = new SimpleObjectProperty<Client>(this, "client", client);
        this.event = new SimpleObjectProperty<Event>(this, "event", event);
    }

    public IntegerProperty idProperty() {
        return id;
    }
    public int getId() { return this.id.get(); }
    public void setId(int id) {
        this.id.set(id);
    }

    public ObjectProperty<LocalDate> startTimeProperty() {
        return startTime;
    }
    public LocalDate getStartTime() { return this.startTime.get(); }
    public void setStartTim(LocalDate startTime) {
        this.startTime.set(startTime);
    }

    public ObjectProperty<LocalDate> EndTimeProperty() {
        return endTime;
    }
    public LocalDate getEndTime() { return this.endTime.get(); }
    public void setEndTime(LocalDate endTime) {
        this.endTime.set(endTime);
    }

    public ObjectProperty<LocalDate> year_monthProperty() {
        return year_month;
    }
    public LocalDate getYear_Month() { return this.year_month.get(); }
    public void setYear_month(LocalDate year_month) {
        this.year_month.set(year_month);
    }

    public DoubleProperty hourlyRateProperty() {
        return hourlyRate;
    }
    public double getHourlyRate() { return this.hourlyRate.get(); }
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate.set(hourlyRate);
    }

    public Employee employeeProperty() {
        return employee;
    }
    public Employee getEmployee() { return this.employee.get(); }
    public void setEmployee(Employee employee) {
        this.employee.set(employee);
    }

    public Client clientProperty() {
        return client;
    }
    public Client getClient() { return this.client.get(); }
    public void setClient(Client client) {
        this.client.set(client);
    }

    public Event eventProperty() {
        return event;
    }
    public Event getEvent() { return this.event.get();}
    public void setEvent(Event event) {
        this.event.set(event);
    }
}
