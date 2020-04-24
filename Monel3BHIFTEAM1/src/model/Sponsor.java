package model;

import java.time.LocalDate;

public class Sponsor extends Person {

    public Sponsor(Salutation salutation, String firstName, String lastName, String street, String houseNumber, int zipCode, String place, LocalDate birthDate) {
        super(salutation, firstName, lastName, street, houseNumber, zipCode, place, birthDate);
    }
}
