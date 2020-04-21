package model;

import java.time.LocalDate;

public class Employee extends Person {
    public Employee(Salutation salutation, String title, String name, String street, String houseNumber, int zipCode, String place, String telNr, String email, LocalDate birthDate) {
        super(salutation, title, name, street, houseNumber, zipCode, place, telNr, email, birthDate);
        //TODO
    }
}
