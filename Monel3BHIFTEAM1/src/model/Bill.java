package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bill {
	private IntegerProperty nr;
	private IntegerProperty klientId;	
	private ObjectProperty<LocalDate> dateOfIssue;		//time where you get the bill
	private StringProperty use;							//purpose of use
	
	public Bill(int nr, int klientId, LocalDate dateOfIssue, String use) {
		super();
		this.nr = new SimpleIntegerProperty(this, "nr", nr);
		this.klientId = new SimpleIntegerProperty(this, "klientId", klientId);
		this.dateOfIssue = new SimpleObjectProperty<LocalDate>(this, "dateOfIssue", dateOfIssue);
		this.use = new SimpleStringProperty(this, "use", use);
	}

	public IntegerProperty nrProperty() {
		return nr;
	}
	public int getNr() {
		return nr.get();
	}
	public void setNr(int nr) {
		this.nr.set(nr);
	}

	public IntegerProperty klientIdProperty() {
		return klientId;
	}
	public int getKlientId() {
		return klientId.get();
	}
	public void setKlientId(int klientId) {
		this.klientId.set(klientId);
	}

	public ObjectProperty<LocalDate> dateOfIssueProperty() {
		return dateOfIssue;
	}
	public LocalDate getDateOfIssue() {
		return dateOfIssue.get();
	}
	public void setDateOfIssue(LocalDate dateOfIssue) {
		this.dateOfIssue.set(dateOfIssue);;
	}

	public StringProperty useProperty() {
		return use;
	}
	public String getUse() {
		return use.get();
	}
	public void setUse(String use) {
		this.use.set(use);
	}
}
