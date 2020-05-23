package model;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Client extends Person{
    private LongProperty ssnr;
    private StringProperty diagnose;
    private StringProperty job;                         //deutsch --> Beschäftigung
    private StringProperty allergies;
    private ObjectProperty<Person> esv;                 //Elternschutzvertreter
    private ObjectProperty<Person> emergencyContact1;
    private ObjectProperty<Person> emergencyContact2;
    private ObjectProperty<Privacy> privacy;
    private StringProperty other;

    public Client(int id,Salutation salutation, String title, String firstName, String lastName, String adress, int zipCode, String place, String telNr, String email, LocalDate birthDate, long ssnr, String diagnose, String job, String allergies, Person esv, Person emergencyContact1, Person emergencyContact2, Privacy privacy, String other) {
        super(id, salutation, title, firstName, lastName, adress, zipCode, place, telNr, email, birthDate);
        this.ssnr = new SimpleLongProperty(this, "ssnr", ssnr);
        this.diagnose = new SimpleStringProperty(this, "diagnose", diagnose);
        this.job = new SimpleStringProperty(this, "job", job);
        this.allergies = new SimpleStringProperty(this, "allergies", allergies);
        this.esv = new SimpleObjectProperty<Person>(this, "esv", esv);
        this.emergencyContact1 = new SimpleObjectProperty<Person>(this, "emergencyContact1", emergencyContact1);
        this.emergencyContact2 = new SimpleObjectProperty<Person>(this, "emergencyContact2", emergencyContact2);
        this.privacy = new SimpleObjectProperty<Privacy>(this, "privacy", privacy);
        this.other = new SimpleStringProperty(this, "other", other);
    }

    public Client() {
        super();
        this.ssnr = new SimpleLongProperty(this, "ssnr");
        this.diagnose = new SimpleStringProperty(this, "diagnose", "");
        this.job = new SimpleStringProperty(this, "job", "");
        this.allergies = new SimpleStringProperty(this, "allergies", "");
        this.esv = new SimpleObjectProperty<Person>(this, "esv", null);
        this.emergencyContact1 = new SimpleObjectProperty<Person>(this, "emergencyContact1", null);
        this.emergencyContact2 = new SimpleObjectProperty<Person>(this, "emergencyContact2", null);
        this.privacy = new SimpleObjectProperty<Privacy>(this, "privacy");
        this.other = new SimpleStringProperty(this, "other");
    }

    public Client(Salutation salutation, String firstName, String lastName) {
        super(salutation, firstName, lastName);

        this.ssnr = new SimpleLongProperty(this, "ssnr");
        this.diagnose = new SimpleStringProperty(this, "diagnose", "");
        this.job = new SimpleStringProperty(this, "job", "");
        this.allergies = new SimpleStringProperty(this, "allergies", "");
        this.esv = new SimpleObjectProperty<Person>(this, "esv", null);
        this.emergencyContact1 = new SimpleObjectProperty<Person>(this, "emergencyContact1", null);
        this.emergencyContact2 = new SimpleObjectProperty<Person>(this, "emergencyContact2", null);
        this.privacy = new SimpleObjectProperty<Privacy>(this, "privacy");
        this.other = new SimpleStringProperty(this, "other");
    }

    public Client(int id) {
        super(id);
    }

    /* public Client(Salutation salutation, String title, String firstName, String lastName, String street, String houseNumber, int zipCode, String place, String telNr, String email, LocalDate birthDate, IntegerProperty ssnr, StringProperty iban, StringProperty bic) {
        super(salutation, title, firstName, lastName, street, houseNumber, zipCode, place, telNr, email, birthDate);
        this.ssnr = ssnr;
        this.iban = iban;
        this.bic = bic;
    }*/

   public static Client fromResults(ResultSet rs) throws SQLException {
       return new Client(rs.getInt("id"), Salutation.valueOf(rs.getString("anrede")), rs.getString("titel"), rs.getString("vorname"), rs.getString("nachname"), rs.getString("strasse_hausnummer"), rs.getInt("plz"), rs.getString("ort"), rs.getString("telefonnummer"), rs.getString("email"), LocalDate.parse(rs.getString("geburtsdatum")), rs.getInt("svnr"), rs.getString("diagnose"), rs.getString("beschaeftigung"), rs.getString("allergien"), null, null, null, new Privacy(), rs.getString("sonstiges"));
   }

    /*
     * GETTERS FOR VALUES AND PROPERTIES
     */
    public long getSsnr() {
        return ssnr.get();
    }

    public LongProperty ssnrProperty() {
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

    public Privacy getPrivacy() {
        return privacy.get();
    }

    public ObjectProperty<Privacy> privacyProperty() {
        return privacy;
    }

    public String getOther() {
        return other.get();
    }

    public StringProperty otherProperty() {
        return other;
    }

    //SETTER
    public void setDiagnose(String diagnose) {
        this.diagnose.set(diagnose);
    }

    public void setJob(String job) {
        this.job.set(job);
    }

    public void setAllergies(String allergies) {
        this.allergies.set(allergies);
    }

    public void setEsv(Person esv) {
        this.esv.set(esv);
    }

    public void setEmergencyContact1(Person emergencyContact1) {
        this.emergencyContact1.set(emergencyContact1);
    }

    public void setEmergencyContact2(Person emergencyContact2) {
        this.emergencyContact2.set(emergencyContact2);
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy.set(privacy);
    }

    public void setSsnr(long ssnr) {
        this.ssnr.set(ssnr);
    }


    public void setOther(String other) {
        this.other.set(other);
    }
}
