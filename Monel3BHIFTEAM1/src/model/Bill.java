package model;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bill {
	private IntegerProperty nr;
	private ObjectProperty<Client> client;
	private ObjectProperty<LocalDate> dateOfIssue;		//time where you get the bill
	private StringProperty use;							//purpose of use
	private ObservableList<EventProtocol> eventProtocols;			//List of all eventProtocols
	
	public Bill(int nr, Client client, LocalDate dateOfIssue, String use) {
		this.nr = new SimpleIntegerProperty(this, "nr", nr);
		this.client = new SimpleObjectProperty<Client>(this, "client", client);
		this.dateOfIssue = new SimpleObjectProperty<LocalDate>(this, "dateOfIssue", dateOfIssue);
		this.use = new SimpleStringProperty(this, "use", use);
		this.eventProtocols = FXCollections.observableArrayList(new ArrayList<EventProtocol>());
	}

	public Bill(Client client) {
		this.nr = new SimpleIntegerProperty(this, "nr", 0);
		this.client = new SimpleObjectProperty<Client>(this, "client", client);
		this.dateOfIssue = new SimpleObjectProperty<LocalDate>(this, "dateOfIssue", null);
		this.use = new SimpleStringProperty(this, "use", null);
		this.eventProtocols = FXCollections.observableArrayList(new ArrayList<EventProtocol>());
	}

	public static Bill fromResults(ResultSet rs) throws SQLException {
		LocalDate ad = null;
		if (rs.getString("ausstellungsdatum") != null) {
			ad = LocalDate.parse(rs.getString("ausstellungsdatum"));
		}
		return new Bill(rs.getInt("rechnungsnummer"), null, ad, rs.getString("verwendungszweck"));
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
		return client;
	}
	public Client getClient() {
		return client.get();
	}
	public void setClient(Client client) {
		this.client.set(client);
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
	
	public ObservableList<EventProtocol> getEventProtocols() {
		return this.eventProtocols;
	}
	public void setEventProtocols(ObservableList<EventProtocol> eventProtocols) {
		this.eventProtocols = eventProtocols;
	}
	public boolean addEventProtocol(EventProtocol eventProtocol) {
		return this.eventProtocols.add(eventProtocol);
	}
	public boolean removeEventProtocol(EventProtocol eventProtocol) {
		return this.eventProtocols.remove(eventProtocol);
	}
}
