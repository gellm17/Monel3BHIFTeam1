package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Person {
    private IntegerProperty id;
    private ObjectProperty<Salutation> salutation;
    private StringProperty title;
    private StringProperty name;
    private StringProperty street;
    private IntegerProperty houseNumber;
    private IntegerProperty zipCode;
    private StringProperty place;
    private StringProperty telNr;
    private StringProperty email;
    private ObjectProperty<LocalDate> birthDate;



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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber.get();
    }

    public IntegerProperty houseNumberProperty() {
        return houseNumber;
    }

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
}
