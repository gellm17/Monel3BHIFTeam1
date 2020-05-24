package model;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class Person {
    private IntegerProperty id;
    private ObjectProperty<Salutation> salutation;
    private StringProperty title;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty streetAndNr;
    private IntegerProperty zipCode;
    private StringProperty place;
    private StringProperty telNr;
    private StringProperty email;
    private ObjectProperty<LocalDate> birthDate;


    public Person(int id, Salutation salutation, String title, String firstName, String lastName,String adress, int zipCode, String place, String telNr, String email, LocalDate birthDate) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.salutation = new SimpleObjectProperty<Salutation>(this, "salutation", salutation);
        this.title = new SimpleStringProperty(this, "title", title);
        this.firstName = new SimpleStringProperty(this, "firstName", firstName);
        this.lastName = new SimpleStringProperty(this, "lastName", lastName);
        this.streetAndNr = new SimpleStringProperty(this, "streetAndNr", adress);
        this.zipCode = new SimpleIntegerProperty(this, "zipCode", zipCode);
        this.place = new SimpleStringProperty(this, "place", place);
        this.telNr = new SimpleStringProperty(this, "telNr", telNr);
        this.email = new SimpleStringProperty(this, "email", email);
        this.birthDate = new SimpleObjectProperty<LocalDate>(this, "birthDate", birthDate);
    }
    public Person(){
        this.id = new SimpleIntegerProperty(this, "id", 0);
        this.salutation = new SimpleObjectProperty<Salutation>(this, "salutation", Salutation.Herr);
        this.firstName = new SimpleStringProperty(this, "firstName", "");
        this.lastName = new SimpleStringProperty(this, "lastName", "");
        this.streetAndNr = new SimpleStringProperty(this, "streetAndNr", "");
        this.zipCode = new SimpleIntegerProperty(this, "zipCode");
        this.place = new SimpleStringProperty(this, "place", "");
        this.birthDate = new SimpleObjectProperty<LocalDate>(this, "birthDate", LocalDate.now());
        this.title = new SimpleStringProperty(this, "title", "");
        this.telNr = new SimpleStringProperty(this, "telNr", "");
        this.email = new SimpleStringProperty(this, "email", "");
    }

    public Person(int id) {
        this.id = new SimpleIntegerProperty(this, "id", id);
    }



    public Person(Salutation salutation, String firstName, String lastName, String street, String houseNumber, int zipCode, String place, LocalDate birthDate) {
        this.id = new SimpleIntegerProperty(this, "id", 0);
        this.salutation = new SimpleObjectProperty<Salutation>(this, "salutation", salutation);
        this.firstName = new SimpleStringProperty(this, "firstName", firstName);
        this.lastName = new SimpleStringProperty(this, "lastName", lastName);
        this.streetAndNr = new SimpleStringProperty(this, "streetAndNr", street + " " + houseNumber);
        this.zipCode = new SimpleIntegerProperty(this, "zipCode", zipCode);;
        this.place = new SimpleStringProperty(this, "place", place);
        this.birthDate = new SimpleObjectProperty<LocalDate>(this, "birthDate", birthDate);

        this.title = new SimpleStringProperty(this, "title", "");
        this.telNr = new SimpleStringProperty(this, "telNr", "");
        this.email = new SimpleStringProperty(this, "email", "");
    }

    public Person(Salutation salutation, String firstName, String lastName){
        this.id = new SimpleIntegerProperty(this, "id", 0);
        this.salutation = new SimpleObjectProperty<Salutation>(this, "salutation", salutation);
        this.firstName = new SimpleStringProperty(this, "firstName", firstName);
        this.lastName = new SimpleStringProperty(this, "lastName", lastName);

        this.streetAndNr = new SimpleStringProperty(this, "streetAndNr", "");
        this.zipCode = new SimpleIntegerProperty(this, "zipCode");
        this.place = new SimpleStringProperty(this, "place", "");
        this.birthDate = new SimpleObjectProperty<LocalDate>(this, "birthDate", null);
        this.title = new SimpleStringProperty(this, "title", "");
        this.telNr = new SimpleStringProperty(this, "telNr", "");
        this.email = new SimpleStringProperty(this, "email", "");
    }

    public static Person fromResults(ResultSet rs) throws SQLException {
        return new Person(rs.getInt("id"), Salutation.valueOf(rs.getString("anrede")), rs.getString("titel"), rs.getString("vorname"), rs.getString("nachname"), rs.getString("strasse_hausnummer"), rs.getInt("plz"), rs.getString("ort"), rs.getString("telefonnummer"), rs.getString("email"), LocalDate.parse(rs.getString("geburtsdatum")));
    }

    /*
     * GETTERS FOR PROPERTIES AND VALUES
     */
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public Salutation getSalutation() {
        return salutation.get();
    }

    public ObjectProperty<Salutation> salutationProperty() {
        return salutation;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getFirstName() { return firstName.get(); }

    public String getLastName() { return lastName.get(); }

    public StringProperty lastNameProperty() { return lastName; }

    public String getStreetAndNr() { return streetAndNr.get(); }

    public StringProperty streetAndNrProperty() { return streetAndNr; }

    public int getZipCode() {
        return zipCode.get();
    }

    public IntegerProperty zipCodeProperty() {
        return zipCode;
    }

    public String getPlace() {
        return place.get();
    }

    public StringProperty placeProperty() {
        return place;
    }

    public String getTelNr() {
        return telNr.get();
    }

    public StringProperty telNrProperty() {
        return telNr;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public void setId(int id) { this.id.set(id); }

    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public void setTelNr(String telNr) {
        this.telNr.set(telNr);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setSalutation(Salutation salutation) {
        this.salutation.set(salutation);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setStreetAndNr(String streetAndNr) {
        this.streetAndNr.set(streetAndNr);
    }

    public void setZipCode(int zipCode) {
        this.zipCode.set(zipCode);
    }

    public void setPlace(String place) {
        this.place.set(place);
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate.set(birthDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getId() == person.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return this.lastName.getValue() + " "+ this.firstName.getValue();
    }
}
