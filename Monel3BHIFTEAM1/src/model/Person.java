package model;

import javafx.beans.property.*;

import java.time.LocalDate;

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

    private static int counterForID = 0;

    /*public Person(Salutation salutation, String title, String firstName, String lastName,String street, String houseNumber, int zipCode, String place, String telNr, String email, LocalDate birthDate) {
        this.id = new SimpleIntegerProperty(this, "id", counterForID++);
        this.salutation = new SimpleObjectProperty<Salutation>(this, "salutation", salutation);
        this.title = new SimpleStringProperty(this, "title", title);
        this.firstName = new SimpleStringProperty(this, "firstName", firstName);
        this.lastName = new SimpleStringProperty(this, "lastName", lastName);
        this.streetAndNr = new SimpleStringProperty(this, "streetAndNr", street + " " + houseNumber);
        this.zipCode = new SimpleIntegerProperty(this, "zipCode", zipCode);
        this.place = new SimpleStringProperty(this, "place", place);
        this.telNr = new SimpleStringProperty(this, "telNr", telNr);
        this.email = new SimpleStringProperty(this, "email", email);
        this.birthDate = new SimpleObjectProperty<LocalDate>(this, "birthDate", birthDate);
    }*/

    public Person(Salutation salutation, String firstName, String lastName, String street, String houseNumber, int zipCode, String place, LocalDate birthDate) {
        this.id = new SimpleIntegerProperty(this, "id", counterForID++);
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

    /*
            GETTERS FOR PROPERTIES AND VALUES
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

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setTelNr(String telNr) {
        this.telNr.set(telNr);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
