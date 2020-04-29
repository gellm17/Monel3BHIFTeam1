package model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Employee extends Person {
    private IntegerProperty ssnr;
    private BooleanProperty volunteering;
    private StringProperty occupationGroup;
    private StringProperty salaryLevel;
    private IntegerProperty hoursPerWeek;
    private ObjectProperty<LocalDate> dateSalaryLevel;
    private StringProperty iban;
    private StringProperty bic;
    private ObjectProperty<LocalDate> dateOfEmployment;
    private ObjectProperty<Privacy> privacy;

    public Employee(Salutation salutation, String firstName, String lastName) {
        super(salutation, firstName, lastName);

        ssnr = new SimpleIntegerProperty(this, "ssnr", 0);
        volunteering = new SimpleBooleanProperty(this, "volunteering");
        occupationGroup = new SimpleStringProperty(this, "occupationGroup");
        salaryLevel = new SimpleStringProperty(this, "salaryLevel");
        hoursPerWeek = new SimpleIntegerProperty(this, "hoursPerWeek");
        dateSalaryLevel = new SimpleObjectProperty<LocalDate>(this, "dateSalaryLevel");
        iban = new SimpleStringProperty(this, "iban");
        bic = new SimpleStringProperty(this, "bic");
        dateOfEmployment = new SimpleObjectProperty<LocalDate>(this, "dateOfEmployment");
        privacy = new SimpleObjectProperty<Privacy>(this, "privacy");
    }
}
