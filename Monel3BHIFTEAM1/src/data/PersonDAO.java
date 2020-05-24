package data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PersonDAO {
    private ObservableList<Client> clients = FXCollections.observableList(new ArrayList<Client>());         // ArrayList mit allen Personen
    private ObservableList<Employee> employees = FXCollections.observableList(new ArrayList<Employee>());   // ArrayList mit allen Employees
    private ObservableList<Sponsor> sponsor = FXCollections.observableList(new ArrayList<>());              // ArrayList mit allen Company
    private static PersonDAO instance = null;

    private PersonDAO() {
        //clients.add(new Client(Salutation.Herr, "Dr.", "Michael", "Gell", "Sägestraße", "543", 5582, "St.Michael", "066666666606", "michael.gell@gmx.at", LocalDate.now(), 11111));
        //clients.add(new Client(Salutation.Herr, "Dr.", "Michael", "Gell", "Sägestraße", "543", 5582, "St.Michael", "066666666606", "michael.gell@gmx.at", LocalDate.now(), 22222));
        //clients.add(new Client(Salutation.Herr, "Dr.", "Michael", "Gell", "Sägestraße", "543", 5582, "St.Michael", "066666666606", "michael.gell@gmx.at", LocalDate.now(), 23452));
        //clients.add(new Client(Salutation.Herr, "Clemens", "Burgmann"));
        //employees.add(new Employee(Salutation.Herr, "Mike", "Gell"));
    }
    public static PersonDAO getInstance() {
        if (instance == null) {
            instance = new PersonDAO();
        }
        return instance;
    }

    public boolean addPerson(Person p) {
        boolean success = false;
        if(p instanceof Client) {
            Client c = (Client)p;
            success = clients.add(c);
        } else if (p instanceof Employee){
            Employee emp = (Employee) p;
            success = employees.add(emp);
        } else if (p instanceof Sponsor){
            Sponsor sp = (Sponsor) p;
            success = sponsor.add(sp);
        }
        return success;
    }

    public boolean deletePerson(Person p) {
        boolean success = false;
        if(p instanceof Client) {
            Client c = (Client)p;
            success = clients.remove(c);
        } else if (p instanceof Employee){
            Employee emp = (Employee) p;
            success = employees.remove(emp);
        } else if (p instanceof Sponsor){
            Sponsor sp = (Sponsor) p;
            success = sponsor.remove(sp);
        }
        return success;
    }
    
    // GETTER
    public ObservableList<Sponsor> getSponsor() {
        return sponsor;
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public ObservableList<Employee> getEmployees() {
        return employees;
    }

    public void setClients(ObservableList<Client> clients) { this.clients = clients; }

    public void setEmployees(ObservableList<Employee> employees) { this.employees = employees; }

    public void setSponsor(ObservableList<Sponsor> sponsor) { this.sponsor = sponsor; }
}
