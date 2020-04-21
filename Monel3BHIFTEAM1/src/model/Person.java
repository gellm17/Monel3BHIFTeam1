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
}
