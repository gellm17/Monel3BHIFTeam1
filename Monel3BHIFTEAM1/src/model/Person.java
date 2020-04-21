package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Person {
    private IntegerProperty id;
    private StringProperty name;
    private ObjectProperty<LocalDate> birthDate;
    private StringProperty telNr;
    private StringProperty adress;
    private StringProperty email;


}
