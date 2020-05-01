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

    public Employee() {
        super();

        ssnr = new SimpleIntegerProperty(this, "ssnr");
        volunteering = new SimpleBooleanProperty(this, "volunteering");
        occupationGroup = new SimpleStringProperty(this, "occupationGroup", "");
        salaryLevel = new SimpleStringProperty(this, "salaryLevel", "");
        hoursPerWeek = new SimpleIntegerProperty(this, "hoursPerWeek");
        dateSalaryLevel = new SimpleObjectProperty<LocalDate>(this, "dateSalaryLevel");
        iban = new SimpleStringProperty(this, "iban", "");
        bic = new SimpleStringProperty(this, "bic", "");
        dateOfEmployment = new SimpleObjectProperty<LocalDate>(this, "dateOfEmployment");
        privacy = new SimpleObjectProperty<Privacy>(this, "privacy");
    }

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

    public int getSsnr() {
        return ssnr.get();
    }

    public IntegerProperty ssnrProperty() {
        return ssnr;
    }

    public void setSsnr(int ssnr) {
        this.ssnr.set(ssnr);
    }

    public boolean isVolunteering() {
        return volunteering.get();
    }

    public BooleanProperty volunteeringProperty() {
        return volunteering;
    }

    public void setVolunteering(boolean volunteering) {
        this.volunteering.set(volunteering);
    }

    public String getOccupationGroup() {
        return occupationGroup.get();
    }

    public StringProperty occupationGroupProperty() {
        return occupationGroup;
    }

    public void setOccupationGroup(String occupationGroup) {
        this.occupationGroup.set(occupationGroup);
    }

    public String getSalaryLevel() {
        return salaryLevel.get();
    }

    public StringProperty salaryLevelProperty() {
        return salaryLevel;
    }

    public void setSalaryLevel(String salaryLevel) {
        this.salaryLevel.set(salaryLevel);
    }

    public int getHoursPerWeek() {
        return hoursPerWeek.get();
    }

    public IntegerProperty hoursPerWeekProperty() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek.set(hoursPerWeek);
    }

    public LocalDate getDateSalaryLevel() {
        return dateSalaryLevel.get();
    }

    public ObjectProperty<LocalDate> dateSalaryLevelProperty() {
        return dateSalaryLevel;
    }

    public void setDateSalaryLevel(LocalDate dateSalaryLevel) {
        this.dateSalaryLevel.set(dateSalaryLevel);
    }

    public String getIban() {
        return iban.get();
    }

    public StringProperty ibanProperty() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban.set(iban);
    }

    public String getBic() {
        return bic.get();
    }

    public StringProperty bicProperty() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic.set(bic);
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment.get();
    }

    public ObjectProperty<LocalDate> dateOfEmploymentProperty() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment.set(dateOfEmployment);
    }

    public Privacy getPrivacy() {
        return privacy.get();
    }

    public ObjectProperty<Privacy> privacyProperty() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy.set(privacy);
    }
}
