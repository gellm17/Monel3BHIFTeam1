package model;

import data.BillDAO;
import data.EventDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.*;

public class Settings implements Initializable, Serializable {
    private final static String FILENAME = "Settings.lmaa";

    private static Settings instance = null;

    private transient ObservableList<String> klientColumns = FXCollections.observableList(new ArrayList<String>());
    private transient ObservableList<String> klientColumnsLoaded = FXCollections.observableList(new ArrayList<String>());

    private transient ObservableList<String> employeeColumns = FXCollections.observableList(new ArrayList<String>());
    private transient ObservableList<String> employeeColumnsLoaded = FXCollections.observableList(new ArrayList<String>());

    private transient ObservableList<String> sponsorColumns = FXCollections.observableList(new ArrayList<String>());
    private transient ObservableList<String> sponsorColumnsLoaded = FXCollections.observableList(new ArrayList<String>());

    private transient ObservableList<String> activityColumns = FXCollections.observableList(new ArrayList<String>());
    private transient ObservableList<String> activityColumnsLoaded = FXCollections.observableList(new ArrayList<String>());

    private transient ObservableList<String> protocolColumns = FXCollections.observableList(new ArrayList<String>());
    private transient ObservableList<String> protocolColumnsLoaded = FXCollections.observableList(new ArrayList<String>());

    private transient ObservableList<Integer> size = FXCollections.observableList(new ArrayList<Integer>());

    private transient ObservableList<Double> hourlyRates = FXCollections.observableList(new ArrayList<Double>());

    private transient Color color = Color.orange;
    private transient FontStyle font = FontStyle.Arial;
    private transient int selectedSize = 18;
    private transient double selectedHourlyRate = 3.20;
    private transient StringProperty companyName = new SimpleStringProperty(this, "companyName", "Monel GmbH");
    private transient StringProperty uid_Number = new SimpleStringProperty(this, "uid_Number", "ATU 75050926");
    private transient StringProperty iban = new SimpleStringProperty(this, "iban", "AT09 3946 4000 0015 0490");
    private transient StringProperty bic = new SimpleStringProperty(this, "bic", "BKAUATWW");
    private transient StringProperty street = new SimpleStringProperty(this, "street", "Villacher Straße");
    private transient IntegerProperty nr = new SimpleIntegerProperty(this, "nr", 95);
    private transient IntegerProperty plz = new SimpleIntegerProperty(this, "plz", 9800);
    private transient StringProperty location = new SimpleStringProperty(this, "location", "Spittal/Drau");

