package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Sponsor extends Person {

    private StringProperty firmenname;
    private StringProperty firmenTelNr;
    private StringProperty firmenEmail;

    public Sponsor(Salutation salutation, String firstName, String lastName) {
        super(salutation, firstName, lastName );

        firmenname = new SimpleStringProperty(this, "firmenname");
        firmenTelNr = new SimpleStringProperty(this, "firmenTelNr");
        firmenEmail = new SimpleStringProperty(this, "firmenEmail");
    }

    public String getFirmenname() {
        return firmenname.get();
    }
    public StringProperty getFirmennameProperty() {
        return firmenname;
    }
    public void setFirmenname(StringProperty firmenname) {
        this.firmenname = firmenname;
    }

    public String getFirmenTalNr() {
        return firmenTelNr.get();
    }
    public StringProperty getFirmenTalNrProperty() {
        return firmenTelNr;
    }
    public void setFirmenTalNr(StringProperty firmenTelNr) {
        this.firmenTelNr = firmenTelNr;
    }

    public String getFirmenEmail() {
        return firmenEmail.get();
    }
    public StringProperty getFirmenEmailProperty() {
        return firmenEmail;
    }
    public void setFirmenEmail(StringProperty firmenEmail) {
        this.firmenEmail = firmenEmail;
    }
}
