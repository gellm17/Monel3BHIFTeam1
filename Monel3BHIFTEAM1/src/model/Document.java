package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Document {
	private IntegerProperty 				id;
	private StringProperty 					path;
	private StringProperty 					documentType;
	private SimpleObjectProperty<Owner>		owner;
	private ObjectProperty<Person> 			ownerPerson;
	private ObjectProperty<EventProtocol> 	ownerEvent;

	public Document(int id, String path, String documentType, Owner owner, Person ownerIdPerson, EventProtocol ownerIdEvent) {
		this.id = new SimpleIntegerProperty(this, "dID", id);
		this.path = new SimpleStringProperty(this, "path", path);
		this.documentType = new SimpleStringProperty(this, "documentType", documentType);
		this.owner = new SimpleObjectProperty<Owner>(this, "owner", owner);
		this.ownerPerson = new SimpleObjectProperty<Person>(this, "ownerIdPerson", ownerIdPerson);
		this.ownerEvent = new SimpleObjectProperty<EventProtocol>(this, "ownerIdEvent", ownerIdEvent);
	}

	public Document(int id, String path, String documentType, Owner owner, Person ownerIdPerson) {
		this.id = new SimpleIntegerProperty(this, "dID", id);
		this.path = new SimpleStringProperty(this, "path", path);
		this.documentType = new SimpleStringProperty(this, "documentType", documentType);
		this.owner = new SimpleObjectProperty<Owner>(this, "owner", owner);
		this.ownerPerson = new SimpleObjectProperty<Person>(this, "ownerIdPerson", ownerIdPerson);
	}
	
	public Document(int id, String path, String documentType, Owner owner, EventProtocol ownerIdEvent) {
		this.id = new SimpleIntegerProperty(this, "dID", id);
		this.path = new SimpleStringProperty(this, "path", path);
		this.documentType = new SimpleStringProperty(this, "documentType", documentType);
		this.owner = new SimpleObjectProperty<Owner>(this, "owner", owner);
		this.ownerEvent = new SimpleObjectProperty<EventProtocol>(this, "ownerIdEvent", ownerIdEvent);
	}

	public static Document fromResults(ResultSet rs) throws SQLException {
		return new Document(rs.getInt("id"), rs.getString("pfad"), rs.getString("dokumentenart"), Owner.valueOf(rs.getString("besitzer")), null, null);
	}

	public IntegerProperty idPeopwery() {
		return id;
	}
	public int getID() {
		return this.id.get();
	}
	public void setdID(int dID) {
		this.id.set(dID);
	}

	public StringProperty pathProperty() {
		return path;
	}
	public String getPath() {
		return this.path.get();
	}
	public void setPath(String path) {
		this.path.set(path);
	}

	public StringProperty documentTypePropertie() {
		return documentType;
	}
	public String getDocumentType() {
		return this.documentType.get();
	}
	public void setDocumentType(String documentType) {
		this.documentType.set(documentType);
	}

	public SimpleObjectProperty<Owner> ownerProperty() {
		return owner;
	}
	public Owner getOwner() {
		return this.owner.get();
	}
	public void setOwner(Owner owner) {
		this.owner.set(owner);
	}

	public ObjectProperty<Person> ownerPersonProperty() {
		return ownerPerson;
	}
	public Person getOwnerPerson() {
		return this.ownerPerson.get();
	}
	public void setOwnerPerson(Person ownerPerson) {
		this.ownerPerson.set(ownerPerson);
	}

	public ObjectProperty<EventProtocol> ownerEventProperty() {
		return ownerEvent;
	}
	public EventProtocol getOwnerEvent() {
		return this.ownerEvent.get();
	}
	public void setOwnerEvent(EventProtocol ownerEvent) {
		this.ownerEvent.set(ownerEvent);
	}
	
	
	
	
}
