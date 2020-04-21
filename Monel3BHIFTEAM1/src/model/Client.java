package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Client extends Person{
    private IntegerProperty ssnr;
    private StringProperty diagnose;
    private StringProperty job;                         //deutsch --> Beschäftigung
    private StringProperty iban;
    private StringProperty bic;
    private StringProperty allergies;



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
}
