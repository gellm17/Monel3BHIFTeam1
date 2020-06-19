package model;

import data.EventDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.*;

public class Settings implements Initializable {
    private static ObservableList<String> klientColumns = FXCollections.observableList(new ArrayList<String>());
    private static ObservableList<String> klientColumnsLoaded = FXCollections.observableList(new ArrayList<String>());

    private static ObservableList<String> employeeColumns = FXCollections.observableList(new ArrayList<String>());
    private static ObservableList<String> employeeColumnsLoaded = FXCollections.observableList(new ArrayList<String>());

    private static ObservableList<String> sponsorColumns = FXCollections.observableList(new ArrayList<String>());
    private static ObservableList<String> sponsorColumnsLoaded = FXCollections.observableList(new ArrayList<String>());

    private static ObservableList<String> activityColumns = FXCollections.observableList(new ArrayList<String>());
    private static ObservableList<String> activityColumnsLoaded = FXCollections.observableList(new ArrayList<String>());

    private static ObservableList<String> protocolColumns = FXCollections.observableList(new ArrayList<String>());
    private static ObservableList<String> protocolColumnsLoaded = FXCollections.observableList(new ArrayList<String>());

    private static ObservableList<Integer> size = FXCollections.observableList(new ArrayList<Integer>());

    private static ObservableList<Double> hourlyRates = FXCollections.observableList(new ArrayList<Double>());

    private static Color color = Color.orange;
    private static FontStyle font = FontStyle.Arial;
    private static int selectedSize = 18;
    private static double selectedHourlyRate = 3.20;
    private static StringProperty companyName = new SimpleStringProperty(new Settings(), "companyName", "Monel GmbH");
    private static StringProperty uid_Number = new SimpleStringProperty(new Settings(), "uid_Number", "ATU 75050926");
    private static StringProperty iban = new SimpleStringProperty(new Settings(), "iban", "AT09 3946 4000 0015 0490");
    private static StringProperty bic = new SimpleStringProperty(new Settings(), "bic", "BKAUATWW");
    private static StringProperty street = new SimpleStringProperty(new Settings(), "street", "Villacher Straße");
    private static IntegerProperty nr = new SimpleIntegerProperty(new Settings(), "nr", 95);
    private static IntegerProperty plz = new SimpleIntegerProperty(new Settings(), "plz", 9800);
    private static StringProperty location = new SimpleStringProperty(new Settings(), "location", "Spittal/Drau");

    private static Settings instance = new Settings();

    private Settings() {
        if (klientColumns.isEmpty()) {
            klientColumns.addAll("Kundennummer", "Anrede", "Titel", "Vorname", "Nachname", "Straße/Nr", "PLZ", "Ort", "Telefonnummer", "E-Mail", "Geburtsdatum", "SVNR", "Diagnose", "Allergien", "Sonstiges", "Beschäftigung");
            employeeColumns.addAll("Mitarbeiter", "Anrede", "Titel", "Vorname", "Nachname", "Straße/Nr", "PLZ", "Ort", "Telefonnummer", "E-Mail", "Geburtsdatum", "SVNR", "Ehrenamt", "Verwengungsgruppe", "Gehaltsstuffe", "Stunden pro Wochen", "Vorrückdatum", "IBAN", "BIC", "Einstellungsdatum");
            sponsorColumns.addAll("Anrede", "Titel", "Vorname", "Nachname", "Straße/Nr", "PLZ", "Ort", "Telefonnummer", "E-Mail", "Geburtsdatum", "Firmenname", "Firmentelefonnummer");
            activityColumns.addAll("Datum", "Name", "Kategorie");
            protocolColumns.addAll("Protokoll", "Startzeit", "Endzeit", "Monat_Jahr", "Stundensatz", "Mitarbeiter", "Kunde", "Aktivität", "Fahrtkposten");
        }
    }

