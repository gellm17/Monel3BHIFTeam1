package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sponsor extends Person {

    private StringProperty companyName;
    private StringProperty companyTelNr;
    private StringProperty companyEmail;

    public Sponsor(Salutation salutation, String firstName, String lastName) {
        super(salutation, firstName, lastName );

        companyName = new SimpleStringProperty(this, "companyName");
        companyTelNr = new SimpleStringProperty(this, "companyTelNr");
        companyEmail = new SimpleStringProperty(this, "companyEmail");
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
