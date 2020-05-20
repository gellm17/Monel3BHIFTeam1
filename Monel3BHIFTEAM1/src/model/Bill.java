package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bill {
	private IntegerProperty nr;
	private ObjectProperty<Client> clientId;	
	private ObjectProperty<LocalDate> dateOfIssue;		//time where you get the bill
	private StringProperty use;							//purpose of use
	private ArrayList<EventProtocol> eventProtocols;			//List of all eventProtocols
	
	public Bill(int nr, Client clientId, LocalDate dateOfIssue, String use) {
		this.nr = new SimpleIntegerProperty(this, "nr", nr);
		this.clientId = new SimpleObjectProperty<Client>(this, "clientId", clientId);
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

	public ObjectProperty<Client> clientIdProperty() {
		return clientId;
	}
	public Client getKlientId() {
		return clientId.get();
	}
	public void setKlientId(Client clientId) {
		this.clientId.set(clientId);
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
	
	public ArrayList<EventProtocol> getEventProtocols() {
		return this.eventProtocols;
	}
	public void setEventProtocols(ArrayList<EventProtocol> eventProtocols) {
		this.eventProtocols = eventProtocols;
	}
	public boolean addEventProtocol(EventProtocol eventProtocol) {
		return this.eventProtocols.add(eventProtocol);
	}
	public boolean removeEventProtocol(EventProtocol eventProtocol) {
		return this.eventProtocols.remove(eventProtocol);
	}
}