    private Settings() {
        getData();
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ObservableList<String> getKlientColumns() {
        return klientColumns;
    }

    public void setKlientColumns(ObservableList<String> klientColumns) {
        this.klientColumns = klientColumns;
    }


    public ObservableList<String> getKlientColumnsLoaded() {
        return klientColumnsLoaded;
    }

    public void setKlientColumnsLoaded(ObservableList<String> klientColumnsLoaded) {
        this.klientColumnsLoaded = klientColumnsLoaded;
    }

    public ObservableList<String> getEmployeeColumns() {
        return employeeColumns;
    }

    public void setEmployeeColumns(ObservableList<String> employeeColumns) {
        this.employeeColumns = employeeColumns;
    }

    public ObservableList<String> getEmployeeColumnsLoaded() {
        return employeeColumnsLoaded;
    }

    public void setEmployeeColumnsLoaded(ObservableList<String> employeeColumnsLoaded) {
        this.employeeColumnsLoaded = employeeColumnsLoaded;
    }

    public ObservableList<String> getSponsorColumns() {
        return sponsorColumns;
    }

    public void setSponsorColumns(ObservableList<String> sponsorColumns) {
        this.sponsorColumns = sponsorColumns;
    }

    public ObservableList<String> getSponsorColumnsLoaded() {
        return sponsorColumnsLoaded;
    }

    public void setSponsorColumnsLoaded(ObservableList<String> sponsorColumnsLoaded) {
        this.sponsorColumnsLoaded = sponsorColumnsLoaded;
    }

    public ObservableList<String> getActivityColumns() {
        return activityColumns;
    }

    public void setActivityColumns(ObservableList<String> activityColumns) {
        this.activityColumns = activityColumns;
    }

    public ObservableList<String> getActivityColumnsLoaded() {
        return activityColumnsLoaded;
    }

    public void setActivityColumnsLoaded(ObservableList<String> activityColumnsLoaded) {
        this.activityColumnsLoaded = activityColumnsLoaded;
    }

    public ObservableList<String> getProtocolColumns() {
        return protocolColumns;
    }

    public void setProtocolColumns(ObservableList<String> protocolColumns) {
        this.protocolColumns = protocolColumns;
    }

    public ObservableList<String> getProtocolColumnsLoaded() {
        return protocolColumnsLoaded;
    }

    public void setProtocolColumnsLoaded(ObservableList<String> protocolColumnsLoaded) {
        this.protocolColumnsLoaded = protocolColumnsLoaded;
    }

    public ObservableList<Integer> getSize() {
        return size;
    }

    public void setSize(ObservableList<Integer> size) {
        this.size = size;
    }

    public ObservableList<Double> getHourlyRates() {
        return hourlyRates;
    }

    public void setHourlyRates(ObservableList<Double> hourlyRates) {
        this.hourlyRates = hourlyRates;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public FontStyle getFont() {
        return font;
    }

    public void setFont(FontStyle font) {
        this.font = font;
    }

    public int getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(int selectedSize) {
        this.selectedSize = selectedSize;
    }

    public double getSelectedHourlyRate() {
        return selectedHourlyRate;
    }

    public void setSelectedHourlyRate(double selectedHourlyRate) {
        this.selectedHourlyRate = selectedHourlyRate;
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

    public String getUid_Number() {
        return uid_Number.get();
    }

    public StringProperty uid_NumberProperty() {
        return uid_Number;
    }

    public void setUid_Number(String uid_Number) {
        this.uid_Number.set(uid_Number);
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

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public int getNr() {
        return nr.get();
    }

    public IntegerProperty nrProperty() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr.set(nr);
    }

    public int getPlz() {
        return plz.get();
    }

    public IntegerProperty plzProperty() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz.set(plz);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    //adds the column in the loaded on and removes form the other Array
    public boolean klientColumnLoadIn(String c) {
        boolean ret = false;
        ret = klientColumns.remove(c);
        if (ret){
            klientColumnsLoaded.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public boolean employeeColumnLoadIn(String c) {
        boolean ret = false;
        ret = employeeColumns.remove(c);
        if (ret){
            employeeColumnsLoaded.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public boolean sponsorColumnLoadIn(String c) {
        boolean ret = false;
        ret = sponsorColumns.remove(c);
        if (ret){
            sponsorColumnsLoaded.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public boolean activityColumnLoadIn(String c) {
        boolean ret = false;
        ret = activityColumns.remove(c);
        if (ret){
            activityColumnsLoaded.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public boolean protocolColumnLoadIn(String c) {
        boolean ret = false;
        ret = protocolColumns.remove(c);
        if (ret){
            protocolColumnsLoaded.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public boolean klientColumnLoadOut(String c) {
        boolean ret = false;
        ret = klientColumnsLoaded.remove(c);
        if (ret){
            klientColumns.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public boolean employeeColumnLoadOut(String c) {
        boolean ret = false;
        ret = employeeColumnsLoaded.remove(c);
        if (ret){
            employeeColumns.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public boolean sponsorColumnLoadOut(String c) {
        boolean ret = false;
        ret = sponsorColumnsLoaded.remove(c);
        if (ret){
            sponsorColumns.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public boolean activityColumnLoadOut(String c) {
        boolean ret = false;
        ret = activityColumnsLoaded.remove(c);
        if (ret){
            activityColumns.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public boolean protocolColumnLoadOut(String c) {
        boolean ret = false;
        ret = protocolColumnsLoaded.remove(c);
        if (ret){
            protocolColumns.add(c);
        }
        return ret;
    }

    //moves a column in the loadedArray up
    public boolean klientColumnMoveUp(String c) {
        boolean ret = false;
        String change;
        int index = klientColumnsLoaded.indexOf(c);
        if (index > 0) {
            change = klientColumnsLoaded.get(index-1);
            klientColumnsLoaded.set(index-1, klientColumnsLoaded.get(index));
            klientColumnsLoaded.set(index, change);
            ret = true;
        }
        return ret;
    }

    //moves a column in the loadedArray up
    public  boolean employeeColumnMoveUp(String c) {
        boolean ret = false;
        String change;
        int index = employeeColumnsLoaded.indexOf(c);
        if (index > 0) {
            change = employeeColumnsLoaded.get(index-1);
            employeeColumnsLoaded.set(index-1, employeeColumnsLoaded.get(index));
            employeeColumnsLoaded.set(index, change);
            ret = true;
        }
        return ret;
    }

    //moves a column in the loadedArray up
    public boolean sponsorColumnMoveUp(String c) {
        boolean ret = false;
        String change;
        int index = sponsorColumnsLoaded.indexOf(c);
        if (index > 0) {
            change = sponsorColumnsLoaded.get(index-1);
            sponsorColumnsLoaded.set(index-1, sponsorColumnsLoaded.get(index));
            sponsorColumnsLoaded.set(index, change);
            ret = true;
        }
        return ret;
    }

    //moves a column in the loadedArray up
    public boolean activityColumnMoveUp(String c) {
        boolean ret = false;
        String change;
        int index = activityColumnsLoaded.indexOf(c);
        if (index > 0) {
            change = activityColumnsLoaded.get(index-1);
            activityColumnsLoaded.set(index-1, activityColumnsLoaded.get(index));
            activityColumnsLoaded.set(index, change);
            ret = true;
        }
        return ret;
    }

    //moves a column in the loadedArray up
    public boolean protocolColumnMoveUp(String c) {
        boolean ret = false;
        String change;
        int index = protocolColumnsLoaded.indexOf(c);
        if (index > 0) {
            change = protocolColumnsLoaded.get(index-1);
            protocolColumnsLoaded.set(index-1, protocolColumnsLoaded.get(index));
            protocolColumnsLoaded.set(index, change);
            ret = true;
        }
        return ret;
    }

    //moves a column in the loadedArray down
    public boolean klientColumnMoveDown(String c) {
        boolean ret = false;
        String change;
        int index = klientColumnsLoaded.indexOf(c);
        int ende = klientColumnsLoaded.size();
        if (index < ende-1) {
            change = klientColumnsLoaded.get(index+1);
            klientColumnsLoaded.set(index+1, klientColumnsLoaded.get(index));
            klientColumnsLoaded.set(index, change);
            ret = true;
        }
        return ret;
    }

    //moves a column in the loadedArray down
    public boolean employeeColumnMoveDown(String c) {
        boolean ret = false;
        String change;
        int index = employeeColumnsLoaded.indexOf(c);
        int ende = employeeColumnsLoaded.size();
        if (index < ende-1) {
            change = employeeColumnsLoaded.get(index+1);
            employeeColumnsLoaded.set(index+1, employeeColumnsLoaded.get(index));
            employeeColumnsLoaded.set(index, change);
            ret = true;
        }
        return ret;
    }

    //moves a column in the loadedArray down
    public boolean sponsorColumnMoveDown(String c) {
        boolean ret = false;
        String change;
        int index = sponsorColumnsLoaded.indexOf(c);
        int ende = sponsorColumnsLoaded.size();
        if (index < ende-1) {
            change = sponsorColumnsLoaded.get(index+1);
            sponsorColumnsLoaded.set(index+1, sponsorColumnsLoaded.get(index));
            sponsorColumnsLoaded.set(index, change);
            ret = true;
        }
        return ret;
    }

    //moves a column in the loadedArray down
    public boolean activityColumnMoveDown(String c) {
        boolean ret = false;
        String change;
        int index = activityColumnsLoaded.indexOf(c);
        int ende = activityColumnsLoaded.size();
        if (index < ende-1) {
            change = activityColumnsLoaded.get(index+1);
            activityColumnsLoaded.set(index+1, activityColumnsLoaded.get(index));
            activityColumnsLoaded.set(index, change);
            ret = true;
        }
        return ret;
    }

    //moves a column in the loadedArray down
    public boolean protocolColumnMoveDown(String c) {
        boolean ret = false;
        String change;
        int index = protocolColumnsLoaded.indexOf(c);
        int ende = protocolColumnsLoaded.size();
        if (index < ende-1) {
            change = protocolColumnsLoaded.get(index+1);
            protocolColumnsLoaded.set(index+1, protocolColumnsLoaded.get(index));
            protocolColumnsLoaded.set(index, change);
            ret = true;
        }
        return ret;
    }

    public void getData() {
        try {
            FileInputStream is = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(is);
            Object o = ois.readObject();
            if (o instanceof Settings) {
                instance = (Settings)o;
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e +", create default dummies...");
            klientColumns.addAll("Kundennummer", "Anrede", "Titel", "Vorname", "Nachname", "Straße/Nr", "PLZ", "Ort", "Telefonnummer", "E-Mail", "Geburtsdatum", "SVNR", "Diagnose", "Allergien", "Sonstiges", "Beschäftigung");
            employeeColumns.addAll("Mitarbeiter", "Anrede", "Titel", "Vorname", "Nachname", "Straße/Nr", "PLZ", "Ort", "Telefonnummer", "E-Mail", "Geburtsdatum", "SVNR", "Ehrenamt", "Verwengungsgruppe", "Gehaltsstuffe", "Stunden pro Wochen", "Vorrückdatum", "IBAN", "BIC", "Einstellungsdatum");
            sponsorColumns.addAll("Anrede", "Titel", "Vorname", "Nachname", "Straße/Nr", "PLZ", "Ort", "Telefonnummer", "E-Mail", "Geburtsdatum", "Firmenname", "Firmentelefonnummer");
            activityColumns.addAll("Datum", "Name", "Kategorie");
            protocolColumns.addAll("Protokoll", "Startzeit", "Endzeit", "Monat_Jahr", "Stundensatz", "Mitarbeiter", "Kunde", "Aktivität", "Fahrtkposten");
        }
    }

    public void saveData() {
        try {
            FileOutputStream os = new FileOutputStream(FILENAME);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            os.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        ArrayList<String> help = new ArrayList<String>(getKlientColumns());
        s.writeObject(help);
        help = new ArrayList<String>(getKlientColumnsLoaded());
        s.writeObject(help);
        help = new ArrayList<String>(getEmployeeColumns());
        s.writeObject(help);
        help = new ArrayList<String>(getEmployeeColumnsLoaded());
        s.writeObject(help);
        help = new ArrayList<String>(getSponsorColumns());
        s.writeObject(help);
        help = new ArrayList<String>(getSponsorColumnsLoaded());
        s.writeObject(help);
        help = new ArrayList<String>(getActivityColumns());
        s.writeObject(help);
        help = new ArrayList<String>(getActivityColumnsLoaded());
        s.writeObject(help);
        help = new ArrayList<String>(getProtocolColumns());
        s.writeObject(help);
        help = new ArrayList<String>(getProtocolColumns());
        s.writeObject(help);
        s.writeObject(new ArrayList<Integer>(getSize()));
        s.writeObject(new ArrayList<Double>(getHourlyRates()));
        s.writeObject(getColor());
        s.writeObject(getFont());
        s.writeObject(getSelectedSize());
        s.writeObject(getSelectedHourlyRate());
        s.writeObject(getCompanyName());
        s.writeObject(getUid_Number());
        s.writeObject(getIban());
        s.writeObject(getBic());
        s.writeObject(getStreet());
        s.writeObject(getNr());
        s.writeObject(getPlz());
        s.writeObject(getLocation());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        klientColumns = FXCollections.observableList((ArrayList<String>)s.readObject());
        klientColumnsLoaded = FXCollections.observableList((ArrayList<String>)s.readObject());
        employeeColumns = FXCollections.observableList((ArrayList<String>)s.readObject());
        employeeColumnsLoaded = FXCollections.observableList((ArrayList<String>)s.readObject());
        sponsorColumns = FXCollections.observableList((ArrayList<String>)s.readObject());
        sponsorColumnsLoaded = FXCollections.observableList((ArrayList<String>)s.readObject());
        activityColumns = FXCollections.observableList((ArrayList<String>)s.readObject());
        activityColumnsLoaded = FXCollections.observableList((ArrayList<String>)s.readObject());
        protocolColumns = FXCollections.observableList((ArrayList<String>)s.readObject());
        protocolColumnsLoaded = FXCollections.observableList((ArrayList<String>)s.readObject());
        size = FXCollections.observableList((ArrayList<Integer>)s.readObject());
        hourlyRates = FXCollections.observableList((ArrayList<Double>)s.readObject());
        color = (Color) s.readObject();
        font = (FontStyle) s.readObject();
        selectedSize = (int) s.readObject();
        selectedHourlyRate = (double) s.readObject();
        companyName = new SimpleStringProperty(this, "companyName", (String)s.readObject());
        uid_Number = new SimpleStringProperty(this, "uid_Number", (String) s.readObject());
        iban = new SimpleStringProperty(this, "iban", (String) s.readObject());
        bic = new SimpleStringProperty(this, "bic", (String) s.readObject());
        street = new SimpleStringProperty(this, "street", (String) s.readObject());
        nr = new SimpleIntegerProperty(this, "nr", (int) s.readObject());
        plz = new SimpleIntegerProperty(this, "plz", (int) s.readObject());
        location = new SimpleStringProperty(this, "location", (String) s.readObject());
    }
}