    /*public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public static ObservableList<String> getKlientColumns() {
        return klientColumns;
    }

    public static void setKlientColumns(ObservableList<String> klientColumns) {
        Settings.klientColumns = klientColumns;
    }


    public static ObservableList<String> getKlientColumnsLoaded() {
        return klientColumnsLoaded;
    }

    public static void setKlientColumnsLoaded(ObservableList<String> klientColumnsLoaded) {
        Settings.klientColumnsLoaded = klientColumnsLoaded;
    }

    public static ObservableList<String> getEmployeeColumns() {
        return employeeColumns;
    }

    public static void setEmployeeColumns(ObservableList<String> employeeColumns) {
        Settings.employeeColumns = employeeColumns;
    }

    public static ObservableList<String> getEmployeeColumnsLoaded() {
        return employeeColumnsLoaded;
    }

    public static void setEmployeeColumnsLoaded(ObservableList<String> employeeColumnsLoaded) {
        Settings.employeeColumnsLoaded = employeeColumnsLoaded;
    }

    public static ObservableList<String> getSponsorColumns() {
        return sponsorColumns;
    }

    public static void setSponsorColumns(ObservableList<String> sponsorColumns) {
        Settings.sponsorColumns = sponsorColumns;
    }

    public static ObservableList<String> getSponsorColumnsLoaded() {
        return sponsorColumnsLoaded;
    }

    public static void setSponsorColumnsLoaded(ObservableList<String> sponsorColumnsLoaded) {
        Settings.sponsorColumnsLoaded = sponsorColumnsLoaded;
    }

    public static ObservableList<String> getActivityColumns() {
        return activityColumns;
    }

    public static void setActivityColumns(ObservableList<String> activityColumns) {
        Settings.activityColumns = activityColumns;
    }

    public static ObservableList<String> getActivityColumnsLoaded() {
        return activityColumnsLoaded;
    }

    public static void setActivityColumnsLoaded(ObservableList<String> activityColumnsLoaded) {
        Settings.activityColumnsLoaded = activityColumnsLoaded;
    }

    public static ObservableList<String> getProtocolColumns() {
        return protocolColumns;
    }

    public static void setProtocolColumns(ObservableList<String> protocolColumns) {
        Settings.protocolColumns = protocolColumns;
    }

    public static ObservableList<String> getProtocolColumnsLoaded() {
        return protocolColumnsLoaded;
    }

    public static void setProtocolColumnsLoaded(ObservableList<String> protocolColumnsLoaded) {
        Settings.protocolColumnsLoaded = protocolColumnsLoaded;
    }

    public static ObservableList<Integer> getSize() {
        return size;
    }

    public static void setSize(ObservableList<Integer> size) {
        Settings.size = size;
    }

    public static ObservableList<Double> getHourlyRates() {
        return hourlyRates;
    }

    public static void setHourlyRates(ObservableList<Double> hourlyRates) {
        Settings.hourlyRates = hourlyRates;
    }

    public static Color getColor() {
        return color;
    }

    public static void setColor(Color color) {
        Settings.color = color;
    }

    public static FontStyle getFont() {
        return font;
    }

    public static void setFont(FontStyle font) {
        Settings.font = font;
    }

    public static int getSelectedSize() {
        return selectedSize;
    }

    public static void setSelectedSize(int selectedSize) {
        Settings.selectedSize = selectedSize;
    }

    public static double getSelectedHourlyRate() {
        return selectedHourlyRate;
    }

    public static void setSelectedHourlyRate(double selectedHourlyRate) {
        Settings.selectedHourlyRate = selectedHourlyRate;
    }

    public static String getCompanyName() {
        return companyName.get();
    }

    public static StringProperty companyNameProperty() {
        return companyName;
    }

    public static void setCompanyName(String companyName) {
        Settings.companyName.set(companyName);
    }

    public static String getUid_Number() {
        return uid_Number.get();
    }

    public static StringProperty uid_NumberProperty() {
        return uid_Number;
    }

    public static void setUid_Number(String uid_Number) {
        Settings.uid_Number.set(uid_Number);
    }

    public static String getIban() {
        return iban.get();
    }

    public static StringProperty ibanProperty() {
        return iban;
    }

    public static void setIban(String iban) {
        Settings.iban.set(iban);
    }

    public static String getBic() {
        return bic.get();
    }

    public static StringProperty bicProperty() {
        return bic;
    }

    public static void setBic(String bic) {
        Settings.bic.set(bic);
    }

    public static String getStreet() {
        return street.get();
    }

    public static StringProperty streetProperty() {
        return street;
    }

    public static void setStreet(String street) {
        Settings.street.set(street);
    }

    public static int getNr() {
        return nr.get();
    }

    public static IntegerProperty nrProperty() {
        return nr;
    }

    public static void setNr(int nr) {
        Settings.nr.set(nr);
    }

    public static int getPlz() {
        return plz.get();
    }

    public static IntegerProperty plzProperty() {
        return plz;
    }

    public static void setPlz(int plz) {
        Settings.plz.set(plz);
    }

    public static String getLocation() {
        return location.get();
    }

    public static StringProperty locationProperty() {
        return location;
    }

    public static void setLocation(String location) {
        Settings.location.set(location);
    }

    //adds the column in the loaded on and removes form the other Array
    public static boolean klientColumnLoadIn(String c) {
        boolean ret = false;
        ret = klientColumns.remove(c);
        if (ret){
            klientColumnsLoaded.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public static boolean employeeColumnLoadIn(String c) {
        boolean ret = false;
        ret = employeeColumns.remove(c);
        if (ret){
            employeeColumnsLoaded.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public static boolean sponsorColumnLoadIn(String c) {
        boolean ret = false;
        ret = sponsorColumns.remove(c);
        if (ret){
            sponsorColumnsLoaded.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public static boolean activityColumnLoadIn(String c) {
        boolean ret = false;
        ret = activityColumns.remove(c);
        if (ret){
            activityColumnsLoaded.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public static boolean protocolColumnLoadIn(String c) {
        boolean ret = false;
        ret = protocolColumns.remove(c);
        if (ret){
            protocolColumnsLoaded.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public static boolean klientColumnLoadOut(String c) {
        boolean ret = false;
        ret = klientColumnsLoaded.remove(c);
        if (ret){
            klientColumns.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public static boolean employeeColumnLoadOut(String c) {
        boolean ret = false;
        ret = employeeColumnsLoaded.remove(c);
        if (ret){
            employeeColumns.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public static boolean sponsorColumnLoadOut(String c) {
        boolean ret = false;
        ret = sponsorColumnsLoaded.remove(c);
        if (ret){
            sponsorColumns.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public static boolean activityColumnLoadOut(String c) {
        boolean ret = false;
        ret = activityColumnsLoaded.remove(c);
        if (ret){
            activityColumns.add(c);
        }
        return ret;
    }

    //adds the column in the loaded on and removes form the other Array
    public static boolean protocolColumnLoadOut(String c) {
        boolean ret = false;
        ret = protocolColumnsLoaded.remove(c);
        if (ret){
            protocolColumns.add(c);
        }
        return ret;
    }

    //moves a column in the loadedArray up
    public static boolean klientColumnMoveUp(String c) {
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
    public static boolean employeeColumnMoveUp(String c) {
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
    public static boolean sponsorColumnMoveUp(String c) {
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
    public static boolean activityColumnMoveUp(String c) {
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
    public static boolean protocolColumnMoveUp(String c) {
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
    public static boolean klientColumnMoveDown(String c) {
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
    public static boolean employeeColumnMoveDown(String c) {
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
    public static boolean sponsorColumnMoveDown(String c) {
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
    public static boolean activityColumnMoveDown(String c) {
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
    public static boolean protocolColumnMoveDown(String c) {
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
}
