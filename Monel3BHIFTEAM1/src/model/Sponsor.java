package model;

import java.time.LocalDate;

public class Sponsor extends Person {

    private StringProperty firmenname;
    private StringProperty firmenTelNr;
    private StringProperty firmenEmail;

    public Sponsor(Salutation salutation, String firstName, String lastName) {
        super(salutation, firstName, lastName );

        firmenname = new SimpleIntegerProperty(this, "firmenname");
        firmenTelNr = new SimpleIntegerProperty(this, "firmenTelNr");
        firmenEmail = new SimpleIntegerProperty(this, "firmenEmail");
    }

    public int getFirmenname() {
        return firmenname.get();
    }
    public StringProperty getFirmennameProperty() {
        return firmenname;
    }
    public void setFirmenname(StringProperty firmenname) {
        this.firmenname = firmenname;
    }

    public int getFirmenTalNr() {
        return firmenTelNr.get();
    }
    public StringProperty getFirmenTalNrProperty() {
        return firmenTelNr;
    }
    public void setFirmenTalNr(StringProperty firmenTelNr) {
        this.firmenTelNr = firmenTelNr;
    }

    public int getFirmenEmail() {
        return firmenEmail.get();
    }
    public StringProperty getFirmenEmailProperty() {
        return firmenEmail;
    }
    public void setFirmenEmail(StringProperty firmenEmail) {
        this.firmenEmail = firmenEmail;
    }
}
