package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Sponsor extends Person {

    private StringProperty companyName;
    private StringProperty companyTelNr;
    private StringProperty companyEmail;

    public Sponsor(int id, Salutation salutation, String title, String firstName, String lastName, String adress, int zipCode, String place, String telNr, String email, LocalDate birthDate, String companyName, String companyTelNr, String companyEmail) {
        super(id, salutation, title, firstName, lastName, adress, zipCode, place, telNr, email, birthDate);
        this.companyName = new SimpleStringProperty(this, "companyName", companyName);
        this.companyTelNr = new SimpleStringProperty(this, "companyTelNr", companyTelNr);
        this.companyEmail = new SimpleStringProperty(this, "companyEmail", companyTelNr);
    }

    public Sponsor(Salutation salutation, String firstName, String lastName) {
        super(salutation, firstName, lastName );

        companyName = new SimpleStringProperty(this, "companyName");
        companyTelNr = new SimpleStringProperty(this, "companyTelNr");
        companyEmail = new SimpleStringProperty(this, "companyEmail");
    }

    public static Sponsor fromResults(ResultSet rs) throws SQLException {
        return new Sponsor(rs.getInt("id"), Salutation.valueOf(rs.getString("anrede")), rs.getString("titel"), rs.getString("vorname"), rs.getString("nachname"), rs.getString("strasse_hausnummer"), rs.getInt("plz"), rs.getString("ort"), rs.getString("telefonnummer"), rs.getString("email"), LocalDate.parse(rs.getString("geburtsdatum")), rs.getString("firmenname"), rs.getString("firmentelefonnummer"), rs.getString("firmenemail"));
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public String getCompanyTelNr() {
        return companyTelNr.get();
    }

    public StringProperty companyTelNrProperty() {
        return companyTelNr;
    }

    public void setCompanyTelNr(String companyTelNr) {
        this.companyTelNr.set(companyTelNr);
    }

    public String getCompanyEmail() {
        return companyEmail.get();
    }

    public StringProperty companyEmailProperty() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail.set(companyEmail);
    }
}
