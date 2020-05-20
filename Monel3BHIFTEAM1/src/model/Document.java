package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Document {
	private IntegerProperty 				dID;
	private StringProperty 					path;
	private StringProperty 					documentType;
	private SimpleObjectProperty<Owner>							owner;
	private ObjectProperty<Person> 			ownerIdPerson;
	private ObjectProperty<EventProtocol> 	ownerIdEvent;
	
	public Document(int dID, String path, String documentType, Owner owner, Person ownerIdPerson) {
		this.dID = new SimpleIntegerProperty(this, "dID", dID);
		this.path = new SimpleStringProperty(this, "path", path);
		this.documentType = new SimpleStringProperty(this, "documentType", documentType);
		this.owner = new SimpleObjectProperty<Owner>(this, "owner", owner);
		this.ownerIdPerson = new SimpleObjectProperty<Person>(this, "ownerIdPerson", ownerIdPerson);
	}
	
	public Document(int dID, String path, String documentType, Owner owner, EventProtocol ownerIdEvent) {
		this.dID = new SimpleIntegerProperty(this, "dID", dID);
		this.path = new SimpleStringProperty(this, "path", path);
		this.documentType = new SimpleStringProperty(this, "documentType", documentType);
		this.owner = new SimpleObjectProperty<Owner>(this, "owner", owner);
		this.ownerIdEvent = new SimpleObjectProperty<EventProtocol>(this, "ownerIdEvent", ownerIdEvent);
	}

	public IntegerProperty idPeopwery() {
		return dID;
	}
	public int getID() {
		return this.dID.get();
	}
	public void setdID(int dID) {
		this.dID.set(dID);
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

	public ObjectProperty<Person> ownerIdPersonProperty() {
		return ownerIdPerson;
	}
	public Person getOwnerIdPerson() {
		return this.ownerIdPerson.get();
	}
	public void setOwnerIdPerson(Person ownerIdPerson) {
		this.ownerIdPerson.set(ownerIdPerson);
	}

	public ObjectProperty<EventProtocol> ownerIdEventProperty() {
		return ownerIdEvent;
	}
	public EventProtocol getOwnerIdEvent() {
		return this.ownerIdEvent.get();
	}
	public void setOwnerIdEvent(EventProtocol ownerIdEvent) {
		this.ownerIdEvent.set(ownerIdEvent);
	}
	
	
	
	
}
