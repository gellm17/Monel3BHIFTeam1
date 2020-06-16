package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.HashSet;

public class Settings {

    //Farbe ? (string)
    //Tabbellendaten ????!!
    //??private ArrayList<Integer> size = new ArrayList<Integer>(); //add values in Constructor

    private static FontStyle font;
    private static HashSet<Double> hourlyRates;
    private static StringProperty companyName;
    private static StringProperty uid_Number;
    private static StringProperty iban;
    private static StringProperty bic;
    private static  StringProperty street;
    private static IntegerProperty nr;
    private static IntegerProperty plz;
    private static StringProperty location;




    //adds a hourlyRate in a HashSet
    public boolean addHourlyRate(double hourlyRate) {
        if (hourlyRates == null) {
            hourlyRates = new HashSet<Double>();
        }
        return hourlyRates.add(hourlyRate);
    }

    //Removes a hourlyRate form the HashSet
    public boolean removeHourlyRate(double hourlyRate) {
        return hourlyRates.remove(hourlyRate);
    }
}
