package model;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Employee extends Person {
    private LongProperty ssnr;
    private BooleanProperty volunteering;
    private ObjectProperty<OccupationGroup> occupationGroup;
    private ObjectProperty<SalaryLevel> salaryLevel;
    private IntegerProperty hoursPerWeek;
    private ObjectProperty<LocalDate> dateSalaryLevel;
    private StringProperty iban;
    private StringProperty bic;
    private ObjectProperty<LocalDate> dateOfEmployment;
    private ObjectProperty<Privacy> privacy;

    public Employee(int id, Salutation salutation, String title, String firstName, String lastName, String adress, int zipCode, String place, String telNr, String email, LocalDate birthDate, long ssnr, boolean volunteering, OccupationGroup occupationGroup, SalaryLevel salaryLevel, int hoursPerWeek, LocalDate dateSalaryLevel, String iban, String bic, LocalDate dateOfEmployment, Privacy privacy) {
        super(id, salutation, title, firstName, lastName, adress, zipCode, place, telNr, email, birthDate);
        this.ssnr = new SimpleLongProperty(this, "ssnr", ssnr);
        this.volunteering = new SimpleBooleanProperty(this, "volunteering", volunteering);
        this.occupationGroup = new SimpleObjectProperty<OccupationGroup>(this, "occupationGroup", occupationGroup);
        this.salaryLevel = new SimpleObjectProperty<SalaryLevel>(this, "salaryLevel", salaryLevel);
        this.hoursPerWeek = new SimpleIntegerProperty(this, "hoursPerWeek", hoursPerWeek);
        this.dateSalaryLevel = new SimpleObjectProperty<LocalDate>(this, "dateSalaryLevel", dateSalaryLevel);
        this.iban = new SimpleStringProperty(this, "iban", iban);
        this.bic = new SimpleStringProperty(this, "bic", bic);
        this.dateOfEmployment = new SimpleObjectProperty<LocalDate>(this, "dateOfEmployment", dateOfEmployment);
        this.privacy = new SimpleObjectProperty<Privacy>(this, "privacy", privacy);
    }

    public Employee() {
        super();

        ssnr = new SimpleLongProperty(this, "ssnr");
        volunteering = new SimpleBooleanProperty(this, "volunteering");
        occupationGroup = new SimpleObjectProperty<OccupationGroup>(this, "occupationGroup", OccupationGroup.VG1);
        salaryLevel = new SimpleObjectProperty<SalaryLevel>(this, "salaryLevel", SalaryLevel.GS1);
        hoursPerWeek = new SimpleIntegerProperty(this, "hoursPerWeek");
        dateSalaryLevel = new SimpleObjectProperty<LocalDate>(this, "dateSalaryLevel");
        iban = new SimpleStringProperty(this, "iban", "");
        bic = new SimpleStringProperty(this, "bic", "");
        dateOfEmployment = new SimpleObjectProperty<LocalDate>(this, "dateOfEmployment");
        privacy = new SimpleObjectProperty<Privacy>(this, "privacy");
    }

    public Employee(Salutation salutation, String firstName, String lastName) {
        super(salutation, firstName, lastName);

        ssnr = new SimpleLongProperty(this, "ssnr");
        volunteering = new SimpleBooleanProperty(this, "volunteering");
        occupationGroup = new SimpleObjectProperty<OccupationGroup>(this, "occupationGroup", OccupationGroup.VG1);
        salaryLevel = new SimpleObjectProperty<SalaryLevel>(this, "salaryLevel", SalaryLevel.GS1);
        hoursPerWeek = new SimpleIntegerProperty(this, "hoursPerWeek");
        dateSalaryLevel = new SimpleObjectProperty<LocalDate>(this, "dateSalaryLevel");
        iban = new SimpleStringProperty(this, "iban", "");
        bic = new SimpleStringProperty(this, "bic", "");
        dateOfEmployment = new SimpleObjectProperty<LocalDate>(this, "dateOfEmployment");
        privacy = new SimpleObjectProperty<Privacy>(this, "privacy");
    }

    public static Employee fromResults(ResultSet rs) throws SQLException {
        LocalDate vorrueckdatum = null;
                if (rs.getString("vorrueckdatum") != null) {
            vorrueckdatum = LocalDate.parse(rs.getString("vorrueckdatum"));
        }
        return new Employee(rs.getInt("id"), Salutation.valueOf(rs.getString("anrede")), rs.getString("titel"), rs.getString("vorname"), rs.getString("nachname"), rs.getString("strasse_hausnummer"), rs.getInt("plz"), rs.getString("ort"), rs.getString("telefonnummer"), rs.getString("email"), LocalDate.parse(rs.getString("geburtsdatum")), rs.getInt("svnr"), (rs.getInt("amt") == 1 ? true : false), OccupationGroup.valueOf(rs.getString("verwendungsgruppe")), SalaryLevel.valueOf(rs.getString("gehaltsstufe")), rs.getInt("wochenstunden"), vorrueckdatum, rs.getString("iban"), rs.getString("bic"), LocalDate.parse(rs.getString("einstelldatum")), new Privacy());
    }

    public long getSsnr() {
        return ssnr.get();
    }

    public LongProperty ssnrProperty() {
        return ssnr;
    }

    public void setSsnr(long ssnr) {
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

    public OccupationGroup getOccupationGroup() {
        return occupationGroup.get();
    }

    public ObjectProperty<OccupationGroup> occupationGroupProperty() {
        return occupationGroup;
    }

    public void setOccupationGroup(OccupationGroup occupationGroup) {
        this.occupationGroup.set(occupationGroup);
    }

    public SalaryLevel getSalaryLevel() {
        return salaryLevel.get();
    }

    public ObjectProperty<SalaryLevel> salaryLevelProperty() {
        return salaryLevel;
    }

    public void setSalaryLevel(SalaryLevel salaryLevel) {
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
