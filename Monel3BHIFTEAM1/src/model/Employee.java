package model;

import java.time.LocalDate;

public class Employee extends Person {

    public Employee(Salutation salutation, String firstName, String lastName, String street, String houseNumber, int zipCode, String place, LocalDate birthDate) {
        super(salutation, firstName, lastName, street, houseNumber, zipCode, place, birthDate);
    }
}
