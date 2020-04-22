package model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Client extends Person{
    private IntegerProperty ssnr;
    private StringProperty diagnose;
    private StringProperty job;                         //deutsch --> Beschäftigung
    private StringProperty iban;
    private StringProperty bic;
    private StringProperty allergies;
    private ObjectProperty<Person> esv;                 //Elternschutzvertreter
    private ObjectProperty<Person> emergencyContact1;
    private ObjectProperty<Person> emergencyContact2;

    public Client(Salutation salutation, String title, String firstName, String lastName, String street, String houseNumber, int zipCode, String place, String telNr, String email, LocalDate birthDate, int ssnr, String diagnose, String job, String iban, String bic, String allergies, Person esv, Person emergencyContact1, Person emergencyContact2) {
        super(salutation, title, firstName, lastName, street, houseNumber, zipCode, place, telNr, email, birthDate);
        this.ssnr = new SimpleIntegerProperty(this, "ssnr", ssnr);
        this.diagnose = new SimpleStringProperty(this, "diagnose", diagnose);
        this.job = new SimpleStringProperty(this, "job", job);
        this.iban = new SimpleStringProperty(this, "iban", iban);
        this.bic = new SimpleStringProperty(this, "bic", bic);
        this.allergies = new SimpleStringProperty(this, "allergies", allergies);
        this.esv = new SimpleObjectProperty<Person>(this, "esv", esv);
        this.emergencyContact1 = new SimpleObjectProperty<Person>(this, "emergencyContact1", emergencyContact1);
        this.emergencyContact2 = new SimpleObjectProperty<Person>(this, "emergencyContact2", emergencyContact2);
    }

    /*
            GETTERS FOR VALUES AND PROPERTIES
             */
    public int getSsnr() {
        return ssnr.get();
    }

    public IntegerProperty ssnrProperty() {
        return ssnr;
    }

    public String getDiagnose() {
        return diagnose.get();
    }

    public StringProperty diagnoseProperty() {
        return diagnose;
    }

    public String getJob() {
        return job.get();
    }

    public StringProperty jobProperty() {
        return job;
    }

    public String getIban() {
        return iban.get();
    }

    public StringProperty ibanProperty() {
        return iban;
    }

    public String getBic() {
        return bic.get();
    }

    public StringProperty bicProperty() {
        return bic;
    }

    public String getAllergies() {
        return allergies.get();
    }

    public StringProperty allergiesProperty() {
        return allergies;
    }

    public Person getEsv() { return esv.get(); }

    public ObjectProperty<Person> esvProperty() { return esv; }

    public Person getEmergencyContact1() { return emergencyContact1.get(); }

    public ObjectProperty<Person> emergencyContact1Property() { return emergencyContact1; }

    public Person getEmergencyContact2() { return emergencyContact2.get(); }

    public ObjectProperty<Person> emergencyContact2Property() { return emergencyContact2; }
}
