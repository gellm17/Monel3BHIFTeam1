package model;

import java.time.LocalDate;

public class Employee extends Person {

    public Employee(Salutation salutation, String title, String firstName, String lastName, String street, String houseNumber, int zipCode, String place, String telNr, String email, LocalDate birthDate) {
        super(salutation, title, firstName, lastName, street, houseNumber, zipCode, place, telNr, email, birthDate);
        //TODO
    }
}
